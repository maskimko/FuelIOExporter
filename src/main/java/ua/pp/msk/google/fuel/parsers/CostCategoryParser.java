/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.pp.msk.google.fuel.parsers;

import java.util.LinkedList;
import java.util.List;
import ua.pp.msk.google.fuel.entities.CostCategory;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka maskimko 
 */
public class CostCategoryParser extends AbstractParser<CostCategory>{

    private static final String COSTTYPEID = "CostTypeID";
    private static final String NAME = "Name";
    private static final String PRIORITY = "priority";
    private static final String COLOR = "color";
    
    private final List<CostCategory> cc = new LinkedList<>();
    public CostCategoryParser(){
        super( CostCategory.class);
    }
    @Override
    public void parse(String s) {
        super.parse(s);
        super.records.forEach(r -> {
            CostCategory current = new CostCategory();
            current.setCostTypeId(Integer.parseInt(r.get(super.headerMap.get(COSTTYPEID))));
            current.setPriority(Integer.parseInt(r.get(super.headerMap.get(PRIORITY))));
            current.setName(r.get(super.headerMap.get(NAME)));
            current.setColor(r.get(super.headerMap.get(COLOR)));
            cc.add(current);
        });
    }

    @Override
    public List<CostCategory> getEntries() {
        return cc;
    }

}
