/*
 * Test to prove the insert of new user account
 * We insert a new user account
 * we will use the user’s parameters and verify that it exists in database.
 */

import java.util.List;
import model.Authorities;
import model.UserAccount;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import service.impl.AuthoritiesServiceImpl;
import service.impl.UserAccountServiceImpl;
import util.ConstantTestUser;

/**
 *
 * @author paniagua
 */
public class TestNewUserAccounts {
    
    private UserAccountServiceImpl userService;
    private AuthoritiesServiceImpl authoritiesServiceImpl;
    private Authorities   authorities1;
    private UserAccount user1;
  
            
    public TestNewUserAccounts() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    //We collect the credentials of the first administrator
    @Before
    public void setUp() 
    {     
    userService = new UserAccountServiceImpl();
    authoritiesServiceImpl = new AuthoritiesServiceImpl();
     authorities1  = authoritiesServiceImpl.allUsers().get(0);//Recogemos el primer administrador de la tabla           
     userService.setAuthorities(authorities1);     
    }
    
    @After
    public void tearDown() {
         userService.removeUserAccount(user1);
    }

    // In this test we'll create a new user
    // We will check that the new register has been created in database.
     @Test
     public void newUserAccount() {         
                   
        user1= ConstantTestUser.mockUser1;         
        user1.setAuthorities(userService.getAuthorities());
         
        //Saved in database
         userService.newUserAccount(user1);
         
        //We recover from database user searching for firstname , lastname e iban         
        List<UserAccount> listUser =  userService.getUserAccountsByParams(user1);
        
        //Check that returns one and only one database.     
         //Check that a condition is true
         assertTrue (listUser.size()==1);       
     
         boolean iguales = listUser.get(0).equals(user1);
        //Check that two users are equal        
         assertEquals(listUser.get(0),user1);     
              
     }
     

}
