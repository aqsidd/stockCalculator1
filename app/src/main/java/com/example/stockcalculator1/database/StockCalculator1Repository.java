package com.example.stockcalculator1.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.stockcalculator1.database.entities.StockCalculator1;
import com.example.stockcalculator1.MainActivity;
import com.example.stockcalculator1.database.entities.User;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StockCalculator1Repository {

    private final StockCalculator1DAO stockCalculatorDAO;

    private final UserDAO userDAO;

    private ArrayList<StockCalculator1> allLogs;
    private static StockCalculator1Repository repository;

    private StockCalculator1Repository(Application application){
        StockCalculator1Database db = StockCalculator1Database.getDatabase(application);
        this.stockCalculatorDAO = db.stockCalculator1DAO();
        this.userDAO = db.userDAO();
        this.allLogs = (ArrayList<StockCalculator1>) this.stockCalculatorDAO.getAllRecords();
    }


    public static StockCalculator1Repository getRepository(Application application){
        if(repository != null){
            return repository;
        }
        Future<StockCalculator1Repository> future = StockCalculator1Database.databaseWriteExecutor.submit(new Callable<StockCalculator1Repository>() {
            @Override
            public StockCalculator1Repository call() throws Exception {
                return new StockCalculator1Repository(application);
            }
        });

        try {
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            Log.d(MainActivity.TAG, "Problem 3");
        }
        return null;

    }



    public ArrayList<StockCalculator1> getAllLogs(){
        Future<ArrayList<StockCalculator1>> future = StockCalculator1Database.databaseWriteExecutor.submit(new Callable<ArrayList<StockCalculator1>>() {
            @Override
            public ArrayList<StockCalculator1> call() throws Exception {
                return (ArrayList<StockCalculator1>) stockCalculatorDAO.getAllRecords();
            }
        });
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            Log.i(MainActivity.TAG, "Problem stockcalculator1 in repository");
        }
        return null;
    }

    public void insertStockCalculator1(StockCalculator1 stockCalculator){
        StockCalculator1Database.databaseWriteExecutor.execute(()->
        {
            stockCalculatorDAO.insert(stockCalculator);
        });
    }


    public void insertUser(User... user){
        StockCalculator1Database.databaseWriteExecutor.execute(()->
        {
            userDAO.insert(user);
        });
    }

    public LiveData<User> getUserByUserName(String username) {
        return userDAO.getUserByUserName(username);
    }

    public LiveData<User> getUserByUserId(int userId) {
        return userDAO.getUserByUserId(userId);
    }


    public ArrayList<StockCalculator1> getAllLogsByUserId(int loggedInUserId) {
        Future<ArrayList<StockCalculator1>> future = StockCalculator1Database.databaseWriteExecutor.submit(new Callable<ArrayList<StockCalculator1>>() {
            @Override
            public ArrayList<StockCalculator1> call() throws Exception {
                return (ArrayList<StockCalculator1>) stockCalculatorDAO.getRecordsByUserId(loggedInUserId);
            }
        });
        try{
            return future.get();
        }catch (InterruptedException | ExecutionException e){
            Log.i(MainActivity.TAG, "Problem stockcalculator1 in repository");
        }
        return null;
    }
}
