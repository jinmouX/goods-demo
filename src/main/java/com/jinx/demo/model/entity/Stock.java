package com.jinx.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存表
 * @TableName test_stock_jinx
 */
@TableName(value ="test_stock_jinx")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Long goodsId;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品数量
     */
    private Integer number;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}