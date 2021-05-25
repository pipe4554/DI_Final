package exceptions;

public class ClientNotFoundException extends Exception {

    public ClientNotFoundException(String men) {
        super(men);
    }
}