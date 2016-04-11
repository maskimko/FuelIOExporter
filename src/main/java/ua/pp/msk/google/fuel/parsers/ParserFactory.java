/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.google.fuel.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka maskimko 
 */
public class ParserFactory {
    private final static Pattern vehiclePatern = Pattern.compile("^\"?##\\s*[Vv]ehicle\"?");
    public static SectionParser getParser(String header){
        SectionParser sp = null;
        Matcher vm = vehiclePatern.matcher(header);
        if (vm.matches()){
            sp = new VehicleParser();
        }
        return sp;
    }
}
