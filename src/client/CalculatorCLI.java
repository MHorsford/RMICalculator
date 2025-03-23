package src.client;

import src.model.CalculatorRemote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class CalculatorCLI {
    private static CalculatorRemote calculator;
    private static String currentServer = "Nenhum";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHelp();

        while (true) {
            System.out.print("\n[" + currentServer + "] > ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] tokens = input.split(" ");
            String command = tokens[0].toLowerCase();

            switch (command) {
                case "connect":
                    handleConnect(tokens);
                    break;
                case "list":
                    printOperations();
                    break;
                case "call":
                    handleCall(tokens);
                    break;
                case "exit":
                    System.out.println("Encerrando...");
                    System.exit(0);
                default:
                    System.err.println("Comando inválido!");
                    printHelp();
            }
        }
    }

    // Conecta ao servidor RMI
    private static void handleConnect(String[] tokens) {
        if (tokens.length != 4) {
            System.err.println("Uso: connect <host> <porta> <nome_servidor>");
            return;
        }

        try {
            String host = tokens[1];
            int port = Integer.parseInt(tokens[2]);
            String serverName = tokens[3];

            Registry registry = LocateRegistry.getRegistry(host, port);
            calculator = (CalculatorRemote) registry.lookup(serverName);
            currentServer = serverName;
            System.out.println("Conectado a '" + serverName + "' em " + host + ":" + port);
        } catch (Exception e) {
            System.err.println("Erro na conexão: " + e.getMessage());
        }
    }

    // Executa operações remotas
    private static void handleCall(String[] tokens) {
        if (calculator == null) {
            System.err.println("Conecte-se a um servidor primeiro!");
            return;
        }

        if (tokens.length < 2) {
            System.err.println("Uso: call <operação> [args...]");
            return;
        }

        String operation = tokens[1].toLowerCase();
        try {
            double result = switch (operation) {
                case "add" -> handleAdd(tokens);
                case "subtract" -> handleSubtract(tokens);
                case "multiply" -> handleMultiply(tokens);
                case "divide" -> handleDivide(tokens);
                case "sqrt" -> handleSqrt(tokens);
                case "sin" -> handleSin(tokens);
                case "cos" -> handleCos(tokens);
                case "log" -> handleLog(tokens);
                default -> throw new IllegalArgumentException("Operação desconhecida: " + operation);
            };
            System.out.println("Resultado: " + result);
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    // Métodos auxiliares para operações
    private static double handleAdd(String[] tokens) throws Exception {
        validateArgs(tokens, 2);
        double a = Double.parseDouble(tokens[2]);
        double b = Double.parseDouble(tokens[3]);
        return calculator.add(a, b);
    }

    private static double handleSubtract(String[] tokens) throws Exception {
        validateArgs(tokens, 2);
        double a = Double.parseDouble(tokens[2]);
        double b = Double.parseDouble(tokens[3]);
        return calculator.subtract(a, b);
    }

    private static double handleMultiply(String[] tokens) throws Exception {
        validateArgs(tokens, 2);
        double a = Double.parseDouble(tokens[2]);
        double b = Double.parseDouble(tokens[3]);
        return calculator.multiply(a, b);
    }

    private static double handleDivide(String[] tokens) throws Exception {
        validateArgs(tokens, 2);
        double a = Double.parseDouble(tokens[2]);
        double b = Double.parseDouble(tokens[3]);
        return calculator.divide(a, b);
    }

    private static double handleSqrt(String[] tokens) throws Exception {
        validateArgs(tokens, 1);
        double num = Double.parseDouble(tokens[2]);
        return calculator.sqrt(num);
    }

    private static double handleSin(String[] tokens) throws Exception {
        validateArgs(tokens, 1);
        double angle = Double.parseDouble(tokens[2]);
        return calculator.sin(angle);
    }

    private static double handleCos(String[] tokens) throws Exception {
        validateArgs(tokens, 1);
        double angle = Double.parseDouble(tokens[2]);
        return calculator.cos(angle);
    }

    private static double handleLog(String[] tokens) throws Exception {
        validateArgs(tokens, 1);
        double num = Double.parseDouble(tokens[2]);
        return calculator.log(num);
    }

    // Valida quantidade de argumentos
    private static void validateArgs(String[] tokens, int required) {
        if (tokens.length < required + 2) {
            throw new IllegalArgumentException(
                "Argumentos insuficientes para '" + tokens[1] + "'. Esperados: " + required
            );
        }
    }

    // Ajuda
    private static void printHelp() {
        System.out.println("""
            === Calculadora RMI CLI ===
            Comandos:
              connect <host> <porta> <nome_servidor>  Conectar a um servidor
              list                                    Listar operações
              call <operação> [args...]               Executar operação
              exit                                    Sair
            """);
    }

    // Lista de operações
    private static void printOperations() {
        System.out.println("""
            Operações Disponíveis:
            - add <a> <b>           Soma
            - subtract <a> <b>       Subtração
            - multiply <a> <b>       Multiplicação
            - divide <a> <b>         Divisão
            - sqrt <x>               Raiz quadrada
            - sin <ângulo>           Seno (graus)
            - cos <ângulo>           Cosseno (graus)
            - log <x>                Logaritmo natural
            """);
    }
}