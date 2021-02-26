package businesslogic.layer;

public class DCClient implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
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
        return "First Name=" + fname + ", Last Name=" + lname + ", Phone Number=" + number;
    }


    //auto gen
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fname == null) ? 0 : fname.hashCode());
        result = prime * result + ((lname == null) ? 0 : lname.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DCClient other = (DCClient) obj;
        if (fname == null) {
            if (other.fname != null)
                return false;
        } else if (!fname.equals(other.fname))
            return false;
        if (lname == null) {
            if (other.lname != null)
                return false;
        } else if (!lname.equals(other.lname))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        return true;
    }
}
