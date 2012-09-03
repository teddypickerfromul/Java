
package com.hibernatedb.helpers;

import com.hibernatedb.entities.Product;
import com.hibernatedb.entities.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ProductHelper {
    	public static Product createIfNotExists(Session session, String name,
			String description, Double cost) {
                Product result = getByName(session, name);
		if (result == null) {
                        if(cost >= 0.0) {  
                            result = new Product();
                            result.setName(name);
                            result.setDescription(description);
                            result.setCost(cost);
                        }
                        else {
                            throw new IllegalArgumentException("invalid price: it must be >= 0");
                        }
		}
		return result;
	}
        
        
        public static Product getByName(Session session, String name) {
            	if (session == null)
			throw new IllegalArgumentException("session is null");
		if (name == null)
			throw new IllegalArgumentException("name is null");
                
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.setMaxResults(1);
                
                return (Product) criteria.uniqueResult();
        }
        
        public static Product getById(Session session, long id) {
            	if (session == null)
			throw new IllegalArgumentException("session is null");
                
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setMaxResults(1);
                
                return (Product) criteria.uniqueResult();            
        }
        
        public static List getByPrice(Session session, double price) {
            	if (session == null)
			throw new IllegalArgumentException("session is null");
                
		Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("cost", price));
                
                return criteria.list();            
        }
        
        public static void deleteById(Session session, long id) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                
                Product result = getById(session, id);
                //session.delete(result);
        }
        
        public static void deleteByName(Session session, String name) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                if (name == null)
			throw new IllegalArgumentException("name is null");
                
                Product result = getByName(session, name);
                session.delete(result);
                
                //session.getTransaction().commit();
        }
        
        public static void changeDescById(Session session, long id, String desc) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                if (desc == null)
			throw new IllegalArgumentException("description is null");
                
                Product result = getById(session, id);
                result.setDescription(desc);
                session.update(result);
                
                //session.getTransaction().commit();
        }
        
        public static void changeDescByName(Session session, String name, String desc) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                if (name == null)
			throw new IllegalArgumentException("name is null");
                
                Product result = getByName(session, name);
                result.setName(name);
                session.update(result);
                
                //session.getTransaction().commit();
        }
        
        public static void changeNameById(Session session, long id, String name) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                if (name == null)
			throw new IllegalArgumentException("description is null");
                
                Product result = getById(session, id);
                result.setName(name);
                session.update(result);
                
                //session.getTransaction().commit();
        }
        
        public static void changePriceById(Session session, long id, double cost) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                        
                if(cost >= 0.0) {
                    Product result = getById(session, id);
                    result.setCost(cost);
                    session.update(result);
                
                    //session.getTransaction().commit();
                } else {
                    throw new IllegalArgumentException("invalid price: it must be >= 0");
                }
        }
        
        public static void changePriceByName(Session session, String name, double cost) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                if (name == null)
			throw new IllegalArgumentException("name is null");                
                        
                if(cost >= 0.0) {
                    Product result = getByName(session, name);
                    result.setCost(cost);
                    session.update(result);
                
                    //session.getTransaction().commit();
                } else {
                    throw new IllegalArgumentException("invalid price: it must be >= 0");
                }
        }
        
        //TODO: сделать одним запросом
        
        public static Product getProductWithMaxPrice(Session session) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                
                
                Criteria pricecriteria = session.createCriteria(Product.class);
                pricecriteria.setProjection(Projections.max("cost"));
                Double maxPrice = (Double) pricecriteria.uniqueResult();
                
                Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("cost", maxPrice));
		criteria.setMaxResults(1);
                
                return (Product) criteria.uniqueResult(); 
        }
        
        //TODO: сделать одним запросом
        
        public static Product getProductWithMinPrice(Session session) {
                if (session == null)
			throw new IllegalArgumentException("session is null");
                
                
                Criteria pricecriteria = session.createCriteria(Product.class);
                pricecriteria.setProjection(Projections.min("cost"));
                Double maxPrice = (Double) pricecriteria.uniqueResult();
                
                Criteria criteria = session.createCriteria(Product.class);
		criteria.add(Restrictions.eq("cost", maxPrice));
		criteria.setMaxResults(1);
                
                return (Product) criteria.uniqueResult(); 
        }        
        
}
