package br.com.wagnerv.util; // Mais uma vez, estamos no pacote 'util'.
// Isso já nos diz que essa classe é uma "ferramenta" auxiliar.
// Ela não contém a lógica principal do nosso sistema (como cadastrar um livro),
// mas nos ajuda a preparar algo para a visualização.

import br.com.wagnerv.model.Livro; // Precisamos do nosso "molde de livro" aqui,
// porque essa classe vai pegar os dados de cada Livro
// para transformá-los em HTML.
import jakarta.servlet.http.HttpServletRequest; // Importamos isso porque, para gerar URLs corretas
// (especialmente para o botão de exclusão),
// precisamos de informações da requisição HTTP atual.

import java.util.List; // Precisamos disso para saber que vamos receber uma "lista" de livros.

/**
 * Pensa nessa classe 'GerarHtmlLivro' como um "montador de cards" ou um "gerador de relatórios"
 * em HTML para os nossos livros. A função dela é bem específica: pegar uma lista de objetos Livro
 * (que são os dados "puros") e transformá-los em pedaços de código HTML bonitos e prontos
 * para serem mostrados na página web.
 *
 * A grande vantagem de ter essa classe separada é que a gente não mistura o "como os dados são exibidos"
 * (o HTML) com o "como os dados funcionam" (a lógica do nosso LivroServlet).
 * É uma forma de manter as coisas organizadas e fáceis de dar manutenção!
 */
public class GerarHtmlLivro {

    /**
     * Esse é o coração da nossa classe. É o método que faz toda a mágica de
     * pegar os livros e transformar em HTML.
     *
     * Ele é 'public static', o que significa que podemos chamá-lo diretamente
     * usando 'GerarHtmlLivro.gerar(...)', sem precisar criar um objeto GerarHtmlLivro.
     * É como uma função utilitária que está sempre à disposição.
     *
     * @param request O objeto de requisição HTTP. Usamos ele para construir a URL
     * do formulário de exclusão de forma correta, garantindo que
     * funcione em qualquer ambiente.
     * @param livros Uma "lista" de objetos 'Livro'. Esses são os livros que
     * a gente quer que apareçam na nossa página.
     * @return Uma String gigante que é o HTML completo dos cards de todos os livros
     * que foram passados na lista.
     */
    public static String gerar(HttpServletRequest request, List<Livro> livros) {
        // A gente usa um 'StringBuilder' porque vamos montar essa String HTML em pedaços.
        // É muito mais eficiente (rápido!) do que ficar usando o operador '+' para concatenar strings
        // repetidamente dentro de um loop.
        StringBuilder html = new StringBuilder();

        // Aqui começa a mágica! A gente vai passar por CADA livro que está na lista.
        // Para cada livro, a gente vai "desenhar" um card HTML.
        for (Livro livro : livros) {
            // A gente começa abrindo uma 'div' com a classe 'livro-card'.
            // É essa 'div' que o nosso CSS vai usar pra estilizar o card, dar a ele aquela aparência legal.
            html.append("<div class='livro-card'>");

            // --- O Botão de Excluir ---
            // Aqui a gente cria um formulário HTML para o botão de exclusão.
            // É um formulário separado porque, quando a gente clica em "Excluir",
            // queremos enviar uma requisição específica para o servidor (para o nosso LivroServlet).
            html.append("<form class='delete-form' method='post' action='").append(request.getContextPath()).append("/livros'>");
            // O 'request.getContextPath()' é importante! Ele pega o caminho base da sua aplicação no servidor,
            // garantindo que a URL 'action' do formulário esteja sempre correta, não importa onde seu app esteja instalado.
            // Por exemplo: se seu app rodar em 'localhost:8080/biblioteca', ele adiciona '/biblioteca' automaticamente.

            // Esse campo 'hidden' (escondido) é importante. Ele diz para o LivroServlet
            // que a "ação" que a gente quer fazer é 'delete' (excluir).
            html.append("<input type='hidden' name='action' value='delete'/>");
            // E esse outro campo 'hidden' passa o ID do livro que a gente quer excluir.
            // O LivroServlet vai usar esse ID para saber qual livro remover da lista.
            html.append("<input type='hidden' name='id' value='").append(livro.getId()).append("'/>");
            // Finalmente, o botão em si! Ele tem um 'title' para aparecer um texto ao passar o mouse,
            // e o '<i class='fas fa-trash'></i>' é o código para mostrar o ícone da lixeira
            // (graças ao Font Awesome que a gente importou no CSS!).
            html.append("<button type='submit' title='Excluir'><i class='fas fa-trash'></i></button>");
            html.append("</form>"); // Fecha o formulário do botão de exclusão.

            // --- As Informações do Livro ---
            // Agora a gente começa a adicionar os parágrafos com as informações do livro.
            // Usamos tags <p> para cada informação (Título, Autor, etc.) e <strong> para deixar o rótulo em negrito.
            // E aqui a gente pega os dados de cada 'livro' da lista usando os métodos 'get' (getTitulo(), getAutor(), etc.).
            // Essa linha é crucial: ela exibe o título! Se não aparecer, o problema não é aqui,
            // mas sim de como o 'titulo' está sendo setado no objeto Livro, ou se há um CSS que o esconde.
            html.append("<p><strong>Título:</strong> ").append(livro.getTitulo()).append("</p>");
            html.append("<p><strong>Autor:</strong> ").append(livro.getAutor()).append("</p>");
            html.append("<p><strong>Ano:</strong> ").append(livro.getAno()).append("</p>");
            html.append("<p><strong>ISBN:</strong> ").append(livro.getIsbn()).append("</p>");
            html.append("<p><strong>ID:</strong> ").append(livro.getId()).append("</p>");

            html.append("</div>"); // Fecha a 'div' do card do livro.
        }

        // Depois de passar por todos os livros e montar o HTML de cada um,
        // a gente transforma o nosso 'StringBuilder' em uma String final e a devolve.
        // Essa String vai ser inserida lá no nosso JSP para ser exibida na página.
        return html.toString();
    }
}