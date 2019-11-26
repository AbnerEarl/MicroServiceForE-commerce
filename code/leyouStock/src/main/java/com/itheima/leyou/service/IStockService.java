package com.itheima.leyou.service;

import java.util.Map;

public interface IStockService {

    /**
     * 获取商品列表，主要为了展示商品列表页
     * @return Map，包含list，包含一个商品的map
     */
    public Map<String, Object> getStockList();

    public Map<String, Object> getStock(String sku_id);

    public Map<String, Object> insertLimitPolicy(Map<String, Object> policyInfo);
}
