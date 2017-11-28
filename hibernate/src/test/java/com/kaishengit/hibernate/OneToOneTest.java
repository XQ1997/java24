package com.kaishengit.hibernate;

import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Person;
import com.kaishengit.pojo.Post;
import com.kaishengit.pojo.PostContent;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OneToOneTest {

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
        Person person = new Person();
        person.setPersonName("王老六");

        Card card = new Card();
        card.setCardNum("889977");

        card.setPerson(person);

        //最佳实践：先存主数据，再存从数据
        session.save(person);
        session.save(card);
    }

    @Test
    public void find() {
        Person person = (Person) session.get(Person.class,4);
        System.out.println(person.getPersonName());
        System.out.println(person.getCard().getCardNum());
    }

    @Test
    public void delete() {
        Person person = (Person) session.get(Person.class,5);
        session.delete(person);
    }

    @Test
    public void savePost() {
        Post post = new Post();
        post.setTitle("白雪公主与猎人");

        PostContent postContent = new PostContent();
        postContent.setContent("势力扩大飞机拉萨看得见flask的发送旅客的飞机ask两地分居阿拉山口地方");
        postContent.setPost(post);

        post.setPostContent(postContent);

        session.save(post);
        session.save(postContent);
    }

    @Test
    public void findByPost() {
        Post post = (Post) session.get(Post.class,5);
        System.out.println(post.getTitle());
        System.out.println(post.getPostContent().getContent());
    }

}
