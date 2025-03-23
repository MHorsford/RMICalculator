package src.server;


import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Uso: java server.CalculatorServer <NomeServidor> <Porta>");
            System.exit(1);
        }

        String nomeServidor = args[0];
        int porta;

        // Validação da porta
        try {
            porta = Integer.parseInt(args[1]);
            if (porta < 1024 || porta > 65535) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro: Porta inválida. Use um valor entre 1024 e 65535.");
            System.exit(1);
            return;
        }

        try {
            CalculatorImpl calculadora = new CalculatorImpl();
            Registry registry = LocateRegistry.createRegistry(porta);
            registry.bind(nomeServidor, calculadora);

            System.out.println("Servidor '" + nomeServidor + "' registrado na porta " + porta);
            System.out.println("Métodos disponíveis: add, subtract, multiply, divide, sqrt, sin, cos, log");

            // Shutdown hook para desregistrar o servidor
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    registry.unbind(nomeServidor);
                    System.out.println("\nServidor '" + nomeServidor + "' desregistrado.");
                } catch (Exception e) {
                    System.err.println("Erro ao desregistrar servidor: " + e.getMessage());
                }
            }));

        } catch (RemoteException e) {
            System.err.println("Erro de comunicação RMI: " + e.getMessage());
            if (e.getMessage().contains("Port already in use")) {
                System.err.println("Dica: Use uma porta diferente ou libere a porta " + porta);
            }
        } catch (AlreadyBoundException e) {
            System.err.println("Erro: Nome '" + nomeServidor + "' já está em uso!");
            System.err.println("Dica: Use um nome único para cada servidor");
        } catch (Exception e) {
            System.err.println("Erro crítico: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
