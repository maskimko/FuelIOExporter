/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.google.fuel.parsers;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.LoggerFactory;
import ua.pp.msk.google.fuel.entities.Cost;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka
 * maskimko
 */
public class CostParser extends AbstractParser<Cost> {

    private static final String COSTTITLE = "CostTitle";
    private static final String DATE = "Date";
    private static final String ODOMETER = "Odo";
    private static final String COSTTYPEID = "CostTypeID";
    private static final String NOTES = "Notes";
    private static final String COST = "Cost";
    private static final String FLAG = "flag";
    private static final String IDR = "idR";
    private static final String READ = "read";
    private static final String REMINDODO = "RemindOdo";
    private static final String REMINDDATE = "RemindDate";
    private static final String ISTEMPLATE = "isTemplate";
    private static final String REPEATODO = "RepeatOdo";
    private static final String REPEATMONTHS = "RepeatMonths";

    private List<Cost> entries = new LinkedList<>();

    public CostParser() {
        super(Cost.class);
    }

    @Override
    public void parse(String s) {
        super.parse(s);
        super.records.forEach(r -> {
            try {
                Cost cost = new Cost();
                cost.setCostTitle(getAsString(r, COSTTITLE));
                cost.setDate(getAsCalendar(r, DATE));
                cost.setOdometer(getAsInteger(r, ODOMETER));
                cost.setCostTypeId(getAsInteger(r, COSTTYPEID));
                cost.setNotes(getAsString(r, NOTES));
                cost.setFlag(getAsBoolean(r, FLAG));
                cost.setIdR(getAsInteger(r, IDR));
                cost.setRead(getAsBoolean(r, READ));
                cost.setRemindOdometer(getAsInteger(r, REMINDODO));
                cost.setRemindDate(getAsCalendar(r, REMINDDATE));
                cost.setIsTemplate(getAsBoolean(r, ISTEMPLATE));
                cost.setRepeatOdometer(getAsInteger(r, REPEATODO));
                cost.setRepeatMonths(getAsInteger(r, REPEATMONTHS));
                entries.add(cost);
            } catch (IllegalStateException | ParseException ex) {
                LoggerFactory.getLogger(this.getClass()).error("Cannot parse CSV record " + r.toString(), ex);
            }
        });
        ParserFactory.currentVehicle.setCosts(entries);
    }

    @Override
    public List<Cost> getEntries() {
        return entries;
    }

}
