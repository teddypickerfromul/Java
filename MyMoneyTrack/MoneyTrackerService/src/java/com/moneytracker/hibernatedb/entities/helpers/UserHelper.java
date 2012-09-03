package com.moneytracker.hibernatedb.entities.helpers;

import com.moneytracker.hibernatedb.entities.User;
import com.moneytracker.utils.AppSettings;
;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.moneytracker.utils.LoginErrorException;
import com.moneytracker.utils.Validator;
import java.io.IOException;
import org.ini4j.InvalidFileFormatException;
import com.moneytracker.utils.RegErrorException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.moneytracker.utils.LoginErrorException;
import com.moneytracker.utils.Validator;
import java.io.IOException;
import org.ini4j.InvalidFileFormatException;
import com.moneytracker.utils.RegErrorException;
import org.hibernate.loader.custom.Return;



public class UserHelper {

    public static User createIfNotExists(Session session, String login,
            String password, String email) {

        User result = getByLogin(session, login);
        if (result == null) {
            result = new User();
            result.setLogin(login);
            result.setPassword(password);
            result.setEmail(email);
        }
        return result;
    }

    public static User getByLogin(Session session, String login) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        criteria.setMaxResults(1);

        return (User) criteria.uniqueResult();
    }

    public static User getByMail(Session session, String email) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (email == null) {
            throw new IllegalArgumentException("email is null");
        }

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("email", email));
        criteria.setMaxResults(1);

        return (User) criteria.uniqueResult();
    }

    public static long login(Session session, String login, String password) throws LoginErrorException {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        User result = getByLogin(session, login);
        if (result != null) {
            if (result.getPassword() == null ? password.trim() != null : !result.getPassword().equals(password.trim())) {
                //TODO: грамотная обработка исключения
                return -1;
                //throw new LoginErrorException("Для пользователя "+Long.toString(result.getId())+" указан неверный пароль");
            } else {
                return result.getId();
            }
        } else {
            //TODO: грамотная обработка исключения
            //throw new LoginErrorException("Пользователя с логином "+login+" не существует !");
            return -1;
        }
    }

    public static User register(AppSettings appcfg, Session session, String login, String password, String email) throws InvalidFileFormatException, IOException, RegErrorException {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }
        if (email == null) {
            throw new IllegalArgumentException("email is null");
        }

        Validator val = new Validator(appcfg);
        User user = null;

        if (val.checkLogin(login)) {
            if (val.checkPassword(password)) {
                if (val.checkEmailAddress(email)) {
                    user = createIfNotExists(session, login, password, email);
                }
            }
        }
        return user;
    }

    public static User getById(Session session, long id) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setMaxResults(1);

        return (User) criteria.uniqueResult();
    }

    public static void deleteById(Session session, long id) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }

        User result = getById(session, id);
        UserOutlayHelper.deleteAllByUser(session, result);
        UserIncomeHelper.deleteAllByUser(session, result);
        UserBudgetHelper.deleteAllByOwner(session, result);
        session.delete(result);
        session.getTransaction().commit();
    }

    public static void deleteByLogin(Session session, String login) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (login == null) {
            throw new IllegalArgumentException("login is null");
        }

        User result = getByLogin(session, login);
        UserOutlayHelper.deleteAllByUser(session, result);
        UserIncomeHelper.deleteAllByUser(session, result);
        UserBudgetHelper.deleteAllByOwner(session, result);
        session.delete(result);

        session.getTransaction().commit();
    }
}
