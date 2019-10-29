/*
 * Services to manage crud for user account
 * Insert, modify, list, find and delete user account.
 */
package service;

import java.util.List;
import model.UserAccount;

/**
 *
 * @author paniagua
 */
public interface UserAccountService 
{
    // Find user account by id
    public UserAccount getUserAccount(Long userID);
    // Find user account by iban
    public UserAccount getUserAccountByIban(String iban);
    // Create a new user account in teh system
    public String newUserAccount(UserAccount user);
    // Modify an existing user account
    public String modifyUserAccount(UserAccount user);
    // Delete an existing user account
    public void removeUserAccount(UserAccount user);
    // List all user account 
    public List<UserAccount> getUserAccountsByParams(UserAccount user);
}
