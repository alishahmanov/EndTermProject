package exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String email) {
        super("Client with email: " + email + " doesn't exist");
    }
}
