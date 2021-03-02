package pressentation.layer;

import businesslogic.layer.Bookings;
import businesslogic.layer.*;
import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.*;

import java.time.*;
import java.util.*;

public final class ClientControls {
    //TODO: remove my insistence on taking a string for each parameter
    //TODO: move these helper classes maybe? Maybe rename this one?
    private ClientControls() {}

    public static DCClient newClient(String message) {
        pl(message);
        String fname = askString("Input first name:");
        String lname = askString("Input last name:");
        String number = askString("Input number:");
        return new DCClient(fname, lname, number);
    }

    public static DCVenue newVenue(String message) {
        pl(message);
        String venueName = askString("Input venue name:");
        String venueAddress = askString("Input venue address:");
        String venueNumber = askString("Input venue number:");
        return new DCVenue(venueName, venueAddress, venueNumber);
    }


    public static Menu newFullMenu(String message) {
        pl(message);
        String title = askString("Enter the menu's title: ");
        Menu ret = new Menu(title);

        for (boolean b = false; Boolean.FALSE.equals(b); b = askYesNo("Finished? : ")) {
            String item = askString("Enter the menu item: ");
            Double price = askDouble("Enter the price for the Item: ");
            ret.addMenuItem(item, price); 
        }

        return new Menu(title);
    }

    public static List<Menu> newFullMenus() {
        List<Menu> menus = new ArrayList<>();
        for (boolean b = false; Boolean.FALSE.equals(b); b = askYesNo("Want to add another menu? : ")) {
            menus.add(newFullMenu(""));
        }
        return menus;
    }

    private static DCEvent newFullEvent(String message) {
        String type = askString("Enter event type: ");
        LocalDateTime dt = askLDT("Enter the date for the event");
        int kidsTotal = askInt("Enter number of children attending");
        int adultsTotal = askInt("Enter number of adults attending");

        String decorations = "";
        if (askYesNo("Would you like to add decorations")) {
            decorations = askString("Enter the decorations you would like");
        }

        DCVenue venue = newVenue("");
        List<Menu> menus = newFullMenus();

        return new DCEvent(type, dt, venue, kidsTotal, adultsTotal, menus, decorations);
    }

    //TODO: finish this implementaiton or move somewhere else
    public static DCBooking newBookingAll(Bookings b) {

        // double basecost = askString("Input venue name:");
        // double paid = askString("Input venue address:");
        // String venueNumber = askString("Input venue number:");
        // DCBooking booking = new DCBooking(baseCost, paid, new Random().nextInt(1000000), client, event);
        // //DABooking.addDCBooking(booking);
    }
}
