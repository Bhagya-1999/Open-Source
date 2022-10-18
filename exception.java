import java.util.Scanner;

class Encrypter {
    public static String encryptMessage(String name) throws InvalidMessageException{
        if(!Validator.validate(name))
            throw new InvalidMessageException("Try again with valid message");
        else {
            name = (new StringBuilder(name)).reverse().toString();
            return name.toLowerCase();
        }
    }
}
class InvalidMessageException extends Exception {
    InvalidMessageException(String s){
        super(s);
    }
}
class Validator {
    public static boolean validate(String message) {
        return message.matches("[A-Za-z0-9 ]+");
    }
}

public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    
    public static void main(String[] args) {
        String message = INPUT_READER.nextLine();
        
        try {
            String encrypted_message = Encrypter.encryptMessage(message);
            if(! encrypted_message.startsWith("InvalidMessageException"))
                System.out.println(encrypted_message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}