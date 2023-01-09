# Incident API

Api para cadastro de incidentes, com ela pode-se cadastrar, revisar, excluir e auditar incidentes.



### Inicialização e execução:

Instruções e tecnologias necessárias para execução:

- JDK 17;
- Maven
- IDE (Opicional)
- Docker (indico docker desktop utilizando o SO windows);

O Docker será responsável por baixar uma imagem do mysql ou do SGBD de sua preferência. 

Abaixo o arquivo docker-compose.yaml onde temos a configuração genérica para rodar a aplicação. Caso queira escalar essa aplicação para um ambiente de produção é recomendável ajustar as mesmas para garantir a segurança.

```yaml
version: '3.1'
services:
  db:
    image: mysql
    container_name: mysql
    environment:
      MYSQL_ROOT_PASSWORD: root
    ports:
      - "3306:3306"
    volumes:
      - dev_data:/var/lib/mysql

volumes:
  dev_data:
```



Com o docker instalado em sua maquina você vai precisar rodar na pasta raiz do projeto os seguintes comandos:

- Para baixar a imagem do SGBD e inicializar o container:

  ```shell
  docker-compose up 
  ```

- Caso queira verificar se o container foi inicializado:

  ```shell
  docker-compose ps
  ```

  

Após fazer o clone do projeto e iniciar o container Docker chegou a hora de rodar aplicação, podemos utilizar de uma ide para isto ou rodando o comando:

```shell
mvn spring-boot:run
```



Na API contamos com o ORM(Object Relational Mapping) Hibernate que está responsável por criar a entidade e as constraits do banco de dados, mas caso queira uma melhor configuração da sua tabela você pode usar  os comandos abaixo para gerar seu banco e a respectiva tabela.

```sql
CREATE DATABASE incident_db

USE incident_db

CREATE TABLE `incident` (
  `id_incident` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(300) NOT NULL,
  `status` varchar(10) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `closed_at` date DEFAULT NULL,
  PRIMARY KEY (`id_incident`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```



Todas as configurações foram feitas, a aplicação esta rodando logo você pode acompanhar a documentação dos endpoints [clicando aqui]( http://localhost:8080/swagger-ui/index.html#)



## Observações:



