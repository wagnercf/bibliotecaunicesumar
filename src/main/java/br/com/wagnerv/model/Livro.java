package br.com.wagnerv.model; // Aqui a gente define onde essa classe "mora" no nosso projeto.
// O pacote 'model' é o lugar certinho para as classes que representam
// os 'objetos' ou 'entidades' do nosso sistema – neste caso, um Livro.

import br.com.wagnerv.exception.LivroException; // Importamos nossa exceção personalizada.
// Se algo der errado na validação de um Livro,
// a gente tem um jeito específico de avisar!

/**
 * Pensa nessa classe 'Livro' como a "carteira de identidade" de cada livro
 * que a gente vai cadastrar na nossa biblioteca. Ela guarda todas as informações
 * importantes sobre um livro: o que ele é, quem escreveu, quando foi feito,
 * e um código único pra ele.
 *
 * Ela também tem umas regrinhas para garantir que os dados que a gente coloca
 * sejam válidos, pra não ter livro "sem título" ou com "ano zero", sabe?
 */
public class Livro {
    // --- Atributos da Classe (as "características" de um Livro) ---

    // Isso aqui é um contador estático. 'static' significa que ele pertence à CLASSE Livro,
    // e não a um livro específico. É como um "número de série" geral que a gente usa
    // pra dar um ID único pra cada novo livro que é criado.
    private static int contador = 1;

    // Cada livro vai ter o seu ID único.
    private int id;
    // O nome do livro.
    private String titulo;
    // Quem escreveu.
    private String autor;
    // O ano de publicação.
    private int ano;
    // O ISBN é tipo o CPF do livro, um identificador padrão mundial.
    // Aqui a gente vai gerar um automaticamente pra simplificar.
    private String isbn;

    /**
     * O 'Construtor Padrão' da classe Livro.
     * Quando a gente cria um 'new Livro()', esse pedaço de código é executado.
     * É como se ele preparasse o livro para ser preenchido.
     */
    public Livro() {
        // A primeira coisa que fazemos é dar um ID único para esse novo livro.
        // A gente pega o valor atual do 'contador' e depois incrementa ele para o próximo livro.
        this.id = contador++;
        // E também geramos um ISBN automaticamente para ele.
        this.isbn = gerarIsbn();
    }

    /**
     * Método privado para gerar um ISBN.
     * 'private' significa que só a própria classe Livro pode chamar esse método.
     * Ele cria uma sequência aleatória de números, simulando um ISBN.
     * Não é um ISBN real de verdade, mas serve pro nosso propósito de trabalho!
     * @return Uma String que simula um número ISBN.
     */
    private String gerarIsbn() {
        // Começa com "123" pra parecer um ISBN.
        StringBuilder sb = new StringBuilder("123");
        // Adiciona 9 dígitos aleatórios de 0 a 9.
        for (int i = 0; i < 9; i++) {
            sb.append((int)(Math.random() * 10)); // Math.random() gera entre 0.0 e 1.0. Multiplica por 10 e pega a parte inteira.
        }
        return sb.toString(); // Converte o StringBuilder para String.
    }

    /**
     * Esse método é super importante! Ele serve para 'validar' se as informações
     * que a gente colocou no livro fazem sentido e atendem às nossas regras de negócio.
     * Por exemplo, um livro não pode não ter título, né?
     *
     * @throws LivroException Se alguma regra de validação não for cumprida,
     * a gente "lança" uma LivroException com uma mensagem clara.
     * Quem chamar esse método vai ter que lidar com essa exceção.
     */
    public void validar() throws LivroException {
        // Verifica se o título está vazio ou só com espaços em branco.
        if (titulo == null || titulo.trim().isEmpty())
            throw new LivroException("Título obrigatório."); // Se sim, lança a exceção!

        // Faz a mesma coisa para o autor.
        if (autor == null || autor.trim().isEmpty())
            throw new LivroException("Autor obrigatório.");

        // E aqui verifica se o ano é válido (maior que zero).
        if (ano <= 0)
            throw new LivroException("Ano inválido.");
    }

    // --- Métodos Getters e Setters (para acessar e modificar as informações do Livro) ---
    // Pensa neles como as "portinhas" para a gente pegar (get) ou mudar (set)
    // os valores dos atributos do livro de forma controlada.

    public int getId() {
        return id; // Retorna o ID único do livro.
    }

    public String getTitulo() {
        return titulo; // Retorna o título do livro.
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo; // Define o título do livro. 'this.titulo' se refere ao atributo da classe.
    }

    public String getAutor() {
        return autor; // Retorna o autor.
    }

    public void setAutor(String autor) {
        this.autor = autor; // Define o autor.
    }

    public int getAno() {
        return ano; // Retorna o ano de publicação.
    }

    public void setAno(int ano) {
        this.ano = ano; // Define o ano de publicação.
    }

    // Adicione este getter:
    public String getIsbn() {
        return isbn; // Retorna o ISBN gerado automaticamente.
    }
}