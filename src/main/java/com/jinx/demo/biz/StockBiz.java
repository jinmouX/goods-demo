package com.jinx.demo.biz;

import com.jinx.demo.model.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StockBiz {

    /**
     * 合并
     * @param stock
     * @param order
     * @return
     */
    public List<Stock> mergeStock(List<Stock> stock, List<Stock> order){

        // 创建一个HashMap来保存合并后的库存
        Map<Long, Stock> mergedStock = new HashMap<>();

        // 遍历stock集合，将商品按照goodsId保存到HashMap中
        for (Stock goods : stock) {
            mergedStock.put(goods.getGoodsId(), goods);
        }

        // 遍历order集合，将与stock中商品ID相同的记录合并数量
        for (Stock goods : order) {
            Long goodsId = goods.getGoodsId();
            if (mergedStock.containsKey(goodsId)) {
                Stock mergedGoods = mergedStock.get(goodsId);
                mergedGoods.setNumber(mergedGoods.getNumber() + goods.getNumber());
            } else {
                mergedStock.put(goodsId, goods);
            }
        }

        // 将HashMap中的值转为List并返回
        return new ArrayList<>(mergedStock.values());
    }
    /**
     * 合并去重
     */
    public List<Stock> distinctStock(List<Stock> StockList){
        log.info("去重前:{}",StockList);
        Map<Long, Integer> StockMap = new HashMap<>();
        for (Stock Stock : StockList) {
            Long id = Stock.getGoodsId();
            int number = Stock.getNumber();
            StockMap.put(id, StockMap.getOrDefault(id, 0) + number);
        }

        // 构建新的集合
        List<Stock> newStockList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : StockMap.entrySet()) {
            Long StockId = entry.getKey();
            int number = entry.getValue();
            newStockList.add(new Stock(StockId, "", number));
        }
        log.info("去重后:{}",newStockList);
        return newStockList;
    }

    public List<Stock> distinctStock2(List<Stock> StockList){
        log.info("去重前:{}",StockList);
        Map<Long,Stock> StockMap = new HashMap<>();
        for (Stock stock : StockList) {
            Long id = stock.getGoodsId();
            if(StockMap.containsKey(id)){
                Stock s = StockMap.get(id);
                s.setNumber(s.getNumber()+stock.getNumber());
            }else {
                StockMap.put(id, stock);
            }
        }

        // 构建新的集合
        List<Stock> newStockList = new ArrayList<>();
        for (Map.Entry<Long,Stock> entry : StockMap.entrySet()) {
            newStockList.add(entry.getValue());
        }
        log.info("去重后:{}",newStockList);
        return newStockList;
    }
}
