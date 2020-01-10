package com.digitalwindows;

import com.digitalwindows.entities.Course;
import com.digitalwindows.entities.Student;
import org.hibernate.Session;

public class MainWithoutCFG {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            // #1
            System.out.println("------------------ #1 ------------------");

            session.beginTransaction();
            Course course4 = new Course("Course-4");
            Course course5 = new Course("Course-5");
            Course course6 = new Course("Course-6");
            Course[] courses = {course4, course5, course6};

            for (int i = 0; i < 15; i++) {
                Student student = new Student("FirstName-1" + i, "LastName1"+i, "EMAIL:1"+i+"@gmail.com");
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
