package businesslogic.layer;

public class DCClient {
    private String fname;
    private String lname;
    private String number;

    public DCClient(String fname, String lname, String number) {
        this.fname = fname;
        this.lname = lname;
        this.number = number;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "DCClient [First Name=" + fname + ", Last Name=" + lname + ", number=" + number + "]";
    }
}
