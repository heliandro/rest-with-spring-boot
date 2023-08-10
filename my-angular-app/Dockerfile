# Use uma imagem base com Node.js
FROM node:20-alpine AS build

WORKDIR /app

# Copie os arquivos de configuração e dependências
COPY package*.json ./
RUN npm install
COPY . .

# Construa o app Angular
RUN npm run build

# Use uma imagem mais leve para servir o app
FROM nginx:alpine

# Remova a configuração padrão do Nginx
RUN rm -rf /usr/share/nginx/html/*

# Copie os arquivos do build para o diretório de conteúdo do NGINX
COPY --from=build /app/dist/my-angular-app /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]