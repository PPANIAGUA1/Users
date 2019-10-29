/*
 * Charge of persistence of model UserAccount
 * Propagates every chaange of userAccount in database
 */
package dao;

import java.util.List;
import model.UserAccount;

/**
 *
 * @author paniagua
 */
public interface UserAccountDAO 
{
    //Recover a useraccount from db looking for id.
    public UserAccount readUserbyID(Long id)
            throws Exception;
    
    //Recover a useraccount from db loking for iban.
    public UserAccount readUsersByIban(String iban)
            throws Exception;
    
    //Update a ussercount modified in the aplication
    public Boolean updateUserAccount(UserAccount user)
            throws Exception;

    //Remove a useraccount ,deleted in the aplication, from datbase 
    public void deleteUserAccount(UserAccount userInfo)
            throws Exception;
    
    //Recover all useraccount wich have the same fields as user 
     public List<UserAccount> getUserAccountsByParams(UserAccount user)
            throws Exception;

}
