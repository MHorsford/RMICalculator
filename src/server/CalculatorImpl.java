package src.server;

import src.model.CalculatorRemote;
import src.model.exception.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements CalculatorRemote {

    public CalculatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double add(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double subtract(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double multiply(double a, double b) throws RemoteException {
        return a * b;
    }

    @Override
    public double divide(double a, double b) throws RemoteException, DivisionByZeroException {
        if (b == 0) throw new DivisionByZeroException();
        return a / b;
    }

    @Override
    public double sqrt(double number) throws RemoteException, NegativeNumberException {
        if (number < 0) throw new NegativeNumberException();
        return Math.sqrt(number);
    }

    @Override
    public double sin(double angle) throws RemoteException {
        return Math.sin(Math.toRadians(angle));
    }

    @Override
    public double cos(double angle) throws RemoteException {
        return Math.cos(Math.toRadians(angle));
    }

    @Override
    public double log(double number) throws RemoteException, NonPositiveNumberException {
        if (number <= 0) throw new NonPositiveNumberException();
        return Math.log(number);
    }
}