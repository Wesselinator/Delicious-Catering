package pressentation.layer;

import static pressentation.layer.ShortConsoleMethods.*;

import java.util.Scanner;

public final class Ask {
    private Ask() {
    }

    // idk how to generlize this. it annoys me
    public static int askInt(String message) {

        Scanner scan = new Scanner(System.in);
        pl(message);
        return scan.nextInt();

    }

    public static Double askDouble(String message) {

        Scanner scan = new Scanner(System.in);
        pl(message);
        return scan.nextDouble();

    }

    // maybe take a string in and read the first letter for y/n
    public static boolean askBoolean(String message) {
        Scanner scan = new Scanner(System.in);
        pl(message);
        return scan.nextBoolean();

    }

    public static String askString(String message) {
        Scanner scan = new Scanner(System.in);
        pl(message);
        return scan.nextLine();
 
    
    }
}
