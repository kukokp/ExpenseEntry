package com.kepss.expenseentry.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kepss.expenseentry.table.tbl_vehicle_hdr;

import java.util.List;

@Dao
public interface VehicleDao {

    @Insert
    long saveData(tbl_vehicle_hdr obj);

    @Query("select * from tbl_vehicle_hdr")
    List<tbl_vehicle_hdr> selectData();

    @Query("select count(*) from tbl_vehicle_hdr")
    String countOfVehicle();
}
