/*
 *Implements methods of UserAccountService
*/
package service.impl;

import dao.impl.UserAccountDaoImpl;
import java.util.List;
import model.Authorities;
import model.UserAccount;
import org.hibernate.service.spi.ServiceException;
import service.UserAccountService;

/**
 *
 * @author paniagua
 */
public class UserAccountServiceImpl implements UserAccountService{
    
    private Authorities authorities;  // Use the credentials of the logged user to filter userAccount and to create new userAccount
    private UserAccountDaoImpl dao; // DAO object that provides us access to our database.

    public UserAccountServiceImpl() {
        this.dao = UserAccountDaoImpl.getInstance(); // We instantiate the DAO in the builder  of class.
    }
            
    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }
    
    /*
     * We recovered the userAccount using id parameter  
     * @param iban    
     * @throws ServiceException      
     */    
    @Override
    public UserAccount getUserAccount(Long userID) throws ServiceException 
    {        
        UserAccount userAccount = null;
        try {
            userAccount = dao.getUserbyID(userID); // Call to method of dao for recovering userAccount from database with id parameter 
            //Only is allowed to handle user account whom create it
            if (userAccount.getAuthorities().getIdAuth() != this.authorities.getIdAuth()) //if user administrator is not who create userAccount return null
            {
                userAccount = null;
            }
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

        return userAccount;
    }
    
    /*
     * Recover the userAccount using iban parameter 
     * @param iban    
     * @throws ServiceException    
     */
    @Override
    public UserAccount getUserAccountByIban(String iban) throws ServiceException 
    {
        UserAccount user = null;

        try {
            user = dao.getUsersByIban(iban);// Call to method of dao for recovering userAccount from database with iban parameter 
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

        return user;
    }
   
    /**
     * Modify a user passed by parameter
     * @param user
     * @throws ServiceException
     */
    @Override
    public String modifyUserAccount(UserAccount user) throws ServiceException 
    {
        String resul = "";
        try {           
            dao.updateUserAccount(user);// Call to method of dao to modify userAccount into database. 
            resul = "User saved succesfully";
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }
        return resul;
    }
    
     /**
     * Create a new UserAccount
     * @param user
     * @throws ServiceException
     */
    @Override
    public String newUserAccount(UserAccount user) throws ServiceException 
    {
        String resul = "";
        try {          
            System.out.println(user.getId());
            user.setAuthorities(this.authorities);//Inject object authorities with the logged administrator user
            dao.updateUserAccount(user);// Call to method of dao to insert a new userAccount into database. 
            resul = "User created succesfully";           
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }
        return resul;
    }

    /**
     * Remove a user account
     * @param user
     * @throws ServiceException
     */
    @Override
    public void removeUserAccount(UserAccount user) throws ServiceException 
    {
      try {      
         dao.deleteUserAccount(user);  // Call to method of dao to delete a userAccount into database. 
      }
      catch (Exception e) {
         throw new ServiceException(e.toString());
      }
    }    
    
    /**
     * Search a user account by same parameters
     * @param user
     * @throws ServiceException
     */
    @Override
    public List<UserAccount> getUserAccountsByParams(UserAccount user) 
    {
        List<UserAccount> users;

        try {
            user.setAuthorities(authorities);
            users = dao.getUserAccountsByParams(user);// Call to method of dao to find userAccount into database with the parameters in user object. 
        } catch (Exception e) {
            throw new ServiceException(e.toString());
        }

        return users;
    }
    
}
