package com.example.stockcalculator1.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.stockcalculator1.database.entities.StockCalculator1;
import com.example.stockcalculator1.MainActivity;
import com.example.stockcalculator1.database.entities.User;
import com.example.stockcalculator1.database.typeConverters.LocalDateTypeConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDateTypeConverter.class)
@Database(entities = {StockCalculator1.class, User.class}, version = 1, exportSchema = false)
public abstract class StockCalculator1Database extends RoomDatabase {

    public static final String USER_TABLE = "usertable";
    private static final String DATABASE_NAME = "StockCalculator1database";

    public static final String STOCK_CALCULATOR1_TABLE = "stockCalculator1Table";

    private static volatile StockCalculator1Database INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static StockCalculator1Database getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (StockCalculator1Database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),StockCalculator1Database.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration().addCallback(addDefaultValues).build();
                }
            }
        }
        return INSTANCE;
    }


    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED");

            databaseWriteExecutor.execute(() -> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                dao.insert(admin);

                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);
            });


        }
    };


    public abstract StockCalculator1DAO stockCalculator1DAO();

    public abstract UserDAO userDAO();
}
