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

    public void deleteAll() {
        bookingsData.remove(1);
        bookingsData.clear();
        saveBookings();
    }

    public boolean addBooking(DCBooking booking) {
        String bookingNumber = "";
        do {
            bookingNumber = String.format("%s", (Math.round(Math.random() * 1000000)));    
        } while (!bookingNumberOpen(bookingNumber));

        booking.setBookingNumber(bookingNumber);

        if (bookingsData.contains(booking)) {
            pl("FAILED - This Booking Exists");
            return false;
        } else {
            bookingsData.add(booking);
            pl("Booking Added!");
            saveBookings();
            return true;
        }
        
    }

    //helper functs

    public boolean bookingNumberOpen(String bookingNumber) {
        boolean found = false;
        for (DCBooking booking : bookingsData) {
            found |= booking.getBookingNumber().equals(bookingNumber);
        }
        return !found;
    }

    public static boolean bookingDateOpen(LocalDateTime date)
    {
        List<DCBooking> savedBookings = FileHandler.readBookings();

        for (DCBooking booking : savedBookings) {
            //only grab the date part of the DateTime and compare them
            if (booking.getEvent().getDtEvent().toLocalDate().equals(date.toLocalDate())) {
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

        return null;
    }


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
        return blist.stream().map(DCBooking::toString).collect(Collectors.joining("\n"));
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
        bookingMenu.showUntilExit("Save and return to Client Menu");
        saveBookings();
    }

    public void payBookings(DCClient activeClient) {
        ConsoleMenu bookingMenu = new ConsoleMenu();

        pl("Please select the Booking you want to add funds to");
        nl();
        for (DCBooking booking : getActiveClientBookings(activeClient)) {
            bookingMenu.add(booking.getBookingNumber(), booking::addPayment, () -> askDouble("How much do you want to pay? (Status: "+booking.getPaymentStatus()+")"));
        }

        bookingMenu.showUntilExit("Return to Client Menu");
        saveBookings();
    }


}
