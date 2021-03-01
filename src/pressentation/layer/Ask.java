package pressentation.layer;

import static pressentation.layer.ShortConsoleMethods.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public static Boolean askYesNo(String message) {
        while (true) {

            pl(message);

            try (Scanner scan = new Scanner(System.in)) {
                String word = scan.next();
                Character letter = word.toLowerCase().toCharArray()[0];
    
                if (letter == 'y') {
                    return true;
                }
    
                if (letter == 'n') {
                    return false;
                } 
            } catch (Exception e) {
                pl("Please enter yes/no. (Don't do that again)");
            }

            pl("Please enter yes/no");
        }
    }

    public static String askString(String message) {
        Scanner scan = new Scanner(System.in);
        pl(message);
        return scan.nextLine();    
    }

    public static LocalDateTime askLDT(String message) {
        while (true) {
            pl(message);
            try {
                String date = askString("Enter date in the format yyyy-MM-dd HH:mm :");
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                pl("Please use the format 'yyyy-MM-dd HH:mm'");
            }
        }
    }
    
}
