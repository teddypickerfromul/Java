package com.moneytracker.hibernatedb.entities.helpers;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.moneytracker.hibernatedb.entities.UserBudget;
import com.moneytracker.hibernatedb.entities.User;
import java.util.Iterator;

public class UserBudgetHelper {

    public static UserBudget createIfNotExists(Session session, User owner, String name, String description, double amount) {
        if (session == null) {
            throw new IllegalArgumentException("sesion is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (description == null) {
            throw new IllegalArgumentException("desc is null");
        }

        UserBudget result = getByAllParametersWithoutId(session, owner, name, description, amount);
        if (result == null) {
            result = new UserBudget();
            result.setOwner(owner);
            result.setName(name);
            result.setDescription(description);
            result.setAmount(amount);
            
            session.saveOrUpdate(result);
        }
        return result;
    }

    public static UserBudget getByAllParameters(Session session, long id, User owner, String name, String description, double amount) {
        if (session == null) {
            throw new IllegalArgumentException("sesion is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (description == null) {
            throw new IllegalArgumentException("desc is null");
        }

        Criteria criteria = session.createCriteria(UserBudget.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.add(Restrictions.eq("owner", owner));
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("description", description));
        criteria.add(Restrictions.eq("amount", amount));
        criteria.setMaxResults(1);

        return (UserBudget) criteria.uniqueResult();
    }

    public static UserBudget getByAllParametersWithoutId(Session session, User owner, String name, String description, double amount) {
        if (session == null) {
            throw new IllegalArgumentException("sesion is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (description == null) {
            throw new IllegalArgumentException("desc is null");
        }

        Criteria criteria = session.createCriteria(UserBudget.class);
        criteria.add(Restrictions.eq("owner", owner));
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("description", description));
        criteria.add(Restrictions.eq("amount", amount));
        criteria.setMaxResults(1);

        return (UserBudget) criteria.uniqueResult();
    }

    public static List getAllByOwner(Session session, User owner) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }
        
        
        Criteria criteria = session.createCriteria(UserBudget.class);
        criteria.add(Restrictions.eq("owner", owner));

        return criteria.list();
    }

    public static double getOverralSumByOwner(Session session, User owner) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }

        double overral = 0;
        List result = getAllByOwner(session, owner);
        Iterator<UserBudget> i = result.iterator();
        while (i.hasNext()) {
            overral += i.next().getAmount();
        }
        return overral;
    }

    public static UserBudget getById(Session session, long id) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }

        Criteria criteria = session.createCriteria(UserBudget.class);
        criteria.add(Restrictions.eq("id", id));
        criteria.setMaxResults(1);

        return (UserBudget) criteria.uniqueResult();
    }

    public static UserBudget getByUserAndName(Session session, User owner, String name) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        Criteria criteria = session.createCriteria(UserBudget.class);
        criteria.add(Restrictions.eq("owner", owner));
        criteria.add(Restrictions.eq("name", name));
        criteria.setMaxResults(1);

        return (UserBudget) criteria.uniqueResult();
    }

    public static List getAllByName(Session session, String name) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        Criteria criteria = session.createCriteria(UserBudget.class);
        criteria.add(Restrictions.eq("name", name));

        return criteria.list();
    }

    public static List getAllByDescription(Session session, String description) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (description == null) {
            throw new IllegalArgumentException("description is null");
        }

        Criteria criteria = session.createCriteria(UserBudget.class);
        criteria.add(Restrictions.eq("description", description));

        return criteria.list();
    }

    public static UserBudget updateOwnerAmount(Session session, User owner, String name) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }

        double owner_outlays_sum = UserOutlayHelper.getOverralSumByUser(session, owner);
        double owner_incomes_sum = UserIncomeHelper.getOverralSumByUser(session, owner);
        UserBudget result = getByUserAndName(session, owner, name);
        result.setAmount(result.getAmount() + owner_incomes_sum - owner_outlays_sum);
        session.update(result);

        return result;
    }

    public static boolean checkOwnerAmount(Session session, User owner, String name) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }

        UserBudget result = getByUserAndName(session, owner, name);
        if (result.getAmount() < 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void deletyByAllParameters(Session session, long id, User owner, String name, String description, double amount) {
        if (session == null) {
            throw new IllegalArgumentException("sesion is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (description == null) {
            throw new IllegalArgumentException("desc is null");
        }

        UserBudget result = getByAllParameters(session, id, owner, name, description, amount);
        session.delete(result);
    }

    //TODO: сделать одним запросом
    public static void deleteAllByOwner(Session session, User owner) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }

        List result = getAllByOwner(session, owner);
        Iterator<UserBudget> i = result.iterator();
        while (i.hasNext()) {
            UserBudget item = (UserBudget) i.next();
            session.delete(item);
        }
    }

    public static void clearAllbyOwner(Session session, User owner) {
        if (session == null) {
            throw new IllegalArgumentException("session is null");
        }
        if (owner == null) {
            throw new IllegalArgumentException("owner is null");
        }

        List result = getAllByOwner(session, owner);
        Iterator<UserBudget> i = result.iterator();
        while (i.hasNext()) {
            UserBudget item = (UserBudget) i.next();
            item.setAmount(0);
            session.update(item);
        }
    }
}
