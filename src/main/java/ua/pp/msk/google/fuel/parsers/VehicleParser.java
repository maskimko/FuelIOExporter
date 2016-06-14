/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.google.fuel.parsers;

import java.util.LinkedList;
import java.util.List;
import org.slf4j.LoggerFactory;
import ua.pp.msk.google.fuel.entities.Vehicle;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka maskimko 
 */
public class VehicleParser extends AbstractParser<Vehicle>{

    private final static String NAME = "Name";
    private final static String DESCRIPTION = "Description";
    private final static String DISTUNIT = "DistUnit";
    private final static String FUELUNIT = "FuelUnit";
    private final static String CONSUMPTIONUNIT = "ConsumptionUnit";
    private final static String IMPORTCSVDATEFORMAT = "ImportCSVDateFormat";
    private final static String VIN = "VIN";
    private final static String INSURANCE = "Insurance";
    private final static String PLATE = "Plate";
    private final static String MAKE = "Make";
    private final static String MODEL = "Model";
    private final static String YEAR = "Year";
    private final static String TANKCOUNT = "TankCount";
    private final static String TANK1TYPE = "Tank1Type";
    private final static String TANK2TYPE = "Tank2Type";
    
    private List<Vehicle> entries = new LinkedList<>();
    public VehicleParser() {
        super(Vehicle.class);
    }

    
    
    @Override
    public void parse(String s) {
     super.parse(s);
        super.records.forEach(r -> { 
            try {
                LoggerFactory.getLogger(this.getClass()).debug(String.format("Working on CSV record %s", r.toString()));
                Vehicle v = new Vehicle();
                v.setName(getAsString(r, NAME));
                v.setDescription(getAsString(r, DESCRIPTION));
                v.setDistanceUnit(getAsInteger(r, DISTUNIT));
                v.setFuelUnit(getAsInteger(r, FUELUNIT));
                v.setConsumptionUnit(getAsInteger(r, CONSUMPTIONUNIT));
                v.setImportCSVDateFormat(getAsString(r, IMPORTCSVDATEFORMAT));
                v.setVin(getAsString(r, VIN));
                v.setInsurance(getAsString(r, INSURANCE));
                v.setPlate(getAsString(r, PLATE));
                v.setMake(getAsString(r, MAKE));
                v.setModel(getAsString(r, MODEL));
                v.setYear(getAsInteger(r, YEAR));
                v.setTankCount(getAsInteger(r, TANKCOUNT));
                v.setTank1Type(getAsInteger(r, TANK1TYPE));
                v.setTank2Type(getAsInteger(r, TANK2TYPE));
                entries.add(v);
                ParserFactory.currentVehicle = v;
            } catch (IllegalStateException ex) {
                LoggerFactory.getLogger(this.getClass()).error(ex.getMessage(), ex);
            }
        });
        if (entries.size() > 1) LoggerFactory.getLogger(this.getClass()).warn("Multiple vehicles occured in the file!!!");
    }

    @Override
    public List<Vehicle> getEntries() {
        return entries;
    }

}
