package br.com.wagnerv.exception; // Define o pacote onde a classe está localizada.
// O nome 'exception' é uma convenção para classes que representam exceções.

/**
 * Classe de exceção personalizada para erros específicos relacionados a operações com Livros.
 *
 * Esta classe herda de `java.lang.Exception`, o que a torna uma exceção checada (checked exception).
 * Isso significa que qualquer método que possa lançar uma `LivroException` precisa
 * declará-la em sua assinatura (`throws LivroException`) ou capturá-la (`try-catch`).
 *
 * O uso de exceções personalizadas melhora a legibilidade e manutenção do código,
 * permitindo que a aplicação trate erros de forma mais específica e granular,
 * em vez de usar exceções genéricas (como `Exception` ou `RuntimeException`).
 * Por exemplo, se uma validação de negócio de um livro falha, lançamos uma LivroException,
 * deixando claro o tipo de problema que ocorreu.
 */
public class LivroException extends Exception { // 'extends Exception' indica que esta é uma exceção checada.

    /**
     * Construtor da LivroException que aceita uma mensagem de erro.
     *
     * @param msg A mensagem descritiva do erro que ocorreu. Esta mensagem
     * será acessível através do método `getMessage()` da exceção.
     */
    public LivroException(String msg) {
        // Chama o construtor da classe pai (Exception) passando a mensagem.
        // Isso armazena a mensagem internamente na exceção, tornando-a disponível
        // quando a exceção for capturada e seu método getMessage() for chamado.
        super(msg);
    }

    // Opcionalmente, você poderia adicionar outros construtores, como:
    // public LivroException(String msg, Throwable cause) {
    //     super(msg, cause); // Para envolver outra exceção que causou esta
    // }

    // E métodos específicos, se a exceção precisasse carregar mais dados além da mensagem.
    // Ex: private int codigoErro;
    //     public int getCodigoErro() { return codigoErro; }
    // Mas para um trabalho de faculdade, o construtor com String msg é geralmente suficiente.
}