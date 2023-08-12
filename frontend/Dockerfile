# Use uma imagem base com Node.js
FROM node:20-alpine AS build

WORKDIR /app

# Copie os arquivos de configuração e dependências
COPY package*.json ./
RUN npm install
COPY . .

ARG PROFILE=dev
RUN echo $PROFILE > profile.txt

# Construa o app Angular
RUN npm run build:${PROFILE}

# Use uma imagem mais leve para servir o app
FROM nginx:alpine

WORKDIR /app

# Copie o valor da variável PROFILE do arquivo intermediário
COPY --from=build /app/profile.txt .

# Leitura do valor da variável PROFILE do arquivo intermediário
ARG PROFILE=$(cat profile.txt)

# Remova a configuração padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copie os arquivos do build para o diretório de conteúdo do NGINX
COPY --from=build /app/dist/$PROFILE /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]