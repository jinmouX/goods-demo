package com.jinx.demo.biz;

import com.jinx.demo.model.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockBizTest {

    @Resource
    private StockBiz stockBiz;

    @Test
    public void mergeStockTest(){
        List<Stock> stock = new ArrayList<>();
        List<Stock> order = new ArrayList<>();
        stock.add(new Stock(1001L,"苹果",5));
        stock.add(new Stock(1002L,"小米",10));
        stock.add(new Stock(1003L,"华为",5));

        order.add(new Stock(1001L,"苹果",5));
        order.add(new Stock(1003L,"华为",5));
        order.add(new Stock(1004L,"天音",10));
        List<Stock> newList = stockBiz.mergeStock(stock, order);
        newList.forEach(System.out::println);
    }

    @Test
    public void distinctStockTest(){
        List<Stock> stock = new ArrayList<>();
        stock.add(new Stock(1001L,"苹果",5));
        stock.add(new Stock(1002L,"小米",10));
        stock.add(new Stock(1003L,"华为",5));
        stock.add(new Stock(1001L,"苹果",5));
        stock.add(new Stock(1003L,"华为",5));
        stock.add(new Stock(1004L,"天音",10));

        stockBiz.distinctStock(stock).forEach(System.out::println);
    }
    @Test
    public void distinctStockTest2(){
        List<Stock> stock = new ArrayList<>();
        stock.add(new Stock(1001L,"苹果",5));
        stock.add(new Stock(1002L,"小米",10));
        stock.add(new Stock(1003L,"华为",5));
        stock.add(new Stock(1001L,"苹果",5));
        stock.add(new Stock(1003L,"华为",5));
        stock.add(new Stock(1004L,"天音",10));

        stockBiz.distinctStock2(stock).forEach(System.out::println);
    }

}