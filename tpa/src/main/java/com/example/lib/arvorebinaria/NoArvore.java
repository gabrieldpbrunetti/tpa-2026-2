package com.example.lib.arvorebinaria;

class NoArvore<T> {
    T valor;
    NoArvore<T> esquerda, direita;

    NoArvore(T valor) {
        this.valor = valor;
    }
}
