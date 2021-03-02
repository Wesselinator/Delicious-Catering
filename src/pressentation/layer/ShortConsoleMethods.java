//A static class to import for short console methods
package pressentation.layer;

public final class ShortConsoleMethods {
    private ShortConsoleMethods() {}

    public static void pl(String s) {
        System.out.println(s);
    }

    public static void nl() {
        System.out.println();
    }

    public static void p(String s) {
        System.out.print(s);
    }

    //this method clears the console output - needs to be tested
    public static void cs() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    } 
}
