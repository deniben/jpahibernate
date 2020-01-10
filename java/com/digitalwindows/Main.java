package com.digitalwindows;

import com.digitalwindows.configuration.HibernateConfiguration;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateConfiguration.getSessionFactory().openSession();
        session.beginTransaction();

        // Check database version
        String sql = "select version()";

        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.err.println("Version:" + result);

        session.getTransaction().commit();
        session.close();


        HibernateConfiguration.shutdown();
    }
}
