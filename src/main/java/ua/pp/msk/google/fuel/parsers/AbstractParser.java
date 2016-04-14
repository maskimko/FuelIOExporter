/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.google.fuel.parsers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka maskimko 
 */
public abstract class AbstractParser<T> implements SectionParser{

    private final Logger logger;
    public abstract  List<T> getEntries();
    protected Map<String, Integer> headerMap;
    protected List<CSVRecord> records;
    private Class type;
    
    
    public AbstractParser(Class<T> c){
        this.logger = LoggerFactory.getLogger(c);
        type = c;
    }
    
    @Override
    public void parse(String s) {
       try (CSVParser  parser = CSVParser.parse(s, CSVFormat.DEFAULT.withHeader())){
            headerMap = parser.getHeaderMap();
            records = parser.getRecords();
            for (CSVRecord r: records){
               logger.debug(type.getName());
                headerMap.forEach((h, v) -> {
                 logger.debug(String.format("\t%-20s: %s", h, r.get(v)));
                });
               
            }
        }  catch (IOException ex) {
           logger.error(ex.getMessage());
        }
    }

}
