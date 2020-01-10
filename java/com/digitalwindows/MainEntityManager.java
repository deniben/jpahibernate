package com.digitalwindows;


import com.digitalwindows.configuration.HibernateConfiguration;
import com.digitalwindows.entities.Course;
import com.digitalwindows.entities.Student;
import org.hibernate.Session;

public class MainEntityManager {
    public static void main(String[] args) {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()){
            // #1
            System.out.println("------------------ #1 ------------------");

            session.beginTransaction();
            Course course1 = new Course("Course-1");
            Course course2 = new Course("Course-2");
            Course course3 = new Course("Course-3");
            Course[] courses = {course1, course2, course3};

            for (int i = 0; i < 15; i++) {
                Student student = new Student("FirstName-" + i, "LastName"+i, "EMAIL:"+i+"@gmail.com");
                Course course = courses[i % courses.length];
                course.addStudent(student);
            }


            for (Course c : courses)
                session.save(c);

            session.getTransaction().commit();
        }  catch (Exception ex) {
       ex.printStackTrace();
    }


    }
}
