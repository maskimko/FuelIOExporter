/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.pp.msk.google.fuel.entities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Maksym Shkolnyi aka Maksym Shkolnyi "&lt; maskimko@ukr.net &gt;" aka
 * maskimko
 */
public class Vehicle implements Serializable {

    private long id;
    private String googleId;
    private String name;
    private String description;
    private int distanceUnit;
    private int fuelUnit;
    private int consumptionUnit;
    private String importCSVDateFormat;
    private String vin;
    private String insurance;
    private String plate;
    private String make;
    private String model;
    private int year;
    private int tankCount;
    private int tank1Type;
    private int tank2Type;
    private List<CostCategory> costCategories;
    private List<Cost> costs;
    private List<Log> logs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(int distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public int getFuelUnit() {
        return fuelUnit;
    }

    public void setFuelUnit(int fuelUnit) {
        this.fuelUnit = fuelUnit;
    }

    public int getConsumptionUnit() {
        return consumptionUnit;
    }

    public void setConsumptionUnit(int consumptionUnit) {
        this.consumptionUnit = consumptionUnit;
    }

    public String getImportCSVDateFormat() {
        return importCSVDateFormat;
    }

    public void setImportCSVDateFormat(String importCSVDateFormat) {
        this.importCSVDateFormat = importCSVDateFormat;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTankCount() {
        return tankCount;
    }

    public void setTankCount(int tankCount) {
        this.tankCount = tankCount;
    }

    public int getTank1Type() {
        return tank1Type;
    }

    public void setTank1Type(int tank1Type) {
        this.tank1Type = tank1Type;
    }

    public int getTank2Type() {
        return tank2Type;
    }

    public void setTank2Type(int tank2Type) {
        this.tank2Type = tank2Type;
    }

    public List<CostCategory> getCostCategories() {
        return costCategories;
    }

    public void setCostCategories(List<CostCategory> costCategories) {
        this.costCategories = costCategories;
    }

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }

   

    
}
