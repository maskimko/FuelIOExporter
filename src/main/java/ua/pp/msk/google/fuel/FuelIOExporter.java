package ua.pp.msk.google.fuel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.management.Query;

import org.slf4j.LoggerFactory;
import ua.pp.msk.google.fuel.parsers.ParserFactory;
import ua.pp.msk.google.fuel.parsers.SectionParser;

public class FuelIOExporter {

    private static String FUELIOID;
    private static String FUELIOIDSYNC;

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME
            = "Drive API Java Quickstart";

    /**
     * Directory to store user credentials for this application.
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/drive-java-quickstart.json");

    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY
            = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    /**
     * Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials at
     * ~/.credentials/drive-java-quickstart.json
     */
    private static final List<String> SCOPES
            = Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY, DriveScopes.DRIVE_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (GeneralSecurityException | IOException t) {
            LoggerFactory.getLogger(FuelIOExporter.class).error(t.getMessage(), t);
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in
                = FuelIOExporter.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets
                = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow
                = new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     *
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
        Drive service = getDriveService();
       
        listFuelIOFileIds(service).stream().forEach(sid -> parseFile(service, sid));
    }

    public static void listAllFiles(Drive service) throws IOException {
        listFileIds(service, null);
    }

    public static List<String> listFileIds(Drive service, String query) throws IOException {
        List<String> ids = new LinkedList<>();
        Drive.Files.List list = service.files().list();
        if (query != null && query.length() > 0) {
            list.setQ(query);
        }
        do {
            FileList files = list.execute();
            files.getFiles().forEach((f) -> {LoggerFactory.getLogger(FuelIOExporter.class).debug(String.format("%s\t%s", f.getName(), f.getId())); ids.add(f.getId());});
            list.setPageToken(files.getNextPageToken());
        } while (list.getPageToken() != null && list.getPageToken().length() > 0);
        return ids;
    }

    public static List<String> listFuelIOFileIds(Drive service) throws IOException {
        
        // listFiles(service, "?q='0B4GzeDA85UlVY2k0cEpiZ1ZFVnc'+in+parents");
        
        return listFileIds(service, String.format("'%s' in parents", getFuelIOSyncId(service)));

    }

    private static synchronized String getFuelIOId(Drive service) {
        if (FUELIOID == null || FUELIOID.length() == 0) {
            try {
                Drive.Files.List list = service.files().list();
                list.setQ("name = 'Fuelio'");
                List<File> files = list.execute().getFiles();
                if (!files.isEmpty()) {
                    String fioId = files.get(0).getId();
                    LoggerFactory.getLogger(FuelIOExporter.class).debug("Found FuelIO folder with ID " + fioId);
                    FUELIOID = fioId;
                }
            } catch (IOException ex) {
                LoggerFactory.getLogger(FuelIOExporter.class).error(ex.getMessage(), ex);
            }
        }
        return FUELIOID;
    }
    
    private static synchronized String getFuelIOSyncId(Drive service) {
        if (FUELIOIDSYNC == null || FUELIOIDSYNC.length() == 0) {
            try {
                Drive.Files.List list = service.files().list();
                list.setQ(String.format("name = '%s' and '%s' in parents", "sync", getFuelIOId(service)));
                List<File> files = list.execute().getFiles();
                if (!files.isEmpty()) {
                    String fioSynId = files.get(0).getId();
                    LoggerFactory.getLogger(FuelIOExporter.class).debug("Found FuelIO sync folder with ID " + fioSynId);
                    FUELIOIDSYNC = fioSynId;
                }
            } catch (IOException ex) {
                LoggerFactory.getLogger(FuelIOExporter.class).error(ex.getMessage(), ex);
            }
        }
        return FUELIOIDSYNC;
    }

    private static void parseFile(Drive service, String id) {
        LoggerFactory.getLogger(FuelIOExporter.class).debug("Parsing file id " + id);
        Pattern headerPattern = Pattern.compile("^\"?##.*\"?");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(service.files().get(id).executeMediaAsInputStream()))) {
            String line;
            SectionParser sp = null;
            StringBuilder sb = null;
            while ((line = br.readLine()) != null) {
                Matcher m = headerPattern.matcher(line);
                if (m.matches()) {
                    if (sp != null && sb != null) {
                        sp.parse(sb.toString());
                    }
                    sp = ParserFactory.getParser(line);
                    sb = new StringBuilder();
                } else {
                    if (sb != null) {
                        sb.append(line).append("\r\n");
                    }
                }
            }
            //Parse last one
            if (sp != null && sb != null) {
                sp.parse(sb.toString());
            }
        } catch (IOException ioex) {
            LoggerFactory.getLogger(FuelIOExporter.class).error(ioex.getMessage(), ioex);
        }
    }

}
