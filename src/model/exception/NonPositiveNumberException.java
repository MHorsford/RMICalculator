package src.model.exception;

public class NonPositiveNumberException extends Exception {
    public NonPositiveNumberException() {
        super("O número deve ser positivo.");
    }
}