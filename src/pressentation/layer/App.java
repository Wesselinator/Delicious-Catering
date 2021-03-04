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

        pl("1. Admin");
        pl("2. Client");
        nl();
        int option = askInt("Enter option: ");

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
    }

}

// Make payment method
// Check details
// Change menu
// ^^^^^^^
// Login
//
//