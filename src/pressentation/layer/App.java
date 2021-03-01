package pressentation.layer;

import static pressentation.layer.ConsolePresentation.*;
import static pressentation.layer.ShortConsoleMethods.*;

import java.util.Scanner;

import static pressentation.layer.ClientControls.*;
import static pressentation.layer.AdminControls.*;
import static pressentation.layer.Ask.*;

public class App {
    public static void main(String[] args) throws Exception {
        // Scanner input = new Scanner(System.in);
        
        // System.out.print("Enter woord");
        // String werk = input.nextLine();

        // System.out.println(werk);

        // System.out.print("Enter woord");
        // String werk2 = input.nextLine();

        AdminControls admin = new AdminControls();
        int option = askInt("1.Admin\n2.Client");

        

        

        if (option == 1) {
            // while (true) {
            // String username = askString("Please input username");
            // String password = askString("Please input password");
            // if(admin.login(username, password)){
            // break;
            // }
            // }
            // admin.adminOptionMenu();

        } else if (option == 2) {
            register();
        } else {
            p("error");
        }
    }
}
