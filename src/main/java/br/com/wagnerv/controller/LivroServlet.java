package br.com.wagnerv.controller; // Define o pacote onde a classe está localizada.
// 'controller' indica que esta classe é responsável por controlar o fluxo da aplicação.

import br.com.wagnerv.exception.LivroException; // Importa a classe de exceção personalizada para Livro.
import br.com.wagnerv.model.Livro;             // Importa a classe de modelo Livro (representa um livro).
import br.com.wagnerv.util.GerarHtmlLivro;     // Importa a classe utilitária para gerar o HTML dos livros.

import jakarta.servlet.ServletException;         // Classes para manipulação de Servlets.
import jakarta.servlet.annotation.WebServlet;    // Anotação para mapear o Servlet a uma URL.
import jakarta.servlet.http.HttpServlet;         // Classe base para Servlets HTTP.
import jakarta.servlet.http.HttpServletRequest;  // Objeto que representa a requisição HTTP.
import jakarta.servlet.http.HttpServletResponse; // Objeto que representa a resposta HTTP.

import java.io.IOException;     // Exceção de I/O (Input/Output).
import java.util.ArrayList;     // Implementação de lista dinâmica.
import java.util.Collections;   // Utilitários para coleções, incluindo sincronização.
import java.util.List;          // Interface para lista de objetos.

/**
 * Servlet principal responsável por gerenciar as operações CRUD (Create, Read, Update, Delete)
 * de livros na aplicação. Ele atua como o "controlador" que recebe as requisições do usuário,
 * interage com o modelo (Livro) e envia os dados para a camada de visualização (JSP).
 *
 * Anotação @WebServlet:
 * - `name = "livroServlet"`: Define um nome lógico para o Servlet.
 * - `value = "/livros"`: Mapeia este Servlet para a URL "/livros".
 * Isso significa que toda requisição para "http://seu_servidor/seu_app/livros"
 * será processada por este Servlet.
 */
@WebServlet(name = "livroServlet", value = "/livros")
public class LivroServlet extends HttpServlet {

    // Identificador de serialização para garantir compatibilidade em diferentes versões.
    private static final long serialVersionUID = 1L;

    // Lista estática e sincronizada para armazenar os objetos Livro em memória.
    // 'static' significa que a lista é compartilhada por todas as instâncias do Servlet (na prática, uma única).
    // 'synchronizedList' garante que a lista seja thread-safe, evitando problemas de concorrência
    // quando múltiplos usuários acessam simultaneamente.
    private List<Livro> livros;

    /**
     * Método de inicialização do Servlet.
     * É invocado uma única vez quando o Servlet é carregado pelo contêiner (ex: Tomcat).
     */
    @Override
    public void init() {
        // Inicializa a lista de livros como uma ArrayList sincronizada.
        livros = Collections.synchronizedList(new ArrayList<>());
        // Exemplo: Para um trabalho, você pode adicionar alguns livros iniciais aqui para teste
        // livros.add(new Livro(1, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943, "978-85-7973-045-8"));
        // livros.add(new Livro(2, "Dom Casmurro", "Machado de Assis", 1899, "978-85-8021-036-7"));
    }

