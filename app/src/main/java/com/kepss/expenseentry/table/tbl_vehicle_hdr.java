package com.kepss.expenseentry.table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class tbl_vehicle_hdr {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    private String vehicleName;
    private String vehicleNumber;
    private String createDateTime;
    private String StartKM;
    private String CurrantKM;

    public tbl_vehicle_hdr(String vehicleName, String vehicleNumber, String createDateTime, String startKM, String currantKM) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.createDateTime = createDateTime;
        StartKM = startKM;
        CurrantKM = currantKM;
    }

    public tbl_vehicle_hdr() {
    }

    public String getStartKM() {
        return StartKM;
    }

    public void setStartKM(String startKM) {
        StartKM = startKM;
    }

    public String getCurrantKM() {
        return CurrantKM;
    }

    public void setCurrantKM(String currantKM) {
        CurrantKM = currantKM;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }
}
