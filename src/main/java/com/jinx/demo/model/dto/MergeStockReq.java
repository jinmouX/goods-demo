package com.jinx.demo.model.dto;

import com.jinx.demo.model.entity.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MergeStockReq implements Serializable {

    private List<Stock> stock;
    private List<Stock> order;

    private static final long serialVersionUID = 1L;
}
