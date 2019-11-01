package com.kepss.expenseentry.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kepss.expenseentry.table.tbl_config;

@Dao
public interface ConfigDao {

    @Insert
    long insertData(tbl_config config);

    @Query("select config_name from tbl_config limit 1")
    String getLastRecord();
}
