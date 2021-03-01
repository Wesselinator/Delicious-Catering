package pressentation.layer;

import static pressentation.layer.ConsolePresentation.*;
import static pressentation.layer.ShortConsoleMethods.*;
import static pressentation.layer.Ask.*;
public class App {
    public static void main(String[] args) throws Exception {

        int option = askInt("1. Client\n2. Admin");
        p(Integer.toString(option));
        
    }
}
