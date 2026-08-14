package com.example;

public class ListaEncadeada<T> implements IColecao<T> {
    Node<T> head;
    Comparator<T> comparator;
    boolean ordernar;

    public ListaEncadeada(Comparator<T> comparator, boolean ordernar) {
        this.head = null;
        this.comparator = comparator;
        this.ordernar = ordernar;
    }

    private boolean isEmpty() {
        return this.head == null;
    }

    private Node<T> getLastNode() {
        if (this.isEmpty()) return null;
        Node<T> iterator = this.head;
        while (iterator.next != null) iterator = itreator.next;
        return iterator;
    }

    public boolean adicionar(T novoValor) {
        Node<T> newNode = new Node(novoValor);
        
        Node<T> last = this.getLastNode();
        if (last == null)
            this.head = newNode;
        else
            last.next = newNode;
        
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
