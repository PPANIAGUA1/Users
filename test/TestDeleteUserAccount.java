/*
 * Test to prove the delete of user account
 * We created new user accounts in database and then we delete it
 * and we'll use user account parameters to check that it don´t exist in database.
 */

import java.util.List;
import model.Authorities;
import model.UserAccount;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.impl.AuthoritiesServiceImpl;
import service.impl.UserAccountServiceImpl;
import util.ConstantTestUser;

/**
 *
 * @author paniagua
 */
public class TestDeleteUserAccount {
    private UserAccountServiceImpl userService;
    private AuthoritiesServiceImpl authoritiesServiceImpl;
    private Authorities   authorities1;
     private UserAccount user1; // User Account to renmove.
    
    public TestDeleteUserAccount() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() 
    {               
        userService = new UserAccountServiceImpl();
        authoritiesServiceImpl = new AuthoritiesServiceImpl();
        authorities1  = authoritiesServiceImpl.allUsers().get(0);//we recover the first administrator user
        userService.setAuthorities(authorities1);    
        
        //Create a new user with administrator authorities1
        user1= ConstantTestUser.mockUser1;  
        userService.newUserAccount(user1);
        user1.setAuthorities(userService.getAuthorities());
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void deleteUserAccount() 
     {         
        //Recover user created in before
        //searchng by firstname , lastname and iban         
        List<UserAccount> listUser =  userService.getUserAccountsByParams(user1);
        
        //We check that it returns one and only one database        
        //Check that a condition is true
        assertTrue (listUser.size()==1);   
         
        //Once recovered we delete it
        userService.removeUserAccount(listUser.get(0));
        //We look again for the same parameters in database
         listUser =  userService.getUserAccountsByParams(user1);
        
        //Check that the search does not return data  
         assertTrue (listUser.isEmpty());                
     }
     
}
