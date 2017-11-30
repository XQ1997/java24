package com.kaishengit.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Father<T,PK> {

    public Father() {
        System.out.println("createFather");
        Class clazz = this.getClass();//Son class
        //Class superClazz = clazz.getSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] paramTypes = parameterizedType.getActualTypeArguments();
        Class firstParamClass = (Class) paramTypes[0];
        System.out.println(firstParamClass);
    }

}
