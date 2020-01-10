package com.digitalwindows;
import com.digitalwindows.entities.Course;
import com.digitalwindows.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        try {
            Properties prop= new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb?useLegacyDatetimeCode=false&serverTimezone=UTC");
            prop.setProperty("hibernate.connection.username", "root");
            prop.setProperty("hibernate.connection.password", "root");
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.show_sql", "true");
            prop.setProperty("hibernate.hbm2ddl.auto", "update");

            sessionFactory = new Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Course.class)
                    .buildSessionFactory();
            Session session = sessionFactory.openSession();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }
}
