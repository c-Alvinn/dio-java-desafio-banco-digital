package com.banco.digital.banco;

public interface MetodosBanco {

    public boolean abrirConta(Conta conta);

    public boolean excluirConta(Conta conta);

    public boolean adicionarCliente(Cliente cliente);

    public boolean excluirCliente(Cliente cliente);
}
