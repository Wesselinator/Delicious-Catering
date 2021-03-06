//this is a data class that holds all the data for an Event
package businesslogic.layer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import pressentation.layer.menu.ConsoleMenu;
import static pressentation.layer.Ask.*;
import pressentation.layer.controls.DCConsoleEngine;

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

    private ArrayList<DCMenu> menus;

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
        this.dtEvent = copy.dtEvent; //this is probably shallow, but we don't use it anywhere so its fine (?)
        this.venue = new DCVenue(copy.venue); //deep
        this.kids = copy.kids; //value
        this.adults = copy.adults; //value

        //this.menus = new ArrayList<>(copy.menus); //idk if this is deep or shallow
        //verbose version
        this.menus = new ArrayList<>();
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

    private static final DateTimeFormatter DTfmt = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("A "+type+" Event");
        ret.append(" is Scheduled for " + DTfmt.format(dtEvent) + " at " + venue.toString());
        ret.append("\n\n");
        ret.append( String.format("%s people will be attending. (%s kids and %s adults)", getPeople(), kids, adults) );
        ret.append("\n\n");
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

        eventEdit.add("Edit Type", this::setType, () -> askString("Enter new type (Was '"+type+"'): "));
        eventEdit.add("Edit Date Time", this::setDtEvent, () -> askLDT("Enter new date and time (Was '"+dtEvent.toString()+"'): ")); //no DT checking
        eventEdit.add("Edit Amount of kids", this::setKids, () -> askInt("Enter new amount kids (Was "+kids+"): "));
        eventEdit.add("Edit Amount of adults", this::setAdults, () -> askInt("Enter new amount adults (Was "+adults+"): "));
        eventEdit.add("Edit Venue", venue::editVenue);
        eventEdit.add("Edit Menus", this::editMenus);

        if (decoration) {
            eventEdit.add("Edit your Decoration Request", this::setClientDecoRequest, () -> askString("Enter new value (Was "+clientDecoRequest+"): "));
        } else {
            eventEdit.add("Add a Decoration Request", this::setClientDecoRequest, () -> askString("Enter your request : "));
        }

        eventEdit.showUntilExit("Return");
    }

    private void editMenus() {
        ConsoleMenu menuEdit = new ConsoleMenu();

        do {
            menuEdit.clear();
            menuEdit.add("Add another menu", menus::add, DCConsoleEngine::newFullMenu);
            for (DCMenu menu : menus) {
                menuEdit.add("Edit " + menu.toShortString(), menu::editMenu);
            }
        } while (menuEdit.show("Return"));        
    }
    
}
