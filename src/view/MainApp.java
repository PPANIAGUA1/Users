package view;

import java.util.Scanner;
import service.impl.UserAccountServiceImpl;
import util.Login;

/**
 * Main Application Class. 
 *  Options will be displayed and depending on the option will go to one method or another of Controllerapp
 * @author paniagua
 */
public class MainApp {

    public static void main(String[] args) {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);  
        // Instantiate the service to implement user operations        
        UserAccountServiceImpl userService = new UserAccountServiceImpl();
        // Injection to the service the administrator user logged.
        userService.setAuthorities(Login.loginSucces());
        ControllerApp controllerApp = new ControllerApp(userService , scanner);
        if (null != userService.getAuthorities()) 
        {
            while (opcion != 7) 
            {
                System.out.println("1. New user");
                System.out.println("2. Find user by id");
                System.out.println("3. Find users by parameters");
                System.out.println("4. List all user");
                System.out.println("5. Update user");
                System.out.println("6. Delete user");
                System.out.println("7. End");
                System.out.println("Choose one option:");
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        controllerApp.newUser();
                        break;
                    case 2:
                        controllerApp.findUser();
                        break;
                    case 3:
                        controllerApp.findUsersByParams();
                        break;
                    case 4:
                        controllerApp.listAllUser();
                        break;
                    case 5:
                        controllerApp.updateUser();
                        break;
                    case 6:
                        controllerApp.deleteUser();
                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("invalid option\n");
                        break;
                }
            }
        }

    }
}
