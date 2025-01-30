package exceptions;

public class InsoleNotFoundException extends RuntimeException {
    public InsoleNotFoundException(int id) {
        super("Insole with ID: " + id + " doesn't exist");
    }
}
