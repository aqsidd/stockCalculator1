package com.example.stockcalculator1.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.stockcalculator1.database.entities.StockCalculator1;

import java.util.List;

@Dao
public interface StockCalculator1DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(StockCalculator1 stockCalculator1);

    @Query("SELECT * FROM " + StockCalculator1Database.STOCK_CALCULATOR1_TABLE + " ORDER BY date DESC")
    List<StockCalculator1> getAllRecords();

//    @Query("SELECT * FROM " + StockCalculator1Database.STOCK_CALCULATOR1_TABLE + " WHERE userId = :userId ORDER BY date DESC")
//    LiveData<List<StockCalculator1>> getAllLogsByUserId(int userId);

    @Query("SELECT * FROM " + StockCalculator1Database.STOCK_CALCULATOR1_TABLE + " WHERE userId = :loggedInUserId ORDER BY date DESC")
    List<StockCalculator1> getRecordsByUserId(int loggedInUserId);
}
