# tpa-2026-2

Repositório da disciplina de Técnicas de Programação Avançada (TPA) — 2026/2.

## Trabalho 1 — Análise de complexidade sobre estruturas de listas

O código fica em [`tpa/`](tpa), um projeto Maven.

### Organização do código

```
tpa/src/main/java/
├── colecao/
│   └── IColecao.java                  # interface genérica da coleção (adicionar/pesquisar/remover/quantidadeNos)
├── com/example/lib/                   # biblioteca de lista encadeada genérica
│   ├── ListaEncadeada.java            # implementação de IColecao<T> como lista encadeada
│   └── Node.java                      # nó da lista
└── com/example/app/                   # programa de teste (usa a biblioteca acima)
    ├── App.java                       # ponto de entrada e menu principal
    ├── Menu.java                      # entrada/saída com o usuário e leitura de arquivo
    ├── Contato.java                   # classe de domínio (nome, telefone)
    ├── ComparatorContatoNome.java     # ordena/identifica contatos por nome (desempate por telefone)
    └── ComparatorContatoTelefone.java # ordena/identifica contatos por telefone (chave única)
```

- `colecao.IColecao` fica num pacote próprio: é o contrato compartilhado que qualquer estrutura de dados (lista, e futuramente árvore) implementa, para que bibliotecas futuras possam ser usadas pelo `App` sem alterar o resto do programa.
- `com.example.lib` é a biblioteca genérica de lista — não conhece `Contato`, não imprime nada, não tem regra de negócio.
- `com.example.app` é o programa de contatos que usa a biblioteca — toda regra de negócio (ex.: não permitir telefone duplicado) e toda interação com o usuário ficam aqui.

O `App` mantém duas instâncias de `IColecao<Contato>`: uma identificada por telefone (fonte da verdade, chave única) e outra por nome (índice auxiliar de busca). Isso permite que tanto "pesquisar por nome" quanto "pesquisar/remover por telefone" usem só os métodos padrão da interface (`pesquisar`/`remover`), com bom desempenho quando a lista é ordenada.

### Como rodar

Pré-requisitos: JDK 21+ e Maven.

```
cd tpa
mvn compile
java -cp target/classes com.example.app.App
```

O programa pergunta se as listas devem ser ordenadas e em seguida mostra um menu para carregar um arquivo de contatos, adicionar, pesquisar (por nome ou telefone), remover e alterar contatos.

Para usar a opção "Carregar arquivo", crie um arquivo `entrada.txt` na pasta a partir de onde o comando `java` foi executado (por padrão, `tpa/`), no formato:

```
<quantidade de contatos>
nome1,telefone1
nome2,telefone2
...
```

### Testes

```
cd tpa
mvn test
```
