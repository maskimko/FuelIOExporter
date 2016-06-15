/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.google.fuel.entities;

import java.io.Serializable;
import java.lang.reflect.Field;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka
 * maskimko
 */
public class CostCategory implements Serializable {

    private long id;
    private int costTypeId;
    private String name;
    private int priority;
    private String color;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(int costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\tCost Category:\n");
        try {
            for (Field f : this.getClass().getDeclaredFields()) {
                
                    sb.append(String.format("\t\t%-18s : %s\n", f.getName(), f.get(this)));
                
            }
        } catch (IllegalAccessException ex) {
            LoggerFactory.getLogger(this.getClass()).warn("Cannot produce a pretty output, fall back to the standard one");
            LoggerFactory.getLogger(this.getClass()).debug("This should never happen" + ex.getMessage(), ex);
            sb.append("{" + "id=").append(id).append(", costTypeId=").append(costTypeId).append(", name=").append(name).append(", priority=").append(priority).append(", color=").append(color).append('}');
        }
        return sb.toString();
    }

}
