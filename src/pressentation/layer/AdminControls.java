package pressentation.layer;

import static pressentation.layer.ConsolePresentation.*;
import static pressentation.layer.ShortConsoleMethods.*;

import java.util.ArrayList;
import java.util.List;

import static pressentation.layer.Ask.*;

import businesslogic.layer.Bookings;
import businesslogic.layer.DCBooking;
import businesslogic.layer.DCClient;
import businesslogic.layer.Bookings.*;
import businesslogic.layer.DCClient.*;

public class AdminControls {
    private String userName;
    private String passWord;

    public AdminControls() {
        this.userName = "Admin";
        this.passWord = "Admin";
    }


    public boolean login(String username, String password) {
        return (this.userName.equals(username) && this.passWord.equals(password));
    }

    public void adminOptionMenu(){
        int option = askInt("1. Show all bookings\n2. Show pending confirmations");
        if(option==1){
            showBookings();
        } else if(option == 2){
            //confirmation();
        }

    }

    //TODO: No bookings appear because there are no booking items currently in the system
    public void showBookings() {
        Bookings booking = new Bookings();
       
        List<DCBooking> bookings =  booking.getBookings();
        for (DCBooking dcBooking : bookings) {
            System.out.println(dcBooking.toString());
        }

		//bookings.forEach(cl -> System.out.println(cl.toString()));
    }

    //TODO: Confirmation cannot be set 
    // public void confirmation() {

    // }
}
