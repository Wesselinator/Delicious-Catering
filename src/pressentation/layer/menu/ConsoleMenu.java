package pressentation.layer.menu;

import static pressentation.layer.Ask.*;
import static pressentation.layer.ShortConsoleMethods.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsoleMenu extends ArrayList<MenuItem> {
    private static final long serialVersionUID = 1L;

    public ConsoleMenu(List<String> items, List<Runnable> actions) {
        super(MenuItem.menuFromLists(items, actions));
    }

    public ConsoleMenu(Collection<? extends MenuItem> c) {
        super(c);
    }

    public ConsoleMenu() {
    }

    public ConsoleMenu(int initialCapacity) {
        super(initialCapacity);
    }

    public boolean show(String exitLine) {
        pl("---------------------------------------");
        nl();
        for (int i = 0; i < size(); i++) {
            pl( (i+1) + ": " + get(i).toString());
        }
        nl();
        pl("0: " + exitLine);
        nl();
        nl();
        int option = Math.abs(askInt("Enter menu option: "));
        int index = option - 1;

        if (option == 0) {
            return Boolean.FALSE;
        } else if (option > size()) {
            pl("Not an item on the menu!");
        } else {
            get(index).excecute();
            return Boolean.TRUE;
        }

        return Boolean.TRUE; // didnt exit
    }

    public void showUntilExit(String exitLine) {
        while (show(exitLine)) {
            nl();
        }
    }


    public boolean add(String item, Runnable action) {
        return super.add(new MenuItem(item, action));
    }

    public <T> boolean add(String item, Consumer<T> takes, Supplier<T> gives) {
        Runnable action = () -> takes.accept(gives.get());
        return super.add(new MenuItem(item, action));
    } 

    public boolean add(String item, Supplier<String> print) {
        Runnable action = () -> pl(print.get());
        return super.add(new MenuItem(item, action));
    }
}
