package com.kepss.expenseentry.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kepss.expenseentry.dao.ConfigDao;
import com.kepss.expenseentry.dao.ExpenseDao;
import com.kepss.expenseentry.dao.VehicleDao;
import com.kepss.expenseentry.table.tbl_config;
import com.kepss.expenseentry.table.tbl_expense;
import com.kepss.expenseentry.table.tbl_vehicle_hdr;

@Database(entities = {tbl_expense.class, tbl_vehicle_hdr.class, tbl_config.class}, version = 1)
public abstract class roomDatabase extends RoomDatabase {
    private static roomDatabase db;

    public static roomDatabase getContext(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context, roomDatabase.class, "Expense.db").fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }

    public abstract ExpenseDao expenseDao();

    public abstract ConfigDao configDao();

    public abstract VehicleDao vehicleDao();
}
