package com.jinx.demo.service;

import com.jinx.demo.common.BaseResponse;
import com.jinx.demo.model.dto.MergeStockReq;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinx.demo.model.entity.Stock;

import java.util.List;

/**
* @author jinx
* @description 针对表【test_stock_jinx(商品表)】的数据库操作Service
* @createDate 2023-09-26 11:09:12
*/
public interface StockService extends IService<Stock> {

    void validParams(MergeStockReq mergeStockReq);

    BaseResponse<List<Stock>> mergeStock(MergeStockReq mergeStockReq);

    BaseResponse queryStockByStockId(String goodsId);
}
