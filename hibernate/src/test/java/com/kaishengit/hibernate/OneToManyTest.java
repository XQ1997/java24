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

import java.util.HashSet;
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

        /*
        Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("user.id",user.getId()));
        List<Address> addressList = criteria.list();
        for(Address address : addressList) {
            System.out.println(address.getCityName() + " -> " + address.getAddress());
        }*/

    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUserName("赵丽丽");
        session.saveOrUpdate(user);
    }

    @Test
    public void saveAddress() {
        User user = (User) session.get(User.class,6);

        Address address = new Address();
        address.setCityName("郑州");
        address.setAddress("郑开大道110号");
        address.setUser(user);

        session.save(address);
    }

    @Test
    public void save() {
        User user = new User();
        user.setUserName("Jackson");

        Address address = new Address();
        address.setCityName("纽约");
        address.setAddress("第五大道");
        address.setUser(user);

        Address address2 = new Address();
        address2.setCityName("洛杉矶");
        address2.setAddress("莫宁路110号");
        address2.setUser(user);

        /*Set<Address> addressSet = new HashSet<Address>();
        addressSet.add(address);
        addressSet.add(address2);

        user.setAddressSet(addressSet);*/


        //最佳实践：先存一，再存多，只需要让多的一端去维护关系
        session.save(user);
        session.save(address);
        session.save(address2);
    }

    @Test
    public void delete() {
        /*Criteria criteria = session.createCriteria(Address.class);
        criteria.add(Restrictions.eq("user.id",6));
        List<Address> addressList =  criteria.list();
        for(Address address : addressList) {
            session.delete(address);
        }*/

        User user = (User) session.get(User.class,1);
        session.delete(user);
    }

    @Test
    public void delete2() {
        User user = (User) session.get(User.class,5);
        session.delete(user);
    }


}
