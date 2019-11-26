package com.itheima.leyou.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderDaoImpl implements IOrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> insertOrder(Map<String, Object> orderInfo) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String sql = "insert into tb_order (order_id, total_fee, actual_fee, post_fee, payment_type, user_id, status, create_time) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        boolean result = jdbcTemplate.update(sql, orderInfo.get("order_id"), orderInfo.get("total_fee"), orderInfo.get("actual_fee"),
                orderInfo.get("post_fee"), orderInfo.get("payment_type"), orderInfo.get("user_id"), orderInfo.get("status"),
                orderInfo.get("create_time"))==1;

        if (!result){
            resultMap.put("result", false);
            resultMap.put("msg", "写入订单主表失败！");
            return resultMap;
        }

        sql = "INSERT INTO tb_order_detail (order_id, sku_id, num, title, own_spec, price, image, create_time) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        result = jdbcTemplate.update(sql, orderInfo.get("order_id"), orderInfo.get("sku_id"), orderInfo.get("num"),
                orderInfo.get("title"), orderInfo.get("own_spec"), orderInfo.get("price"), orderInfo.get("image"),
                orderInfo.get("create_time"))==1;

        if (!result){
            resultMap.put("result", false);
            resultMap.put("msg", "写入订单明细表失败！");
            return resultMap;
        }

        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }

    public boolean updateOrderStatus(String order_id) {
        String sql = "update tb_order set status = 2 where order_id = ?";
        return jdbcTemplate.update(sql, order_id)==1;
    }
}
