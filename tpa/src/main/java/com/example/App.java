package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Menu m = new Menu();
        char opcao;
        do {
            opcao = m.obterAcao();
            System.out.println(opcao);

        } while(opcao != '0');
    }
}
