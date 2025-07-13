<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Bem-vindo a Biblioteca Virtual</title>

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />

    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />

    <style>
        body, html {
            height: 100%;
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #141e30, #243b55);
            display: flex;
            justify-content: center;
            align-items: center;
            color: #f0f4ff;
            overflow: hidden;
        }

        .container {
            background: rgba(255,255,255,0.12);
            padding: 50px 60px;
            border-radius: 25px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.6);
            text-align: center;
            max-width: 500px;
            width: 90vw;
            backdrop-filter: blur(12px);
            animation: fadeInUp 1s ease forwards;
            position: relative;
        }

        .icon {
            font-size: 6rem;
            margin-bottom: 25px;
            color: #5db9ff;
            text-shadow: 0 3px 10px rgba(93, 185, 255, 0.75);
        }

        h1 {
            font-weight: 700;
            font-size: 2.8rem;
            margin-bottom: 1rem;
            letter-spacing: 1.2px;
            text-shadow: 0 2px 8px rgba(0,0,0,0.7);
        }

        p {
            font-weight: 500;
            font-size: 1.3rem;
            margin-bottom: 3rem;
            color: #c7d1ffcc;
        }

        a.btn-entrar {
            display: inline-flex;
            align-items: center;
            gap: 10px;
            background-color: #5db9ff;
            padding: 18px 45px;
            font-size: 1.25rem;
            font-weight: 700;
            border-radius: 40px;
            color: #141e30;
            text-decoration: none;
            box-shadow: 0 8px 25px rgba(93, 185, 255, 0.6);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        a.btn-entrar:hover {
            background-color: #3a8ddd;
            box-shadow: 0 12px 40px rgba(58,141,221,0.9);
            color: #fff;
        }

        a.btn-entrar i {
            font-size: 1.5rem;
        }

        /* Fade In Animation */
        @keyframes fadeInUp {
            from {
                opacity: 0;
                transform: translateY(40px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Background animated bubbles */
        body::before {
            content: "";
            position: fixed;
            top: -20%;
            left: -20%;
            width: 150%;
            height: 150%;
            background: radial-gradient(circle at center, #5db9ff55, transparent 70%);
            animation: pulse 6s ease-in-out infinite alternate;
            z-index: 0;
        }

        @keyframes pulse {
            0% {
                transform: scale(1);
                opacity: 0.4;
            }
            100% {
                transform: scale(1.2);
                opacity: 0.15;
            }
        }
    </style>
</head>
<body>

<div class="container" role="main">
    <div class="icon"><i class="fas fa-book-open"></i></div>
    <h1>Bem-vindo a Biblioteca Virtual</h1>
    <p>Gerencie sua ordem de livros de forma simples, elegante e moderna.</p>
    <a href="livros" class="btn-entrar" role="button" aria-label="Entrar na Biblioteca">
        <i class="fas fa-sign-in-alt"></i> Entrar na Biblioteca
    </a>
</div>

</body>
</html>
