package com.jinx.demo.service.impl;

import com.jinx.demo.model.dto.SaveStockReq;
import com.jinx.demo.model.entity.Stock;
import com.jinx.demo.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Resource
    private OrderService orderService;

    @Test
    public void saveStockTest1(){
        SaveStockReq saveStockReq = new SaveStockReq();
        saveStockReq.setOrderId("2021-01-20-1003");
        List<Stock> stock = new ArrayList<>();
        stock.add(new Stock(1001L,"苹果",5));
        stock.add(new Stock(1001L,"苹果",5));

        stock.add(new Stock(1002L,"小米",10));

        stock.add(new Stock(1003L,"华为",5));
        stock.add(new Stock(1003L,"华为",5));

        stock.add(new Stock(1004L,"天音",10));
        saveStockReq.setOrder(stock);
        orderService.saveStock(saveStockReq);
    }

}