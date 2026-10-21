package com.example.lib.arvorebinaria;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparador) {
        super(comparador);
    }

    // A inserção da árvore binária chama adicionar(filho) recursivamente; por ligação dinâmica
    // essas chamadas caem aqui, então cada nó do caminho é rebalanceado na volta da recursão.
    @Override
    protected NoArvore<T> adicionar(NoArvore<T> no, T novoValor) {
        return balancear(super.adicionar(no, novoValor));
    }

    // Mesmo raciocínio da inserção. No caso de dois filhos, a remoção do sucessor também
    // passa por aqui, rebalanceando o caminho até ele.
    @Override
    protected NoArvore<T> remover(NoArvore<T> no, T valor) {
        return balancear(super.remover(no, valor));
    }

    /**
     * Fator de balanceamento: altura da subárvore esquerda menos a da direita.
     * Em uma árvore AVL fica sempre entre -1 e 1.
     */
    protected int fatorBalanceamento(NoArvore<T> no) {
        return altura(no.esquerda) - altura(no.direita);
    }

    /**
     * Restaura a propriedade AVL no nó (assumindo que as subárvores já estão balanceadas
     * e com alturas atualizadas) e retorna a nova raiz da subárvore.
     */
    protected NoArvore<T> balancear(NoArvore<T> no) {
        if (no == null)
            return null;

        int fb = fatorBalanceamento(no);
        if (fb > 1) {
            // Caso esquerda-direita: rotação dupla
            if (fatorBalanceamento(no.esquerda) < 0)
                no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no);
        }
        if (fb < -1) {
            // Caso direita-esquerda: rotação dupla
            if (fatorBalanceamento(no.direita) > 0)
                no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no);
        }
        return no;
    }

    protected NoArvore<T> rotacaoDireita(NoArvore<T> no) {
        NoArvore<T> novaRaiz = no.esquerda;
        no.esquerda = novaRaiz.direita;
        novaRaiz.direita = no;
        atualizarAltura(no);
        atualizarAltura(novaRaiz);
        return novaRaiz;
    }

    protected NoArvore<T> rotacaoEsquerda(NoArvore<T> no) {
        NoArvore<T> novaRaiz = no.direita;
        no.direita = novaRaiz.esquerda;
        novaRaiz.esquerda = no;
        atualizarAltura(no);
        atualizarAltura(novaRaiz);
        return novaRaiz;
    }
}
