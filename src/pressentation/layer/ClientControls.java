package pressentation.layer;

import businesslogic.layer.Menu;
import dataaccess.layer.Bookings;
import businesslogic.layer.DCBooking;
import businesslogic.layer.DCClient;
import businesslogic.layer.DCEvent;
import businesslogic.layer.DCVenue;
import static pressentation.layer.Ask.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.text.SimpleDateFormat;

public final class ClientControls {

    public static void register() {
        // instantiate client
        String fname = askString("Input first name:");
        String lname = askString("Input last name:");
        String number = askString("Input number:");
        DCClient client = new DCClient(fname, lname, number);

        // Instantiate venue
        String venueName = askString("Input venue name:");
        String venueAddress = askString("Input venue address:");
        String venueNumber = askString("Input venue number:");
        DCVenue venue = new DCVenue(venueName, venueAddress, venueNumber);

        // instantiate menu
        List<Menu> menus = new ArrayList<>();
        while (true) {

            String title = askString("Enter menu title:");
            boolean adultmenu = askBoolean("Is this an adult menu 0/1");
            boolean flag = true;
            List<String> menuItem = new ArrayList<String>();
            List<Double> menuPrices = new ArrayList<Double>();
            while (flag) {
                menuItem.add(askString("Enter a menu item:"));
                menuPrices.add(askDouble("Enter prices for that item:"));
                String cont = askString("Would you like to add another item y/n");
                if (cont.toUpperCase() == "N") {
                    flag = false;
                }
            }
            menus.add(new Menu(title, menuItem, menuPrices, adultmenu));

            String cont = askString("Would you like to add another Menu y/n");
            if (cont.toUpperCase() == "N") {
                break;
            }

        }

 
    }
}
