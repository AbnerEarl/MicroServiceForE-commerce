package com.itheima.leyou.service;

import java.util.Map;

public interface IStorageService {

    public Map<String, Object> insertStorage(String sku_id, double in_quanty, double out_quanty);
}
