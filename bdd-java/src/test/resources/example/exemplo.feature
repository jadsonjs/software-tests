# language: pt

  Funcionalidade: Login
    Como um usuário do sistema
    Quero conseguir me logar
    Para que eu tenha acesso às funcionalidades do sistema

    Cenario: Login Válido
      Dado Que eu tenho um conta ativa no sistema
      E eu acesso a página do login
      E eu preencho os campos de login e senha com informações válidas
      Quando eu clico no botão de logar
      Entao O sistema deve me redirecionar para página inicial

    Cenario: Login Inválido
      Dado Que eu tenho um conta ativa no sistema
      E eu acesso a página do login
      E eu preencho os campos de login e senha com informações inválidas
      Quando eu clico no botão de logar
      Entao O sistema deve me mostrar uma mensagem de credenciais inválidas