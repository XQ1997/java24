package com.kaishengit.hibernate;

import com.kaishengit.pojo.Product;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NativeSQLTest {

    private Session session;

    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
    }

    @After
    public void after() {
        session.getTransaction().commit();
    }


    @Test
    public void findAll() {
        String sql = "select * from product order by id desc";
        SQLQuery sqlQuery = session.createSQLQuery(sql);

        List<Object[]> list = sqlQuery.list();
        for(Object[] data : list) {
            System.out.println(data[0] + "  ->  " + data[1] + "  ->  " + data[2]);
        }
    }

    @Test
    public void findAllToPojo() {
        String sql = "select * from product order by id desc";
        SQLQuery sqlQuery = session.createSQLQuery(sql)
                            .addEntity(Product.class);

        List<Product> list = sqlQuery.list();
        showList(list);
    }

    @Test
    public void findBy() {
        String sql = "select * from product where id = ? order by id desc";
        SQLQuery sqlQuery = session.createSQLQuery(sql)
                .addEntity(Product.class);

        //sqlQuery.setParameter("id",49);
        sqlQuery.setParameter(0,49);

        List<Product> list = sqlQuery.list();
        showList(list);
    }


    private void showList(List<Product> productList) {
        for(Product product : productList) {
            System.out.println(product);
        }
    }

}
