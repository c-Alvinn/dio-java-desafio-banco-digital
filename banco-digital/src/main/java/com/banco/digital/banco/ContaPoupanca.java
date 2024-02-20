package com.banco.digital.banco;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente, String senha) {
        super(cliente, senha);
    }

    @Override
    public String infoConta() {
        String result = "----- Conta Poupanca -----\n\n" +
                "Titular: " + super.getCliente().getNome() + "\n" +
                "Agencia: " + super.getAgencia() + "\n" +
                "Numero da conta: " + super.getNumero() + "\n" +
                "Senha: " + super.getSenha() + "\n" +
                "Saldo: " + super.getSaldo() + "\n" +
                "--------------------------\n";
        return result;
    }

    @Override
    public String toString() {
        String result = "----- Conta Poupanca -----\n\n" +
                "Titular: " + super.getCliente().getNome() + "\n" +
                "Agencia: " + super.getAgencia() + "\n" +
                "Numero da conta: " + super.getNumero() + "\n" +
                "--------------------------\n";
        return result;
    }
}