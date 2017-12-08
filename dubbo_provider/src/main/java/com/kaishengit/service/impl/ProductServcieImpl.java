package com.kaishengit.service.impl;

import com.kaishengit.service.ProductServcie;

import java.util.Arrays;
import java.util.List;

public class ProductServcieImpl implements ProductServcie {

    public List<String> findAllProductNames() {
        return Arrays.asList("充电宝","数据线","手机","音响");
    }

    public void save(String name) {
        System.out.println("saved -> " + name);
    }
}
