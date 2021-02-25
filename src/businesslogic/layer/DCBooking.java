package businesslogic.layer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DCBooking {
    public Double baseCost;
    public Double paid;
    public int bookingNumber;
    private boolean confirmed = false;

    public DCClient client;
    private DCEvent event;
    
    //THIS WILL NOT WORK
    //Java is refrence passed so we'll end up changing the og anyway
    //The Way to fix it is to implement DEEP COPING on all the object leading up to DCEvent which isn't difficult just work
    //I think we should just rethink how to do the same thing without needing a copy
    public DCEvent getEvent() {

        DCEvent ret = event;
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

        if ( paid >= (baseCost*0.50) && ChronoUnit.DAYS.between(LocalDateTime.now(), event.dtEvent) > 15 ) {
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

    //make sure all is here
}
