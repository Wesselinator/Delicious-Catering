//Just a STATIC class to hold all the words and menu strings to keep App.java cleanner
package pressentation.layer;

import static pressentation.layer.ShortConsoleMethods.*;

public final class ConsolePresentation {
    private ConsolePresentation() {}

    public static void intro() {
        pl("Welcome to Delicious Catering");
        pl("1. Client");
        pl("2. Admin");        
    }
}
