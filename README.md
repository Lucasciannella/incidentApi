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

![](https://media.giphy.com/media/X5teVOTidUW1xQO1kg/giphy.gif)



## Observações:

### Tecnologias utilizadas:

- Lombock: visando reduzir a quantidade de código escrito ;

- H2 database: para os testes de repositorio;

- Spring validation: para as validações de dominio;

- Spring actuator: Um plus para poder mostrar o estado da aplicação e algumas metricas;

  ```yaml
  management:
    endpoints:
      web:
        exposure:
          include: info, health, metrics
  ```

  Disponível nos endpoints:

  - server:port/actuator/info
  - server:port/actuator/health
  - server:port/actuator/metrics

  

  ### Notas:

  Primeiro quero deixar claro que foi prazeroso fazer este desafio embora não tenha feito tudo que desejava deixo aqui alguns pontos

  

- Método que retorna os ultimos 20 registros:

    ​	Eu optei por criar um método paginado, onde setei por default os parâmetros de busca do mesmo, mas, poderia ter solucionado 	de diversas maneiras, essa foi a que encontrei sendo mais plausível por que acreditei que ganharia com a paginação podendo 	ser usada não somente para esta função, dando uma certa versatilidade e resolvendo o problema da mesma maneira, exemplo: 	criar um método e passar uma Query nativa com @Query, ou utilizar do própio dialeto do Hibernate.

    

- Maior Cobertura nos testes:
  
   Poderia ter feito os testes de integração do sistema e testar as constrains de inserção do banco de dados.

- Criei um método além do requisitado:
	Não sei o que desejavam com o atributo closed at, porém tive diversas ideias, pensei até em criar um registo de backup das remoções, mas achei inviável já que estava explicito que queriam uma função delete,com isso criei um método void PATCH para atualizar essa hora e o atributo novo o STATUS, que é variavel de acordo com o "momento" do incidente.
  
  
- Criei um método Trasformer para abstrair o builder da camada de serviço:
	Você avaliador, vai econtrar esse método na interface IncidentTranformer e na classe IncidentTransformerImpl, criei para deixar o código mais limpo e manutenível,após ter  notado que estava repetindo código. 
	Utilizei da sobrecarga para me ajudar, porém um dos métodos não me pareceu fazer muito sentido e eu pensarei em uma possível refatoração pra isto.
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

