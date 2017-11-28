package com.kaishengit.hibernate;

import com.kaishengit.pojo.Product;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CriteriaTest {

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
        Criteria criteria = session.createCriteria(Product.class);
        List<Product> productList = criteria.list();
        showList(productList);
    }

    @Test
    public void findBy() {
        Criteria criteria = session.createCriteria(Product.class);

        //criteria.add(Restrictions.eq("id",49));
        //where product_name like '巧克力'
        //criteria.add(Restrictions.like("productName","巧克力", MatchMode.ANYWHERE));
        //criteria.add(Restrictions.eq("productInventory",250));
        //criteria.add(Restrictions.in("productInventory", Arrays.asList(100,150)));

        Criterion productNameCriterion = Restrictions.eq("productName","巧克力");
        Criterion productInventoryCriterion = Restrictions.eq("productInventory",100);

        //criteria.add(Restrictions.or(productInventoryCriterion,productNameCriterion));


        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(productNameCriterion);
        disjunction.add(productInventoryCriterion);

        criteria.add(disjunction);


        List<Product> productList = criteria.list();
        showList(productList);
    }

    @Test
    public void findAndOrder() {
        Criteria criteria = session.createCriteria(Product.class);

        criteria.addOrder(Order.desc("productInventory"));
        criteria.addOrder(Order.asc("productName"));

        List<Product> productList = criteria.list();
        showList(productList);
    }

    @Test
    public void page() {
        Criteria criteria = session.createCriteria(Product.class);

        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(0);
        criteria.setMaxResults(5);

        List<Product> productList = criteria.list();
        showList(productList);
    }

    @Test
    public void count() {
        Criteria criteria = session.createCriteria(Product.class);

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.avg("productInventory"));

        criteria.setProjection(projectionList);

        Object[] data = (Object[]) criteria.uniqueResult();
        System.out.println("RowCount -> " + data[0]);
        System.out.println("AVG -> " + data[1]);


        //count(*)
        //criteria.setProjection(Projections.rowCount());
        //criteria.setProjection(Projections.max("productInventory"));
        //Integer count = (Integer) criteria.uniqueResult();
        //System.out.println("count : " + count);
    }




    private void showList(List<Product> productList) {
        for(Product product : productList) {
            System.out.println(product);
        }
    }


}
