package ua.pp.msk.google.fuel;

import java.io.IOException;

import com.google.api.services.drive.Drive;
import ua.pp.msk.google.fuel.drive.GoogleDriveWorker;

public class FuelIOExporter {

   

  

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
        GoogleDriveWorker gdw = new GoogleDriveWorker();
        Drive service = gdw.getDriveService();
       
        gdw.listFuelIOFileIds(service).stream().forEach(sid -> gdw.parseFile(service, sid));
    }

   

}
