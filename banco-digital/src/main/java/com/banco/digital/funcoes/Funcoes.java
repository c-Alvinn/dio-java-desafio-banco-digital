package com.banco.digital.funcoes;

import java.util.List;

import javax.swing.JOptionPane;

import com.banco.digital.banco.Banco;
import com.banco.digital.banco.Cliente;
import com.banco.digital.banco.Conta;
import com.banco.digital.banco.ContaCorrente;
import com.banco.digital.banco.ContaPoupanca;

public class Funcoes {

    private Banco banco;

    public Funcoes(Banco banco) {
        this.banco = banco;
    }

    private boolean existeCliente() {
        if (banco.getClientes().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Não existem clientes cadastrados.\nVoltando ao menu inicial...");
            return false;
        }
        return true;
    }

    private boolean existeConta() {
        if (banco.getContas().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Não existem contas cadastradas.\nVoltando ao menu inicial...");
            return false;
        }
        return true;
    }

    public void registrarCliente() {
        String nome = "";
        String cpf = "";
        List<Cliente> clientes = banco.getClientes();

        // faz um loop para caso o usuario nao insira nada
        while (nome.equals("")) {
            nome = JOptionPane.showInputDialog("Informe seu nome:");
            // verifica se o usuario cancelou a acao
            if (nome == null) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
                return;
            }
            // verifica se existe clientes adicionados
            if (!clientes.isEmpty()) {
                // variavel boolean para voltar no campo de insercao de nome
                boolean x = false;
                for (Cliente c : clientes) {
                    // verifica na lista de clientes se existe algum com o mesmo nome
                    if (c.getNome().equals(nome)) {
                        JOptionPane.showMessageDialog(null, "Nome já existente...\n\nInforme novamente.");
                        x = true;
                        break;
                    }
                }
                // caso seja verdadeiro, volta para o inicio do while
                if (x) {
                    nome = "";
                }
            }
        }

