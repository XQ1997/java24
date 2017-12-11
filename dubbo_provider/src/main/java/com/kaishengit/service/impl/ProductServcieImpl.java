package com.kaishengit.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.kaishengit.service.ProductServcie;

import java.util.Arrays;
import java.util.List;

@Service(timeout = 5000,version = "1.2")
public class ProductServcieImpl implements ProductServcie {

    public List<String> findAllProductNames() {
        return Arrays.asList("充电宝","数据线","手机","音响");
    }

    public void save(String name) {
        System.out.println("saved -> " + name);
    }
}
