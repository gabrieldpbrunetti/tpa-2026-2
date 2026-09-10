package com.example.app;

import java.util.Comparator;

public class ComparatorContatoTelefone implements Comparator<Contato> {

    @Override
    public int compare(Contato contato1, Contato contato2) {
        if (contato1 == contato2) {
            return 0;
        }

        if (contato1 == null) {
            return -1;
        }

        if (contato2 == null) {
            return 1;
        }

        if (contato1.getTelefone() == null && contato2.getTelefone() == null) {
            return 0;
        }

        if (contato1.getTelefone() == null) {
            return -1;
        }

        if (contato2.getTelefone() == null) {
            return 1;
        }

        return contato1.getTelefone().compareTo(contato2.getTelefone());
    }
}
