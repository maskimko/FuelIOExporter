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
     private final static Pattern logPatern = Pattern.compile("^\"?##\\s*[Ll]og\"?");
     private final static Pattern costCategoryPatern = Pattern.compile("^\"?##\\s*[Cc]ost[Cc]ategories\"?");
      private final static Pattern costPatern = Pattern.compile("^\"?##\\s*[Cc]osts\"?");
    public static SectionParser getParser(String header){
        SectionParser sp = null;
        Matcher vm = vehiclePatern.matcher(header);
        if (vm.matches()){
            sp = new VehicleParser();
        }
        if (logPatern.matcher(header).matches()){
            sp = new LogParser();
        }
        if (costCategoryPatern.matcher(header).matches()){
            sp = new CostCategoryParser();
        }
        if (costPatern.matcher(header).matches()){
            sp = new CostParser();
        }
        return sp;
    }
}
