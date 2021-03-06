//holds all the relavant data for a venue
package businesslogic.layer;

import static pressentation.layer.Ask.*;

import pressentation.layer.menu.ConsoleMenu;

public class DCVenue implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String venueName; //venue name
    private String adress; //the adress for the venue
    private String number; //the number to contact the venue

    //others

    public DCVenue(String venueName, String adress, String number) {
        this.venueName = venueName;
        this.adress = adress;
        this.number = number;
    }

    public DCVenue(DCVenue copy) {
        this(copy.venueName, copy.adress, copy.number);
        //yeah! all varables are value based!
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }




    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        
        ret.append(venueName + " which is located at " + adress);
        ret.append('\n');
        ret.append("Contact Number: " + number);

        return ret.toString();
    }

    //present
    public void editVenue() {
        ConsoleMenu venueEdit = new ConsoleMenu();

        venueEdit.add("Edit Venue name", this::setVenueName, () -> askString("Enter new Venue name (Was '"+venueName+"'): "));
        venueEdit.add("Edit Adress", this::setAdress, () -> askString("Enter new Adress (Was '"+adress+"'): "));
        venueEdit.add("Edit Number", this::setNumber, () -> askString("Enter new Number (Was '"+number+"'): "));

        venueEdit.showUntilExit("Return");
    }

}
