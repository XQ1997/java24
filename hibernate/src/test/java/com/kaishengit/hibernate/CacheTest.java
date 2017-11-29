package com.kaishengit.hibernate;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Test;

public class CacheTest {

    @Test
    public void firstLevelCache() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,10);
        //session.clear();
        session.evict(user);
        System.out.println(session.contains(user));
        user = (User) session.get(User.class,10);
        user = (User) session.get(User.class,10);


        session.getTransaction().commit();
    }


    @Test
    public void secondLevelCache() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        User user = (User) session.get(User.class,10);

        session.getTransaction().commit();

        Cache cache = HibernateUtil.getSessionFactory().getCache();
        //cache.evictEntityRegion(User.class);
        cache.evictAllRegions();


        Session session2 = HibernateUtil.getSession();
        session2.getTransaction().begin();

        user = (User) session2.get(User.class,10);
        System.out.println(user.getUserName());

        session2.getTransaction().commit();
    }
}
