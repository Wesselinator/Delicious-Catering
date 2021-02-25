//A data class that holds a menu
package dataaccess.layer;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String title;
    private List<String> items = new ArrayList<String>();
    private List<Double> prices = new ArrayList<Double>();

    public Menu(String title, List<String> items, List<Double> prices) {
        this.title = title;
        this.items = items;
        this.prices = prices;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<Double> getPrices() {
        return prices;
    }

    public void setPrices(List<Double> prices) {
        this.prices = prices;
    }

    //add the list add as a method instead of the setter?

    @Override
    public String toString() {
        return "You forgot to do this one";
    }
}
