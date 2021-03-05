package pressentation.layer;

import pressentation.layer.controls.*;
import pressentation.layer.menu.ConsoleMenu;
import pressentation.layer.menu.MenuItem;
import businesslogic.layer.*;
import static pressentation.layer.ShortConsoleMethods.*;

public class App {
    private static final String DCART = " ___      ___  _      ____   __  ____  ___   __ __  _____        __   ____  ______    ___  ____   ____  ____    ____ \r\n|   \\    /  _]| |    |    | /  ]|    |/   \\ |  |  |/ ___/       /  ] /    ||      |  /  _]|    \\ |    ||    \\  /    |\r\n|    \\  /  [_ | |     |  | /  /  |  ||     ||  |  (   \\_       /  / |  o  ||      | /  [_ |  D  ) |  | |  _  ||   __|\r\n|  D  ||    _]| |___  |  |/  /   |  ||  O  ||  |  |\\__  |     /  /  |     ||_|  |_||    _]|    /  |  | |  |  ||  |  |\r\n|     ||   [_ |     | |  /   \\_  |  ||     ||  :  |/  \\ |    /   \\_ |  _  |  |  |  |   [_ |    \\  |  | |  |  ||  |_ |\r\n|     ||     ||     | |  \\     | |  ||     ||     |\\    |    \\     ||  |  |  |  |  |     ||  .  \\ |  | |  |  ||     |\r\n|_____||_____||_____||____\\____||____|\\___/  \\__,_| \\___|     \\____||__|__|  |__|  |_____||__|\\_||____||__|__||___,_|";
    public static void main(String[] args) {
        Bookings allBookings = new Bookings();
        Registrations allClients = new Registrations();

        Controls adminControls = new AdminControls(allBookings, allClients);
        Controls clientControls = new ClientControls(allBookings, allClients);


        System.out.println(DCART);
        pl("Welcome!"); //or someting

        ConsoleMenu MainMenu = new ConsoleMenu();
        MainMenu.add(new MenuItem("Admin", adminControls::login));
        MainMenu.add(new MenuItem("Client", clientControls::login));

        MainMenu.showUntilExit("Exit");
    }

    

}
