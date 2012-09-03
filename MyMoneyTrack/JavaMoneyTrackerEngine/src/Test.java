
import com.hibernatedb.entities.*;
import com.hibernatedb.helpers.*;
import com.hibernatedb.utils.AppSettings;
import com.hibernatedb.utils.CustomFormattedDateTime;
import com.hibernatedb.utils.Validator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Test {

    public static final Log logger = LogFactory.getLog(Test.class);
    
	public static void main(String[] args) {
            		        
	        try {
	            SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
                    Session session = sessionFactory.openSession();
                    try {
                                                
                        session.beginTransaction();
                        User test = UserHelper.createIfNotExists(session, "John", "Griffith", "mail@mail.ru");
                        session.saveOrUpdate(test);
                        User test2 = UserHelper.createIfNotExists(session, "John2", "Griffith", "2mail@mail.ru");
                        session.saveOrUpdate(test2);
                        
                        User out = UserHelper.getByMail(session, "mail@mail.ru");
                                                
                        Validator val = new Validator();
                                               
                        User registeredUser = UserHelper.register(val.getAppSettingsObject(), session, "rightuser", "rightpassword", "rightuser@gmail.com");
                        session.saveOrUpdate(registeredUser);
                        logger.info(registeredUser.toString());
                        
                        User found = UserHelper.getById(session, 2);
                        logger.info(found.toString());
                        
                        long loggeduserId = UserHelper.login(session, "rightuser", "rightpassword");
                        logger.info("Id of logged user is "+loggeduserId);
                        
                        Product product = ProductHelper.createIfNotExists(session, "Product 1", "Some description", 60.50);
                        session.saveOrUpdate(product);
                        logger.info(product.toString());
                        
                        Product product2 = ProductHelper.createIfNotExists(session, "Product 2", "Another description", 290.50);
                        session.saveOrUpdate(product2);
                        logger.info(product.toString());                        
                        
                        //ProductHelper.deleteById(session, product2.getId());
                        
                        Product product3 = ProductHelper.getById(session, 1);
                        ProductHelper.changePriceById(session, product3.getId(), 22);
                        
                        Product maxpriceProduct = ProductHelper.getProductWithMaxPrice(session);                       
                        logger.info(maxpriceProduct.toString());
                        
                        List results = ProductHelper.getByPrice(session, 290.50);
                        logger.info(results);
                        
                        CustomFormattedDateTime datetime = new CustomFormattedDateTime() ;

                        UserOutlay useroutlay = UserOutlayHelper.createIfNotExists(session, UserHelper.getById(session, 1), ProductHelper.getById(session, 1), 777, datetime.now());
                        session.saveOrUpdate(useroutlay);
                        
                        UserOutlay useroutlay2 = UserOutlayHelper.createIfNotExists(session, UserHelper.getById(session, 2), ProductHelper.getById(session, 2), 777, datetime.now());
                        session.saveOrUpdate(useroutlay2);
                        
                        UserOutlay useroutlay3 = UserOutlayHelper.createIfNotExists(session, UserHelper.getById(session, 1), ProductHelper.getById(session, 2), 777, datetime.now());
                        session.saveOrUpdate(useroutlay3);                        
                        
                        logger.info(useroutlay.toString());
                        
                        List results2 = UserOutlayHelper.getAllByUserAndYear(session, UserHelper.getById(session, 1), "2012");
                        logger.info(results2.toString());                       
                        logger.info(UserOutlayHelper.getOverralSumByUser(session, UserHelper.getById(session, 1)));
                        
                        UserIncome income1 = UserIncomeHelper.createIfNotExists(session, UserHelper.getById(session, 1), ProductHelper.getById(session, 1), 2, datetime.now());
                        logger.info(income1);
                        session.saveOrUpdate(income1);
                        
                        UserBudget testBudget = UserBudgetHelper.createIfNotExists(session, UserHelper.getById(session, 1), "Some budget", "Some description", 100000);
                        logger.info(testBudget.toString());
                                      
                        session.getTransaction().commit();
                        
                    }
                    catch (HibernateException ex) {
                        if (session.getTransaction().isActive()) {
                            session.getTransaction().rollback();
                        }
                        ex.printStackTrace();
                    }
                    finally {
                        if (session != null) {
                            session.close();
                        }
                    }
                }
	        catch (Throwable ex)  {
	            logger.error("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	}
}