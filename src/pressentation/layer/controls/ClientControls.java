package pressentation.layer.controls;

import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.*;
import static pressentation.layer.controls.DCConsoleEngine.*;

import businesslogic.layer.Bookings;
import businesslogic.layer.DCClient;
import businesslogic.layer.Registrations;
import pressentation.layer.menu.ConsoleMenu;

public class ClientControls implements Controls {
    private Bookings allBookings;
    private Registrations allclients;

    public ClientControls(Bookings bookings, Registrations clients) {
        this.allBookings = bookings;
        this.allclients = clients;
    }

    private DCClient activeClient = null;

    private boolean bLogin(DCClient test) {
        return allclients.getClients().contains(test);
    }

    private boolean askClientLogin(int attempts) {
        for (int i = 0; i < attempts; i++) {
            pl(attempts-i + " attempts remain");
            String fname = askString("Please input First Name");
            String lname = askString("Please input Last Name");
            String number = askString("Please input Number");
            DCClient test = new DCClient(fname, lname, number);
            if (bLogin(test)) {
                activeClient = test;
                return true;
            }
        }
        pl("Max attempts reached!");
        return false;
    }

    @Override
    public void login() {
        activeClient = null;
        if (!askClientLogin(3)) {
            return;
        }


        menu();    
    }

    //TODO: Implement the  add booking  logic
    private void menu() {
        ConsoleMenu clientMenu = new ConsoleMenu();

        clientMenu.add("Show Bookings", Bookings::showDCBookingsList, () -> allBookings.getActiveClientBookings(activeClient));
        //
        clientMenu.add("Pay Bookings", allBookings::payBookings,  () -> activeClient);
        clientMenu.add("Edit Bookings", allBookings::editBookings, () -> activeClient);

        clientMenu.showUntilExit("Return to Main Menu");
    }

    

}
