package pressentation.layer;

import static pressentation.layer.ConsolePresentation.*;
import static pressentation.layer.ShortConsoleMethods.*;
import static pressentation.layer.Ask.*;

public class AdminControls {
    private String userName;
    private String passWord;

    public AdminControls() {
        this.userName = "Admin";
        this.passWord = "Admin";
    }

    // dalk anders
    public boolean login(String username, String password) {

        if ((this.userName.equals(username)) && (this.passWord.equals(password))) {
            return true;
        } else {
            return false;
        }
    }

    // public void adminOptionMenu(){
    //     int option = askInt("1. Show all bookings\n2. Show pending confirmations")
    //     if(option==1){
    //         showBookings();
    //     } else if(option == 2){
    //         confirmation();
    //     }

    // }

    // public void showBookings() {

    // }

    // public void confirmation() {

    // }
}