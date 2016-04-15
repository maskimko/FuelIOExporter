/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.google.fuel.entities;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka
 * maskimko
 */
public class Log implements Serializable {

    private long id;
    
    private Calendar date;
    private int odometer;
    private float fuel;
    private boolean full;
    private Float price;
    private Float consumption;
    private Float latitude;
    private Float longitude;
    private String city;
    private String notes;
    private boolean missed;
    private int tankNumber;
    private int fuelType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setDate(Date date) {
        this.date = Calendar.getInstance();
        this.date.setTime(date);
    }

    public void setDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        setDate(sdf.parse(date));
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public float getFuel() {
        return fuel;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getConsumption() {
        return consumption;
    }

    public void setConsumption(float consumption) {
        this.consumption = consumption;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isMissed() {
        return missed;
    }

    public void setMissed(boolean missed) {
        this.missed = missed;
    }

    public int getTankNumber() {
        return tankNumber;
    }

    public void setTankNumber(int tankNumber) {
        this.tankNumber = tankNumber;
    }

    public int getFuelType() {
        return fuelType;
    }

    public void setFuelType(int fuelType) {
        this.fuelType = fuelType;
    }

}
