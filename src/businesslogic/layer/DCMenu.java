//A data class that holds a menu
package businesslogic.layer;

import java.util.ArrayList;
import java.util.List;

import pressentation.layer.menu.ConsoleMenu;
import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.*;

public class DCMenu implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private List<String> items = new ArrayList<>();
    private List<Double> prices = new ArrayList<>();

    private boolean isAdultMenu = false;

    public DCMenu(String title) {
        this.title = title;
    }

    public DCMenu(String title, List<String> items, List<Double> prices, Boolean isAdultMenu) {
        this.title = title;
        this.items = items;
        this.prices = prices;
        this.isAdultMenu = isAdultMenu;
    }

    public DCMenu(DCMenu copy) {
        this.title = copy.title; //value, thus deep
        this.items = new ArrayList<>(items); //deep
        this.prices = new ArrayList<>(copy.prices); //deep
        this.isAdultMenu = copy.isAdultMenu; //value, thus deep
    }

    //g&s

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public List<String> getItems() {
        return items;
    }
    
    public List<Double> getPrices() {
        return prices;
    }

    public void setAdult() {
        isAdultMenu = true;
    }

    //other

    public void addMenuItem(String item, Double price) {
        items.add(item);
        prices.add(price);
    }

    public String getMenuItem(int index) {
        return String.format("%s - R%.2f", items.get(index), prices.get(index));
    }

    //change if general solution is needed
    private void applyDiscount(Double percentageDiscounted) {
        prices.replaceAll(p -> p*(percentageDiscounted/100) );
    }

    public boolean applyAdultDiscount() { //return might be required
        if (Boolean.TRUE.equals(isAdultMenu)) {
            applyDiscount(15.0);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(title + '\n');

        for (int i = 0; i < items.size(); i++) {
            ret.append('\n');
            ret.append(getMenuItem(i));
        }

        return ret.toString();
    }

    public String toShortString() {
        if (isAdultMenu) {
            return title + "[This is an Adult Menu]";
        }
        return title;
    }

    //present
    public void editMenu() {
        ConsoleMenu menuEdit = new ConsoleMenu();

        pl("Choose a item to ");
        for (int i = 0; i < items.size(); i++) {
            int index = i;
            menuEdit.add(getMenuItem(index), this::editItem, () -> index);
        }

        menuEdit.showUntilExit("Exit to Main Menu");
    }

    private void editItem(int index) {
        String item = askString("New item name for '"+items.get(index)+"' :");
        if (item.isEmpty()) {
            item = items.get(index);
        }

        Double price = askDouble("New price for '"+item+"' (-1 for same) :");
        if (price < 0) {
            price = prices.get(index);
        }


        items.set(index, item); 
        prices.set(index, price);
    }
}
