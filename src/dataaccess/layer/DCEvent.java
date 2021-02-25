//this is a data class that holds all the data for an Event
package dataaccess.layer;

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

    public Menu[] Menu;

    public boolean decoration;
    //ask additional questions

    //not doing getters and setters yet, waiting for all the questions we need to ask
}
