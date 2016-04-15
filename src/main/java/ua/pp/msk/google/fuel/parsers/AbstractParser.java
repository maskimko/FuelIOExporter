/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.google.fuel.parsers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka
 * maskimko
 */
public abstract class AbstractParser<T> implements SectionParser {

    private final Logger logger;

    public abstract List<T> getEntries();
    protected Map<String, Integer> headerMap;
    protected List<CSVRecord> records;
    private Class type;

    public AbstractParser(Class<T> c) {
        this.logger = LoggerFactory.getLogger(c);
        type = c;
    }

    @Override
    public void parse(String s) {
        try (CSVParser parser = CSVParser.parse(s, CSVFormat.DEFAULT.withHeader())) {
            headerMap = parser.getHeaderMap();
            records = parser.getRecords();
            if (logger.isDebugEnabled()){
                records.stream().map((r) -> {
                    logger.debug(type.getName());
                    return r;
                }).forEach((r) -> {
                    headerMap.forEach((h, v) -> {
                        logger.debug(String.format("\t%-20s: %s", h, r.get(v)));
                    });
                });
            }
        } catch (IOException ex) {
            logger.error("Cannot parse CSV " + ex.getMessage(), ex);
        }
    }

    protected String getAsString(CSVRecord r, String header) throws IllegalStateException {
        if (headerMap == null || headerMap.isEmpty()) {
            throw new IllegalStateException("Header Map is emplty. Cannot get the value. Perhaps parse method has been not run yet...");
        }
        String val = r.get(headerMap.get(header));
        return val.trim();

    }

    protected int getAsInteger(CSVRecord r, String header) throws IllegalStateException {
        String val = getAsString(r, header);
        if (val != null && val.length() > 0) {
            return Integer.parseInt(val);
        }
        return -1;
    }

    protected boolean getAsBoolean(CSVRecord r, String header) throws IllegalStateException {
        int val = getAsInteger(r, header);
        return val > 0;
    }

    protected Float getAsFloat(CSVRecord r, String header) throws IllegalStateException {
        String val = getAsString(r, header);
        if (val != null && val.length() > 0) {
            return Float.parseFloat(val);
        }
        return null;
    }
     protected Calendar getAsCalendar(CSVRecord r, String header) throws IllegalStateException, ParseException {
         String dateformat = (ParserFactory.currentVehicle == null 
                   || ParserFactory.currentVehicle.getImportCSVDateFormat() == null 
                   || ParserFactory.currentVehicle.getImportCSVDateFormat().isEmpty())
                   ? ParserFactory.currentVehicle.getImportCSVDateFormat()
                   : "yyyy-MM-dd"; 
         SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
         String val = getAsString(r, header);
         logger.debug(String.format("Parsing date %s (format: %s)", val, dateformat));
         Calendar c = Calendar.getInstance();
         c.setTime(sdf.parse(val));
         return c;
    }

}
