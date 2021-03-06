package pressentation.layer;

import static pressentation.layer.ShortConsoleMethods.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.*;

public final class Ask {
    private Ask() {}

    private static String prettyMessageEnd(String message) {
        Character endLetter = message.toCharArray()[message.length()-1];

        switch (endLetter) {
            case ' ':
                return message;
        
            case ':':
                return message + " ";
            
            default:
                return message + ": ";
        }
    }

    //cant use a static scanner var because it gets set to null in the closing process

    private static <R> R askObject(String message, String error, Function<Scanner, R> funct) {
        String prettyMessage = prettyMessageEnd(message);
        do {
            p(prettyMessage);
            try (Scanner scanner = new Scanner(new UncloseableInputStream(System.in))) {
                return funct.apply(scanner);
            } catch (Exception e) {
                pl(error);
            }
        } while (true);
    }
    // :) i made it generic!

    public static int askInt(String message) {
        return askObject(message, "Please enter a Number!", Scanner::nextInt);
    }

    public static Double askDouble(String message) {
        return askObject(message, "Please enter a Number with a fraction! (0,51)", Scanner::nextDouble); //is it called a decimal?
    }

    public static boolean askBoolean(String message) {
        return askObject(message, "Please enter either 'true' or 'false'", Scanner::nextBoolean);
    }

    public static boolean askYesNo(String message) {
        while (true) {
            Character letter = askString(message).toLowerCase().toCharArray()[0];
    
            if (letter == 'y') {
                return true;
            }
    
            if (letter == 'n') {
                return false;
            }

            pl("Please enter Yes or No");
        }

    }

    public static String askString(String message) {
        return askObject(message, "That wasn't a string", Scanner::nextLine);   
    }

    //we should do this better
    //maybe more verbose
    public static LocalDateTime askLDT(String message) {
        while (true) {
            p(message);
            String date = askString("Enter date in the format yyyy-MM-dd HH:mm :");
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                pl("Please use the format 'yyyy-MM-dd HH:mm'");
            }
        }
    } 

}
