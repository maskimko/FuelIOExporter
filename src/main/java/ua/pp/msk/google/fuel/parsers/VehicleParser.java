/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.google.fuel.parsers;

import java.io.IOException;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka maskimko 
 */
public class VehicleParser implements SectionParser{

    
    public VehicleParser() {
    }

    
    
    @Override
    public void parse(String s) {
       try (CSVParser parser = CSVParser.parse(s, CSVFormat.DEFAULT.withHeader())){
           Map<String, Integer> headerMap = parser.getHeaderMap();
           for (CSVRecord r : parser){
               headerMap.forEach((h, v) -> {
                   System.out.println(String.format("\t%-20s: %s", h, r.get(v)));
               });
               System.out.println();
           }
       } catch ( IOException ex) {
           System.err.println(ex.getMessage());
       }
       
    }

}
