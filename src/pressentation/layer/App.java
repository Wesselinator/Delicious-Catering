package pressentation.layer;


import static pressentation.layer.ShortConsoleMethods.*;

import businesslogic.layer.*;
import static pressentation.layer.Ask.*;

public class App {
    public static void main(String[] args) {
        Bookings allBookings = new Bookings();
        Registrations allClients = new Registrations();

        //java passes by reference
        AdminControls adminControls = new AdminControls(allBookings, allClients);
        ClientControls clienControls = new ClientControls(allBookings, allClients);

        int option = -1;
        int clientOption = -1;

        while (true) {
            pl("1. Admin");
            pl("2. Client");
            nl();
            pl("0.Exit");
            pl("================");

            option = askInt("Enter option: ");

            switch (option) {
                case 1:
                    //3 tries
                    for (int i = 0; i < 3; i++) {
                        if (adminControls.adminLogin()) {
                        pl("Success!");
                        adminControls.adminOptionMenu(); 
                        }      
                    }
                    //structure should be changed if admin needs to explicitly quit    
                    break;
                case 2:
                    pl("1.Register");
                    pl("2.Login");
                    nl();
                    pl("0.Back");
                    pl("================");
                    clientOption = askInt("Enter option: ");
                    switch (clientOption) {
                        case 1:
                            clienControls.newBookingAll();
                            break;
                        case 2:
                            clienControls.clientLogin();
                            break;
                        case 0:
                            //go back or restart app.java
                            break;
                        default:
                            p("Enter a valid option please");
                            break;
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    p("Enter a valid option please");
                    break;
            }
        }

        /*
        if (option == 1) {
            //3 tries
            for (int i = 0; i < 3; i++) {
                if (adminControls.adminLogin()) {
                    pl("Success!");
                    adminControls.adminOptionMenu();       
                }
            }
            //structure should be changed if admin needs to explicitly quit

        } else if (option == 2) {
            int option2 = askInt("1.Register\n2.Login");
            if (option2 == 1) {
                clienControls.newBookingAll();
            } else if (option2 == 2) {
                clienControls.clientLogin();
            } else {
                p("Not an option.");
            }

        } else {
            p("error");
        }
        */
    }

}

// Make payment method
// Check details
// Change menu
// ^^^^^^^
// Login
//
//