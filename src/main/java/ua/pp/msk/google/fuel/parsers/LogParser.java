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
import ua.pp.msk.google.fuel.entities.Log;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka maskimko 
 */
public class LogParser extends AbstractParser<Log>{

    //TODO finish this parser
    
    public LogParser() {
        super(Log.class);
    }

    @Override
    public void parse(String s) {
       super.parse(s);
    }

    @Override
    public List<Log> getEntries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
