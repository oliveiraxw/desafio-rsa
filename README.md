# 1 - Como compilar e executar a aplicação
<b>Para compilar e executar:</b> na raiz do projeto, execute o comando: mvnw spring-boot:run ou mvn spring-boot:run

# 2 - Como executar os testes unitários
Na raiz do projeto, execute o comando: mvnw test ou mvn test

# 3 - Informações adicionais

## Swagger OpenAPI 3
http://localhost:8080/desafio/swagger-ui.html

## Utilização dos endpoints
### Utilize o endpoint "ClientSide - Gerar Par de Chaves RSA", onde é retornada uma chave privada e uma chave pública.
#### A Chave Privada teoricamente fica armazenada no cliente, onde é responsável pela leitura das informações.
#### A Chave Pública será enviada ao servidor, onde é responsável pela criptografia das informações.

<i>
    Dessa forma, o servidor não conhece a chave privada do usuário, então não consegue descriptografar as informações. Logo, a descriptografia fica em responsabilidade do cliente que possui a chave privada.
    </br>
    A chave pública será vinculada a um usuário no momento do cadastro.
</i>

### Realize o cadastro do usuário, informando o email e o nome, e a chave pública que será vinculada a este usuário.

### Utilize o endpoint "ClientSide - Descriptografar", onde este receberá uma chave privada e uma informação(nome ou email, por exemplo) que será descriptografada.

<i>Obs: quando necessário, a chave(privada ou pública) é informada no cabeçalho da requisição(ver a coleção postman_collection.json).</i>