package Exception;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(){

    }

    public InvalidLoginException(String message) {
        super(message);
    }

}