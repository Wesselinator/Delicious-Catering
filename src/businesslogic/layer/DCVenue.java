//holds all the relavant data for a venue
package businesslogic.layer;

public class DCVenue {
    private String venueName; //venue Name
    private String adress; //the adress for the venue
    private String number; //the number to contact the venue
    //others

    public DCVenue(String adress, String number) {
        this.adress = adress;
        this.number = number;
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
        // TODO Auto-generated method stub
        return super.toString();
    }
}