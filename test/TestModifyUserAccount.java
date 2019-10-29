/*
 * Test to prove the modified of user account
 * We recover from database a user account and we'll modify it
 * we will use the user’s parameters and verify that it update in database.
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
public class TestModifyUserAccount {

    private Authorities authorities1; // User administrator who registry the user´s account in bddd
    private UserAccount user1; // User Account to modify.
    private UserAccountServiceImpl userService; //Service to recover user1
    private AuthoritiesServiceImpl authoritiesServiceImpl;//Service to recover authorities1

    public TestModifyUserAccount() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    /*
    * We are going to create a new user to modify it in the test
    */
    @Before
    public void setUp() 
    {
        userService = new UserAccountServiceImpl();
        authoritiesServiceImpl = new AuthoritiesServiceImpl();      
        authorities1  = authoritiesServiceImpl.allUsers().get(0);//Recover the fist administrator user from database
        userService.setAuthorities(authorities1); //inject it into the user service 
       
        //We create a user account with the user administrator user authorities1
        user1 = ConstantTestUser.mockUser1;
        userService.newUserAccount(user1);
        user1.setAuthorities(userService.getAuthorities());
    }
    
    /*
    * Delete the user created for testing.
    */
    @After
    public void tearDown() {
        userService.removeUserAccount(user1);

    }

    //We are going to modify the  UsserAccount inserted in the first test
    //and then we modified it with the paramater of mockUser2 from ConstantTestuser
    @Test
    public void modifyUserAccount() {

        //Recuperamos de base de datos el usuario buscando por firstname , lastname e iban         
        List<UserAccount> listUser = userService.getUserAccountsByParams(user1);

        //We check that it returns one and only one database         
        //Check that a condition is true
        assertTrue(listUser.size() == 1);

        UserAccount user = listUser.get(0);

        //Modify with the paramater of mockUser2 f
        user.setFirstName(ConstantTestUser.mockUser2.getFirstName());
        user.setLastname(ConstantTestUser.mockUser2.getLastname());
        user.setIban(ConstantTestUser.mockUser2.getIban());

        //Saved the userAccount in database
        userService.modifyUserAccount(user);

        //Recove it from database
        listUser = userService.getUserAccountsByParams(user);
        //We check that it returns one and only one database        
        //Check that a condition is true
        assertTrue(listUser.size() == 1);

        //Check that two objects are equal       
        assertEquals(listUser.get(0), user);
    }
}
