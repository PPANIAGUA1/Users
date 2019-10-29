/*
 * It is the controller and here we will call the different
 * service depending on the option received in the MainApp.
 */
package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.UserAccount;
import service.impl.UserAccountServiceImpl;

/**
 *
 * @author paniagua
 */
public class ControllerApp {
   
    private UserAccountServiceImpl userService;
    private Scanner scanner;
    //In the controller we're going to inject the service and the scanner object
    public ControllerApp(UserAccountServiceImpl userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }  
            
    public void newUser() {
        //We create a new user object to inject in our system        
        UserAccount user = new UserAccount();
        user.setId(null);
        System.out.println("Insert first name:");
        scanner.nextLine();
        user.setFirstName(scanner.nextLine());

        System.out.println("Insert last name:");
        user.setLastname(scanner.nextLine());

        System.out.println("Insert iban:");
        user.setIban(scanner.nextLine());
        System.out.println(user);
        //Once we have the user object, we passed it to the service for the insertion
        String msg = userService.newUserAccount(user);

        System.out.println(msg);
        System.out.println();
    }

    public void findUser() {
        List<UserAccount> listausers = new ArrayList<UserAccount>();
        System.out.println("Insert id  :");     
        //We passed the id from console and the service will returns the user of that id.
        UserAccount user = userService.getUserAccount(scanner.nextLong());
        if (user != null) {
            listausers.add(user);
        }
        listUsers(listausers);

    }

    public void listAllUser() {
        //Called the service without parameter. It must return all user.      
        List<UserAccount> listausers = userService.getUserAccountsByParams(new UserAccount());
        listUsers(listausers);
    }

    public void findUsersByParams() {
        System.out.println("Find by ...");
        scanner.nextLine();
        UserAccount user = new UserAccount();
        //We asked for all user parameters.
        //if the answer Y we include it in the search parameters.       
        System.out.println("Find by First name?(Y/N)");
        if ("Y".equals(scanner.nextLine().toUpperCase())) {
            System.out.println("Write first name:");
            user.setFirstName(scanner.nextLine());
        }
        System.out.println("Find by Last name?(Y/N)");
        if ("Y".equals(scanner.nextLine().toUpperCase())) {
            System.out.println("Write last name:");
            user.setLastname(scanner.nextLine());

        }        
        System.out.println("Find by Iban?(Y/N)");
        if ("Y".equals(scanner.nextLine().toUpperCase())) {
            System.out.println("Write Iban:");
            user.setIban(scanner.nextLine());
        }
       
        //Called the service to search by parameters
        List<UserAccount> listausers = userService.getUserAccountsByParams(user);
        listUsers(listausers);
    }
      
    public void updateUser() {
        System.out.println("write user id to update:");
        //we collect the user to modify by user id.
        UserAccount user = userService.getUserAccount(scanner.nextLong());
        scanner.nextLine();
        Boolean modify = false;
        //If it exits in database, show it on screen and ask for the new value 
        //if the user wishes to modify it.     
        if (user != null) {
            String newIban = user.getIban();
            System.out.println("First name: " + user.getFirstName() + " Modify?(Y/N)");
            if ("Y".equals(scanner.nextLine().toUpperCase())) {
                System.out.println("New first name:");
                user.setFirstName(scanner.nextLine());
                modify = true;
            }
            System.out.println("Last name: " + user.getLastname() + " Modify?(Y/N)");
            if ("Y".equals(scanner.nextLine().toUpperCase())) {
                System.out.println("New last name:");
                user.setLastname(scanner.nextLine());
                modify = true;
            }
            System.out.println("Iban: " + user.getIban() + " Modify?(Y/N)");
            if ("Y".equals(scanner.nextLine().toUpperCase())) {
                System.out.println("New Iban:");
                newIban = scanner.nextLine();
            }
            if (!newIban.equals(user.getIban())) 
            {                
                //If a different iban has been introduced it is necessary to make sure that it does not exist
                if (userService.getUserAccountByIban(newIban) == null) {
                    user.setIban(newIban);
                    modify = true;
                } else {
                    System.out.println("ERROR. This iban already exist");
                }
            }
            if (modify) {
                //Called to service to do the user update.
                String resul = userService.modifyUserAccount(user);
                System.out.println(resul);
                System.out.println();
            }

        } else {
            System.out.println("user not found....");
            System.out.println();
        }

    }

     public void deleteUser() {
        System.out.println("write user id to delete:");
           //we collect the user to remove by user id.
        UserAccount user = userService.getUserAccount(scanner.nextLong());

        if (user != null) {
            System.out.println(user);
            //Called to service to do the user update.
            userService.removeUserAccount(user);
            System.out.println("user eliminado...");
        } else {
            System.out.println("user not found...");
        }
    }
    
     //Method to print the users list by console,
     private void listUsers(List<UserAccount> listausers) {
        System.out.println("listing user accounts....");
        if (null != listausers && listausers.size() > 0) {
            System.out.printf("%-15s %-30s %-30s  %-30s %n", "User ID", "First Name", "Last Name", "IBAN");
            System.out.printf("%-15s %-30s %-30s  %-30s %n", "_______", "___________", "__________", "_______");
            for (UserAccount u : listausers) {
                System.out.printf("%-15s %-30s %-30s  %-30s %n", u.getId(), u.getFirstName(), u.getLastname(), u.getIban());
            }
        } else {
            System.out.println("users not found....");
        }
        System.out.println();
    }
}
