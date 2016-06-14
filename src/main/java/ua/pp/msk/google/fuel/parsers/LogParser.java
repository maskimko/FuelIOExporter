/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.google.fuel.parsers;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.LoggerFactory;
import ua.pp.msk.google.fuel.entities.Log;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka
 * maskimko
 */
public class LogParser extends AbstractParser<Log> {

    //TODO finish this parser
    private final List<Log> entries = new LinkedList<>();
    private static final String DATA = "Data";
    private static final String ODOMETER = "Odo (km)";
    private static final String FUEL = "Fuel (litres)";
    private static final String FULL = "Full";
    private static final String PRICE = "Price (optional)";
    private static final String CONSUMPTION = "l/100km (optional)";
    private static final String LATITUDE = "latitude (optional)";
    private static final String LONGITUDE = "longitude (optional)";
    private static final String CITY = "City (optional)";
    private static final String NOTES = "Notes (optional)";
    private static final String MISSED = "Missed";
    private static final String TANKNUMBER = "TankNumber";
    private static final String FUELTYPE = "FuelType";

    public LogParser() {
        super(Log.class);
    }

    @Override
    public void parse(String s) {
        super.parse(s);
        for (CSVRecord r : super.records) {
            try {
                Log l = new Log();
                l.setDate(r.get(super.headerMap.get(DATA)));
                l.setOdometer(Integer.parseInt(r.get(super.headerMap.get(ODOMETER))));
                l.setFuel(Float.parseFloat(r.get(super.headerMap.get(FUEL))));
                l.setFull(Integer.parseInt(r.get(super.headerMap.get(FULL))) > 0);
                l.setPrice(Float.parseFloat(r.get(super.headerMap.get(PRICE))));
                String consumptionValue = r.get(super.headerMap.get(CONSUMPTION));
                if (consumptionValue != null && consumptionValue.trim().length() > 0) {
                    l.setConsumption(Float.parseFloat(consumptionValue));
                }
                String latValue = r.get(super.headerMap.get(LATITUDE));
                if (latValue != null && latValue.trim().length() > 0) {
                    l.setLatitude(Float.parseFloat(latValue));
                }
                String lonValue = r.get(super.headerMap.get(LONGITUDE));
                if (lonValue != null && lonValue.trim().length() > 0) {
                    l.setLongitude(Float.parseFloat(lonValue));
                }
                l.setCity(r.get(super.headerMap.get(CITY)));
                l.setNotes(r.get(super.headerMap.get(NOTES)));
                l.setMissed(Integer.parseInt(r.get(super.headerMap.get(MISSED))) > 0);
                Integer tn = super.headerMap.get(TANKNUMBER); 
                l.setTankNumber((tn == null) ? 1 : Integer.parseInt(r.get(tn)));
                Integer ft = super.headerMap.get(FUELTYPE);
                l.setFuelType((ft == null) ? 0 : Integer.parseInt(r.get(ft)));
                entries.add(l);
            } catch (ParseException ex) {
                LoggerFactory.getLogger(this.getClass()).error("Cannot parse date in CSV record " + r.toString());
            }
        }
        ParserFactory.currentVehicle.setLogs(entries);
    }

    @Override
    public List<Log> getEntries() {
        return entries;
    }

}
