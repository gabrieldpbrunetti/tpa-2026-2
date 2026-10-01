# tpa-2026-2

Repositório da disciplina de Técnicas de Programação Avançada (TPA) — 2026/2.

## Trabalho 2 — Análise de complexidade sobre estruturas de listas e árvore binária

O código fica em [`tpa/`](tpa), um projeto Maven. O programa de contatos permite escolher, ao iniciar, entre lista ordenada, lista não ordenada e árvore binária de busca (`ArvoreBinaria<T>`).

### Organização do código

```
tpa/
├── pom.xml
├── entrada_lista/                     # arquivos de contatos usados nos testes de listas
│   ├── entrada_1000.txt
│   ├── entrada_100000.txt
│   ├── entrada_200000.txt
│   └── entrada_300000.txt
└── src/
    ├── main/java/                     # código (detalhado abaixo)
    └── test/java/com/example/app/
        └── AppTest.java
```

```
tpa/src/main/java/
├── colecao/
│   └── IColecao.java                  # interface genérica da coleção (adicionar/pesquisar/remover/quantidadeNos)
├── com/example/lib/
│   ├── listaencadeada/                # biblioteca de lista encadeada genérica
│   │   ├── ListaEncadeada.java        # implementação de IColecao<T> como lista encadeada
│   │   └── Node.java                  # nó da lista
│   └── arvorebinaria/                 # biblioteca de árvore binária de busca genérica
│       ├── ArvoreBinariaBase.java     # classe abstrata fornecida pelo professor (implementa IColecao<T>)
│       ├── ArvoreBinaria.java         # implementação de ArvoreBinariaBase<T>
│       └── NoArvore.java              # nó da árvore
└── com/example/app/                   # programa de teste (usa a biblioteca acima)
    ├── App.java                       # ponto de entrada e menu principal
    ├── Menu.java                      # entrada/saída com o usuário e leitura de arquivo
    ├── Contato.java                   # classe de domínio (nome, telefone)
    ├── ComparatorContatoNome.java     # ordena/identifica contatos por nome (desempate por telefone)
    └── ComparatorContatoTelefone.java # ordena/identifica contatos por telefone (chave única)
```

- `colecao.IColecao` fica num pacote próprio: é o contrato compartilhado que qualquer estrutura de dados (lista, árvore) implementa, para que bibliotecas futuras possam ser usadas pelo `App` sem alterar o resto do programa.
- `com.example.lib.listaencadeada` e `com.example.lib.arvorebinaria` são as bibliotecas genéricas de lista e de árvore — não conhecem `Contato`, não imprimem nada, não têm regra de negócio.
- `com.example.app` é o programa de contatos que usa a biblioteca — toda regra de negócio (ex.: não permitir telefone duplicado) e toda interação com o usuário ficam aqui.

O `App` mantém duas instâncias de `IColecao<Contato>`: uma identificada por telefone (fonte da verdade, chave única) e outra por nome (índice auxiliar de busca). Isso permite que tanto "pesquisar por nome" quanto "pesquisar/remover por telefone" usem só os métodos padrão da interface (`pesquisar`/`remover`), com bom desempenho quando a lista é ordenada ou quando se usa a árvore.

### Como rodar

Pré-requisitos: JDK 21+ e Maven.

```
cd tpa
mvn compile
java -cp target/classes com.example.app.App
```

Ao iniciar, o programa pergunta qual estrutura usar:

```
Qual estrutura deseja usar?
1. Lista ordenada
2. Lista não ordenada
3. Árvore binária
```

Em seguida mostra um menu para carregar um arquivo de contatos, adicionar, pesquisar (por nome ou telefone), remover e alterar contatos.

A opção "Carregar arquivo" lê o arquivo `entrada.txt` da pasta a partir de onde o comando `java` foi executado (por padrão, `tpa/`). Para usar um dos arquivos de [`tpa/entrada_lista/`](tpa/entrada_lista), copie-o para `tpa/entrada.txt`:

```
cd tpa
cp entrada_lista/entrada_100000.txt entrada.txt
```

O arquivo deve estar no formato:

```
<quantidade de contatos>
nome1,telefone1
nome2,telefone2
...
```

Os métodos da árvore (`adicionar`, `pesquisar`, `remover`, `altura`) são recursivos. Com arquivos em que os telefones estão em ordem crescente, a árvore fica degenerada (altura ≈ n) e a pilha padrão do Java estoura (`StackOverflowError`) a partir de algumas dezenas de milhares de contatos. Nesse caso, rode com uma pilha maior:

```
java -Xss1g -cp target/classes com.example.app.App
```

### Testes

```
cd tpa
mvn test
```
