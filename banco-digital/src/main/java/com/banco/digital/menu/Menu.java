package com.banco.digital.menu;

import javax.swing.JOptionPane;

import com.banco.digital.banco.Banco;
import com.banco.digital.banco.Conta;
import com.banco.digital.funcoes.Funcoes;

import lombok.Getter;

@Getter
public class Menu {

    private Banco banco;
    private Funcoes fun;

    // construtor personalizado
    public Menu(Banco banco) {
        this.banco = banco;
        this.fun = new Funcoes(banco);
    }

    // menu inicial
    public void init() {

        int opc;
        String[] options = { "Cadastrar Cliente", "Criar Conta Corrente", "Criar Conta Poupanca", "Entrar na Conta",
                "Listar Contas", "Sair do Programa" };
        do {
            opc = JOptionPane.showOptionDialog(null, "Menu Inicial", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (opc) {
                case 0 -> {
                    fun.registrarCliente();
                    break;
                }

                case 1 -> {
                    fun.registrarConta(1);
                    break;
                }

                case 2 -> {
                    fun.registrarConta(2);
                    break;
                }

                case 3 -> {
                    Conta conta = fun.entrarConta();
                    if (conta != null) {
                        menuConta(conta);
                        break;
                    }
                    break;
                }

                case 4 -> {
                    fun.listarContas();
                    break;
                }

                default -> {
                    System.exit(0);
                }
            }
        } while (opc != 5);
    }

    private void menuConta(Conta conta) {
        int opc;
        String[] options = { "Sacar", "Depositar", "Transferir", "Sair da Conta" };
        do {
            opc = JOptionPane.showOptionDialog(null, conta.infoConta() + "\nEscolha o que você quer fazer.",
                    "Conta de " + conta.getCliente().getNome(),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            switch (opc) {
                case 0 -> {
                    fun.saque(conta);
                    break;
                }
                case 1 -> {
                    fun.deposito(conta);
                    break;
                }
                case 2 -> {
                    fun.transferencia(conta);
                }
                default -> {
                    return;
                }
            }
        } while (opc != 3);
    }
}
