package com.example.lib;
import java.util.Comparator;

import colecao.IColecao;

public class ListaEncadeada<T> implements IColecao<T> {
    private Node<T> head;
    private boolean ordernar;
    private Comparator<T> comparator;

    public ListaEncadeada(Comparator<T> comparator, boolean ordernar) {
        this.head = null;
        this.comparator = comparator;
        this.ordernar = ordernar;
    }

    private boolean isEmpty() {
        return this.head == null;
    }

    private void sortedInsert(Node<T> newNode) {
        Node<T> iterator = this.head;

        if (this.comparator.compare(this.head.value, newNode.value) > 0) {
            newNode.next = this.head;
            this.head = newNode;
            return;
        } 

        while (iterator.next != null && this.comparator.compare(iterator.next.value, newNode.value) <= 0)
            iterator = iterator.next;

        newNode.next = iterator.next;
        iterator.next = newNode;
    }

    private void unsortedInserted(Node<T> newNode) {
        Node<T> iterator = this.head;
        while (iterator.next != null)
            iterator = iterator.next;

        iterator.next = newNode;
    }

    public boolean adicionar(T novoValor) {
        Node<T> newNode = new Node<T>(novoValor);
        if (this.isEmpty())
            this.head = newNode;
        else if (this.ordernar)
            this.sortedInsert(newNode);
        else
            this.unsortedInserted(newNode);
        
        return true;
    }

    public T pesquisar(T valor) {
        Node<T> iterator = this.head;
        while (iterator != null) {
            int cmp = this.comparator.compare(iterator.value, valor);
            if (cmp == 0)
                return iterator.value;
            if (this.ordernar && cmp > 0)
                return null;

            iterator = iterator.next;
        }
        return null;
    }

    public boolean remover(T valor) {
        if (this.isEmpty()) return false;

        int cmpHead = this.comparator.compare(this.head.value, valor);
        if (cmpHead == 0) {
            this.head = this.head.next;
            return true;
        }
        if (this.ordernar && cmpHead > 0)
            return false;

        Node<T> iterator = this.head;
        while (iterator.next != null) {
            int cmp = this.comparator.compare(iterator.next.value, valor);
            if (cmp == 0) {
                iterator.next = iterator.next.next;
                return true;
            }
            if (this.ordernar && cmp > 0)
                return false;

            iterator = iterator.next;
        }

        return false;
    }

    public int quantidadeNos() {
        Node<T> iterator = this.head;
        int count = 0;
        while(iterator != null) {
            count++;
            iterator = iterator.next;
        }
        return count;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> iterator = this.head;
        while (iterator != null) {
            sb.append(iterator.value);
            if (iterator.next != null) sb.append(", ");
            iterator = iterator.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
