//This is a data holder class for the client registrations
package dataaccess.layer;

import java.util.*;

import businesslogic.layer.DCClient;

public class Registrations {
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

    public List<DCClient> getClients() {
        return clients;
    }

    public List<String> getClientList() {
        List<String> ret = new ArrayList<>();
        clients.forEach(c -> ret.add(c.toString()));
        return ret;
    }

    public void saveRegistrations () {
        FileHandler.writeRegistrations(clients);
    }

    @Override
    public String toString() {
        return String.join("\n", getClientList());
    }
}
