package com.itheima.leyou.controller;

import com.itheima.leyou.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StorageController {

    @Autowired
    private IStorageService iStorageService;

    @RequestMapping(value = "/insertStorage/{sku_id}/{inquanty}/{outquanty}")
    public Map<String, Object> insertStorage(@PathVariable("sku_id") String sku_id,
                                             @PathVariable("inquanty") double inquanty, @PathVariable("outquanty") double outquanty){
        return iStorageService.insertStorage(sku_id, inquanty, outquanty);
    }
}
