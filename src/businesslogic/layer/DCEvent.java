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

    @Override
    public String toString() {
        StringBuilder sbRet = new StringBuilder();
        sbRet.append("A "+type+" Event\n");
        sbRet.append("Is Scheduled for " + dtEvent.toString() + " At " + venue.toString()+"\n");
        sbRet.append( String.format("%s people will be attending. (%s kids and %s adults)%n", getPeople(), kids, adults) );
        sbRet.append("menues"); //todo
        if (decoration) {
            sbRet.append("The client has specified decorations.\n");
            sbRet.append("They requested: \"" + clientDecoRequest + "\"\n");
        }
        return sbRet.toString();
    }

    
}
