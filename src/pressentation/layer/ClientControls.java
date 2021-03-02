package pressentation.layer;

import businesslogic.layer.Bookings;
import businesslogic.layer.*;
import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.*;

import java.time.*;
import java.util.*;

public final class ClientControls {
    // TODO: remove my insistence on taking a string for each parameter
    // TODO: move these helper classes maybe? Maybe rename this one?
    public static Bookings allBookings;

    private ClientControls() {
    }

    public static DCClient newClient() {
        String fname = askString("Input first name:");
        String lname = askString("Input last name:");
        String number = askString("Input number:");
        return new DCClient(fname, lname, number);
    }

    public static DCVenue newVenue() {
        String venueName = askString("Input venue name:");
        String venueAddress = askString("Input venue address:");
        String venueNumber = askString("Input venue number:");
        return new DCVenue(venueName, venueAddress, venueNumber);
    }

    public static Menu newFullMenu() {
        String title = askString("Enter the menu's title: ");
        Menu ret = new Menu(title);

        for (boolean b = false; Boolean.FALSE.equals(b); b = askYesNo("Finished? Y/N: ")) {
            String item = askString("Enter the menu item: ");
            Double price = askDouble("Enter the price for the Item: ");
            ret.addMenuItem(item, price);
        }

        return new Menu(title);
    }

    public static List<Menu> newFullMenus() {
        List<Menu> menus = new ArrayList<>();
        for (boolean b = true; Boolean.TRUE.equals(b); b = askYesNo("Want to add another menu? : ")) {
            menus.add(newFullMenu());
        }
        return menus;
    }

    private static DCEvent newFullEvent() {
        String type = askString("Enter event type: ");
        LocalDateTime dt = askLDT("Enter the date for the event");
        int kidsTotal = askInt("Enter number of children attending");
        int adultsTotal = askInt("Enter number of adults attending");

        String decorations = "";
        if (askYesNo("Would you like to add decorations y/n")) {
            decorations = askString("Enter the decorations you would like");
        }

        DCVenue venue = newVenue();
        List<Menu> menus = newFullMenus();

        return new DCEvent(type, dt, venue, kidsTotal, adultsTotal, menus, decorations);
    }

    // TODO: finish this implementaiton or move somewhere else
    public static void newBookingAll() {

        String bookingNumber;

        while (true) {
            bookingNumber = Integer.toString(new Random().nextInt(1000000));
            if (!allBookings.getListOfBookingNumbers().contains(bookingNumber)) {
                break;
            }
        }
        DCClient client = newClient();
        DCEvent event = newFullEvent();
        double basecost = askDouble("What is the base cost?");

        allBookings.addDCBooking(new DCBooking(basecost, bookingNumber, client, event));
    }

    public static void clientLogin() {
        DCBooking clientBooking;
        String bookingNumber = askString("Enter you booking number");
        if (allBookings.getListOfBookingNumbers().contains(bookingNumber)) {
            clientBooking = allBookings.findBooking(bookingNumber);
            clientFunctions(clientBooking);
        } else {
            p("Booking number not found!");
        }
    }

    public static void clientFunctions(DCBooking clientBooking) {

        int option3 = askInt("1.Make payment\n2.Check details\n3.Change Menu");
        if (option3 == 1) {
            double ammount = askDouble("Enter ammount you want to pay");
            clientBooking.addPayment(ammount);
            // Dont know how to get the updated client details in the list and replace the
            // old client(outdated client)
        } else if (option3 == 2) {
            System.out.println(clientBooking);
        } else if (option3 == 3) {

        }
    }
    
}
