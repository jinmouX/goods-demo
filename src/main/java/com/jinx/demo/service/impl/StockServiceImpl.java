package com.jinx.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinx.demo.biz.StockBiz;
import com.jinx.demo.common.BaseResponse;
import com.jinx.demo.common.ErrorCode;
import com.jinx.demo.common.ResultUtils;
import com.jinx.demo.exception.BusinessException;
import com.jinx.demo.mapper.StockMapper;
import com.jinx.demo.model.dto.MergeStockReq;
import com.jinx.demo.model.entity.Stock;
import com.jinx.demo.service.StockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author jinx
* @description 针对表【test_stock_jinx(库存表)】的数据库操作Service实现
* @createDate 2023-09-26 15:53:29
*/
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock>
    implements StockService {

    @Resource
    private StockBiz stockBiz;


    @Override
    public void validParams(MergeStockReq mergeStockReq) {
        List<Stock> stock = mergeStockReq.getStock();
        List<Stock> order = mergeStockReq.getOrder();
        if(CollUtil.isEmpty(stock) || CollUtil.isEmpty(order)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    @Override
    public BaseResponse<List<Stock>> mergeStock(MergeStockReq mergeStockReq) {
        List<Stock> stock = mergeStockReq.getStock();
        List<Stock> order = mergeStockReq.getOrder();
        return ResultUtils.success(stockBiz.mergeStock(stock, order));
    }

    @Override
    public BaseResponse queryStockByStockId(String goodsId) {
        Stock stock = this.getById(goodsId);
        return ResultUtils.success(stock);
    }

}




