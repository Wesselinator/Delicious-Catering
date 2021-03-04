package pressentation.layer;


import static pressentation.layer.ShortConsoleMethods.*;

import static pressentation.layer.Ask.*;

import businesslogic.layer.Bookings;
import businesslogic.layer.Registrations;

public class AdminControls {
    private String userName;
    private String passWord;

    private Bookings allBookings;
    private Registrations allclients;

    public AdminControls(Bookings bookings, Registrations clients) {
        this.userName = "Admin";
        this.passWord = "Admin";

        this.allBookings = bookings;
        this.allclients = clients;
    }



    public boolean login(String username, String password) {
        return (this.userName.equals(username) && this.passWord.equals(password));
    }

    public boolean adminLogin() {
        String username = askString("Please input username");
        String password = askString("Please input password");
        return login(username, password);
    }

    public void adminOptionMenu(){
        pl("1. Show all bookings");
        pl("2. Show pending confirmations");
        nl();
        int option = askInt("Enter Option: ");

        if(option==1){
            pl(allBookings.toString());
        } else if(option == 2){
            pl(Bookings.dcBookingsListToString(allBookings.getConfirmedBookings()));
        }

    }
}
