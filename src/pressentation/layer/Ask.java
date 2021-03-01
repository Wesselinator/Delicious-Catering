package pressentation.layer;

import static pressentation.layer.ShortConsoleMethods.*;

import java.util.Scanner;

public final class Ask {
    private Ask() {}

    //idk how to generlize this. it annoys me
    public static int askInt(String message) {
        while (true) {
            try (Scanner scan = new Scanner(System.in)) {
                pl(message);
                return scan.nextInt();
            } catch (Exception e) {
                pl("Wrong Input! Please input a number.");
            }  
        }
    }

    public static Double askDouble(String message) {
        while (true) {
            try (Scanner scan = new Scanner(System.in)) {
                pl(message);
                return scan.nextDouble();
            } catch (Exception e) {
                pl("Wrong Input! Please input a number with a decimal.");
            }   
        }
    }

    //maybe take a string in and read the first letter for y/n
    public static boolean askBoolean(String message) {
        while (true) {
            try (Scanner scan = new Scanner(System.in)) {
                pl(message);
                return scan.nextBoolean();
            } catch (Exception e) {
                pl("Wrong Input! Please input true/false");
            }   
        }
    }
}
