package src.model.exception;


public class DivisionByZeroException extends Exception {
    public DivisionByZeroException() {
        super("Divisão por zero não é permitida.");
    }
}