        // faz um loop para caso o usuario nao insira nada
        while (cpf.equals("")) {
            cpf = JOptionPane.showInputDialog("Informe seu cpf:");
            // verifica se o usuario cancelou a acao
            if (cpf == null) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
                return;
            }
            // verifica se existe clientes adicionados
            if (!clientes.isEmpty()) {
                // variavel boolean para voltar no campo de insercao de cpf
                boolean x = false;
                for (Cliente c : clientes) {
                    // verifica na lista de clientes se existe algum com o mesmo cpf
                    if (c.getCpf().equals(cpf)) {
                        JOptionPane.showMessageDialog(null, "CPF já existente...\n\nInforme novamente.");
                        x = true;
                        break;
                    }
                }
                // caso seja verdadeiro, volta para o inicio do while
                if (x) {
                    cpf = "";
                }
            }
        }

        // tenta inserir na lista de clientes, caso dê erro, mostra a mensagem e
        // retorna ao menu anterior
        try {
            banco.adicionarCliente(new Cliente(nome, cpf));
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no Cadastro de Cliente\n\n" + e.getMessage());
            return;
        }
    }

    public void registrarConta(int tipo) {
        // Verifica se não existe cliente
        if (!existeCliente())
            return;

        // armazena a lista de clientes
        List<Cliente> clientes = banco.getClientes();

        // cria um array de string para o JOptioinPane
        int i = 0;
        String[] lista = new String[i];

        // a partir daqui, o array de String será alterado dinamicamente de acordo com a
        // quantidade de clientes

        // itera para cada cliente da lista de clientes
        for (Cliente c : clientes) {
            // cria um novo array com tamanho maior que o anterior
            String[] l = new String[++i];
            // verifica se já existe elementos na lista
            if (lista.length > 0) {
                // armazena os elementos na lista nova se já existe
                for (int x = 0; x < lista.length; x++) {
                    l[x] = lista[x];
                }
            }
            // armazena o nome do cliente na nova posição da lista
            l[clientes.indexOf(c)] = c.getNome();
            // armazena a lista nova para a lista já existente
            lista = l;
        }

        // inicializando a variavel antes do try, pois precisa utilizar ela depois
        String clienteNome = "";

        try {
            // inicia o JOptionPane para receber qual cliente o usuario deseja selecionar
            clienteNome = JOptionPane.showInputDialog(null, "Escolha um cliente:", "Clientes",
                    JOptionPane.INFORMATION_MESSAGE, null, lista, lista[0]).toString();
        } catch (Exception e) {
            // caso o usuario cancele, dara um erro no toString e entao a excecao e
            // utilizada
            JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
            return;
        }

        // inicializa a variavel
        Cliente cliente = null;

        // verifica na lista de clientes qual é o cliente que o usuário selecionou
        for (Cliente c : clientes) {
            if (c.getNome().equals(clienteNome)) {
                // salva o cliente na variavel
                cliente = c;
                break;
            }
        }
        String senha = "";

        while (senha.equals("")) {
            senha = JOptionPane.showInputDialog("Informe a senha da sua conta:");
            // verifica se o usuario cancelou a acao
            if (senha == null) {
                JOptionPane.showMessageDialog(null, "Cadastro cancelado...");
                return;
            }
        }

        // tenta inserir na lista de contas, caso dê erro, mostra a mensagem e
        // retorna ao menu anterior
        try {
            // variavel para armazenar as informacoes da conta
            String infoConta = "";
            // verifica o tipo da conta => 1 para corrente || 2 para poupanca
            switch (tipo) {
                case 1:
                    // cria o objeto, adiciona na lista e adiciona as informacoes na variavel
                    ContaCorrente contaC = new ContaCorrente(cliente, senha);
                    banco.abrirConta(contaC);
                    infoConta = contaC.infoConta();
                    break;

                case 2:
                    // cria o objeto, adiciona na lista e adiciona as informacoes na variavel
                    ContaPoupanca contaP = new ContaPoupanca(cliente, senha);
                    banco.abrirConta(contaP);
                    infoConta = contaP.infoConta();
                    break;
            }
            // avisa que foi concluido e mostra as informacoes da conta
            JOptionPane.showMessageDialog(null,
                    "Conta cadastrada com sucesso!\nSegue as informações da conta:\n\n" + infoConta);
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no Cadastra da Conta\n\n" + e.getMessage());
            return;
        }
    }

    public Conta entrarConta() {
        String numeroS = "";
        int numero = 0;
        String senha = "";

        // numeroS foi criado para facilitar a verificacao da informacao do JOptionPane

        // dialogo para receber o numero da conta
        while (numeroS.equals("")) {
            numeroS = JOptionPane.showInputDialog("Informe o numero da sua conta:");
            // verifica se o usuario cancelou a acao
            if (numeroS == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada...");
                return null;
            }
            // tenta converter para inteiro
            // caso nao for possivel, retorna para ser inserido novamente
            try {
                numero = Integer.parseInt(numeroS);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Informação inserida inválida.\nTente novamente.");
                numeroS = "";
            }
        }

        // dialogo para receber a senha
        while (senha.equals("")) {
            senha = JOptionPane.showInputDialog("Informe a senha:");
            // verifica se o usuario cancelou a acao
            if (senha == null) {
                JOptionPane.showMessageDialog(null, "Opecação cancelado...");
                return null;
            }
        }

        // pega a lista de contas
        List<Conta> contas = banco.getContas();

        // itera entra as contas
        for (Conta conta : contas) {
            // verifica se a informacao fornecida foi encontrada entra as contas
            if (conta.getNumero() == numero && conta.getSenha().equals(senha)) {
                // exibe uma mensagem para confirmar que o usuario entrou na conta
                JOptionPane.showMessageDialog(null,
                        "Olá " + conta.getCliente().getNome() + "!\n\nSeja bem vindo a sua conta!");
                // retorna a conta
                return conta;
            }
        }
        // caso saia do for e porque nao achou nenhuma conta que bate com as credenciais
        // informadas
        // entao ira retornar null e uma mensagem informando ao usuario
        JOptionPane.showMessageDialog(null, "Informações inválidas.\nTente novamente.");
        return null;
    }

    public void listarContas() {
        // verifica se existe conta
        if (existeConta()) {
            // armazena as contas em uma variavel
            List<Conta> contas = banco.getContas();

            // itera entre as contas
            for (int i = 0; i < contas.size(); i++) {

                // mostra as contas com possibilidade de navegacao ou de voltar
                int opc;
                String[] options = { "Anterior", "Próxima", "Voltar" };
                do {
                    opc = JOptionPane.showOptionDialog(null, contas.get(i).toString(), "Conta",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    // faz a logica por tras da navegacao
                    switch (opc) {
                        case 0 -> {
                            if (i == 0) {
                                break;
                            } else {
                                // diminui 2 pois quando o escopo terminar para comecar a proxima repeticao
                                // ele ira aumentar sempre +1... entao retira-se 2 para diminuir a posicao atual
                                i -= 2;
                                break;
                            }
                        }
                        case 1 -> {
                            // apenas continua o codigo normalmente
                            break;
                        }
                        default -> {
                            // retorna para o menu anterior
                            return;
                        }
                    }
                    break;
                } while (opc != 2);
            }
            JOptionPane.showMessageDialog(null, "Todas as contas foram listadas.");
        }
    }

    public void saque(Conta conta) {
        String valorS = "";
        double valor = 0.0;

        // valorS foi criado para facilitar a verificacao da informacao do JOptionPane

        // dialogo para receber o valor
        while (valorS.equals("")) {
            valorS = JOptionPane.showInputDialog("Informe o valor a ser sacado:");
            // verifica se o usuario cancelou a acao
            if (valorS == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada...");
                return;
            }
            // tenta converter o que foi inserido para double
            // caso nao for possivel, retorna para ser inserido novamente
            try {
                valor = Double.parseDouble(valorS);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Informação inserida inválida.\nTente novamente.");
                valorS = "";
            }
        }

        if (conta.sacar(valor)) {
            JOptionPane.showMessageDialog(null,
                    "Saque de R$" + String.format("%.2f", valor) + " efetuado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para saque.\n");
        }
    }

    public void deposito(Conta conta) {
        String valorS = "";
        double valor = 0.0;

        // valorS foi criado para facilitar a verificacao da informacao do JOptionPane

        // dialogo para receber o valor
        while (valorS.equals("")) {
            valorS = JOptionPane.showInputDialog("Informe o valor a ser depositado:");
            // verifica se o usuario cancelou a acao
            if (valorS == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada...");
                return;
            }
            // tenta converter o que foi inserido para double
            // caso nao for possivel, retorna para ser inserido novamente
            try {
                valor = Double.parseDouble(valorS);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Informação inserida inválida.\nTente novamente.");
                valorS = "";
            }
        }

        conta.depositar(valor);
        JOptionPane.showMessageDialog(null, "Deposito de R$" + String.format("%.2f", valor) + " efetuado com sucesso!");

    }

    public void transferencia(Conta conta) {
        String valorS = "";
        double valor = 0.0;

        // valorS foi criado para facilitar a verificacao da informacao do JOptionPane

        // dialogo para receber o valor
        while (valorS.equals("")) {
            valorS = JOptionPane.showInputDialog("Informe o valor a ser transferido:");
            // verifica se o usuario cancelou a acao
            if (valorS == null) {
                JOptionPane.showMessageDialog(null, "Operação cancelada...");
                return;
            }
            // tenta converter o que foi inserido para double
            // caso nao for possivel, retorna para ser inserido novamente
            try {
                valor = Double.parseDouble(valorS);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Informação inserida inválida.\nTente novamente.");
                valorS = "";
            }
        }

        // armazena a lista de contas
        List<Conta> contas = banco.getContas();

        // cria um array de string para o JOptioinPane
        int i = 0;
        String[] lista = new String[i];

        // a partir daqui, o array de String será alterado dinamicamente de acordo com a
        // quantidade de contas

        // itera para cada conta da lista de contas
        for (Conta c : contas) {
            // verifica se o numero da conta e o mesmo da conta atual
            if (c.getNumero() == conta.getNumero()) {
                // caso o numero seja o mesmo ele pula
                // pois como a lista ira aparecer em transferencia,
                // excluimos a possibilidade de transferir para a mesma conta
                continue;
            } else {
                // cria um novo array com tamanho maior que o anterior
                String[] l = new String[++i];
                // verifica se já existe elementos na lista
                if (lista.length > 0) {
                    // armazena os elementos na lista nova se já existe
                    for (int x = 0; x < lista.length; x++) {
                        l[x] = lista[x];
                    }
                }
                // armazena o numero da conta na nova posição da lista
                l[i - 1] = String.valueOf(c.getNumero());
                // armazena a lista nova para a lista já existente
                lista = l;
            }

        }
        
        // inicializando a variavel antes do try, pois precisa utilizar ela depois
        String numeroConta;

        try {
            // inicia o JOptionPane para receber qual cliente o usuario deseja selecionar
            numeroConta = JOptionPane.showInputDialog(null, "Escolha um cliente:", "Clientes", JOptionPane.INFORMATION_MESSAGE, null, lista, lista[0]).toString();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Operação cancelado...");
            return;
        }

        // inicializa a variavel
        Conta contaDestino = null;

        // verifica na lista de contas qual é a conta que o usuário selecionou
        for (Conta c : contas) {
            if (c.getNumero() == Integer.parseInt(numeroConta)) {
                // salva a conta na variavel
                contaDestino = c;
                break;
            }
        }

        if (conta.transferir(valor, contaDestino)) {
            JOptionPane.showMessageDialog(null, "Transferencia de R$" + String.format("%.2f", valor) + " para "
                    + contaDestino.getCliente().getNome() + " efetuada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Tranferencia nao efetuada.\n\nSaldo insuficiente.");
        }
    }
}
