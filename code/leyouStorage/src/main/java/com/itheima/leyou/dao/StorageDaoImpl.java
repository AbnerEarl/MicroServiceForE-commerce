package com.itheima.leyou.dao;

import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StorageDaoImpl implements IStorageDao{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> insertStorage(String sku_id, double in_quanty, double out_quanty){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        //1、先判断主表里有没有？
        String sql = "select id from tb_stock_storage where sku_id = ?";
        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) jdbcTemplate.queryForList(sql, sku_id);

        int new_id = 0;
        double thisQuanty = in_quanty - out_quanty;
        boolean result = false;

        //2、如果主表里有，获取主表id，作用一用于写入历史表，作用二反回来更新主表
        if (list!=null&&list.size()>0){
            new_id = Integer.parseInt(list.get(0).get("id").toString());
        }else {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            //3、如果主表没有，写入主表，并且获取主表id，作用用于写入历史表
            sql = "INSERT INTO tb_stock_storage (warehouse_id, sku_id, quanty) VALUES (1, "+sku_id+", "+thisQuanty+")";

            final String finalSql = sql;

            result = jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement preparedStatement = connection.prepareStatement(finalSql, Statement.RETURN_GENERATED_KEYS);
                    return preparedStatement;
                }
            }, keyHolder)==1;

            if (!result){
                resultMap.put("result", false);
                resultMap.put("msg", "写入主表失败！");
                return resultMap;
            }

            new_id = keyHolder.getKey().intValue();
        }

        //4、写入历史表
        sql = "INSERT INTO tb_stock_storage_history (stock_storage_id, in_quanty, out_quanty) " +
                "VALUES (?, ?, ?)";
        result = jdbcTemplate.update(sql, new_id, in_quanty, out_quanty)==1;

        if (!result){
            resultMap.put("result", false);
            resultMap.put("msg", "写入历史表失败！");
            return resultMap;
        }

        //5、主表里有的时候,反回来更新主表
        if (list!=null&&list.size()>0){
            sql = "UPDATE tb_stock_storage SET quanty = quanty + "+thisQuanty+" WHERE id = "+new_id;
            result = jdbcTemplate.update(sql)==1;

            if (!result){
                resultMap.put("result", false);
                resultMap.put("msg", "更新主表失败！");
                return resultMap;
            }
        }

        //6、返回正常信息
        resultMap.put("result", true);
        resultMap.put("msg", "");
        return resultMap;
    }
}
