package com.example;
import java.util.Comparator;

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

        while (iterator.next != null && this.comparator(iterator.next.value, newNode.value) <= 0)
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
        while(iterator != null) {
            if (iterator.value.equals(valor))
                return iterator.value;
            
            iterator = iterator.next;
        }
        return null;
    }

    public boolean remover(T valor) {
        if (this.isEmpty()) return false;

        if (this.head.value.equals(valor)) {
            this.head = this.head.next;
            return true;
        }

        Node<T> iterator = this.head;
        while(iterator != null) {
            if (iterator.next != null && iterator.next.value.equals(valor)) {
                    iterator.next = iterator.next.next;
                    return true;
            }
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
    
    public T procurarPor(T value, Comparator<T> comparator) {
        Node<T> iterator = this.head;
        while(iterator != null) {
            if (comparator.compare(iterator.value, value) == 0)
                return iterator.value;
            
            iterator = iterator.next;
        }
        return null;
    }
    
    public procurarPor(T value) {
        return this.procurarPor(value, this.comparator);
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
