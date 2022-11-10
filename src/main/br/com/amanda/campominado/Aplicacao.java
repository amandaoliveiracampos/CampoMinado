package main.br.com.amanda.campominado;

import main.br.com.amanda.campominado.modelo.Tabuleiro;
import main.br.com.amanda.campominado.visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(6, 6, 3);

        new TabuleiroConsole(tabuleiro);

    }
}