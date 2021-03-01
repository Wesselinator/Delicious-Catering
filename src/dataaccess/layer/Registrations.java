//This is a data holder class for the client registrations
//We sholud save/load from this one aswell
package dataaccess.layer;

import java.util.*;

import businesslogic.layer.DCClient;

public class Registrations implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<DCClient> clients = new ArrayList<>();

    public boolean registerClient(String fname, String lname, String number) {
        DCClient newClient = new DCClient(fname, lname, number);
        if (clients.contains(newClient)) {
            return false;
        }
        else {
            clients.add(newClient);
            return true;
        }
    }

    public boolean registerClient(DCClient newClient) {
        return registerClient(newClient.getFname(), newClient.getLname(), newClient.getNumber());
    }

    public void saveRegistrations () {
        FileHandler.writeRegistrations(clients);
    }
}
