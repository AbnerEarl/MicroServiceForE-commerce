package com.itheima.leyou.service;

import java.util.Map;

public interface IOrderService {
    public Map<String,Object> insertOrder(Map<String, Object> orderInfo);

    public Map<String, Object> createOrder(String sku_id, String user_id);

    public Map<String, Object> getOrder(String order_id);

    public Map<String, Object> payOrder(String order_id, String sku_id);

    public Map<String,Object> updateOrderStatus(String order_id);
}
