package com.jinx.demo.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinx.demo.biz.StockBiz;
import com.jinx.demo.common.BaseResponse;
import com.jinx.demo.common.ErrorCode;
import com.jinx.demo.common.ResultUtils;
import com.jinx.demo.exception.BusinessException;
import com.jinx.demo.mapper.OrderMapper;
import com.jinx.demo.model.dto.SaveStockReq;
import com.jinx.demo.model.entity.Order;
import com.jinx.demo.model.entity.Stock;
import com.jinx.demo.service.StockService;
import com.jinx.demo.service.OrderService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author jinx
* @description 针对表【test_order_jinx(订单表)】的数据库操作Service实现
* @createDate 2023-09-26 13:57:00
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Resource
    private StockService goodsService;
    @Resource
    private StockBiz stockBiz;

    @Override
    public void validParams(SaveStockReq saveStockReq) {
        if(StrUtil.isBlank(saveStockReq.getOrderId()) || CollUtil.isEmpty(saveStockReq.getOrder())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    /**
     * 当订单中的商品库存不足时整个订单都会失败
     * @param saveStockReq
     * @return
     */
    @Transactional
    @Override
    public BaseResponse saveStock(SaveStockReq saveStockReq) {
        String orderId = saveStockReq.getOrderId();
        //合并去重
        List<Stock> goodsVoList = stockBiz.distinctStock(saveStockReq.getOrder());
        for(Stock goods:goodsVoList){
            Integer number = goods.getNumber();
            Long goodsId = goods.getGoodsId();
            //sql update from test_stock_jinx set number = number - #{number} where goodsId = #{goodsId} and number >= #{number}
            boolean update = goodsService.update()
                    .setSql("number = number - " + number)
                    .eq("goodsId", goodsId)
                    .ge("number", number).update();
            if(!update){
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "商品库存不足");
            }
            Order order = new Order();
            order.setOrderId(orderId);
            order.setGoodsId(goodsId);
            order.setNumber(number);
            boolean save = this.save(order);
            if(!save){
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "订单创建失败");
            }
        }

        return ResultUtils.success();
    }

}




