package com.itheima.leyou.dao;

import java.util.ArrayList;
import java.util.Map;

public interface IStockDao {
    /**
     * 获取商品列表，主要为了展示商品列表页
     * @return list，包含一个商品的map
     */
    public ArrayList<Map<String, Object>> getStockList();

    public ArrayList<Map<String, Object>> getStock(String sku_id);

    public boolean insertLimitPolicy(Map<String, Object> policyInfo);
}
