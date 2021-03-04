package pressentation.layer;

import pressentation.layer.controls.*;
import pressentation.layer.menu.ConsoleMenu;
import pressentation.layer.menu.MenuItem;
import businesslogic.layer.*;

//TODO: Double check requirements
public class App {
    public static void main(String[] args) {
        Bookings allBookings = new Bookings();
        Registrations allClients = new Registrations();

        Controls adminControls = new AdminControls(allBookings, allClients);
        Controls clientControls = new ClientControls(allBookings, allClients);


        //introduce

        ConsoleMenu MainMenu = new ConsoleMenu();
        MainMenu.add(new MenuItem("Admin", adminControls::login));
        MainMenu.add(new MenuItem("Client", clientControls::login));

        MainMenu.showUntilExit("Exit");
    }

    

}