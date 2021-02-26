//A data class that holds a menu
package businesslogic.layer;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String title;
    private List<String> items = new ArrayList<>();
    private List<Double> prices = new ArrayList<>();

    private Boolean isAdultMenu = false;

    public Menu(String title) {
        this.title = title;
    }

    public Menu(String title, List<String> items, List<Double> prices, Boolean isAdultMenu) {
        this.title = title;
        this.items = items;
        this.prices = prices;
        this.isAdultMenu = isAdultMenu;
    }

    public Menu(Menu copy) {
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
        return items.get(index) + " - R" + prices.get(index).toString();
    }

    //change if general solution is needed
    private void applyDiscount(Double percentageDiscounted) {
        prices.replaceAll( (p) -> p*(percentageDiscounted/100) );
    }

    public boolean applyAdultDiscount() { //return might be required
        if (isAdultMenu) {
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
}
