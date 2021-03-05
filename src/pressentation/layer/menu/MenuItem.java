package pressentation.layer.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
    private String item;
    private Runnable action;  //almost evering writes to the console, maybe make this a String...

    public MenuItem(String item, Runnable action) {
        this.item = item;
        this.action = action;
    }

    public static List<MenuItem> menuFromLists(List<String> items, List<Runnable> actions) {
        List<MenuItem> ret = new ArrayList<>(items.size());

        if (items.size() != actions.size()) {
            return ret;
        }

        for (int i = 0; i < items.size(); i++) {
            ret.add(new MenuItem(items.get(i), actions.get(i)));
        }
        return ret;
    }

    //TODO:This should block or everything will fall apart like a house of cards  D:
    public void excecute() {
        action.run();
    }

    @Override
    public String toString() {
        return item;
    }
}
