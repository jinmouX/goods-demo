package com.jinx.demo.service;

import com.jinx.demo.common.BaseResponse;
import com.jinx.demo.model.dto.SaveStockReq;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinx.demo.model.entity.Order;
import com.jinx.demo.model.entity.Stock;

/**
* @author jinx
* @description 针对表【test_order_jinx(订单表)】的数据库操作Service
* @createDate 2023-09-26 13:57:00
*/
public interface OrderService extends IService<Order> {

    void validParams(SaveStockReq saveStockReq);

    BaseResponse saveStock(SaveStockReq saveStockReq);


}
