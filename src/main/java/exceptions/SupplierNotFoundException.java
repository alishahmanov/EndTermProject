package exceptions;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String email) {
        super("Supplier with email: " + email + " doesn't exist");
    }
}
