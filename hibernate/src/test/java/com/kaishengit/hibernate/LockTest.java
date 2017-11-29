package com.kaishengit.hibernate;

import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LockTest {

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
    public void save() {
        Customer customer = new Customer();
        customer.setName("杰西卡");
        session.save(customer);
    }

    @Test
    public void update() throws InterruptedException {
        Customer customer = (Customer) session.get(Customer.class,"402807816005843001600584325d0000");
        customer.setName("杰西卡-5");
        //Thread.sleep(10000);
    }

    @Test
    public void test() throws InterruptedException {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,10, LockOptions.UPGRADE);


        /*Thread thread = new Thread(new Runnable() {
            public void run() {
                Session session2 = HibernateUtil.getSession();
                session2.getTransaction().begin();

                User user2 = (User) session2.get(User.class,10);
                user2.setUserName("jackson-5");

                session2.getTransaction().commit();
            }
        });
        thread.start();*/

        user.setUserName("jaskson-9");

        Thread.sleep(10000);

        session.getTransaction().commit();
    }






}
