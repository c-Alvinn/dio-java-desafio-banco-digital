package com.banco.digital;

import javax.swing.JOptionPane;

import com.banco.digital.banco.Banco;
import com.banco.digital.menu.Menu;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco("Alvin's Bank");

        JOptionPane.showMessageDialog(null, "Bem vindo ao " + banco.getNome() + "!");

        Menu menu = new Menu(banco);

        menu.init();

    }
}