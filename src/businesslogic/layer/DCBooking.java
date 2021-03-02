package businesslogic.layer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DCBooking implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Double baseCost;
    private Double paid;
    private String bookingNumber;
    private boolean confirmed = false;
    
    private DCClient client;
    public DCEvent event;

    //Constructor added
    public DCBooking(Double basecost, String bookingNumber, DCClient client, DCEvent event) {
        this.bookingNumber = bookingNumber;
        this.baseCost = basecost;
        this.client = client;
        this.event = event;
    }

    //g&s

    public Double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Double baseCost) {
        this.baseCost = baseCost;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    //no way of knowing of numbers alrady in use
    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public DCClient getClient() {
        return client;
    }

    public void setClient(DCClient client) {
        this.client = client;
    }

    public void setEvent(DCEvent event) {
        this.event = event;
    }

    public DCEvent getEvent() {

        DCEvent ret = new DCEvent(event);
        if (event.getPeople() > 40) { //"is above 40"
            ret.discountAdultMenus();
        }
        return ret;
    }

    //able to overpay, should do something about that
    public void addPayment(double ammount) {
        if (paid >= baseCost) {
            return; 
        }

        paid += ammount;

        if ( paid >= (baseCost*0.50) && ChronoUnit.DAYS.between(LocalDateTime.now(), event.getDtEvent()) > 15 ) {
            confirmed = true;
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        
        ret.append("Booking: " + bookingNumber);
        ret.append('\n');
        ret.append("Cost: " + baseCost);
        ret.append('\n');
        ret.append("Paid: " + paid);
        ret.append('\n');
        ret.append("Confirmed: " + confirmed);
        ret.append('\n');
        ret.append("Client: " + client.toString());
        ret.append('\n');
        ret.append("Event:\n" + getEvent().toString());
        ret.append('\n');

        return ret.toString();
    }

 
}
