//this is a data class that holds all the data for an Event
package businesslogic.layer;

import java.time.*;

public class DCEvent {
    public String type;
    public LocalDateTime dtEvent;
    public DCVenue venue;

    public int kids;
    public int adults;

    public int getPeople() {
        return kids + adults;
    }

    public Menu[] menus;

    public boolean decoration;
    public String clientDecoRequest;
    //ask additional questions

    //do setters and getters when needed

    //other

    public void discountAdultMenus() {
        for (Menu menu : menus) {
            menu.applyAdultDiscount();
        }
    }

    //override

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("A "+type+" Event");
        ret.append('\n');
        ret.append("Is Scheduled for " + dtEvent.toString() + " At:\n" + venue.toString());
        ret.append("\n\n");
        ret.append( String.format("%s people will be attending. (%s kids and %s adults)", getPeople(), kids, adults) );
        ret.append('\n');
        ret.append("The menus are:");
        for (Menu menu : menus) {
            ret.append('\n');
            ret.append(menu.toString());
        }
        if (decoration) {
            ret.append('\n');
            ret.append("The client has specified decorations.\n");
            ret.append("They requested: \"" + clientDecoRequest + "\"");
        }

        return ret.toString();
    }

    
}
