/*
 *Implements methods of AuthoritiesDao
*/
package dao.impl;

import dao.AuthoritiesDao;
import java.util.ArrayList;
import java.util.List;
import model.Authorities;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

/**
 *
 * @author paniagua
 */
public class AuthoritiesDaoImpl implements AuthoritiesDao 
{
    private static AuthoritiesDaoImpl daoGenerico = null;
    private final SessionFactory factory; //Used by all threads of the application 
    private Transaction transaction; //Used to prevent inappropriate usage of resources.
    private Session session;    //Used to get a physical connection with a database
    
    public AuthoritiesDaoImpl() {
        factory = HibernateUtil.getSessionFactory();//created by providing Configuration object
    }

    public static AuthoritiesDaoImpl getInstance() 
    {
        if (daoGenerico == null) {
            daoGenerico = new AuthoritiesDaoImpl();
        }
        return daoGenerico;
    }
    
    /*
     * List all user in database  
     */ 
    @Override
    public List<Authorities> listAllUsers() throws Exception 
    {
       List<Authorities> results = new ArrayList<>();
       session = factory.openSession();
       transaction = session.beginTransaction();
        try{
            Criteria cr = session.createCriteria(Authorities.class).setFetchMode("roles", FetchMode.JOIN);//Recover all user of database.
            results = cr.list();
        } catch (Exception ex) {
             transaction.rollback();           
        } finally {
            session.close();
        }
        return results;
    }

    
     /*
     * Seach the user using name and pass
     * @param name  
     * @param pass             
     */  
    @Override
    public Authorities findbyUserPass(String user, String pass) 
    {
        Authorities authorities = null;
        session = factory.openSession();
        transaction = session.beginTransaction();
        try {
            Criteria cr = session.createCriteria(Authorities.class).setFetchMode("idRol", FetchMode.JOIN);//Recover all user of database.
            cr.add(Restrictions.eq("username", user)); //add filter for userneame in the select
            cr.add(Restrictions.eq("pass", pass));    //add filter for pass in the select
            List results = cr.list();
            if (results.size() > 1) {
                throw new UnsupportedOperationException("Login failure.");
            }
            authorities = (Authorities) results.get(0);
        } catch (Exception ex) {
            transaction.rollback();
         } finally {
            session.close();
        }
        return authorities;
    }

}
