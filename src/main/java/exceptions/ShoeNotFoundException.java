package exceptions;

public class ShoeNotFoundException extends RuntimeException {
    public ShoeNotFoundException(int id) {
        super("Shoe with ID: " + id + " doesn't exist");
    }
}
