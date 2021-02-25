//A data class that holds a menu
package businesslogic.layer;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String title;
    private List<String> items = new ArrayList<>();
    private List<Double> prices = new ArrayList<>();

    public Menu(String title) {
        this.title = title;
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

    //other

    public void addMenuItem(String item, Double price) {
        items.add(item);
        prices.add(price);
    }

    public String getMenuItem(int index) {
        return items.get(index) + " - R" + prices.get(index).toString();
    }

    //discount, e.g. 15% of all prices
    public void applyDiscount(Double percentageDiscounted) {
        //change to return
        prices.replaceAll( (p) -> p*(percentageDiscounted/100) );
    }

    @Override
    public String toString() {
        String ret = title + '\n';

        for (int i = 0; i < items.size(); i++) {
            ret = ret + getMenuItem(i) + '\n'; //stringBuilder?
        }

        return ret;
    }
}
