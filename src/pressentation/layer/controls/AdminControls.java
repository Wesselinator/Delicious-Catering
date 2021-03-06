package pressentation.layer.controls;


import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.*;

import businesslogic.layer.Bookings;
import businesslogic.layer.Registrations;
import pressentation.layer.menu.ConsoleMenu;

public class AdminControls implements Controls {
    private String userName;
    private String passWord;

    private Bookings allBookings;
    private Registrations allclients;

    public AdminControls(Bookings bookings, Registrations clients) {
        this.userName = "Admin";
        this.passWord = "Admin";

        this.allBookings = bookings;
        this.allclients = clients;
    }

    private boolean bLogin(String username, String password) {
        return (this.userName.equals(username) && this.passWord.equals(password));
    }

    private boolean askAdminLogin(int attempts) {
        for (int i = 0; i < attempts; i++) {
            p("Login as client: ");
            pl(attempts-i + " attempts remain");
            String username = askString("Please input username");
            String password = askString("Please input password");
            if (bLogin(username, password)) {
                return true;
            }
        }
        pl("Max attempts reached!");
        return false;
    }

    @Override
    public void login() {
        if (!askAdminLogin(3)) {
            return;
        }

        menu();
    }

    private void menu(){
        ConsoleMenu adminMenu = new ConsoleMenu();

        adminMenu.add("Show all bookings", allBookings::toString);
        adminMenu.add("Show confirmations", Bookings::showDCBookingsList, allBookings::getConfirmedBookings);
        adminMenu.add("Register new client", allclients::registerClient, DCConsoleEngine::newClient);

        adminMenu.showUntilExit("Return to Main Menu");
    }
}
