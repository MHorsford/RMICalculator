package src.model;

// RMI
import java.rmi.Remote;
import java.rmi.RemoteException;
// Exceções Personalizadas
import src.model.exception.DivisionByZeroException;
import src.model.exception.NegativeNumberException;
import src.model.exception.NonPositiveNumberException;
/*
 * Interface para o RMI
 */
public interface CalculatorRemote extends Remote {
    // Operações Básicas
    double add(double a, double b) throws RemoteException;
    double subtract(double a, double b) throws RemoteException;
    double multiply(double a, double b) throws RemoteException;
    double divide(double a, double b) throws RemoteException, DivisionByZeroException;
    // Operações Científicas
    double sqrt(double number) throws RemoteException, NegativeNumberException;
    double sin(double angle) throws RemoteException;
    double cos(double angle) throws RemoteException;
    double log(double number) throws RemoteException, NonPositiveNumberException;
}