package pressentation.layer;

import static pressentation.layer.ConsolePresentation.*;
import static pressentation.layer.ShortConsoleMethods.*;

import java.util.Scanner;

import businesslogic.layer.Bookings;
import businesslogic.layer.DCBooking;
import businesslogic.layer.Menu;

import static pressentation.layer.ClientControls.*;
import static pressentation.layer.AdminControls.*;
import static pressentation.layer.Ask.*;

public class App {
    public static void main(String[] args) throws Exception {
        DCBooking clientBooking;

        AdminControls admin = new AdminControls();
        int option = askInt("1.Admin\n2.Client");

        if (option == 1) {
            // while (true) {
            // String username = askString("Please input username");
            // String password = askString("Please input password");
            // if(admin.login(username, password)){
            // break;
            // }
            // }
            // admin.adminOptionMenu();

        } else if (option == 2) {
            int option2 = askInt("1.Register\n2.Login");
            if (option2 == 1) {
                newBookingAll();
            } else if(option2 == 2){
                String bookingNumber = askString("Enter you booking number");
                if (allBookings.getListOfBookingNumbers().contains(bookingNumber)) {
                    clientBooking = allBookings.findBooking(bookingNumber);
                    int option3 = askInt("1.Make payment\n2.Check details\n3.Change Menu");
                    if(option3 == 1){
                        double ammount = askDouble("Enter ammount you want to pay");
                        clientBooking.addPayment(ammount);
                        //Dont know how to get the updated client details in the list and replace the old client(outdated client)
                    }else if(option3 == 2){
                        System.out.println(clientBooking);
                    }else if(option3 ==3){
                        
                    }
                }else{
                    p("Booking number not found!");
                }
            }else{
                p("Not an option.");
            }

        } else {
            p("error");
        }
    }
}
