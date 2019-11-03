package dao.impl;

import dao.UserAccountDAO;
import java.util.List;
import model.UserAccount;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author paniagua
 */
public class UserAccountDaoImpl implements UserAccountDAO 
{
    private static UserAccountDaoImpl daoGenerico = null;
    private final SessionFactory factory; //Used by all threads of the application 
    private Transaction transaction; //Used to prevent inappropriate usage of resources.
    private Session session;    //Used to get a physical connection with a database

    public UserAccountDaoImpl() {
        factory = HibernateUtil.getSessionFactory();//created by providing Configuration object
    }

    public static UserAccountDaoImpl getInstance() {
        if (daoGenerico == null) {
            daoGenerico = new UserAccountDaoImpl();
        }
        return daoGenerico;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean deleteUserAccount(Long id) {
        boolean success = false;
        Object o = null;
        session = factory.openSession(); //Open a session with database
        UserAccount user = (UserAccount) session.load(UserAccount.class, id); //Recover user account of database  by id
        session.delete(user); //Delete registry of user in database
        try {
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit(); //if all has gone well do commit
            success = true;
        } catch (Exception ex) {
            transaction.rollback(); //if  have any problems do rollback
        } finally {
            session.close();
        }
        return success;
    }

     /**
     * Modify a user passed by parameter
     * @param user
     * @throws ServiceException
     */
    @Override
    public Boolean updateUserAccount(UserAccount user) throws Exception {
        boolean success = false;     
        try {
            session = factory.openSession();    
            transaction = session.beginTransaction();
            System.out.println(user.getId());
            session.saveOrUpdate(user); //update user account in database, if not exist user account do insert new registry
            transaction.commit();
            success = true;
        } catch (Exception ex) {
            transaction.rollback();
            throw new UnsupportedOperationException("Not supported yet.");
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                }
            }
        }
        return success;
    }

    /**
     * Remove a user account
     * @param user
     * @throws ServiceException
     */    
    @Override
    public void deleteUserAccount(UserAccount user) throws Exception 
    {      
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.delete(user);//Delete registry of user in database

            transaction.commit();
        } catch (HibernateException e) {            
              transaction.rollback();
             
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                }
            }
        }
    }
    
    /*
     * We recovered the userAccount using id parameter  
     * @param iban    
     * @throws ServiceException      
     */    
    @Override
    public UserAccount getUserbyID(Long id) throws Exception 
    {      
        UserAccount user = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            user = (UserAccount) session.get(UserAccount.class, id);//Recover user account of database  by id

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException e1) {
                    throw new UnsupportedOperationException(e1.toString());
                }
            }
            throw new UnsupportedOperationException(e.toString());
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                }
            }
        }
        return user;
    }

     /*
     * Recover the userAccount using iban parameter 
     * @param iban    
     * @throws ServiceException    
     */
    @Override
    public UserAccount getUsersByIban(String iban) throws Exception 
    {
      UserAccount user = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();

            Criteria criteria
                    = session.createCriteria(UserAccount.class); //Recover all user account of database  by iban
            user = (UserAccount) criteria.add(Restrictions.eq("iban", iban)).uniqueResult(); //Only return one result

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (HibernateException e1) {
                    throw new UnsupportedOperationException(e1.toString());
                }
            }
            throw new UnsupportedOperationException(e.toString());
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                }
            }
        }
        return user;
    }

    /**
     * Search a user account by same parameters
     * @param user
     * @throws ServiceException
     */
    @Override
    public List<UserAccount> getUserAccountsByParams(UserAccount user) throws Exception 
    {
        List<UserAccount> users = null;       
        try {
            session = factory.openSession();
            Criteria criteria = session.createCriteria(UserAccount.class); //Recover user account of database(table users)
            criteria.add(Restrictions.eq("authorities", user.getAuthorities())); //flter for authorities
            if (user.getFirstName() != null) {
                criteria.add(Restrictions.eq("firstName", user.getFirstName()));//flter for firstName
            }
            if (user.getLastname() != null) {
                criteria.add(Restrictions.eq("lastname", user.getLastname()));//flter for lastName
            }
            if (user.getIban() != null) {
                criteria.add(Restrictions.eq("iban", user.getIban()));//flter for iban
            }
            users = criteria.list();
        } catch (HibernateException e) {

            throw new UnsupportedOperationException(e.toString());
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                }
            }
        }
        return users;
    }

}