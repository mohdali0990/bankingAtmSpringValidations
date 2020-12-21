package banking_atm.Exceptions;

public class ApiRequestInternal extends RuntimeException{

    public ApiRequestInternal(String message) {
        super(message);
    }
}
