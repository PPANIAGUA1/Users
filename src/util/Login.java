/*
 *In this class is where the user login are performed 
 *Checking that it exists and who has administrator permissions 
 */
package util;

import java.util.Scanner;
import model.Authorities;
import service.impl.AuthoritiesServiceImpl;

/**
 *
 * @author paniagua
 */
public  class  Login {
    
   public  static Authorities  loginSucces(){
             
        Scanner scanner = new Scanner(System.in);	
        AuthoritiesServiceImpl authoritiesServiceImpl = new AuthoritiesServiceImpl();
        boolean cont;
        Authorities authorities = null;
        int attempt =1;
                
      do {
            cont = false;
            System.out.println("Insert your username:");    
	    String name = scanner.nextLine();
            System.out.println("Insert your password:");           
            String pass = scanner.nextLine();
            //The service is called to search by user and pass in teh database.
            authorities = authoritiesServiceImpl.finByUserPass( name,  pass);
        
        if (null == authorities){
             System.out.println("The username or password is incorrect"); 
             if (attempt<3) {
                System.out.println("Do you want to try again?(Y/N)");  
                //The credentials haven't been found, two more attempts are allowed
                if (scanner.nextLine().toUpperCase().equals("Y")){
                    cont =true;
                    attempt++;                                       
                }               
             }
        }else if(!"administrator".equals(authorities.getRoles().getRol())){//The credentials have been found but the user hasn't the administrator rol.
            System.out.println("Forbidden");
            System.out.println("The user does not have administration permissions");     
        }                          
       } while (cont);            
       return authorities; 
     
       } 
 
    
}
