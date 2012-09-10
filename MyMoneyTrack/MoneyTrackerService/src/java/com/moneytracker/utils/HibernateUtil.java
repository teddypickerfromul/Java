
package com.moneytracker.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;


public class HibernateUtil {

      private static final SessionFactory sessionFactory;

      static {
          try {
              sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
          } catch (Throwable ex) {
              System.err.println("Initial SessionFactory creation failed." + ex);
              throw new ExceptionInInitializerError(ex);
          }
      }

      public static SessionFactory getSessionFactory() {
          return sessionFactory;
      }

      public static Session getCurrentSession() {
          return sessionFactory.getCurrentSession();
      }

}