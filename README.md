# rest-with-spring-boot

## Configurando

Para instalar as dependencias, execute o comando abaixo:

```sh
mvn clean package
```

## Rodando a aplicacao

Para executar a aplicacao, rode o comando abaixo:

```sh
mvn spring-boot:run -Dspring.profiles.active=dev
```

## Publicando a aplicacao

Setando as variaveis de ambiente p iniciar o processo:

```sh
export ENVIRONMENT=dev && export VERSION="1.0.0" 
```

Empacotando a aplicacao (jar):

```sh
mvn clean package -P${ENVIRONMENT}
```

Gerando a imagem docker:

```sh
docker build --build-arg PROFILE=${ENVIRONMENT} --build-arg VERSION=${VERSION} -t heliandro/rest-api-spring-boot-${ENVIRONMENT}:${VERSION} .
```

Testando a imagem:

```sh
docker run -d -p 8080:8080 --name rest-api-spring-boot heliandro/rest-api-spring-boot-${ENVIRONMENT}:${VERSION}
```
Publicando no Docker Hub

```sh
docker push heliandro/rest-api-spring-boot-${ENVIRONMENT}:${VERSION}
```


