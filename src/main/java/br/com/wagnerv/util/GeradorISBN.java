package br.com.wagnerv.util; // Aqui a gente define o "endereço" dessa classe no nosso projeto.
// O pacote 'util' (de utilitários) é o lugar ideal para classes
// que fazem tarefas auxiliares, que não são diretamente um "modelo"
// ou um "controlador", mas que ajudam em alguma funcionalidade.
// Nesse caso, ela nos ajuda a gerar um ISBN.

import java.util.Random; // Importamos a classe 'Random' do Java.
// Essa classe é nossa "máquina de sortear números", essencial
// pra gente conseguir gerar números aleatórios para o ISBN.

/**
 * Pensa nessa classe 'GeradorISBN' como um pequeno "serviço" ou "ferramenta"
 * que a gente tem na nossa biblioteca. A única coisa que ela faz é criar
 * um código ISBN para um livro de forma automática e aleatória.
 *
 * A ideia é simplificar, já que gerar um ISBN real é algo bem complexo
 * e envolveria regras internacionais. Para o nosso trabalho, essa ferramenta
 * que gera um número "parecido" já resolve o problema!
 */
public class GeradorISBN {

    /**
     * Esse é o método principal dessa classe, o "botão de ligar" do nosso gerador.
     * Ele é 'public static', o que significa que a gente pode chamar ele de qualquer lugar
     * do nosso código sem precisar criar um objeto 'GeradorISBN' primeiro.
     * É só usar 'GeradorISBN.gerar()' e ele já te devolve um ISBN prontinho!
     *
     * @return Uma String que representa um ISBN (um código identificador único para o livro),
     * gerado de forma simulada.
     */
    public static String gerar() {
        // Primeiro, a gente cria um objeto 'Random'. Ele é o cara que vai nos dar os números aleatórios.
        Random gerador = new Random();

        // Depois, a gente cria um 'StringBuilder'. Pensa nele como um "caderno de rascunho"
        // onde a gente vai montando o nosso ISBN. Começamos com "123" pra ele já ter uma cara
        // de ISBN, que normalmente começa com números específicos do grupo.
        StringBuilder ISBN = new StringBuilder("123");

        // Agora, a gente vai adicionar 9 números aleatórios a esse "rascunho".
        // Um ISBN real tem 13 dígitos, então a gente começa com 3 e adiciona mais 9,
        // totalizando 12 dígitos para o nosso exemplo.
        for (int i = 0; i < 9; i++) {
            // gerador.nextInt(10) nos dá um número inteiro aleatório entre 0 e 9.
            // A gente adiciona esse número ao nosso StringBuilder.
            ISBN.append(gerador.nextInt(10));
        }

        // No final, quando o ISBN está todo montado no nosso "rascunho",
        // a gente transforma ele em uma String final e devolve pra quem pediu.
        return ISBN.toString();
    }
}