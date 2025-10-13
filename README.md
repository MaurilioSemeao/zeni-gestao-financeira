Olá\! Peço desculpas pela formatação com a notação \`\`. Foi um erro na minha geração anterior.

Você está certíssimo em querer incluir todos os diagramas. Eles são fundamentais para documentar a concepção do projeto.

Abaixo está a versão final e corrigida do seu `README.md`. Adicionei uma nova seção "4. Modelagem de Negócio e Processos" com os campos corretos para você anexar as imagens do Lean Canvas, do Diagrama de Casos de Uso e do BPMN. Basta substituir os caminhos de exemplo pelo caminho real onde as imagens estão no seu projeto.

-----

# Zeni - Sistema de Gestão Financeira Pessoal

## 1\. Introdução

O projeto Zeni consiste no desenvolvimento de um aplicativo web de gestão financeira pessoal, projetado para oferecer uma ferramenta simples, intuitiva e eficiente. O objetivo principal é permitir que o usuário registre, organize e visualize suas finanças diárias, incluindo receitas, despesas e o gerenciamento de múltiplos cartões de crédito. O sistema busca apoiar o processo de tomada de decisão financeira do usuário, contribuindo para um maior controle e estabilidade econômica.

### 1.1. Justificativa

Grande parte da população enfrenta dificuldades na administração de suas finanças pessoais, seja pela falta de hábitos de organização ou pela limitação de ferramentas acessíveis que conciliem facilidade de uso com funcionalidades objetivas. Muitos aplicativos existentes são complexos ou não focam na gestão integrada de cartões de crédito. O Zeni se justifica pela necessidade de uma solução que una simplicidade, design limpo e praticidade, atendendo ao público que deseja melhorar seu planejamento financeiro sem enfrentar barreiras de usabilidade.

## 2\. Autores

  * Douglas Luiz Pereira
  * Ketllen Cristiny Almeida do Nascimento
  * Maurilio Santos Semeão

## 3\. Escopo do Projeto

O desenvolvimento do projeto foi dividido em duas fases: um Produto Mínimo Viável (MVP) e evoluções futuras.

### 3.1. Escopo do MVP (Produto Mínimo Viável)

O MVP contempla as funcionalidades essenciais para que o sistema seja funcional e entregue valor ao usuário final.

  * **Registro de Receitas e Despesas:** Permitir o cadastro de transações com categorias personalizáveis.
  * **Gerenciamento de Cartões de Crédito:** Cadastro e gestão de múltiplos cartões de crédito.
  * **Associação de Despesas e Faturas:** Possibilidade de associar despesas aos cartões e visualizar faturas consolidadas.
  * **Limites de Gastos por Categoria:** Definição de limites de gastos para categorias específicas, com um sistema de alertas automáticos.
  * **Dashboard Interativo:** Apresentação de um painel com gráficos e resumos visuais para análise da saúde financeira.
  * **Exportação de Relatórios:** Geração de relatórios financeiros detalhados em formato Excel.

### 3.2. Escopo Futuro (Pós-MVP)

As funcionalidades abaixo foram planejadas para evoluções futuras do projeto:

  * Integração bancária para importação automática de transações.
  * Definição e acompanhamento de metas de poupança.
  * Aplicativo mobile para Android e iOS.
  * Funcionalidades de planejamento familiar compartilhado.
  * Notificações push e alertas mais personalizados.

## 4\. Modelagem de Negócio e Processos

Para a concepção do projeto, foram utilizados diagramas que ajudam a visualizar o modelo de negócio, os atores envolvidos e os fluxos de processo do sistema.

### 4.1. Lean Canvas

O Lean Canvas abaixo resume o modelo de negócio do Zeni, identificando a proposta de valor, segmentos de clientes, canais, fontes de receita e outros blocos estratégicos.

![Lean Canvas do projeto Zeni](documentacaoDoProjeto\imgUML\canvas.jpg)

### 4.2. Diagrama de Casos de Uso

O diagrama a seguir ilustra as principais interações dos atores (Usuário, Administrador) com as funcionalidades do sistema Zeni.

![Diagrama de Casos de Uso do sistema Zeni](documentacaoDoProjeto\imgUML\CasoDeUso.svg)

### 4.3. Diagrama BPMN (Processo de Negócio)

O diagrama BPMN detalha o fluxo do processo principal da aplicação financeira, desde o cadastro do usuário até o registro de transações e a visualização de relatórios.

![Diagrama BPMN do processo da aplicação financeira](documentacaoDoProjeto\imgUML\bpmnPorcessoAplicacaoFinanceira.svg)

## 5\. Arquitetura e Modelo de Dados

A arquitetura do sistema foi projetada para ser modular e escalável, separando as responsabilidades em diferentes entidades que se relacionam para cumprir os requisitos funcionais.

### 5.1. Diagrama de Entidade-Relacionamento (DER)

O diagrama abaixo ilustra a estrutura do banco de dados, com as principais entidades e seus relacionamentos.

![Diagrama de Entidade-Relacionamento do sistema Zeni](documentacaoDoProjeto\imgUML\DER.svg)

### 5.2. Diagrama de Classes

O Diagrama de Classes detalha não apenas a estrutura de dados, mas também os comportamentos (métodos) de cada objeto do sistema, servindo como um guia para a implementação do código-fonte.

![Diagrama de Classes do sistema Zeni](documentacaoDoProjeto\imgUML\DiagramaDeClasse.svg)

## 6\. Backlog do Produto (MVP)

O backlog a seguir detalha as funcionalidades do MVP, organizadas em Épicos e Histórias de Usuário, que guiarão o desenvolvimento.

### Épico 1: Gestão de Acesso e Perfil do Usuário

  * **HU-01:** Como um novo visitante, eu quero poder me cadastrar no sistema fornecendo meu nome, e-mail и senha, para que eu possa começar a usar as funcionalidades de gestão financeira.
  * **HU-02:** Como um usuário cadastrado, eu quero poder me autenticar no sistema usando meu e-mail и senha, para acessar minhas informações financeiras com segurança.

### Épico 2: Gerenciamento de Transações Financeiras

  * **HU-03:** Como um usuário autenticado, eu quero poder registrar uma nova transação (receita ou despesa), informando descrição, valor, data и associando a uma categoria, para manter meu controle financeiro atualizado.
  * **HU-04:** Como um usuário autenticado, eu quero poder criar, visualizar и editar minhas categorias personalizadas, para organizar minhas transações de acordo com meus hábitos de consumo.

### Épico 3: Gestão de Cartões de Crédito e Faturas

  * **HU-05:** Como um usuário autenticado, eu quero poder cadastrar meus cartões de crédito, informando um apelido, bandeira и dia de vencimento, para centralizar a gestão deles na plataforma.
  * **HU-06:** Como um usuário autenticado, ao registrar uma despesa, eu quero poder associá-la a um dos meus cartões de crédito cadastrados, para que ela seja incluída na fatura correspondente.
  * **HU-07:** Como um usuário autenticado, eu quero poder visualizar a fatura consolidada de cada um dos meus cartões, com a lista de todas as despesas associadas и o valor total, para entender meus gastos do mês.

### Épico 4: Controle de Gastos e Análise Financeira

  * **HU-08:** Como um usuário autenticado, eu quero poder definir um limite de gasto mensal para cada uma das minhas categorias, para controlar melhor meu orçamento.
  * **HU-09:** Como um usuário autenticado, eu quero visualizar um dashboard interativo com gráficos que mostrem a distribuição das minhas despesas por categoria и a evolução das minhas receitas vs. despesas ao longo do tempo, para ter uma visão clara da minha saúde financeira.
  * **HU-10:** Como um usuário autenticado, eu quero poder exportar um relatório das minhas transações de um período selecionado para o formato Excel, para realizar análises mais aprofundadas ou para arquivamento.

### Épico 5: Funcionalidades de Administrador

  * **HU-11:** Como um Administrador, eu quero poder gerenciar parcerias и os anúncios exibidos na versão gratuita do sistema, para controlar as fontes de receita.

## 7\. Tecnologias Propostas

Para o desenvolvimento do projeto, sugere-se a utilização de um ecossistema de tecnologias robusto e amplamente utilizado no mercado para aplicações web, como:

  * **Backend:** Java com Framework Spring (Spring Boot, Spring Data JPA, Spring Security).
  * **Frontend:** HTML5, CSS3 com um framework como Bootstrap ou Tailwind CSS, e JavaScript com um framework como React ou Angular.
  * **Banco de Dados:** PostgreSQL ou MySQL.
  * **Servidor de Aplicação:** Servidor embarcado (Tomcat, via Spring Boot).
  * **Ferramentas de Build:** Maven ou Gradle.
  * **Controle de Versão:** Git.

## 8\. Instruções para Instalação e Execução

Para executar o projeto em um ambiente de desenvolvimento local, siga os passos abaixo:

1.  **Pré-requisitos:**

      * Java JDK (versão 17 ou superior)
      * Maven ou Gradle
      * Git
      * Uma instância de um banco de dados relacional (PostgreSQL/MySQL) rodando localmente.

2.  **Clone o Repositório:**

    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd <NOME_DO_PROJETO>
    ```

3.  **Configuração do Banco de Dados:**

      * Crie um banco de dados com o nome `zeni_db`.
      * No arquivo `src/main/resources/application.properties`, configure a URL do banco de dados, o usuário e a senha.

4.  **Build do Projeto:**

    ```bash
    # Usando Maven
    mvn clean install
    ```

5.  **Execução da Aplicação:**

    ```bash
    # Usando Maven
    mvn spring-boot:run
    ```

6.  **Acesso:**

      * Após a inicialização, a aplicação estará disponível em `http://localhost:8080`.