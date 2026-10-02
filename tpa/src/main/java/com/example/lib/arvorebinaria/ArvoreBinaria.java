package com.example.lib.arvorebinaria;

import java.util.ArrayDeque;
import java.util.Comparator;

public class ArvoreBinaria<T> extends ArvoreBinariaBase<T> {
    protected NoArvore<T> raiz;
    protected int quantidade;

    public ArvoreBinaria(Comparator<T> comparador) {
        super(comparador);
    }

    @Override
    public boolean adicionar(T novoValor) {
        int antes = this.quantidade;
        this.raiz = adicionar(this.raiz, novoValor);
        return this.quantidade > antes;
    }

    protected NoArvore<T> adicionar(NoArvore<T> no, T novoValor) {
        if (no == null) {
            this.quantidade++;
            return new NoArvore<T>(novoValor);
        }

        int cmp = this.comparador.compare(novoValor, no.valor);
        if (cmp < 0)
            no.esquerda = adicionar(no.esquerda, novoValor);
        else
            no.direita = adicionar(no.direita, novoValor);
        return no;
    }

    @Override
    public T pesquisar(T valor) {
        return pesquisar(this.raiz, valor);
    }

    protected T pesquisar(NoArvore<T> no, T valor) {
        if (no == null)
            return null;

        int cmp = this.comparador.compare(valor, no.valor);
        if (cmp == 0)
            return no.valor;
        if (cmp < 0)
            return pesquisar(no.esquerda, valor);
        return pesquisar(no.direita, valor);
    }

    @Override
    public boolean remover(T valor) {
        int antes = this.quantidade;
        this.raiz = remover(this.raiz, valor);
        return this.quantidade < antes;
    }

    protected NoArvore<T> remover(NoArvore<T> no, T valor) {
        if (no == null)
            return null;

        int cmp = this.comparador.compare(valor, no.valor);
        if (cmp < 0) {
            no.esquerda = remover(no.esquerda, valor);
            return no;
        }
        if (cmp > 0) {
            no.direita = remover(no.direita, valor);
            return no;
        }

        if (no.esquerda == null) {
            this.quantidade--;
            return no.direita;
        }
        if (no.direita == null) {
            this.quantidade--;
            return no.esquerda;
        }

        NoArvore<T> sucessor = no.direita;
        while (sucessor.esquerda != null)
            sucessor = sucessor.esquerda;
        no.valor = sucessor.valor;
        no.direita = remover(no.direita, sucessor.valor);
        return no;
    }

    @Override
    public int quantidadeNos() {
        return this.quantidade;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NoArvore<T> no) {
        if (no == null)
            return -1;
        return 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }

    @Override
    public String caminharEmNivel() {
        StringBuilder sb = new StringBuilder("[");
        if (this.raiz != null) {
            ArrayDeque<NoArvore<T>> fila = new ArrayDeque<NoArvore<T>>();
            fila.add(this.raiz);
            while (!fila.isEmpty()) {
                int tamanhoDoNivel = fila.size();
                for (int i = 0; i < tamanhoDoNivel; i++) {
                    NoArvore<T> no = fila.poll();
                    if (i > 0) sb.append(", ");
                    sb.append(no.valor);
                    if (no.esquerda != null) fila.add(no.esquerda);
                    if (no.direita != null) fila.add(no.direita);
                }
                if (!fila.isEmpty()) sb.append("\n");
            }
        }    
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String caminharEmOrdem() {
        StringBuilder sb = new StringBuilder("[");
        caminharEmOrdem(this.raiz, sb);
        sb.append("]");
        return sb.toString();
    }

    protected void caminharEmOrdem(NoArvore<T> no, StringBuilder sb) {
        if (no == null)
            return;

        caminharEmOrdem(no.esquerda, sb);
        sb.append(no.valor).append(", ");
        caminharEmOrdem(no.direita, sb);
        return;
    }
}
