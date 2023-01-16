

  Feature: Login
    Como um usuário do sistema
    Quero conseguir me logar
    Para que eu tenha acesso às funcionalidades do sistema

    Scenario: Login Válido
      Given Que eu tenho um conta ativa no sistema
      And eu acesso a página do login
      And eu preencho os campos de login e senha com informações válidas
      When eu clico no botão de logar
      Then O sistema deve me redirecionar para página inicial

    Scenario: Login Inválido
      Given Que eu tenho um conta ativa no sistema
      And eu acesso a página do login
      And eu preencho os campos de login e senha com informações inválidas
      When eu clico no botão de logar
      Then O sistema deve me mostrar uma mensagem de credenciais inválidas

