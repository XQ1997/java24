package com.kaishengit.hibernate;

import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class OneToManyTest {

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
    public void findAddress() {
        Address address = (Address) session.get(Address.class,2);
        System.out.println(address.getCityName() + " -> " + address.getAddress());

        User user = address.getUser();
        System.out.println(user.getUserName() + " -> " + user.getId());
    }

    @Test
    public void findUser() {
        User user = (User) session.get(User.class,1);
        System.out.println(user.getUserName());

        Set<Address> addressSet = user.getAddressSet();
        for(Address address : addressSet) {
            System.out.println(address.getId() + " -> " + address.getCityName() + " -> " + address.getAddress());
        }


        /*Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("user.id",user.getId()));
        List<Address> addressList = criteria.list();
        for(Address address : addressList) {
            System.out.println(address.getCityName() + " -> " + address.getAddress());
        }*/

    }


}
