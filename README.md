# Projeto Magazine Senai Spring Data

Este é um projeto de exemplo que demonstra a implementação de um sistema de gerenciamento de produtos utilizando Spring Data JPA.

## Modelo de Dados

O modelo de dados consiste em uma entidade `ProdutoModel` que é mapeada para uma tabela no banco de dados. Esta entidade possui os seguintes atributos:

- `id`: Identificador único do produto.
- `nome`: Nome do produto.
- `descricao`: Descrição do produto.

## Repositório

O repositório `ProdutoRepository` é responsável por realizar operações de CRUD (Create, Read, Update, Delete) no banco de dados para a entidade `ProdutoModel`. Além disso, possui métodos personalizados para busca de produtos por nome e descrição.

## Serviço

O serviço `ProdutoService` encapsula a lógica de negócios relacionada aos produtos. Ele utiliza o `ProdutoRepository` para realizar operações no banco de dados e o `ModelMapper` para realizar mapeamento entre objetos DTO e entidades.

## Controlador

O controlador `ProdutoController` expõe endpoints REST para manipulação de produtos. Ele utiliza o serviço `ProdutoService` para realizar operações relacionadas aos produtos, como cadastro, listagem, atualização e exclusão.

## Configuração

A classe `AppConfiguration` configura o `ModelMapper` como um bean gerenciado pelo Spring.

## Dependências

- Java 17
- Spring Boot Starter Web 3.2.4
- Hibernate Validator 8.0.1.Final
- PostgreSQL Driver 42.7.3
- Lombok 1.18.32
- ModelMapper 3.2.0
- Springdoc OpenAPI Starter WebMvc UI 2.0.3
- Springdoc OpenAPI UI 1.6.15

Este projeto utiliza o Maven como gerenciador de dependências.

## Como Executar

Para executar o projeto localmente, é necessário ter o Java e o Maven instalados. Após clonar o repositório, execute o seguinte comando na raiz do projeto:

```bash
mvn spring-boot:run
```

O aplicativo será iniciado e estará acessível em `http://localhost:8080`.

## Documentação da API

A documentação da API está disponível em `http://localhost:8080/swagger-ui.html`, fornecendo detalhes sobre os endpoints e como utilizá-los.

Este projeto foi desenvolvido como parte do curso de DESENVOLVEDOR WEB - BACK-END - JAVA oferecido pelo Senai do projeto Entra21.