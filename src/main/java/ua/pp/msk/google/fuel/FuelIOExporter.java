package ua.pp.msk.google.fuel;

import java.io.IOException;

import com.google.api.services.drive.Drive;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import ua.pp.msk.google.fuel.drive.GoogleDriveWorker;
import ua.pp.msk.google.fuel.entities.Vehicle;

public class FuelIOExporter {

   

  

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
        GoogleDriveWorker gdw = new GoogleDriveWorker();
        Drive service = gdw.getDriveService();
       
       Collection<Vehicle> vl = gdw.listFuelIOFileIds(service).stream().map(sid -> gdw.parseFile(service, sid)).collect(Collectors.toCollection(LinkedList::new));
       vl.forEach(System.out::println);
    }

   

}
