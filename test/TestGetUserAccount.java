/*
 * Test to prove the insert of user accounts
 * We created new user accounts
 * and we'll use user parameters to recover it from database with administrator user
 * who created it. The we'll try to recover with another administrator user
 * to check it isn't possibly.
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
public class TestGetUserAccount 
{
           
    private UserAccountServiceImpl userService;
    private AuthoritiesServiceImpl authoritiesServiceImpl;
    private Authorities   authorities1;
    private Authorities   authorities2;
    private UserAccount user;
    
    public TestGetUserAccount() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /*
    * We are going to create a new user 
    */
    
    @Before
    public void setUp() {
    userService = new UserAccountServiceImpl();
    authoritiesServiceImpl = new AuthoritiesServiceImpl();
    authorities1  = authoritiesServiceImpl.allUsers().get(0);//we recover the first administrator user
           
    userService.setAuthorities(authorities1);   
    //Save in database the userAccount1 from ConstanTestUser
    user= ConstantTestUser.mockUser1;         
    userService.newUserAccount(user);
    }
    
     /*
    * Delete the user created to testing
    */
    @After
    public void tearDown() {
      userService.removeUserAccount(user);
    }
     
    // Recover user created
    // Fist by administrator user who created it, it have to work.
    // Then by another admnistrator, it have not to work.
     @Test
     public void getUserAccountBy()
     {            
        UserAccount user1= ConstantTestUser.mockUser1;                          
         //Recover from databased the user searching by firstname , lastname and iban .          
         List<UserAccount> listUser =  userService.getUserAccountsByParams(user1);
        
        //We check that it returns one and only one database   
         //Check that a condition is true
         assertTrue (listUser.size()==1);       
            
        //Check that two objects are equal
         assertEquals(listUser.get(0),user1);     
         
        //Change administrator user .
        authorities2  = authoritiesServiceImpl.allUsers().get(1);
           
         userService.setAuthorities(authorities2);         
         //Recover from databased the user searching by firstname , lastname and iban .       
         listUser =  userService.getUserAccountsByParams(user1);
         
         //Check that does not return any result in database
         //Check that a condition is true
         assertTrue (listUser.isEmpty());   
         
     }
}
