//holds all the relavant data for a venue
package businesslogic.layer;

public class DCVenue {
    private String venueName; //venue Name
    private String adress; //the adress for the venue
    private String number; //the number to contact the venue
    //others

    public DCVenue(String venueName, String adress, String number) {
        this.venueName = venueName;
        this.adress = adress;
        this.number = number;
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
        
        ret.append(venueName + " is located at " + adress);
        ret.append('\n');
        ret.append("They can be contacted at:" + number);

        return ret.toString();
    }

}