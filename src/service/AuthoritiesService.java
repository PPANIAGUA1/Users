/*
 * Interfaces to manage all operations about 
 * roles and administrator account.
 */
package service;

import java.util.List;
import model.Authorities;

/**
 *
 * @author paniagua
 */
public interface AuthoritiesService {
    
    // Use to login application. Look for user with password in the system
    public Authorities finByUserPass(String name, String pass);
    
    // list all authorites
    public List<Authorities> allUsers();
}
