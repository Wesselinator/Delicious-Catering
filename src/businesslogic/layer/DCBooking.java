package businesslogic.layer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import pressentation.layer.menu.ConsoleMenu;
import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.pl;

public class DCBooking implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Double baseCost;
    private Double paid;
    private String bookingNumber;
    private boolean confirmed = false;
    
    private DCClient client;
    private DCEvent event;

    //Constructor added
    public DCBooking(Double basecost, DCClient client, DCEvent event) {
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

    public Double getPaid() {
        return paid;
    }

    public String getPaymentStatus() {
        StringBuilder sBuilder = new StringBuilder();

        if (paid == 0) {
            sBuilder.append("Not Paid at all!");
        } else if (paid < baseCost) {
            sBuilder.append("Not Fully Paid!");
        } else {
            sBuilder.append("Fully Paid!");
        }

        if (isConfirmed()) {
            sBuilder.append(" Booking is confirmed");
        }

        return sBuilder.toString();
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

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

    //helper methods

    public void addPayment(double ammount) {
        double newPaid = paid + ammount;
        if (newPaid >= baseCost) {
            pl(String.format("You over payed, and R%.2f has been returened to your account ", newPaid-baseCost));
            return; 
        }

        paid = newPaid;

        if ( paid >= (baseCost*0.50) && ChronoUnit.DAYS.between(LocalDateTime.now(), event.getDtEvent()) > 15 ) {
            confirmed = true;
        }
    }

    //TODO: Consider using payment status
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

    //present
    public void editBooking() {
        ConsoleMenu bookingEdit = new ConsoleMenu();

        bookingEdit.add("Edit Base Cost", this::setBaseCost, () -> askDouble("Enter new value (was R"+baseCost+"): "));
        bookingEdit.add("Edit Event", event::editEvent);

        bookingEdit.showUntilExit("Return to Main Menu");
    }
}
