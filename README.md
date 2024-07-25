# swing-java-sql
Projeto desenvolvido em Java + Swing, utilizando SQL. Desenvolvido com o propósito de estudos pessoais.

> [!NOTE]
> Para executar o projeto, primeiro execute o arquivo ```javaSwingSQL.sql``` em um Banco de Dados SQL, como o Workbench. Após a execução, acesse o arquivo ```RunApp.java``` em ```java-ui-teste/JDBC/src``` e rode o código.
> Tenha certeza de adicionar o arquivo JAR ```mysql-connector-j-8.3.0``` no projeto para atribuir o Driver e efetuar a conexão ao Banco de Dados.

>[!WARNING]
> Certifique-se de ter o XAMPP ou Wampserver instalados para conectar ao banco de dados e altere a conexão ao seu Banco de Dados no arquivo ```MyApp.java```, nas variáveis ```url, user, password```.

## Adicionando o Driver com IntelliJ
Antes de tudo, extraia o arquivo ZIP baixado. Após isso, siga o passo a passo para inserir o Driver utilizando o IntelliJ.
/
Botão direito em ```java-ui-teste``` > ```Open Module Settings (F4)``` > Clique em JDBC > Clique no "+" > ```JARs or Directories``` > Selecione ```Downloads/mysql-connector-j-8.3.0/mysql-conector-j-8.3.0.jar``` > OK.
