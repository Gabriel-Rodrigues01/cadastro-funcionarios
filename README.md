📁 Sistema de Cadastro de Funcionários (JavaFX + CSV)
Sistema de gestão de cadastro de funcionários desenvolvido em Java, utilizando a interface gráfica JavaFX e persistência de dados em formato CSV. O projeto aplica conceitos de Programação Orientada a Objetos (POO), arquitetura em camadas (Model, Service, Repository) e processamento de dados com a Stream API do Java.

✨ Funcionalidades Implementadas
O sistema permite gerenciar o ciclo de vida completo do cadastro de funcionários com as seguintes operações:

CRUD Básico & Persistência

✅ Cadastrar: Adiciona um novo funcionário, incluindo dados pessoais, profissionais e endereço.

✅ Persistência em CSV: Todos os dados são salvos automaticamente no arquivo funcionarios.csv, garantindo que as informações não sejam perdidas ao fechar o sistema.

✅ Carregamento Automático: Os dados são carregados do arquivo funcionarios.csv ao iniciar a aplicação.

Validação de Dados
O sistema implementa validações rigorosas para garantir a integridade dos dados, incluindo:

Matrícula: Formato definido (6 dígitos numéricos).

Nome: Não vazio e tamanho mínimo.

Data de Nascimento: Idade mínima de 18 anos.

Salário: Valor positivo.

CEP e CPF: Validação básica de formato.

Relatórios e Processamento de Dados (Stream API)
O sistema utiliza a API Stream do Java 8+ para processamento eficiente de coleções, implementando:

✅ Média Salarial por Cargo: Calcula a média salarial agrupada por cada cargo na empresa.

✅ Agrupamento por Cidade: Lista de funcionários agrupada pelo nome da cidade.

⚙️ Requisitos e Tecnologias
Para rodar este projeto, você precisará ter o seguinte instalado:

Java Development Kit (JDK): Versão 17 ou superior (o projeto foi testado com JDK 22).
Apache Maven: Para gerenciamento de dependências e construção do projeto.

IntelliJ IDEA ou outra IDE com suporte a JavaFX e Maven.

Dependências Principais

<img width="701" height="182" alt="image" src="https://github.com/user-attachments/assets/2982f9eb-fd2b-4c4b-9221-87774c8455a0" />

🚀 Como Executar o Projeto
Siga estes passos para clonar o repositório e executar a aplicação:

1. Clonar o Repositório
Abra o terminal e clone o projeto:

git clone https://github.com/Gabriel-Rodrigues01/cadastro-funcionarios

cd cadastro_funcionarios

2. Abrir na IDE (IntelliJ IDEA)
Abra o IntelliJ.

Selecione File -> Open... e escolha a pasta cadastro_funcionarios.

O IntelliJ deve reconhecer o projeto Maven automaticamente. Se não, clique em "Load Maven Changes" na notificação pop-up.

3. Compilar e Executar
Este projeto utiliza o sistema de módulos (JPMS) do Java.

Reconstruir o Projeto: No menu principal, vá em Build -> Rebuild Project.

Configurar a Execução: Certifique-se de que a configuração de execução principal aponte para a classe HelloApplication no módulo org.cadastro_funcionarios.

Rodar a Aplicação: Clique no botão Run (Geralmente o ícone de ▶️ verde) no IntelliJ.

A aplicação JavaFX será iniciada, exibindo a tela de Cadastro de Funcionários.

🏗️ Estrutura do Código (Arquitetura em Camadas)
O projeto segue a arquitetura em camadas para melhor organização, coesão e baixo acoplamento:

<img width="701" height="391" alt="image" src="https://github.com/user-attachments/assets/9ecc02d0-b420-41b3-bc8b-20dc00d1c030" />



