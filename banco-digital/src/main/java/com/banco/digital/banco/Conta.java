package com.banco.digital.banco;

import lombok.Getter;

@Getter
public abstract class Conta implements MetodosConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    private int agencia;
    private int numero;
    private String senha;
    private double saldo;
    private Cliente cliente;

    public Conta(Cliente cliente, String senha) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.senha = senha;
        this.cliente = cliente;
    }

    @Override
    public boolean sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
    }

    @Override
    public boolean transferir(double valor, Conta contaDestino) {
        if (this.sacar(valor)) {
            contaDestino.depositar(valor);
            return true;
        } else {
            return false;
        }
    }

    public String infoConta() {
        return "";
    }
}
