package com.example;
import java.util.Scanner;
import java.nio.file.Path;

public class Menu {
    private Scanner s;

    public Menu() {
        this.s = new Scanner(System.in);
    }

    public boolean obterOrdenada() {
        System.out.print("Você deseja que as listas sejam ordenadas? <s/n> ");
        char resposta = this.s.nextLine().toLowerCase().charAt(0);
        return resposta == 's';
    }

    public String obterArquivoCarga() {
        System.out.print("Digite o caminho do arquivo: ");
        String path = this.s.nextLine();
        return path;
    }

    public ContatoIn obterContato() {
        System.out.print("Digite o nome do contato: ");
        String nome = this.s.nextLine();
        System.out.print("Digite o telefone do contato: ");
        String telefone = this.n.nextLine();

        return new ContatoIn(nome, telefone);
    }

    public String obterTelefone() {
        System.out.print("Digite o telefone a ser buscado: ");
        String telefone = this.n.nextLine();
        telefone.replaceAll("[()+- ]", "");
        return telefone;
    }

    public String obterNome() {
        System.out.print("Digite o nome a ser buscado: ");
        String nome = this.s.nextLine();
        return nome.strip();
    }

    public void imprimirMenu() {
        string menuOutput = "1 "
    }
}
