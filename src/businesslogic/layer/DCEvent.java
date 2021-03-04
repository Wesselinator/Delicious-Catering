//this is a data class that holds all the data for an Event
package businesslogic.layer;

import java.time.*;
import java.util.*;

import pressentation.layer.menu.ConsoleMenu;
import static pressentation.layer.Ask.*;

public class DCEvent implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private LocalDateTime dtEvent;
    private DCVenue venue;

    private int kids;
    private int adults;

    public int getPeople() {
        return kids + adults;
    }

    private ArrayList<DCMenu> menus = new ArrayList<>();

    private boolean decoration;
    private String clientDecoRequest;

    public DCEvent(String type, LocalDateTime dtEvent, DCVenue venue, int kids, int adults, List<DCMenu> menus, String clientDecoRequest) {
        this.type = type;
        this.dtEvent = dtEvent;
        this.venue = venue;
        this.kids = kids;
        this.adults = adults;
        this.menus = (ArrayList<DCMenu>) menus;

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
        this.dtEvent = copy.dtEvent; //this is probably shallow, but we don't need a copy of this one so its fine (?)
        this.venue = new DCVenue(copy.venue); //deep
        this.kids = copy.kids; //value
        this.adults = copy.adults; //value

        //this.menus = new ArrayList<>(copy.menus); //idk if this is deep or shallow
        //verbose version
        for (DCMenu menu : copy.menus) {
            this.menus.add(new DCMenu(menu));
        }

        this.decoration = copy.decoration; //value
        this.clientDecoRequest = copy.clientDecoRequest; //value
    }

    //s&g

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public void setDtEvent(LocalDateTime dtEvent) {
        this.dtEvent = dtEvent;
    }

    public DCVenue getVenue() {
        return venue;
    }

    public void setVenue(DCVenue venue) {
        this.venue = venue;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public List<DCMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<DCMenu> menus) {
        this.menus = (ArrayList<DCMenu>) menus;
    }

    public String getClientDecoRequest() {
        return clientDecoRequest;
    }

    public void setClientDecoRequest(String clientDecoRequest) {
        decoration = !clientDecoRequest.isEmpty();
        this.clientDecoRequest = clientDecoRequest;
    }

    //other

    public void discountAdultMenus() {
        for (DCMenu menu : menus) {
            menu.applyAdultDiscount();
        }
    }

    public List<String> getMenusNames() {
        List<String> ret = new ArrayList<>();
        menus.forEach(men -> ret.add(men.getTitle()));
        return ret;
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
        for (DCMenu menu : menus) {
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

    //present

    public void editEvent() {
        ConsoleMenu eventEdit = new ConsoleMenu();

        eventEdit.add("Edit Type", this::setType, () -> askString("Enter new value (Was '"+type+"'): "));
        eventEdit.add("Edit Date Time", this::setDtEvent, () -> askLDT("Enter new value (Was '"+dtEvent.toString()+"'): ")); //no checking
        eventEdit.add("Edit Amount of kids", this::setKids, () -> askInt("Enter new value (Was "+kids+"): "));
        eventEdit.add("Edit Amount of kids", this::setAdults, () -> askInt("Enter new value (Was "+adults+"): "));
        eventEdit.add("Edit Venue", venue::editVenue);
        eventEdit.add("Edit Menus", this::editMenus);

        if (decoration) {
            eventEdit.add("Edit your Decoration Request", this::setClientDecoRequest, () -> askString("Enter new value (Was "+clientDecoRequest+"): "));
        } else {
            eventEdit.add("Add a Decoration Request", this::setClientDecoRequest, () -> askString("Enter your request : "));
        }

        eventEdit.showUntilExit("Return to Main Menu");
    }

    private void editMenus() {
        ConsoleMenu menuEdit = new ConsoleMenu();

        for (DCMenu menu : menus) {
            menuEdit.add("Edit " + menu.toShortString(), menu::editMenu);
        }

        menuEdit.showUntilExit("Return to Main Menu");
    }
    
}
