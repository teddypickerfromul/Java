package com.moneytracker;

import com.moneytracker.hibernatedb.entities.Product;
import com.moneytracker.hibernatedb.entities.User;
import com.moneytracker.hibernatedb.entities.UserBudget;
import com.moneytracker.hibernatedb.entities.UserIncome;
import com.moneytracker.hibernatedb.entities.UserOutlay;
import com.moneytracker.hibernatedb.entities.helpers.ProductHelper;
import com.moneytracker.hibernatedb.entities.helpers.UserBudgetHelper;
import com.moneytracker.hibernatedb.entities.helpers.UserHelper;
import com.moneytracker.hibernatedb.entities.helpers.UserIncomeHelper;
import com.moneytracker.hibernatedb.entities.helpers.UserOutlayHelper;

import com.moneytracker.utils.LoginErrorException;
import com.moneytracker.utils.RegErrorException;
import com.moneytracker.utils.Validator;
import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.ini4j.InvalidFileFormatException;

@WebService()
public class MoneyTracker {

    public static final Log logger = LogFactory.getLog(MoneyTracker.class);
    private Session session;
    private SessionFactory sessionFactory;
    private Validator val;

    public MoneyTracker() {
        super();
        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = sessionFactory.openSession();
            this.val = new Validator();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Веб-методы для сущности User
    /*----------------------------------------------------------------------------------------------------------------------------------------------*/
    @WebMethod(operationName = "registerUser")
    public User registerUser(String login, String pass, String email) throws InvalidFileFormatException, IOException, RegErrorException {
        User registeredUser;
        registeredUser = null;
        try {
            session.beginTransaction();
            registeredUser = UserHelper.register(this.val.getAppSettingsObject(), this.session, login, pass, email);
            //TODO: убрать этот костыль
            if (registeredUser != null) {
                session.saveOrUpdate(registeredUser);
            }
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return registeredUser;
    }

    @WebMethod(operationName = "loginUser")
    public long loginUser(String login, String password) throws LoginErrorException {
        long result;
        result = -1;
        try {
            session.beginTransaction();
            result = UserHelper.login(this.session, login, password);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getUserBylogin")
    public User getUserByLogin(String login) {
        User result;
        result = null;
        try {
            session.beginTransaction();
            result = UserHelper.getByLogin(this.session, login);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserById")
    public User getUserById(long id) {
        User result;
        result = null;
        try {
            session.beginTransaction();
            result = UserHelper.getById(this.session, id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserByEmail")
    public User getUserByEmail(String email) {
        User result;
        result = null;
        try {
            session.beginTransaction();
            result = UserHelper.getByMail(this.session, email);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "deleteUserById")
    public void deleteUserById(long id) {
        try {
            session.beginTransaction();
            UserHelper.deleteById(this.session, id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @WebMethod(operationName = "deleteUserByLogin")
    public void deleteUserByLogin(String login) {
        try {
            session.beginTransaction();
            UserHelper.deleteByLogin(this.session, login);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*------------------------------------------------------------------------------------------------------------------------------*/
    // Веб-методы для сущности Product
    @WebMethod(operationName = "createNewProduct")
    public Product createNewProduct(String name, String description, Double cost) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.createIfNotExists(this.session, name, description, cost);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductByName")
    public Product getProductByName(String name) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.getByName(this.session, name);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductById")
    public Product getProductById(long id) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.getById(this.session, id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductByPrice")
    public List getProductByPrice(long id) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.getByPrice(this.session, id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductWithMaxPrice")
    public Product getProductWithMaxPrice() {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.getProductWithMaxPrice(this.session);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductWithMinPrice")
    public Product getProductWithMinPrice() {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.getProductWithMinPrice(this.session);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "deleteProductById")
    public void deleteProductById(long id) {
        try {
            session.beginTransaction();
            ProductHelper.deleteById(this.session, id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @WebMethod(operationName = "deleteProductByName")
    public void deleteProductByName(String name) {
        try {
            session.beginTransaction();
            ProductHelper.deleteByName(this.session, name);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @WebMethod(operationName = "updateProductDescriptionByName")
    public Product updateProductDescriptionByName(String name, String description) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.changeDescByName(this.session, name, description);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductDescriptionById")
    public Product updateProductDescriptionById(long id, String description) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.changeDescById(this.session, id, description);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductNameById")
    public Product updateProductNameById(long id, String description) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.changeNameById(this.session, id, description);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductPriceById")
    public Product updateProductPriceById(long id, double cost) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.changePriceById(this.session, id, cost);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductPriceByName")
    public Product updateProductPriceByName(String name, double cost) {
        Product result;
        result = null;
        try {
            session.beginTransaction();
            result = ProductHelper.changePriceByName(this.session, name, cost);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    /*----------------------------------------------------------------------------------------------------------------------------------------------*/
    // Веб-методы для сущности UserIncome
    //TODO: из-за связи OneToOne возвращается все (то есть поля и User и Обьект)
    // Обьект User и Product берем из таблицы по их Id
    @WebMethod(operationName = "createNewUserIncome")
    public UserIncome createNewUserIncome(long user_id, long product_id, int products_count, String datetime) {
        UserIncome result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.createIfNotExists(this.session, user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserIncomeById")
    public UserIncome getUserIncomeById(long income_id) {
        UserIncome result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.getById(this.session, income_id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserIncomesByUser")
    public List getUserIncomesByUser(long user_id) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id); //TODO: обработать исключения
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllByUser(this.session, user);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getUserIncomesByAllParamsWithoutId")
    public UserIncome getUserIncomesByAllParamsWithoutId(long user_id, long product_id, int products_count, String datetime) {
        UserIncome result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getByAllParametersWithoutId(this.session, null, null, products_count, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByDatetime")
    public List getAllUserIncomesByDatetime(String datetime) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.getAllByDatetime(this.session, datetime);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByProduct")
    public List getAllUserIncomesByProduct(long product_id) {
        List result;
        result = null;
        Product product = ProductHelper.getById(this.session, product_id);
        if (product != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllByProduct(this.session, product);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByYear")
    public List getAllUserIncomesByYear(String year) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.getAllByYear(this.session, year);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByMounth")
    public List getAllUserIncomesByMounth(String mounth) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.getAllByMounth(this.session, mounth);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByDay")
    public List getAllUserIncomesByDay(String day) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.getAllByDay(this.session, day);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByHour")
    public List getAllUserIncomesByHour(String hour) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.getAllByHour(this.session, hour);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByMinute")
    public List getAllUserIncomesByMinute(String minute) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.getAllByMinute(this.session, minute);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndDatetime")
    public List getAllUserIncomesByUserAndDatetime(long user_id, String datetime) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllByUserAndDatetime(this.session, user, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndYear")
    public List getAllUserIncomesByUserAndYear(long user_id, String year) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllByUserAndYear(this.session, user, year);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndMounth")
    public List getAllUserIncomesByUserAndMounth(long user_id, String mounth) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllByUserAndMounth(this.session, user, mounth);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndDay")
    public List getAllUserIncomesByUserAndDay(long user_id, String day) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllByUserAndDay(this.session, user, day);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndHour")
    public List getAllUserIncomesByUserAndHour(long user_id, String hour) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllUserAndHour(this.session, user, hour);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndMinute")
    public List getAllUserIncomesByUserAndMinute(long user_id, String minute) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getAllByUserAndMinute(this.session, user, minute);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserIncomeByAllParams")
    public UserIncome updateUserIncomeByAllParams(long id, long user_id, long product_id, int products_count, String datetime) {
        UserIncome result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.changeByAllParams(this.session, id, user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserIncomeProductById")
    public UserIncome updateUserIncomeProductById(long id, long product_id) {
        UserIncome result;
        result = null;
        Product product = ProductHelper.getById(this.session, product_id);
        if (product != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.changeProductById(this.session, id, product);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserIncomeProductCountById")
    public UserIncome updateUserIncomeProductCountById(long id, int product_count) {
        UserIncome result;
        result = null;
        try {
            session.beginTransaction();
            result = UserIncomeHelper.changeProductCountById(this.session, id, product_count);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUser")
    public double getOverralUserIncomeSumByUser(long user_id) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getOverralSumByUser(this.session, user);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndYear")
    public double getOverralUserIncomeSumByUserAndYear(long user_id, String year) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndYear(this.session, user, year);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndMounth")
    public double getOverralUserIncomeSumByUserAndMounth(long user_id, String mounth) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndMounth(this.session, user, mounth);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndDay")
    public double getOverralUserIncomeSumByUserAndDay(long user_id, String day) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndMounth(this.session, user, day);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndProduct")
    public double getOverralUserIncomeSumByUserAndProduct(long user_id, long product_id) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndProduct(this.session, user, product);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    /*----------------------------------------------------------------------------------------------------------------------------------------------*/
    // Веб-методы для сущности UserOutlay
    // TODO: из-за связи OneToOne возвращается все (то есть поля и User и Обьект)
    // Обьект User и Product берем из таблицы по их Id
    @WebMethod(operationName = "createNewUserOutlay")
    public UserOutlay createNewUserOutlay(long user_id, long product_id, int products_count, String datetime) {
        UserOutlay result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.createIfNotExists(this.session, user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserOutlayById")
    public UserOutlay getUserOutlayById(long income_id) {
        UserOutlay result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.getById(this.session, income_id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserOutlaysByUser")
    public List getUserOutlaysByUser(long user_id) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id); //TODO: обработать исключения
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllByUser(this.session, user);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getUserOutlaysByAllParamsWithoutId")
    public UserOutlay getUserOutlayByAllParamsWithoutId(long user_id, long product_id, int products_count, String datetime) {
        UserOutlay result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getByAllParametersWithoutId(this.session, null, null, products_count, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByDatetime")
    public List getAllUserOutlaysByDatetime(String datetime) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.getAllByDatetime(this.session, datetime);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByProduct")
    public List getAllUserOutlaysByProduct(long product_id) {
        List result;
        result = null;
        Product product = ProductHelper.getById(this.session, product_id);
        if (product != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllByProduct(this.session, product);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByYear")
    public List getAllUserOutlaysByYear(String year) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.getAllByYear(this.session, year);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByMounth")
    public List getAllUserOutlaysByMounth(String mounth) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.getAllByMounth(this.session, mounth);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByDay")
    public List getAllUserOutlaysByDay(String day) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.getAllByDay(this.session, day);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByHour")
    public List getAllUserOutlaysByHour(String hour) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.getAllByHour(this.session, hour);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByMinute")
    public List getAllUserOutlaysByMinute(String minute) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.getAllByMinute(this.session, minute);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndDatetime")
    public List getAllUserOutlaysByUserAndDatetime(long user_id, String datetime) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllByUserAndDatetime(this.session, user, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndYear")
    public List getAllUserOutlaysByUserAndYear(long user_id, String year) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllByUserAndYear(this.session, user, year);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndMounth")
    public List getAllUserOutlaysByUserAndMounth(long user_id, String mounth) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllByUserAndMounth(this.session, user, mounth);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndDay")
    public List getAllUserOutlaysByUserAndDay(long user_id, String day) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllByUserAndDay(this.session, user, day);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndHour")
    public List getAllUserOutlaysByUserAndHour(long user_id, String hour) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllUserAndByHour(this.session, user, hour);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndMinute")
    public List getAllUserOutlaysByUserAndMinute(long user_id, String minute) {
        List result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getAllByUserAndMinute(this.session, user, minute);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserOutlayByAllParams")
    public UserOutlay updateUserOutlayByAllParams(long id, long user_id, long product_id, int products_count, String datetime) {
        UserOutlay result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.changeByAllParams(this.session, id, user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserOutlayProductById")
    public UserOutlay updateUserOutlayProductById(long id, long product_id) {
        UserOutlay result;
        result = null;
        Product product = ProductHelper.getById(this.session, product_id);
        if (product != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.changeProductById(this.session, id, product);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserOutlayProductCountById")
    public UserOutlay updateUserOutlayProductCountById(long id, int product_count) {
        UserOutlay result;
        result = null;
        try {
            session.beginTransaction();
            result = UserOutlayHelper.changeProductCountById(this.session, id, product_count);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUser")
    public double getOverralUserOutlaySumByUser(long user_id) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getOverralSumByUser(this.session, user);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndYear")
    public double getOverralUserOutlaySumByUserAndYear(long user_id, String year) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndYear(this.session, user, year);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndMounth")
    public double getOverralUserOutlaySumByUserAndMounth(long user_id, String mounth) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndMounth(this.session, user, mounth);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndDay")
    public double getOverralUserOutlaySumByUserAndDay(long user_id, String day) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndMounth(this.session, user, day);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndProduct")
    public double getOverralUserOutlaySumByUserAndProduct(long user_id, long product_id) {
        double result;
        result = Double.MIN_VALUE;
        User user = UserHelper.getById(this.session, user_id);
        Product product = ProductHelper.getById(this.session, product_id);
        if (user != null && product != null) {
            try {
                session.beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndProduct(this.session, user, product);
            } catch (HibernateException es) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    /*----------------------------------------------------------------------------------------------------------------------------------------------*/
    // Веб-методы для сущности UserBudget
    /*----------------------------------------------------------------------------------------------------------------------------------------------*/
    @WebMethod(operationName = "createNewUserBudget")
    public UserBudget createNewUserBudget(long owner_id, String name, String description, double amount) {
        UserBudget result;
        result = null;
        User user = UserHelper.getById(this.session, owner_id);
        if (user != null) {
            try {
                session.beginTransaction();
                result = UserBudgetHelper.createIfNotExists(this.session, user, name, description, amount);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserBudgetByAllParams")
    public UserBudget getUserBudgetByAllParams(long id, long owner_id, String name, String description, double amount) {
        UserBudget result;
        result = null;
        User owner = UserHelper.getById(this.session, owner_id);
        if (owner != null) {
            try {
                session.beginTransaction();
                result = UserBudgetHelper.getByAllParameters(this.session, id, owner, name, description, amount);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserBudgetsByOwner")
    public List getAllUserBudgetsByOwner(long owner_id) {
        List result;
        result = null;
        User owner = UserHelper.getById(this.session, owner_id);
        if (owner != null) {
            try {
                session.beginTransaction();
                result = UserBudgetHelper.getAllByOwner(this.session, owner);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserBudgetById")
    public UserBudget getUserBudgetById(long id) {
        UserBudget result;
        result = null;
        try {
            session.beginTransaction();
            result = UserBudgetHelper.getById(this.session, id);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserBudgetByOwnerAndName")
    public UserBudget getUserBudgetByOwnerAndName(long owner_id, String name) {
        UserBudget result;
        result = null;
        User owner = UserHelper.getById(this.session, owner_id);
        if (owner != null) {
            try {
                session.beginTransaction();
                result = UserBudgetHelper.getByUserAndName(this.session, owner, name);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserBudgetsByDescription")
    public List getAllUserBudgetsByDescription(String description) {
        List result;
        result = null;
        try {
            session.beginTransaction();
            result = UserBudgetHelper.getAllByDescription(this.session, description);
        } catch (HibernateException ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserBudgetAmount")
    public UserBudget updateUserBudgetAmount(long owner_id, String name) {
        UserBudget result;
        result = null;
        User owner = UserHelper.getById(this.session, owner_id);
        if (owner != null) {
            try {
                session.beginTransaction();
                result = UserBudgetHelper.updateOwnerAmount(this.session, owner, name);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserBudgetSumByOwner")
    public double getOverralUserBudgetSumByOwner(long owner_id) {
        double result;
        result = Double.MIN_VALUE;
        User owner = UserHelper.getById(this.session, owner_id);
        if (owner != null) {
            try {
                session.beginTransaction();
                result = UserBudgetHelper.getOverralSumByOwner(this.session, owner);
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    //TODO: реализовать нормальный List c нормальным equals() - избавиться от временных списков
    @WebMethod(operationName = "getProductsCountUsedByUser")
    public Set getProductsCountUsedByUser(long user_id) {
        Set result;
        result = null;
        User user = UserHelper.getById(this.session, user_id);
        if (user != null) {
            try {
                session.beginTransaction();
                List tempOutlays = UserOutlayHelper.getAllByUser(this.session, user);
                List tempIncomes = UserIncomeHelper.getAllByUser(this.session, user);

                List outlaysIdList = new ArrayList();
                List incomesIdList = new ArrayList();

                for (Iterator it = tempOutlays.iterator(); it.hasNext();) {
                    UserOutlay outlay_item = (UserOutlay) it.next();
                    outlaysIdList.add(outlay_item.getProduct().getId());
                }

                for (Iterator it = tempIncomes.iterator(); it.hasNext();) {
                    UserIncome outlay_item = (UserIncome) it.next();
                    incomesIdList.add(outlay_item.getProduct().getId());
                }

                tempIncomes.clear();
                tempOutlays.clear();

                if (outlaysIdList != null && incomesIdList != null) {
                    result = new HashSet(outlaysIdList);
                    result.addAll(incomesIdList);
                }
            } catch (HibernateException ex) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
        }
        //session.close();
        return result;
    }

    @WebMethod(operationName = "Hello")
    public void Hello() {
        try {
            this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
            this.session = sessionFactory.openSession();
            this.val = new Validator();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    // ?
    @WebMethod(operationName = "Goodbye")
    public String Goodbye() {
        //this.session.getTransaction().commit();
        this.session.close();
        return "Goodbye";
    }
}
