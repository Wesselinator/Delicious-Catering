package dataaccess.layer;

import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import businesslogic.layer.DCBooking;
import businesslogic.layer.DCClient;

public class FileHandler {

    public FileHandler() {}

    public void saveBookings(ArrayList<DCBooking> bookings) {
        ArrayList<DCBooking> data = bookings;         
        try {
            Path filepath = Paths.get(System.getProperty("user.dir")+"/dataaccess/layer/bookings.ser");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath.toFile()));
            out.writeObject(data);
            out.close();
            System.out.printf("All bookings have been saved.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void saveRegistrations(ArrayList<DCClient> registrations) {
        ArrayList<DCClient> data = registrations;         
        try {
            Path filepath = Paths.get(System.getProperty("user.dir")+"/dataaccess/layer/registrations.ser");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filepath.toFile()));
            out.writeObject(data);
            out.close();
            System.out.printf("All registrations have been saved.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
}
