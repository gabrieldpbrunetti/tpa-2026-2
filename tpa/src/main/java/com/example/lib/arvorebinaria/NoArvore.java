package com.example.lib.arvorebinaria;

class NoArvore<T> {
    T valor;
    NoArvore<T> esquerda, direita;
    int altura;

    NoArvore(T valor) {
        this.valor = valor;
    }
}
