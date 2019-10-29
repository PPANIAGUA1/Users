/*
 *Implements methods of AuthoritiesService
*/
package service.impl;

import dao.impl.AuthoritiesDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Authorities;
import service.AuthoritiesService;

/**
 *
 * @author paniagua
 */
public class AuthoritiesServiceImpl implements AuthoritiesService
{
     /*
     * Seach the user using name and pass
     * @param name  
     * @param pass             
     */    
    @Override
    public Authorities finByUserPass(String name, String pass) 
    {
      Authorities authorities = null;
        try {
             authorities = AuthoritiesDaoImpl.getInstance().findbyUserPass(name, pass); //Call to method of dao for recovering user from database with name and pass.
        } catch (Exception ex) {
            Logger.getLogger(AuthoritiesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return authorities;
     
    }

    /*
     * List all user in database  
     */    
    @Override
    public List<Authorities> allUsers() 
    {
        List<Authorities> lAuthorities = new ArrayList<Authorities>();
        try {
            lAuthorities = AuthoritiesDaoImpl.getInstance().listAllUsers();//Call to method of dao for recovering all user.
        } catch (Exception ex) {
            Logger.getLogger(AuthoritiesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lAuthorities;
    }
    
}
