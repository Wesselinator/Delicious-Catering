//This will be an object that will contain all the bookings for an instace.
//If we are doing data persistance, this object will be used to pull data and to push data to the medium.
//This object should contain methods to change, add, sort and extract information from ALL the bookings
package dataaccess.layer;

import java.time.LocalDateTime;
import java.util.*;

import businesslogic.layer.DCBooking;
import businesslogic.layer.DCClient;

public class Bookings {
    private static final long serialVersionUID = 1L;
    private ArrayList<DCBooking> bookingsData = new ArrayList<>();

    //does not prevent double booking, only prevents adding the same booking twice
    public boolean addDCBooking(DCBooking booking) {
        if (bookingsData.contains(booking)) {
            //duplicate
            return false;
        } else {
            bookingsData.add(booking);
            return true;
        }
        
    }

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

    public DCBooking findBooking(int bookingNumber) {
        for (DCBooking booking : bookingsData) {
            if (booking.getBookingNumber() == bookingNumber) {
                return booking;
            }
        }

        return null; //more elegant fail solution?
    }

    public List<DCBooking> getBookings() {
        return bookingsData;
    }

    public List<String> getListOfBookingNumbers() {
        List<String> ret = new ArrayList<>();
        bookingsData.forEach(bd -> ret.add(""+bd.getBookingNumber()));
        return ret;
    }

    public List<DCClient> getBookedClients() {
        List<DCClient> ret = new ArrayList<>();
        bookingsData.forEach(bd -> ret.add(bd.getClient()));
        return ret;
    }

    public void saveBookings () {
        FileHandler.writeBookings(bookingsData);
    }

    @Override
    public String toString() {
        List<String> ret = new ArrayList<>();
        bookingsData.forEach(bd -> ret.add(bd.toString()));
        return String.join("\n", ret);
    }
}
