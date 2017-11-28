package com.kaishengit.hibernate;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Teacher;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class ManyToManyTest {

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
    public void findStudent() {
        Student student = (Student) session.get(Student.class,5);
        System.out.println(student.getStudentName());

        Set<Teacher> teacherSet = student.getTeacherSet();
        for(Teacher teacher : teacherSet) {
            System.out.println(teacher.getId() + " -> " + teacher.getTeacherName());
        }
    }

    @Test
    public void save() {
        Teacher teacher = new Teacher();
        teacher.setTeacherName("newTeacher2");

        session.save(teacher);

        Student student = (Student) session.get(Student.class,5);

        /*Set<Teacher> teacherSet = new HashSet<Teacher>();
        teacherSet.add(teacher);*/
        Set<Teacher> teacherSet = student.getTeacherSet();
        teacherSet.add(teacher);

        student.setTeacherSet(teacherSet);
    }

    @Test
    public void save2() {
        Student student = new Student();
        student.setStudentName("SSSS-1");

        Student student2 = new Student();
        student2.setStudentName("SSSS-2");

        Teacher teacher = new Teacher();
        teacher.setTeacherName("TTTT-1");

        Teacher teacher2 = new Teacher();
        teacher2.setTeacherName("TTTT-2");

        Set<Teacher> teacherSet = new HashSet<Teacher>();
        teacherSet.add(teacher);
        teacherSet.add(teacher2);

        student.setTeacherSet(teacherSet);
        student2.setTeacherSet(teacherSet);

        //最佳实践：让其中一端维护关系，先存不维护关系的对象，再存维护关系的对象

        /*Set<Student> studentSet = new HashSet<Student>();
        studentSet.add(student);
        studentSet.add(student2);

        teacher.setStudentSet(studentSet);
        teacher2.setStudentSet(studentSet);*/

        session.save(teacher);
        session.save(teacher2);
        session.save(student);
        session.save(student2);



    }


}
