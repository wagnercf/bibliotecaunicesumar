<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Biblioteca Virtual - Cadastro de Livros</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />

    <!-- FontAwesome para ícones -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

    <style>
        /* Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #141e30, #243b55);
            color: #e0e6f8;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 2rem 1rem;
        }

        h1 {
            font-weight: 600;
            font-size: 2.8rem;
            margin-bottom: 1.5rem;
            letter-spacing: 1px;
            text-shadow: 0 2px 6px rgba(0,0,0,0.7);
        }

        /* Form container (Cadastro de Livros) */
        form:not(.delete-form) { /* Especifica para o formulário de adicionar, não o de delete */
            background: rgba(255, 255, 255, 0.1);
            padding: 30px 35px;
            border-radius: 15px;
            box-shadow: 0 8px 30px rgba(0,0,0,0.6);
            width: 100%;
            max-width: 480px;
            backdrop-filter: blur(10px);
            margin-bottom: 3rem;
            transition: transform 0.3s ease;
        }

        form:not(.delete-form):hover {
            transform: translateY(-6px);
        }

        label {
            display: block;
            font-weight: 600;
            margin-bottom: 8px;
            font-size: 1.1rem;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px 15px;
            border-radius: 10px;
            border: none;
            font-size: 1rem;
            background: rgba(255, 255, 255, 0.15);
            color: #e0e6f8;
            box-shadow: inset 0 2px 5px rgba(0,0,0,0.3);
            transition: background 0.3s, box-shadow 0.3s;
            margin-bottom: 15px; /* Espaçamento entre inputs */
        }

        input[type="text"]::placeholder {
            color: #b0b8d4;
            font-style: italic;
        }

        input[type="text"]:focus {
            background: rgba(255, 255, 255, 0.4);
            outline: none;
            box-shadow: 0 0 12px 3px #5db9ff;
            color: #141e30;
            font-weight: 600;
        }

        /* Botão de Adicionar Livro */
        /* Use um seletor mais específico para não conflitar com o botão de excluir */
        button[type="submit"]:not(.delete-form button) {
            margin-top: 20px;
            width: 100%;
            padding: 14px;
            border: none;
            border-radius: 12px;
            background: #5db9ff;
            color: #141e30;
            font-weight: 700;
            font-size: 1.2rem;
            cursor: pointer;
            box-shadow: 0 6px 15px rgba(93, 185, 255, 0.6);
            transition: background-color 0.3s ease;
        }

        button[type="submit"]:not(.delete-form button):hover {
            background: #3a8ddd;
            box-shadow: 0 8px 22px rgba(58, 141, 221, 0.8);
        }

        /* Mensagem de erro */
        .error {
            background: #ff4c4c;
            color: white;
            padding: 14px 20px;
            border-radius: 12px;
            max-width: 480px;
            width: 100%;
            margin-bottom: 2rem;
            font-weight: 600;
            text-align: center;
            box-shadow: 0 0 15px rgba(255, 76, 76, 0.8);
            user-select: none;
        }

        /* Lista de livros (container para os cards) */
        .livros-lista {
            width: 100%;
            max-width: 960px;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 25px;
            padding: 10px;
            justify-content: center;
        }

        /* Card Individual do Livro */
        .livro-card {
            position: relative; /* CRUCIAL: Para posicionar o botão absolutamente dentro dele */
            padding: 20px;
            padding-top: 45px; /* Espaço extra no topo para o botão, para não sobrepor o texto */
            background: rgba(255, 255, 255, 0.12);
            border-radius: 15px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.5);
            color: #fff;
            min-height: 180px;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            gap: 8px;
            font-family: 'Poppins', sans-serif;
            box-sizing: border-box;
            transition: transform 0.3s ease;
            overflow: hidden; /* Importante para o glow do botão não sair do card */
        }

        .livro-card:hover {
            transform: translateY(-5px);
        }

        /* Estilo do Formulário de Exclusão (que contém o botão) */
        form.delete-form {
            position: absolute;
            top: 10px;
            right: 10px;
            margin: 0;
            padding: 0;
            background: transparent; /* GARANTE QUE NÃO TENHA FUNDO */
            border: none; /* GARANTE QUE NÃO TENHA BORDA AZUL/VERMELHA NO FORM */
            box-shadow: none; /* GARANTE QUE NÃO TENHA SOMBRA NO FORM */
            z-index: 10; /* Garante que o botão fique acima do conteúdo */
        }

        /* Estilo do Botão "Excluir" em si (Ícone) */
        form.delete-form button {
            /* REMOVE QUALQUER ESTILO DE BOTÃO GRANDE ANTERIOR */
            width: 35px; /* Tamanho fixo para o botão */
            height: 35px; /* Tamanho fixo para o botão */
            border-radius: 50%; /* Torna o botão um círculo */
            background-color: transparent; /* Fundo transparente */
            border: none; /* SEM BORDA NO PRÓPRIO BOTÃO */
            color: #ff6b6b; /* Cor vermelha para o ícone */
            font-size: 1.3rem; /* Tamanho do ícone */
            cursor: pointer;
            padding: 0; /* REMOVE QUALQUER PADDING INTERNO QUE PUXA O ÍCONE */
            display: flex; /* Para centralizar o ícone */
            align-items: center;
            justify-content: center;
            box-shadow: none; /* REMOVE QUALQUER SOMBRA PADRÃO DO BOTÃO */
            transition: color 0.3s ease, background-color 0.3s ease;
            outline: none; /* Remove a borda de foco padrão */
        }

        form.delete-form button:hover {
            color: #e84141; /* Cor mais escura no hover */
            background-color: rgba(255, 107, 107, 0.15); /* Fundo sutil no hover */
            /* NÃO ADICIONE NENHUMA BORDA OU GLOW AQUI, APENAS MUDE A COR/FUNDO */
        }

        /* Estilos para o texto dentro do card */
        .livro-card p {
            margin: 0; /* Remove margem padrão dos parágrafos */
            line-height: 1.4;
            word-wrap: break-word;
        }

        .livro-card p strong {
            font-size: 1.1em;
        }


        /* Responsive */
        @media (max-width: 600px) {
            form:not(.delete-form) {
                padding: 25px 20px;
            }

            h1 {
                font-size: 2.2rem;
            }

            button[type="submit"]:not(.delete-form button) {
                font-size: 1rem;
            }

            .livros-lista {
                grid-template-columns: 1fr;
            }

            .livro-card {
                width: 100%;
            }
        }
    </style>
</head>
<body>

<h1>Biblioteca Virtual - Cadastro de Livros</h1>

<% if (request.getAttribute("mensagemErro") != null) { %>
<div class="error"><%= request.getAttribute("mensagemErro") %></div>
<% } %>

<form method="post" action="<%= request.getContextPath() %>/livros">
    <input type="hidden" name="action" value="add" />
    <label for="titulo">Título</label>
    <input type="text" id="titulo" name="titulo" placeholder="Digite o título do livro" required />

    <label for="autor">Autor</label>
    <input type="text" id="autor" name="autor" placeholder="Nome do autor" required />

    <label for="ano">Ano</label>
    <input type="text" id="ano" name="ano" placeholder="Ano de publicação" required />

    <button type="submit"><i class="fas fa-plus-circle"></i> Adicionar Livro</button>
</form>

<div class="livros-lista">
    <%= request.getAttribute("htmlLivros") %>
</div>

</body>
</html>
