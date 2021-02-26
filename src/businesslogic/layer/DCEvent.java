//this is a data class that holds all the data for an Event
package businesslogic.layer;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class DCEvent {
    public String type;
    public LocalDateTime dtEvent;
    public DCVenue venue;

    public int kids;
    public int adults;

    public int getPeople() {
        return kids + adults;
    }

    public List<Menu> menus = new ArrayList<>();

    public boolean decoration;
    public String clientDecoRequest;
    //ask additional questions

    public DCEvent(String type, LocalDateTime dtEvent, DCVenue venue, int kids, int adults, List<Menu> menus, String clientDecoRequest) {
        this.type = type;
        this.dtEvent = dtEvent;
        this.venue = venue;
        this.kids = kids;
        this.adults = adults;
        this.menus = menus;

        if (!clientDecoRequest.isEmpty()) {
            this.clientDecoRequest = clientDecoRequest;
            this.decoration = true;
        }
        else {
            this.clientDecoRequest = "";
            this.decoration = false;
        }
    }

    public DCEvent(DCEvent copy) {
        this.type = copy.type; //value
        this.dtEvent = copy.dtEvent; //this is probably shallow, but we don't need a copy of this one so its finr (?)
        this.venue = new DCVenue(copy.venue); //deep
        this.kids = copy.kids; //value
        this.adults = copy.adults; //value

        //this.menus = new ArrayList<>(copy.menus); //idk if this is deep or shallow
        //verbose version
        for (Menu menu : copy.menus) {
            this.menus.add(new Menu(menu));
        }

        this.decoration = copy.decoration; //value
        this.clientDecoRequest = copy.clientDecoRequest; //value
    }

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
