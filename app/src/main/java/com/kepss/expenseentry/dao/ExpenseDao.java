package com.kepss.expenseentry.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kepss.expenseentry.table.tbl_expense;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    Long InsertData(tbl_expense expense);

    @Query("select * from tbl_expense where expDate >= :fromDate and expDate <= :toDate")
    List<tbl_expense> selectDataDateWise(String fromDate, String toDate);

    @Query("select * from tbl_expense")
    List<tbl_expense> selectData();

    @Query("select sum(amt) from tbl_expense where IsInCome = :isIncome and expDate = :strDate")
    String getExpenseDateWise(String strDate, String isIncome);

    @Query("select distinct name from tbl_expense ")
    List<String> getAllExpenseName();

}
