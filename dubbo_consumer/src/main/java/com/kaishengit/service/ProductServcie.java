package com.kaishengit.service;

import java.util.List;

public interface ProductServcie {

    List<String> findAllProductNames();
    void save(String name);

}
