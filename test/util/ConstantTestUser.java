/*
* Constants to be used in the tests.
* We created two user for different testing like insert , to list, modify and delete.
* We use the two user administrators we have to check security,
* that users can only be manipulated by the administrator who created them.
*/
package util;

import model.UserAccount;

/**
 *
 * @author paniagua
 */
public final class ConstantTestUser {
    
    public static final UserAccount mockUser1 = new UserAccount("Francisco","Velasco","ES555555555");    
    public static final UserAccount mockUser2 = new UserAccount("Belén","Ortega","ES44444444");
     
}
