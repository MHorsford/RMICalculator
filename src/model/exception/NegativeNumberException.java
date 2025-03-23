package src.model.exception;

public class NegativeNumberException extends Exception {
    public NegativeNumberException() {
        super("Número negativo não é permitido para esta operação.");
    }
}