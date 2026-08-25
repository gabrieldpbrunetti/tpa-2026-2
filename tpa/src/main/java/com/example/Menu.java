package com.example;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Menu {
    private Scanner s;

    public Menu() {
        this.s = new Scanner(System.in);
    }

    public char obterAcao() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Carregar arquivo\n");
        sb.append("2. Adicionar Contato\n");
        sb.append("3. Pesquisar Contato por nome\n");
        sb.append("4. Pesquisar Contato por telefone\n");
        sb.append("5. Remover contato por telefone\n");
        sb.append("6. Alterar dados de contato\n");
        sb.append("0. Sair");
        System.out.println(sb.toString());
        
        return this.s
            .nextLine()
            .strip()
            .charAt(0);
    }

    public ArrayList<ContatoIn> carregarArquivoContatos(String path) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        int amountOfLines = Integer.parseInt(br.readLine().strip());
        
        ArrayList<ContatoIn> contatos = new ArrayList<>(amountOfLines);
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String nome = parts[0].strip();
            String telefone = parts[1].strip();
            ContatoIn contato = new ContatoIn(nome, telefone);
            contatos.add(contato);
        }
        br.close();
        return contatos;
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
        String telefone = this.s.nextLine();
        return new ContatoIn(nome, telefone);
    }

    public String obterTelefone() {
        System.out.print("Digite o telefone a ser buscado: ");
        String telefone = this.s.nextLine();
        return telefone.replaceAll("[()+- ]", "");
    }

    public String obterNome() {
        System.out.print("Digite o nome a ser buscado: ");
        String nome = this.s.nextLine();
        return nome.strip();
    }

}
