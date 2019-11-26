package com.itheima.leyou.controller;

import com.alibaba.fastjson.JSONObject;
import com.itheima.leyou.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StockController {

    @Autowired
    private IStockService iStockService;

    @RequestMapping(value = "/getStockList")
    public Map<String, Object> getStockList(){
        return iStockService.getStockList();
    }


    @RequestMapping(value = "/getStock/{sku_id}")
    public Map<String, Object> getStock(@PathVariable("sku_id") String sku_id){
        return iStockService.getStock(sku_id);
    }


    @RequestMapping(value = "/insertLimitPolicy/{json}")
    public Map<String, Object> insertLimitPolicy(@PathVariable("json") String json){
        Map<String, Object> policyInfo = JSONObject.parseObject(json, Map.class);
        return iStockService.insertLimitPolicy(policyInfo);
    }
}
