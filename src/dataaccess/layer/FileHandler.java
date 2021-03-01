package dataaccess.layer;

import java.io.*;
import java.util.*;

import businesslogic.layer.DCBooking;
import businesslogic.layer.DCClient;

public final class FileHandler {

    private FileHandler() {}

    public static void writeBookings(List<DCBooking> bookings) {
        List<DCBooking> data = bookings;         
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Data/bookings.ser"))){          
            out.writeObject(data);
            System.out.println("All bookings have been saved.");//This message can also be implemented when the method is used.
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void writeRegistrations(List<DCClient> registrations) {
        List<DCClient> data = registrations;         
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Data/registrations.ser"))){          
            out.writeObject(data);
            System.out.println("All registrations have been saved.");//This message can also be implemented when the method is used.
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static List<DCBooking> readBookings() {
        List<DCBooking> data = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Data/bookings.ser"));){          
            //@SuppressWarnings("unchecked") //ArrayList needs to be redeclared after this but then return cannot see the list
            data = (List<DCBooking>) input.readObject();        
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
            System.out.println("\nfile not found");
            e.printStackTrace();
        }

        return data;
        
    }  
    
    public static List<DCClient> readRegistrations() {
        List<DCClient> data = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("Data/registrations.ser"))){          
            //@SuppressWarnings("unchecked") //ArrayList needs to be redeclared after this but then return cannot see the list
            data = (List<DCClient>) input.readObject();         
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
            System.out.println("\nfile not found");
            e.printStackTrace();
        }

        return data;
        
    }
    
}
