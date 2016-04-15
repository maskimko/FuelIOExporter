/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.google.fuel.entities;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka
 * maskimko
 */
public class Cost implements Serializable {

    private long id;
    
    private String costTitle;
    private Calendar date;
    private int odometer;
    private int costTypeId;
    private String notes;
    private float cost;
    private boolean flag;
    private int idR;
    private boolean read;
    private int remindOdometer;
    private Calendar remindDate;
    private boolean isTemplate;
    private int repeatOdometer;
    private int repeatMonths;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public String getCostTitle() {
        return costTitle;
    }

    public void setCostTitle(String costTitle) {
        this.costTitle = costTitle;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public int getCostTypeId() {
        return costTypeId;
    }

    public void setCostTypeId(int costTypeId) {
        this.costTypeId = costTypeId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getRemindOdometer() {
        return remindOdometer;
    }

    public void setRemindOdometer(int remindOdometer) {
        this.remindOdometer = remindOdometer;
    }

    public Calendar getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Calendar remindDate) {
        this.remindDate = remindDate;
    }

    public boolean isIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(boolean isTemplate) {
        this.isTemplate = isTemplate;
    }

    public int getRepeatOdometer() {
        return repeatOdometer;
    }

    public void setRepeatOdometer(int repeatOdometer) {
        this.repeatOdometer = repeatOdometer;
    }

    public int getRepeatMonths() {
        return repeatMonths;
    }

    public void setRepeatMonths(int repeatMonths) {
        this.repeatMonths = repeatMonths;
    }

}
