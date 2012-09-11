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
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.ini4j.InvalidFileFormatException;

@WebService()
public class MoneyTracker {

    // Веб-методы для сущности User
    /*----------------------------------------------------------------------------------------------------------------------------------------------*/
    @WebMethod(operationName = "registerUser")
    public User registerUser(String login, String pass, String email) throws InvalidFileFormatException, IOException, RegErrorException {
        User registeredUser;
        registeredUser = null;
        try {
            Validator val = new Validator();
            HibernateUtil.getCurrentSession().beginTransaction();
            registeredUser = UserHelper.register(val.getAppSettingsObject(), HibernateUtil.getCurrentSession(), login, pass, email);
            //TODO: убрать этот костыль
            if (registeredUser != null) {
                HibernateUtil.getCurrentSession().saveOrUpdate(registeredUser);
            }
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return registeredUser;
    }

    @WebMethod(operationName = "loginUser")
    public long loginUser(String login, String password) throws LoginErrorException {
        long result;
        result = -1;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserHelper.login(HibernateUtil.getCurrentSession(), login, password);
            //HibernateUtil.getCurrentSession().flush();
            HibernateUtil.getCurrentSession().getTransaction().commit();
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getUserBylogin")
    public User getUserByLogin(String login) {
        User result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserHelper.getByLogin(HibernateUtil.getCurrentSession(), login);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserById")
    public User getUserById(long id) {
        User result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserHelper.getById(HibernateUtil.getCurrentSession(), id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserByEmail")
    public User getUserByEmail(String email) {
        User result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserHelper.getByMail(HibernateUtil.getCurrentSession(), email);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "deleteUserById")
    public void deleteUserById(long id) {
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            UserHelper.deleteById(HibernateUtil.getCurrentSession(), id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
    }

    @WebMethod(operationName = "deleteUserByLogin")
    public void deleteUserByLogin(String login) {
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            UserHelper.deleteByLogin(HibernateUtil.getCurrentSession(), login);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.createIfNotExists(HibernateUtil.getCurrentSession(), name, description, cost);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductByName")
    public Product getProductByName(String name) {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.getByName(HibernateUtil.getCurrentSession(), name);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductById")
    public Product getProductById(long id) {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.getById(HibernateUtil.getCurrentSession(), id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductByPrice")
    public List getProductByPrice(long id) {
        List result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.getByPrice(HibernateUtil.getCurrentSession(), id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductWithMaxPrice")
    public Product getProductWithMaxPrice() {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.getProductWithMaxPrice(HibernateUtil.getCurrentSession());
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getProductWithMinPrice")
    public Product getProductWithMinPrice() {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.getProductWithMinPrice(HibernateUtil.getCurrentSession());
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "deleteProductById")
    public void deleteProductById(long id) {
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            ProductHelper.deleteById(HibernateUtil.getCurrentSession(), id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
    }

    @WebMethod(operationName = "deleteProductByName")
    public void deleteProductByName(String name) {
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            ProductHelper.deleteByName(HibernateUtil.getCurrentSession(), name);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
    }

    @WebMethod(operationName = "updateProductDescriptionByName")
    public Product updateProductDescriptionByName(String name, String description) {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.changeDescByName(HibernateUtil.getCurrentSession(), name, description);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductDescriptionById")
    public Product updateProductDescriptionById(long id, String description) {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.changeDescById(HibernateUtil.getCurrentSession(), id, description);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductNameById")
    public Product updateProductNameById(long id, String description) {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.changeNameById(HibernateUtil.getCurrentSession(), id, description);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductPriceById")
    public Product updateProductPriceById(long id, double cost) {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.changePriceById(HibernateUtil.getCurrentSession(), id, cost);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateProductPriceByName")
    public Product updateProductPriceByName(String name, double cost) {
        Product result;
        result = null;
        try {
            HibernateUtil.getCurrentSession().beginTransaction();
            result = ProductHelper.changePriceByName(HibernateUtil.getCurrentSession(), name, cost);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.createIfNotExists(HibernateUtil.getCurrentSession(), user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.getById(HibernateUtil.getCurrentSession(), income_id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserIncomesByUser")
    public List getUserIncomesByUser(long user_id) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllByUser(HibernateUtil.getCurrentSession(), user);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getUserIncomesByAllParamsWithoutId")
    public UserIncome getUserIncomesByAllParamsWithoutId(long user_id, long product_id, int products_count, String datetime) {
        UserIncome result;
        result = null;
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getByAllParametersWithoutId(HibernateUtil.getCurrentSession(), null, null, products_count, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.getAllByDatetime(HibernateUtil.getCurrentSession(), datetime);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserIncomesByProduct")
    public List getAllUserIncomesByProduct(long product_id) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllByProduct(HibernateUtil.getCurrentSession(), product);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.getAllByYear(HibernateUtil.getCurrentSession(), year);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.getAllByMounth(HibernateUtil.getCurrentSession(), mounth);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.getAllByDay(HibernateUtil.getCurrentSession(), day);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.getAllByHour(HibernateUtil.getCurrentSession(), hour);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.getAllByMinute(HibernateUtil.getCurrentSession(), minute);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndDatetime")
    public List getAllUserIncomesByUserAndDatetime(long user_id, String datetime) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllByUserAndDatetime(HibernateUtil.getCurrentSession(), user, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndYear")
    public List getAllUserIncomesByUserAndYear(long user_id, String year) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllByUserAndYear(HibernateUtil.getCurrentSession(), user, year);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndMounth")
    public List getAllUserIncomesByUserAndMounth(long user_id, String mounth) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllByUserAndMounth(HibernateUtil.getCurrentSession(), user, mounth);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndDay")
    public List getAllUserIncomesByUserAndDay(long user_id, String day) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllByUserAndDay(HibernateUtil.getCurrentSession(), user, day);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndHour")
    public List getAllUserIncomesByUserAndHour(long user_id, String hour) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllUserAndHour(HibernateUtil.getCurrentSession(), user, hour);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserIncomesByUserAndMinute")
    public List getAllUserIncomesByUserAndMinute(long user_id, String minute) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getAllByUserAndMinute(HibernateUtil.getCurrentSession(), user, minute);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserIncomeByAllParams")
    public UserIncome updateUserIncomeByAllParams(long id, long user_id, long product_id, int products_count, String datetime) {
        UserIncome result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.changeByAllParams(HibernateUtil.getCurrentSession(), id, user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserIncomeProductById")
    public UserIncome updateUserIncomeProductById(long id, long product_id) {
        UserIncome result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.changeProductById(HibernateUtil.getCurrentSession(), id, product);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserIncomeHelper.changeProductCountById(HibernateUtil.getCurrentSession(), id, product_count);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUser")
    public double getOverralUserIncomeSumByUser(long user_id) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getOverralSumByUser(HibernateUtil.getCurrentSession(), user);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndYear")
    public double getOverralUserIncomeSumByUserAndYear(long user_id, String year) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndYear(HibernateUtil.getCurrentSession(), user, year);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndMounth")
    public double getOverralUserIncomeSumByUserAndMounth(long user_id, String mounth) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndMounth(HibernateUtil.getCurrentSession(), user, mounth);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndDay")
    public double getOverralUserIncomeSumByUserAndDay(long user_id, String day) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndMounth(HibernateUtil.getCurrentSession(), user, day);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserIncomeSumByUserAndProduct")
    public double getOverralUserIncomeSumByUserAndProduct(long user_id, long product_id) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserIncomeHelper.getOverralSumByUserAndProduct(HibernateUtil.getCurrentSession(), user, product);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.createIfNotExists(HibernateUtil.getCurrentSession(), user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.getById(HibernateUtil.getCurrentSession(), income_id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserOutlaysByUser")
    public List getUserOutlaysByUser(long user_id) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllByUser(HibernateUtil.getCurrentSession(), user);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getUserOutlaysByAllParamsWithoutId")
    public UserOutlay getUserOutlayByAllParamsWithoutId(long user_id, long product_id, int products_count, String datetime) {
        UserOutlay result;
        result = null;
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getByAllParametersWithoutId(HibernateUtil.getCurrentSession(), null, null, products_count, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.getAllByDatetime(HibernateUtil.getCurrentSession(), datetime);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    //TODO: сделать возврат только id пользователя и продукта
    @WebMethod(operationName = "getAllUserOutlaysByProduct")
    public List getAllUserOutlaysByProduct(long product_id) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllByProduct(HibernateUtil.getCurrentSession(), product);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.getAllByYear(HibernateUtil.getCurrentSession(), year);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.getAllByMounth(HibernateUtil.getCurrentSession(), mounth);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.getAllByDay(HibernateUtil.getCurrentSession(), day);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.getAllByHour(HibernateUtil.getCurrentSession(), hour);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.getAllByMinute(HibernateUtil.getCurrentSession(), minute);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndDatetime")
    public List getAllUserOutlaysByUserAndDatetime(long user_id, String datetime) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllByUserAndDatetime(HibernateUtil.getCurrentSession(), user, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndYear")
    public List getAllUserOutlaysByUserAndYear(long user_id, String year) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllByUserAndYear(HibernateUtil.getCurrentSession(), user, year);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndMounth")
    public List getAllUserOutlaysByUserAndMounth(long user_id, String mounth) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllByUserAndMounth(HibernateUtil.getCurrentSession(), user, mounth);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndDay")
    public List getAllUserOutlaysByUserAndDay(long user_id, String day) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllByUserAndDay(HibernateUtil.getCurrentSession(), user, day);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndHour")
    public List getAllUserOutlaysByUserAndHour(long user_id, String hour) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllUserAndByHour(HibernateUtil.getCurrentSession(), user, hour);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserOutlaysByUserAndMinute")
    public List getAllUserOutlaysByUserAndMinute(long user_id, String minute) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getAllByUserAndMinute(HibernateUtil.getCurrentSession(), user, minute);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserOutlayByAllParams")
    public UserOutlay updateUserOutlayByAllParams(long id, long user_id, long product_id, int products_count, String datetime) {
        UserOutlay result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.changeByAllParams(HibernateUtil.getCurrentSession(), id, user, product, products_count, datetime);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserOutlayProductById")
    public UserOutlay updateUserOutlayProductById(long id, long product_id) {
        UserOutlay result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.changeProductById(HibernateUtil.getCurrentSession(), id, product);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserOutlayHelper.changeProductCountById(HibernateUtil.getCurrentSession(), id, product_count);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUser")
    public double getOverralUserOutlaySumByUser(long user_id) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getOverralSumByUser(HibernateUtil.getCurrentSession(), user);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndYear")
    public double getOverralUserOutlaySumByUserAndYear(long user_id, String year) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndYear(HibernateUtil.getCurrentSession(), user, year);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndMounth")
    public double getOverralUserOutlaySumByUserAndMounth(long user_id, String mounth) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndMounth(HibernateUtil.getCurrentSession(), user, mounth);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndDay")
    public double getOverralUserOutlaySumByUserAndDay(long user_id, String day) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndMounth(HibernateUtil.getCurrentSession(), user, day);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserOutlaySumByUserAndProduct")
    public double getOverralUserOutlaySumByUserAndProduct(long user_id, long product_id) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        HibernateUtil.getCurrentSession().beginTransaction();
        Product product = ProductHelper.getById(HibernateUtil.getCurrentSession(), product_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null && product != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserOutlayHelper.getOverralSumByUserAndProduct(HibernateUtil.getCurrentSession(), user, product);
            } catch (HibernateException es) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), owner_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserBudgetHelper.createIfNotExists(HibernateUtil.getCurrentSession(), user, name, description, amount);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserBudgetByAllParams")
    public UserBudget getUserBudgetByAllParams(long id, long owner_id, String name, String description, double amount) {
        UserBudget result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User owner = UserHelper.getById(HibernateUtil.getCurrentSession(), owner_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (owner != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserBudgetHelper.getByAllParameters(HibernateUtil.getCurrentSession(), id, owner, name, description, amount);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getAllUserBudgetsByOwner")
    public List getAllUserBudgetsByOwner(long owner_id) {
        List result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User owner = UserHelper.getById(HibernateUtil.getCurrentSession(), owner_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (owner != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserBudgetHelper.getAllByOwner(HibernateUtil.getCurrentSession(), owner);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserBudgetHelper.getById(HibernateUtil.getCurrentSession(), id);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "getUserBudgetByOwnerAndName")
    public UserBudget getUserBudgetByOwnerAndName(long owner_id, String name) {
        UserBudget result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User owner = UserHelper.getById(HibernateUtil.getCurrentSession(), owner_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (owner != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserBudgetHelper.getByUserAndName(HibernateUtil.getCurrentSession(), owner, name);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
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
            HibernateUtil.getCurrentSession().beginTransaction();
            result = UserBudgetHelper.getAllByDescription(HibernateUtil.getCurrentSession(), description);
        } catch (HibernateException ex) {
            if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                HibernateUtil.getCurrentSession().getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            if (HibernateUtil.getCurrentSession() != null) {
                HibernateUtil.getCurrentSession().close();
            }
        }
        return result;
    }

    @WebMethod(operationName = "updateUserBudgetAmount")
    public UserBudget updateUserBudgetAmount(long owner_id, String name) {
        UserBudget result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User owner = UserHelper.getById(HibernateUtil.getCurrentSession(), owner_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (owner != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserBudgetHelper.updateOwnerAmount(HibernateUtil.getCurrentSession(), owner, name);
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
                ex.printStackTrace();
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        return result;
    }

    @WebMethod(operationName = "getOverralUserBudgetSumByOwner")
    public double getOverralUserBudgetSumByOwner(long owner_id) {
        double result;
        result = Double.MIN_VALUE;
        HibernateUtil.getCurrentSession().beginTransaction();
        User owner = UserHelper.getById(HibernateUtil.getCurrentSession(), owner_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (owner != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                result = UserBudgetHelper.getOverralSumByOwner(HibernateUtil.getCurrentSession(), owner);
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    //TODO: реализовать нормальный List c нормальным equals() - избавиться от временных списков
    @WebMethod(operationName = "getProductsCountUsedByUser")
    public Set getProductsCountUsedByUser(long user_id) {
        Set result;
        result = null;
        HibernateUtil.getCurrentSession().beginTransaction();
        User user = UserHelper.getById(HibernateUtil.getCurrentSession(), user_id);
        HibernateUtil.getCurrentSession().getTransaction().commit();
        if (user != null) {
            try {
                HibernateUtil.getCurrentSession().beginTransaction();
                List tempOutlays = UserOutlayHelper.getAllByUser(HibernateUtil.getCurrentSession(), user);
                HibernateUtil.getCurrentSession().getTransaction().commit();

                HibernateUtil.getCurrentSession().beginTransaction();
                List tempIncomes = UserIncomeHelper.getAllByUser(HibernateUtil.getCurrentSession(), user);
                HibernateUtil.getCurrentSession().getTransaction().commit();

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
                HibernateUtil.getCurrentSession().getTransaction().commit();
            } catch (HibernateException ex) {
                if (HibernateUtil.getCurrentSession().getTransaction().isActive()) {
                    HibernateUtil.getCurrentSession().getTransaction().rollback();
                }
            } finally {
                if (HibernateUtil.getCurrentSession() != null) {
                    HibernateUtil.getCurrentSession().close();
                }
            }
        }
        //HibernateUtil.getCurrentSession().close();
        return result;
    }

    @WebMethod(operationName = "Goodbye")
    public String Goodbye() {
        //HibernateUtil.getCurrentSession().getTransaction().commit();
        HibernateUtil.getCurrentSession().close();
        return "Goodbye";
    }
}
