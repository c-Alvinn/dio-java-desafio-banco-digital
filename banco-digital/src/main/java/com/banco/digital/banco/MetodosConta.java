package com.banco.digital.banco;

public interface MetodosConta {

    public boolean sacar(double valor);

    public void depositar(double valor);

    public boolean transferir(double valor, Conta contaDestino);

}
