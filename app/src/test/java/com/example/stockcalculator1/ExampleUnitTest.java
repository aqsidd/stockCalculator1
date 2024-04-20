package com.example.stockcalculator1;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import com.example.stockcalculator1.database.entities.StockCalculator1;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void StockCalcTest(){
        StockCalculator1 testStock = new StockCalculator1(20, 5, 10, 0);
        assertEquals(testStock.getValue(), 32.21, 0.01);
        assertEquals(testStock.getValue(), 20*Math.pow(1.1, 5), 0.01);
    }

    @Test
    public void toStringTest(){
        StockCalculator1 testStock = new StockCalculator1(20, 5, 10, 0);
        assertEquals("principle: 20.0\ndays: 5\ngrowthRate: 10.0\nValue: "
                + testStock.getValue() + "\ndate: " + testStock.getDate() + "\n============\n", testStock.toString());
    }
}