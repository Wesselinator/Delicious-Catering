//This will be an object that will contain all the bookings for an instace.
//If we are doing data persistance, this object will be used to pull data and to push data to the medium.
//This object should contain methods to change, add, sort and extract information from ALL the bookings
package businesslogic.layer;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import dataaccess.layer.FileHandler;
import pressentation.layer.menu.ConsoleMenu;
import static pressentation.layer.ShortConsoleMethods.*;
import static pressentation.layer.Ask.*;

public class Bookings {
    
    private List<DCBooking> bookingsData = new ArrayList<>();

    public Bookings() {
        loadBookings();
    }

    //might be needed for deep copy
    public List<DCBooking> getBookings() {
        //List<DCBooking> ret = new ArrayList<>();
        //bookingsData.forEach(ret::add);
        return bookingsData;
    }

    //does not prevent double booking, only prevents adding the same booking twice
    public boolean addDCBooking(DCBooking booking) {
        if (bookingsData.contains(booking)) {
            //duplicate
            return false;
        } else {
            bookingsData.add(booking);
            saveBookings();
            return true;
        }
        
    }

    //helper functs

    //return true if the booking date (and time [!]) is open
    public boolean bookingDateOpen(LocalDateTime date)
    {
        //use dateTime compare methods instead
        for (DCBooking booking : bookingsData) {
            if (booking.getEvent().getDtEvent().equals(date)) {
                return false;
            }
        }
        return true;
    }

    public DCBooking findBooking(String bookingNumber) {
        for (DCBooking booking : bookingsData) {
            if (booking.getBookingNumber().equals(bookingNumber)) {
                return booking;
            }
        }

        return null; //more elegant fail solution?
    }

    /* public List<String> getListOfBookingNumbersForActiveClient(DCClient activeClient) {
        return getActiveClientBookings(activeClient).stream().map(DCBooking::toString).collect(Collectors.toList());
        //bookingsData.forEach(bd -> ret.add(""+bd.getBookingNumber()));
        //return ret;
    } */


    public List<DCBooking> getConfirmedBookings() {
        return bookingsData.stream().filter(DCBooking::isConfirmed).collect(Collectors.toList());
    }

    public List<DCBooking> getActiveClientBookings(DCClient activeClient) {
        return bookingsData.stream().filter(b -> b.getClient().equals(activeClient)).collect(Collectors.toList());
    }

    //data

    public void saveBookings () {
        FileHandler.writeBookings(bookingsData);
    }

    //you want to handle every thing in here
    public void loadBookings() {
        bookingsData = FileHandler.readBookings();
    }

    //toStrings

    public static String dcBookingsListToString(List<DCBooking> blist) {
        List<String> ret = new ArrayList<>();
        blist.forEach(bd -> ret.add(bd.toString()));
        return String.join("\n", ret);
    }

    @Override
    public String toString() {
        return dcBookingsListToString(bookingsData);
    }

    //present
    public static void showDCBookingsList(List<DCBooking> blist) {
        pl(dcBookingsListToString(blist));
    }

    
    public void editBookings(DCClient activeClient) {
        ConsoleMenu bookingMenu = new ConsoleMenu();

        pl("Please select the Booking you want to edit");
        nl();
        for (DCBooking booking : getActiveClientBookings(activeClient)) {
            bookingMenu.add(booking.getBookingNumber(), booking::editBooking);
        }
        bookingMenu.showUntilExit("Return to Main Menu");
    }

    //TODO: Generalize
    public void payBookings(DCClient activeClient) {
        ConsoleMenu bookingMenu = new ConsoleMenu();

        pl("Please select the Booking you want to add funds to");
        nl();
        for (DCBooking booking : getActiveClientBookings(activeClient)) {
            bookingMenu.add(booking.getBookingNumber(), booking::addPayment, () -> askDouble("How much do you want to pay? (Status:"+booking.getPaymentStatus()+")"));
        }

        bookingMenu.showUntilExit("Return to Main Menu");
    }


}
