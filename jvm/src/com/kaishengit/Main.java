package com.kaishengit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SuperClass {
    static {
        System.out.println("superclass Static ");
        a = 100;
    }
    public static int a = 10;
}
class SubClass extends SuperClass {
    public static int b = a;
}

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {


        MyClassLoader myClassLoader = new MyClassLoader("D:/");
        //D:/com/kaishengit/Hello.class
        Class clazz = myClassLoader.loadClass("com.kaishengit.Hello");

        Object obj = clazz.newInstance();
        Method hello = clazz.getMethod("hello");
        hello.invoke(obj);

        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getClassLoader().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent().getParent());

       /* ClassLoader classLoader = Main.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());*/


        //System.out.println(SubClass.b);



       // SubClass[] subClasses = new SubClass[10];

        //System.out.println(SubClass.value);

        //byte[] bytes = new byte[1024*1024*10];
       /* for (int i = 0;i < 10;i++) {
            bytes = new byte[1024*1024*1];
        }*/



        /*//-Xms5m -Xmx10M -XX:+PrintGCDetails -XX:+UseSerialGC
	    Runtime runtime = Runtime.getRuntime();
        System.out.println("可用最大内存：" + runtime.maxMemory());
        System.out.println("空闲内存：" + runtime.freeMemory());
        System.out.println("当前可用内存：" + runtime.totalMemory());

        System.out.println("---------------------------------------------");

        byte[] bytes = new byte[1024*1024*4];
        System.out.println("可用最大内存：" + runtime.maxMemory());
        System.out.println("空闲内存：" + runtime.freeMemory());
        System.out.println("当前可用内存：" + runtime.totalMemory());

        System.out.println("---------------------------------------------");

        bytes = new byte[1024*1024*4];
        System.out.println("可用最大内存：" + runtime.maxMemory());
        System.out.println("空闲内存：" + runtime.freeMemory());
        System.out.println("当前可用内存：" + runtime.totalMemory());*/

    }
}
