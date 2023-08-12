# Configuração com Nginx: Proxy Reverso e Balanceamento de Carga

Neste repositório, você encontrará uma configuração que utiliza o Nginx como um servidor proxy reverso e balanceador de carga. O projeto inclui um microserviço de calculadora desenvolvido em Spring Boot, um aplicativo frontend em Angular 15+ e o uso do Docker Compose para facilitar a execução desses componentes juntamente com o Nginx.

## Estrutura do Projeto

- `backend/`: Código-fonte do microserviço de calculadora desenvolvido em Spring Boot.
- `frontend/`: Código-fonte do aplicativo Angular que se conecta ao backend.
- `nginx/`: Arquivos de configuração do Nginx para roteamento e balanceamento de carga.
- `docker-compose.yml`: Orquestração dos serviços usando Docker Compose.

## Executando o Projeto

Siga estas etapas para executar o projeto:

1. Certifique-se de ter o Docker e o Docker Compose instalados.

2. Faca o build do projeto backend

```bash
./build_and_deploy.sh build backend dev
```

3. Execute o Docker Compose para iniciar os serviços:

```bash
docker-compose up -d
```

Isso inicializará os contêineres do backend, frontend e Nginx.

4. Abra seu navegador e acesse:

- Aplicativo Angular: http://frontend.127.0.0.1.nip.io
- Microserviço de Calculadora: http://calculator.127.0.0.1.nip.io/api

## Configuração do Nginx

O Nginx atua como proxy reverso e balanceador de carga para os microserviços de calculadora. A configuração está localizada em nginx/ neste repositório. As rotas são configuradas da seguinte forma:

- `/` mapeia para o aplicativo Angular.
- `/api` mapeia para os microserviços de calculadora.

## Testando a aplicação

1. Execute o comando abaixo para testar o backend

```bash
./build_and_deploy.sh test backend dev
```

2. Execute o comando abaixo para testar o frontend

```bash
./build_and_deploy.sh test frontend dev
```