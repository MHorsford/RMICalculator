
# Calculadora RMI - CLI

Interface de Linha de Comando (CLI) para interagir com um servidor de calculadora remota usando RMI (Remote Method Invocation).

---

## Pré-requisitos
- Java JDK 11 ou superior
- Servidor RMI em execução (veja [Instruções do Servidor](#instruções-do-servidor))

---

## Instalação e Compilação
1. Clone o repositório ou copie os arquivos do projeto.
2. Compile o projeto:
   ```bash
   javac -d bin src/model/*.java src/model/exception/*.java src/server/*.java src/client/CalculatorCLI.java
   ```

---

## Uso Básico

### 1. Iniciar o Servidor
```bash
java -cp bin src.server.CalculatorServer <NOME_DO_SERVIDOR> <PORTA>
# Exemplo:
java -cp bin src.server.CalculatorServer Calculadora 1099
```

---

### 2. Executar a CLI
```bash
java -cp bin src.client.CalculatorCLI
```

---

## Comandos Disponíveis

### Conectar a um Servidor
```bash
connect <host> <porta> <nome_servidor>
# Exemplo:
connect localhost 1099 Calculadora
```

### Listar Operações
```bash
list
```

### Executar Operações
```bash
call <operação> [argumentos...]
# Operações disponíveis:
# - add <a> <b>       Soma
# - subtract <a> <b>  Subtração
# - multiply <a> <b>  Multiplicação
# - divide <a> <b>    Divisão
# - sqrt <x>          Raiz quadrada
# - sin <ângulo>      Seno (graus)
# - cos <ângulo>      Cosseno (graus)
# - log <x>           Logaritmo natural
```

### Sair da CLI
```bash
exit
```

---

## Exemplos de Uso

### Exemplo 1: Operação Básica
```bash
[Calculadora] > call add 5 3
🔄 Resultado: 8.0
```

### Exemplo 2: Operação Científica
```bash
[Calculadora] > call sqrt 25
🔄 Resultado: 5.0
```

### Exemplo 3: Tratamento de Erros
```bash
[Calculadora] > call divide 10 0
❌ Erro: Divisão por zero não permitida.

[Calculadora] > call log -5
❌ Erro: Número deve ser positivo.
```

---

## Observações
- **Multiplos Clientes:** A CLI suporta múltiplas instâncias conectadas ao mesmo servidor simultaneamente.
- **Arquitetura:** O cliente e servidor podem rodar em máquinas diferentes (substitua `localhost` pelo IP do servidor).

---

## Solução de Problemas Comuns

| **Erro**                     | **Causa**                          | **Solução**                              |
|------------------------------|-------------------------------------|------------------------------------------|
| `Connection refused`         | Servidor não está rodando          | Verifique se o servidor foi iniciado     |
| `AlreadyBoundException`      | Nome do servidor já está em uso    | Use um nome único para o servidor        |
| `NegativeNumberException`    | Raiz quadrada de número negativo   | Insira valores >= 0                      |
| `NonPositiveNumberException` | Logaritmo de número não positivo   | Insira valores > 0                       |

---

**Nota:** Para operações trigonométricas (`sin`, `cos`), o ângulo deve ser em graus.
