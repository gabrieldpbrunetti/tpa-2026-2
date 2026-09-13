package com.example.app;

import java.util.Comparator;

public class ComparatorContatoNome implements Comparator<Contato> {
    
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

        if (contato1.getNome() == null && contato2.getNome() == null) {
            return 0;
        }

        if (contato1.getNome() == null) {
            return -1;
        }

        if (contato2.getNome() == null) {
            return 1;
        }

        int porNome = contato1.getNome().compareToIgnoreCase(contato2.getNome());
        if (porNome != 0)
            return porNome;

        // Nomes iguais (homônimos): desempata por telefone para identificar o contato exato.
        // Numa busca só por nome o telefone não é informado, então trata como igual.
        if (contato1.getTelefone() == null || contato2.getTelefone() == null)
            return 0;

        return contato1.getTelefone().compareTo(contato2.getTelefone());
    }
}
