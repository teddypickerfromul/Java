

//TODO: добавить статистику и выгрузку данных

package com.hibernatedb.helpers;

import com.hibernatedb.entities.Product;
import com.hibernatedb.entities.User;
import com.hibernatedb.entities.UserIncome;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

//TODO: делать ли update при наличии записи

public class UserIncomeHelper {
   public static UserIncome createIfNotExists(Session session, User user, Product product, int products_count, String datetime) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw  new IllegalArgumentException("user is nulls");
        if (product == null) 
            throw new IllegalArgumentException("product is null");
        if (datetime == null)
            throw new IllegalArgumentException("datetime is null");
        
        UserIncome result = getByAllParametersWithoutId(session, user, product, products_count, datetime);
        if (result == null) {
                result = new UserIncome();
            	result.setUser(user);
                result.setProduct(product);
                result.setDatetime(datetime);
                result.setProducts_count(products_count);
                result.setOverral();
                
                // session.saveOrUpdate(result);
        }    
        return result;
   }
    
    public static UserIncome getById(Session session, long id) {
	if (session == null)
		throw new IllegalArgumentException("session is null");

		Criteria criteria = session.createCriteria(UserIncome.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.setMaxResults(1);

		return (UserIncome) criteria.uniqueResult();
    }
    
    public static List getAllByUser(Session session, User user){
        if (session == null) 
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("User is null");
        
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.eq("user",user));
        
        return criteria.list();
    }
    
    public static UserIncome getByAllParametersWithoutId(Session session, User user, Product product, int products_count, String datetime) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw  new IllegalArgumentException("user is nulls");
        if (product == null) 
            throw new IllegalArgumentException("product is null");
        if (datetime == null)
            throw new IllegalArgumentException("datetime is null");
        
        Criteria criteria = session.createCriteria(UserIncomeHelper.class);
        criteria.add(Restrictions.eq("user", user));
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.eq("datetime", datetime));
        criteria.add(Restrictions.eq("product_count", products_count));
        criteria.setMaxResults(1);
        
        return (UserIncome) criteria.uniqueResult();
    }    
    
    public static UserIncome getByAllParameters(Session session, long id, User user, Product product, int products_count, String datetime) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw  new IllegalArgumentException("user is nulls");
        if (product == null) 
            throw new IllegalArgumentException("product is null");
        if (datetime == null)
            throw new IllegalArgumentException("datetime is null");
        
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("user", user));
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.eq("datetime", datetime));
        criteria.add(Restrictions.eq("product_count", products_count));
        criteria.setMaxResults(1);
        
        return (UserIncome) criteria.uniqueResult();
    }
    
   public static List getAllByDatetime(Session session, String datetime) {
       if (session == null) 
           throw new IllegalArgumentException("session is null");
       if (datetime == null) 
           throw new IllegalArgumentException("datetime is null");
       
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.eq("datetime", datetime));
       
       return criteria.list();
   }
   
   public static List getAllByProduct(Session session, Product product) {
       if (session == null) 
           throw new IllegalArgumentException("session is null");
       if (product == null) 
           throw new IllegalArgumentException("product is null");
       
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.eq("product", product));
       
       return criteria.list();
   }
   
   public static List getAllByUserAndDatetime(Session session, User user,String datetime) {
       if (session == null) 
           throw new IllegalArgumentException("session is null");
       if (datetime == null) 
           throw new IllegalArgumentException("datetime is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");
       
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.eq("user", user));
       criteria.add(Restrictions.eq("datetime",datetime));
               
       return criteria.list();       
   }
   
   public static List getAllByUserAndProduct(Session session, User user, Product product) {
       if (session == null) 
           throw new IllegalArgumentException("session is null");
       if (product == null) 
           throw new IllegalArgumentException("product is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");       
       
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.eq("user",user));
       criteria.add(Restrictions.eq("product", product));
       
       return criteria.list();        
   }
   
   public static List getAllByYear(Session session, String year) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (year == null)
           throw new IllegalArgumentException("year is null");
       
       String matchString = year + "-"; 
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       
       return criteria.list();
   }
   
   public static List getAllByMounth(Session session, String mounth) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (mounth == null)
           throw new IllegalArgumentException("mounth is null");
       
       String matchString = "-"+mounth+"-";
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       
       return criteria.list();
   }
   
   public static List getAllByDay(Session session, String day) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (day == null)
           throw new IllegalArgumentException("day is null");
       
       String matchString = "-"+day;
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       
       return criteria.list();
   } 
           
   public static List getAllByHour(Session session, String hour) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (hour == null)
           throw new IllegalArgumentException("hour is null");
       
       String matchString = " "+hour+":";
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       
       return criteria.list();
   }
   
   public static List getAllByMinute(Session session, String minute) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (minute == null)
           throw new IllegalArgumentException("minute is null");
       
       String matchString = ":"+minute;
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       
       return criteria.list();
   }
   
   public static List getAllByUserAndYear(Session session,User user, String year) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (year == null)
           throw new IllegalArgumentException("year is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");
       
       String matchString = year + "-"; 
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       criteria.add(Restrictions.eq("user", user));
       
       return criteria.list();
   }
   
   public static List getAllByUserAndMounth(Session session, User user, String mounth) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (mounth == null)
           throw new IllegalArgumentException("mounth is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");
       
       String matchString = "-"+mounth+"-";
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       criteria.add(Restrictions.eq("user",user));
       
       return criteria.list();
   }
   
   public static List getAllByUserAndDay(Session session, User user, String day) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (day == null)
           throw new IllegalArgumentException("mounth is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");
       
       String matchString = "-"+day;
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       criteria.add(Restrictions.eq("user",user));
       
       return criteria.list();
   }
   
   public static List getAllUserAndByHour(Session session, User user, String hour) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (hour == null)
           throw new IllegalArgumentException("hour is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");       
       
       String matchString = " "+hour+":";
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       criteria.add(Restrictions.eq("user",user));
       
       return criteria.list();
   }
   
   public static List getAllByUserAndMinute(Session session, User user, String minute) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (minute == null)
           throw new IllegalArgumentException("minute is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");        
       
       String matchString = ":"+minute;
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       criteria.add(Restrictions.eq("user",user));
       
       return criteria.list();
   }
   
   //TODO: проверка
      
   public static UserIncome changeByAllParams(Session session, long id, User user, Product product, int products_count, String datetime) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw  new IllegalArgumentException("user is nulls");
        if (product == null) 
            throw new IllegalArgumentException("product is null");
        if (datetime == null)
            throw new IllegalArgumentException("datetime is null");
        
        UserIncome result = getByAllParameters(session, id, user, product, products_count, datetime); 
        
        if (result != null) {
            	result.setUser(user);
                result.setProduct(product);
                result.setDatetime(datetime);
                result.setProducts_count(products_count);
                result.setOverral();
                session.update(result);
        }        
        return result;
   }
   
   public static UserIncome changeProductById(Session session, long id, Product product) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (product == null) 
            throw new IllegalArgumentException("product is null");
       
        UserIncome result = getById(session, id);
        if (result != null) {
            result.setProduct(product);
            result.setOverral();
            session.update(result);
        }
        return result;
   }
   
   public static UserIncome changeProductCountById(Session session, long id, int products_count) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
       
        UserIncome result = getById(session, id);
        if (result != null) {
            result.setProducts_count(products_count);
            result.setOverral();
            session.update(result);
        }
        return result;
   }
   
   public static void deleteByAllParametersWithoutId(Session session, User user, Product product, int products_count, String datetime) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw  new IllegalArgumentException("user is nulls");
        if (product == null) 
            throw new IllegalArgumentException("product is null");
        if (datetime == null)
            throw new IllegalArgumentException("datetime is null");
        
        UserIncome result = getByAllParametersWithoutId(session, user, product, products_count, datetime);
        session.delete(result);
    }  
   
   public static void deleteByAllParameters(Session session, long id, User user, Product product, int products_count, String datetime) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw  new IllegalArgumentException("user is nulls");
        if (product == null) 
            throw new IllegalArgumentException("product is null");
        if (datetime == null)
            throw new IllegalArgumentException("datetime is null");
        
        UserIncome result = getByAllParameters(session, id, user, product, products_count, datetime);
        session.delete(result);
    }
   
   public static double getOverralSumByUser(Session session, User user) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        
        double result = 0;
        
        List userList = getAllByUser(session, user);
        Iterator <UserIncome> i = userList.iterator();
        while (i.hasNext()) {
            result += i.next().getOverral();
        }
        return result;
   }
   
   public static double getOverralSumByUserAndProduct(Session session, User user, Product product) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        if (product == null)
            throw new IllegalArgumentException("product is null");
        
        double result = 0;
        
        List userList = getAllByUserAndProduct(session, user, product);
        Iterator <UserIncome> i = userList.iterator();
        while (i.hasNext()) {
            result += i.next().getOverral();
        }
        return result;
   }
   
   public static double getOverralSumByUserAndYear(Session session, User user, String year) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        
        double result = 0;
        List userList = getAllByUserAndYear(session, user, year);
        Iterator <UserIncome> i = userList.iterator();
        while (i.hasNext()) {
            result += i.next().getOverral();
        }
        return result;
   }

   public static double getOverralSumByUserAndMounth(Session session, User user, String mounth) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        
        double result = 0;
        List userList = getAllByUserAndMounth(session, user, mounth);
        Iterator <UserIncome> i = userList.iterator();
        while (i.hasNext()) {
            result += i.next().getOverral();
        }
        return result;
   }   
   
   public static double getOverralSumByUserAndDay(Session session, User user, String day) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        
        double result = 0;
        List userList = getAllByUserAndDay(session, user, day);
        Iterator <UserIncome> i = userList.iterator();
        while (i.hasNext()) {
            result += i.next().getOverral();
        }
        return result;
   }
      
   public static UserIncome getMaxOverralByUser(Session session, User user) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.eq("user", user))
        .setProjection(Projections.max("overral"));
        criteria.setMaxResults(1);
        
        return  (UserIncome) criteria.uniqueResult();      
   }
   
    public static UserIncome getMinOverralByUser(Session session, User user) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.eq("user", user))
                .setProjection(Projections.min("overral"));
        criteria.setMaxResults(1);

        return (UserIncome) criteria.uniqueResult();
    }
    
    //TODO: убрать это
    
    public static UserIncome getAvgOverralByUser(Session session, User user) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.eq("user", user))
                .setProjection(Projections.avg("overral"));
        criteria.setMaxResults(1);

        return (UserIncome) criteria.uniqueResult();
    }
    
   public static UserIncome getMaxOverralByUserAndYear(Session session, User user, String year) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        if (year == null)
            throw new IllegalArgumentException("year is null");
        
        String matchString = year + "-"; 
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("user", user));
        criteria.setProjection(Projections.max("overral"));
        criteria.setMaxResults(1);
        
        return  (UserIncome) criteria.uniqueResult();      
   }
   
   public static UserIncome getMaxOverralByUserAndMounth(Session session, User user, String mounth) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        if (mounth == null)
            throw new IllegalArgumentException("mounth is null");
        
        String matchString = "-"+mounth+"-";
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("user", user));
        criteria.setProjection(Projections.max("overral"));
        criteria.setMaxResults(1);
        
        return  (UserIncome) criteria.uniqueResult();      
   }
   
   public static UserIncome getMaxOverralByUserAndDay(Session session, User user, String day) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        if (day == null)
            throw new IllegalArgumentException("day is null");
           
        String matchString = "-"+day;
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("user", user));
        criteria.setProjection(Projections.max("overral"));
        criteria.setMaxResults(1);
        
        return  (UserIncome) criteria.uniqueResult();      
   }   
   
   public static UserIncome getMaxOverralWithUserAndHour(Session session, User user, String hour){
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (user == null) 
           throw new IllegalArgumentException("user is null");
       if (hour == null)
           throw new IllegalArgumentException("hour is null");
                   
       String matchString = " "+hour+":";
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       criteria.add(Restrictions.eq("user",user));
       criteria.setProjection(Projections.max("overral"));
       criteria.setMaxResults(1);
       
       return (UserIncome) criteria.uniqueResult();
   }
   
   public static UserIncome getMinOverralByUserAndYear(Session session, User user, String year) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        if (year == null)
            throw new IllegalArgumentException("year is null");
        
        String matchString = year + "-"; 
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("user", user));
        criteria.setProjection(Projections.min("overral"));
        criteria.setMaxResults(1);
        
        return  (UserIncome) criteria.uniqueResult();      
   }   

   public static UserIncome getMinOverralByUserAndMounth(Session session, User user, String mounth) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        if (mounth == null)
            throw new IllegalArgumentException("mounth is null");
        
        String matchString = "-"+mounth+"-";
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("user", user));
        criteria.setProjection(Projections.min("overral"));
        criteria.setMaxResults(1);
        
        return  (UserIncome) criteria.uniqueResult();      
   }
   
   public static UserIncome getMinOverralByUserAndDay(Session session, User user, String day) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        if (user == null) 
            throw new IllegalArgumentException("user is null");
        if (day == null)
            throw new IllegalArgumentException("day is null");
           
        String matchString = "-"+day;
        Criteria criteria = session.createCriteria(UserIncome.class);
        criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("user", user));
        criteria.setProjection(Projections.min("overral"));
        criteria.setMaxResults(1);
        
        return  (UserIncome) criteria.uniqueResult();      
   }   
   
   public static UserIncome getMinOverralWithUserAndHour(Session session, User user, String hour){
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (user == null) 
           throw new IllegalArgumentException("user is null");
       if (hour == null)
           throw new IllegalArgumentException("hour is null");
                   
       String matchString = " "+hour+":";
       Criteria criteria = session.createCriteria(UserIncome.class);
       criteria.add(Restrictions.like("datetime", matchString, MatchMode.ANYWHERE));
       criteria.add(Restrictions.eq("user",user));
       criteria.setProjection(Projections.min("overral"));
       criteria.setMaxResults(1);
       
       return (UserIncome) criteria.uniqueResult();
   }   
   
   //TODO сделать одним запросом ?
   
   public static void deleteAllByUser(Session session, User user) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (user == null)
           throw new IllegalArgumentException("user is null");
       
       List result = getAllByUser(session, user);
       Iterator <UserIncome> i = result.iterator();
       while(i.hasNext()) {
           UserIncome item = (UserIncome) i.next();
           session.delete(item);
       }
   }
   
   public static void deleteAllByProduct(Session session, Product product) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (product == null)
           throw new IllegalArgumentException("product is null");
       
       List result = getAllByProduct(session, product);
       Iterator <UserIncome> i = result.iterator();
       while(i.hasNext()) {
           UserIncome item = (UserIncome) i.next();
           session.delete(item);
       }
   }
           
   public static void deleteAllByYear(Session session, String year) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (year == null)
           throw new IllegalArgumentException("year is null");
       
       List result = getAllByYear(session, year);
       Iterator <UserIncome> i = result.iterator();
       while(i.hasNext()) {
           UserIncome item = (UserIncome) i.next();
           session.delete(item);
       }
   }
   
   public static void deleteAllByMounth(Session session, String mounth) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (mounth == null)
           throw new IllegalArgumentException("mounth is null");
       
       List result = getAllByMounth(session, mounth);
       Iterator <UserIncome> i = result.iterator();
       while(i.hasNext()) {
           UserIncome item = (UserIncome) i.next();
           session.delete(item);
       }
   }
   
   public static void deleteAllByDay(Session session, String day) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (day == null)
           throw new IllegalArgumentException("day is null");
       
       List result = getAllByDay(session, day);
       Iterator <UserIncome> i = result.iterator();
       while(i.hasNext()) {
           UserIncome item = (UserIncome) i.next();
           session.delete(item);
       }
   }   
   
   public static void deleteAllByHour(Session session, String hour) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (hour == null)
           throw new IllegalArgumentException("hour is null");
       
       List result = getAllByHour(session, hour);
       Iterator <UserIncome> i = result.iterator();
       while(i.hasNext()) {
           UserIncome item = (UserIncome) i.next();
           session.delete(item);
       }
   }
   
   public static void deleteAllByMinute(Session session, String minute) {
       if (session == null)
           throw new IllegalArgumentException("session is null");
       if (minute == null)
           throw new IllegalArgumentException("minute is null");
       
       List result = getAllByMinute(session, minute);
       Iterator <UserIncome> i = result.iterator();
       while(i.hasNext()) {
           UserIncome item = (UserIncome) i.next();
           session.delete(item);
       }
   }   
   
   public static UserIncome changeProductsCountById(Session session, long id, int product_count) {
        if (session == null)
            throw new IllegalArgumentException("session is null");
        
        UserIncome result = getById(session, id);
        if (result != null) {
            result.setProducts_count(product_count);
            result.setOverral();
            session.update(result);
        }
        return result;
   }
}
