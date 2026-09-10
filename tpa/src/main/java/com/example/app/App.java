package com.example.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import colecao.IColecao;
import com.example.lib.ListaEncadeada;

public class App
{
    public static void main( String[] args )
    {
        Menu m = new Menu();

        boolean ordenada;
        ordenada = m.obterOrdenada();

        // Fonte da verdade: telefone é a chave única do negócio.
        IColecao<Contato> colecaoPorTelefone = new ListaEncadeada<Contato>(new ComparatorContatoTelefone(), ordenada);
        // Índice auxiliar só para acelerar a busca por nome (nome não é único, então nunca é usado para remover).
        IColecao<Contato> colecaoPorNome = new ListaEncadeada<Contato>(new ComparatorContatoNome(), ordenada);

        char opcao;
        do {
            opcao = m.obterAcao();

            if (opcao == '1') {
                try {
                    long inicio = System.nanoTime();
                    ArrayList<Contato> contatos = m.carregarArquivoContatos("entrada.txt");
                    for (Contato contato : contatos) {
                        colecaoPorTelefone.adicionar(contato);
                        colecaoPorNome.adicionar(contato);
                    }
                    long fim = System.nanoTime();
                    System.out.println("Tempo de leitura e montagem da lista: " + (fim - inicio) + " ns");
                } catch (FileNotFoundException e) {
                    System.out.println("Arquivo entrada.txt não encontrado.");
                } catch (IOException e) {
                    System.out.println("Erro ao ler o arquivo entrada.txt.");
                }
            }
            else if (opcao == '2') {
                Contato novoContato = m.obterContato();
                if (colecaoPorTelefone.pesquisar(novoContato) != null) {
                    System.out.println("Já existe um contato com esse telefone.");
                } else {
                    colecaoPorTelefone.adicionar(novoContato);
                    colecaoPorNome.adicionar(novoContato);
                    System.out.println("Contato adicionado.");
                }
            }
            else if (opcao == '3') {
                String nome = m.obterNome();
                long inicio = System.nanoTime();
                Contato encontrado = colecaoPorNome.pesquisar(new Contato(nome, null));
                long fim = System.nanoTime();
                if (encontrado != null)
                    System.out.println("Telefone: " + encontrado.getTelefone());
                else
                    System.out.println("Contato não encontrado.");
                System.out.println("Tempo de busca: " + (fim - inicio) + " ns");
            }
            else if (opcao == '4') {
                String telefone = m.obterTelefone();
                long inicio = System.nanoTime();
                Contato encontrado = colecaoPorTelefone.pesquisar(new Contato(null, telefone));
                long fim = System.nanoTime();
                if (encontrado != null)
                    System.out.println("Nome: " + encontrado.getNome());
                else
                    System.out.println("Contato não encontrado.");
                System.out.println("Tempo de busca: " + (fim - inicio) + " ns");
            }
            else if (opcao == '5') {
                String telefone = m.obterTelefone();

                long inicio = System.nanoTime();
                Contato encontrado = colecaoPorTelefone.pesquisar(new Contato(null, telefone));
                boolean removido = false;
                if (encontrado != null) {
                    removido = colecaoPorTelefone.remover(encontrado);
                    colecaoPorNome.remover(encontrado);
                }
                long fim = System.nanoTime();

                System.out.println(removido ? "Contato removido." : "Contato não existia.");
                System.out.println("Tempo de remoção: " + (fim - inicio) + " ns");
            }
            else if (opcao == '6') {
                String nome = m.obterNome();
                Contato encontrado = colecaoPorNome.pesquisar(new Contato(nome, null));
                if (encontrado == null) {
                    System.out.println("Contato não encontrado.");
                } else {
                    System.out.println("Telefone atual: " + encontrado.getTelefone());
                    Contato novosDados = m.obterContato();

                    colecaoPorTelefone.remover(encontrado);
                    colecaoPorNome.remover(encontrado);

                    colecaoPorTelefone.adicionar(novosDados);
                    colecaoPorNome.adicionar(novosDados);
                    System.out.println("Contato atualizado.");
                }
            }
            else if (opcao == '0') {
                System.out.println("Quantidade total de contatos: " + colecaoPorTelefone.quantidadeNos());
            }

        } while(opcao != '0');
    }
}
