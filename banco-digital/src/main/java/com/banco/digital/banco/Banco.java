package com.banco.digital.banco;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Banco implements MetodosBanco {

    private String nome;
    private List<Conta> contas = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    public Banco(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean abrirConta(Conta conta) {
        return this.contas.add(conta);
    }

    @Override
    public boolean excluirConta(Conta conta) {
        return this.contas.remove(conta);
    }

    @Override
    public boolean adicionarCliente(Cliente cliente) {
        return this.clientes.add(cliente);
    }

    @Override
    public boolean excluirCliente(Cliente cliente) {
        return this.clientes.remove(cliente);
    }
}
