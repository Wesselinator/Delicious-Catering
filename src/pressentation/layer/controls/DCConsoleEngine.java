package pressentation.layer.controls;

import businesslogic.layer.*;
import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.*;

import java.time.*;
import java.util.*;

//TODO: add feedback strings like "this correct?" if time allows
public final class DCConsoleEngine {

    private DCConsoleEngine() {}

    public static DCClient newClient() {
        String fname = askString("Input first name:");
        String lname = askString("Input last name:");
        String number = askString("Input number:");
        return new DCClient(fname, lname, number);
    }

    //TODO: test
    public static DCBooking newBooking(DCClient activeClient) {
        Double basecost = askDouble("Enter the total Cost of the booking");
        DCEvent event = newFullEvent();
        return new DCBooking(basecost, activeClient, event);
    }

    public static DCVenue newVenue() {
        String venueName = askString("Input venue name:");
        String venueAddress = askString("Input venue address:");
        String venueNumber = askString("Input venue number:");
        return new DCVenue(venueName, venueAddress, venueNumber);
    }

    public static DCMenu newFullMenu() {
        String title = askString("Enter the menu's title: ");
        DCMenu ret = new DCMenu(title);
        if (askYesNo("Is this an Adult menu? Y/N")) {
            ret.setAdult();
        }

        for (boolean b = false; Boolean.FALSE.equals(b); b = askYesNo("Finished? Y/N: ")) {
            String item = askString("Enter the menu item: ");
            Double price = askDouble("Enter the price for the Item: ");
            ret.addMenuItem(item, price);
        }

        return new DCMenu(title);
    }

    public static List<DCMenu> newFullMenus() {
        List<DCMenu> menus = new ArrayList<>();
        for (boolean b = true; Boolean.TRUE.equals(b); b = askYesNo("Want to add another menu? Y/N: ")) {
            menus.add(newFullMenu());
        }
        return menus;
    }

    private static DCEvent newFullEvent() {
        String type = askString("Enter event type: ");

        Bookings allbookings = new Bookings();
        LocalDateTime dt = null;
        while (true) {
            if (allbookings.bookingDateOpen(dt = askLDT("Enter the date for the event"))) {
                break;
            }
            pl("The chosen data has already been booked, please choose another date.");
        }
        
        int kidsTotal = askInt("Enter number of children attending");
        int adultsTotal = askInt("Enter number of adults attending");

        String decorations = "";
        if (Boolean.TRUE.equals(askYesNo("Would you like to add decorations? Y/N"))) {
            decorations = askString("Enter the decorations you would like");
        }

        DCVenue venue = newVenue();
        List<DCMenu> menus = newFullMenus();

        //TODO: Indicate Booking complete, Need to show client menu again

        return new DCEvent(type, dt, venue, kidsTotal, adultsTotal, menus, decorations);
    }
    
}
