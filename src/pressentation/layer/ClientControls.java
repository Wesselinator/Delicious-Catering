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

        // instantiate event
        String type = askString("Enter event type");
        LocalDateTime dateTime = LocalDateTime.parse(askString("Enter date: yyyy-MM-dd HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int kidsTotal = askInt("Enter number of children attending");
        int adultsTotal = askInt("Enter number of adults attending");
        String decorations = "";
        String DecorationYesOrNo = askString("Would you like to add decorations");
        if (DecorationYesOrNo.toUpperCase() == "Y") {
            decorations = askString("Enter the decorations you would like");
        }

        DCEvent event = new DCEvent(type, dateTime, venue, kidsTotal, adultsTotal, menus, decorations);

        // CLIENT can input base cost, change if necessary
        double baseCost = askInt("Enter base cost:");
        String PaymentNow = askString("Would you like to pay ahead? y/n");
        double paid = 0;
        if (PaymentNow.toUpperCase() == "Y") {
            paid = askDouble("Enter the amount you would like to pay:");
        }
        //adding registered booking to booking list (datalayer)
        Bookings DABooking = new Bookings();
        DCBooking booking = new DCBooking(baseCost, paid, new Random().nextInt(1000000), client, event);
        DABooking.addDCBooking(booking);
    }
}