    /**
     * Processa as requisições HTTP GET.
     * Este método é chamado quando o navegador faz uma requisição GET para "/livros"
     * (ex: ao digitar a URL ou clicar em um link).
     * Sua principal função é exibir a lista de livros existente.
     *
     * @param request  Objeto HttpServletRequest contendo a requisição do cliente.
     * @param response Objeto HttpServletResponse para enviar a resposta ao cliente.
     * @throws ServletException Exceção lançada por erros no Servlet.
     * @throws IOException      Exceção lançada por erros de I/O.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Gera o HTML formatado dos livros usando a classe utilitária GerarHtmlLivro.
        // Isso separa a lógica de renderização HTML do Servlet, mantendo o código mais limpo.
        String htmlLivros = GerarHtmlLivro.gerar(request, livros);

        // Define o HTML gerado como um atributo no objeto request.
        // Este atributo estará disponível para o JSP.
        request.setAttribute("htmlLivros", htmlLivros);

        // Encaminha (forward) a requisição para a página JSP (index.jsp) na pasta /view.
        // O forward mantém o mesmo objeto request e response, permitindo que o JSP acesse os atributos definidos.
        // A URL no navegador permanece a mesma ("/livros").
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
    }

    /**
     * Processa as requisições HTTP POST.
     * Este método é chamado quando um formulário HTML é submetido via método POST para "/livros".
     * Lida com as ações de "deletar" e "adicionar" livros.
     *
     * @param request  Objeto HttpServletRequest contendo a requisição do cliente (dados do formulário).
     * @param response Objeto HttpServletResponse para enviar a resposta ao cliente.
     * @throws ServletException Exceção lançada por erros no Servlet.
     * @throws IOException      Exceção lançada por erros de I/O.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtém o parâmetro 'action' do formulário para determinar qual operação realizar.
        String action = request.getParameter("action");

        // --- Lógica para EXCLUIR um livro ---
        if ("delete".equals(action)) {
            // Obtém o ID do livro a ser excluído do parâmetro da requisição.
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                try {
                    // Converte o ID de String para int.
                    int idParse = Integer.parseInt(id);
                    // Bloco sincronizado para garantir segurança de thread ao modificar a lista.
                    synchronized (livros) {
                        // Remove o livro da lista se o ID corresponder.
                        // Usa removeIf para remover condicionalmente, iterando pela lista.
                        livros.removeIf(livro -> livro.getId() == idParse);
                    }
                } catch (NumberFormatException e) {
                    // Trata o caso em que o ID não é um número válido.
                    // Para um trabalho, você pode adicionar uma mensagem de erro aqui também.
                    System.err.println("Erro ao converter ID para exclusão: " + e.getMessage());
                }
            }
            // Após a exclusão, redireciona o usuário para a página de listagem de livros.
            // O `sendRedirect` faz com que o navegador do cliente faça uma nova requisição GET para "/livros".
            // Isso é uma boa prática (Post/Redirect/Get pattern) para evitar submissões duplicadas de formulário.
            response.sendRedirect(request.getContextPath() + "/livros");
            return; // Encerra o método após o redirecionamento para 'delete'.
        }

        // --- Lógica para ADICIONAR um livro ---
        try {
            // Obtém os parâmetros do formulário de adição de livro.
            String titulo = request.getParameter("titulo");
            String autor = request.getParameter("autor");
            String anoStr = request.getParameter("ano");
            // Nota: O campo ISBN não está sendo capturado aqui, mas está no seu modelo Livro e no HTML.
            // Se você o adicionou ao formulário, precisaria de:
            // String isbn = request.getParameter("isbn");

            // Validação inicial para campos obrigatórios.
            if (titulo == null || titulo.trim().isEmpty() ||
                    autor == null || autor.trim().isEmpty() ||
                    anoStr == null || anoStr.trim().isEmpty()) {
                // Lança uma exceção personalizada se algum campo estiver vazio.
                throw new LivroException("Todos os campos devem ser preenchidos.");
            }

            // Cria uma nova instância de Livro.
            Livro livro = new Livro();
            // Define os atributos do livro com os dados recebidos do formulário.
            // Converte título e autor para maiúsculas (lógica de negócio).
            livro.setTitulo(titulo.toUpperCase());
            livro.setAutor(autor.toUpperCase());
            // Converte o ano de String para int. Pode lançar NumberFormatException.
            livro.setAno(Integer.parseInt(anoStr));
            // Se você tivesse ISBN no formulário:
            // livro.setIsbn(isbn);

            // Chama um método de validação do próprio objeto Livro.
            // Isso exemplifica a lógica de negócio dentro do modelo ou próxima a ele.
            livro.validar(); // Supondo que Livro.validar() possa lançar LivroException

            // Bloco sincronizado para garantir segurança de thread ao adicionar à lista.
            synchronized (livros) {
                // Adiciona o novo livro à lista.
                livros.add(livro);
            }
            // Redireciona o usuário para a página de listagem de livros após a adição bem-sucedida.
            response.sendRedirect(request.getContextPath() + "/livros");

        } catch (NumberFormatException e) {
            // Captura a exceção se o 'ano' não puder ser convertido para um número.
            request.setAttribute("mensagemErro", "Ano deve ser um número."); // Define mensagem de erro.
            // Gera novamente o HTML dos livros para exibir a lista existente.
            request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, livros));
            // Encaminha para o JSP para exibir a página com a mensagem de erro e a lista.
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        } catch (LivroException e) {
            // Captura a exceção personalizada LivroException (ex: validação do livro falhou).
            request.setAttribute("mensagemErro", e.getMessage()); // Define a mensagem de erro da exceção.
            // Gera novamente o HTML dos livros para exibir a lista existente.
            request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, livros));
            // Encaminha para o JSP para exibir a página com a mensagem de erro e a lista.
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);
        }
    }
}