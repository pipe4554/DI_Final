package exceptions;

public class WrongOrderDayCreationException extends Exception {

    public WrongOrderDayCreationException(String men) {
        super(men);
    }
}
