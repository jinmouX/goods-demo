package com.jinx.demo.controller;

import com.jinx.demo.common.BaseResponse;
import com.jinx.demo.common.ErrorCode;
import com.jinx.demo.exception.BusinessException;
import com.jinx.demo.model.dto.MergeStockReq;
import com.jinx.demo.model.dto.SaveStockReq;
import com.jinx.demo.model.entity.Stock;
import com.jinx.demo.service.StockService;
import com.jinx.demo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class StockController {

    @Resource
    private StockService goodsService;

    @Resource
    private OrderService orderService;
    @PostMapping("/merge")
    public BaseResponse<List<Stock>> mergeStock(@RequestBody MergeStockReq mergeStockReq) {
        if(mergeStockReq == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        goodsService.validParams(mergeStockReq);
        return goodsService.mergeStock(mergeStockReq);
    }

    @PostMapping("/save")
    public BaseResponse saveStock(@RequestBody SaveStockReq saveStockReq){
        if(saveStockReq == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        orderService.validParams(saveStockReq);
        return orderService.saveStock(saveStockReq);

    }
    @GetMapping("/query/stock/{goodsId}")
    public BaseResponse queryStock(@PathVariable("goodsId") String goodsId){
        return goodsService.queryStockByStockId(goodsId);
    }

}
