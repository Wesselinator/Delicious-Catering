//This is a data holder class for the client registrations
package businesslogic.layer;

import java.util.*;

import dataaccess.layer.FileHandler;

public class Registrations {
    
    private List<DCClient> clients = new ArrayList<>();

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
        clients = FileHandler.readRegistrations();
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
