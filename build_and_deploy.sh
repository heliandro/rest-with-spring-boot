#!/bin/bash

# Recebe o nome da função como argumento de linha de comando
action=$1

# Recebe o nome do módulo (backend ou frontend) como argumento de linha de comando
module=$2

# Recebe o ambiente (dev, uat, prod) como argumento de linha de comando
environment=$3

readonly BACKEND_IMG_NAME="heliandro/calculator-api-spring-boot"
readonly FRONTEND_IMG_NAME="heliandro/calculator-angular"

declare -A module_action_functions
module_action_functions["backend install"]="install_backend_application"
module_action_functions["frontend install"]="install_frontend_application"
module_action_functions["backend run"]="run_backend_application"
module_action_functions["frontend run"]="run_frontend_application"
module_action_functions["backend test"]="test_backend_application"
module_action_functions["frontend test"]="test_frontend_application"
module_action_functions["backend build"]="build_backend_application"
module_action_functions["frontend build"]="build_frontend_application"
module_action_functions["backend docker-build"]="docker_build_backend"
module_action_functions["frontend docker-build"]="docker_build_frontend"
module_action_functions["backend docker-run"]="docker_run_backend"
module_action_functions["frontend docker-run"]="docker_run_frontend"

# Obtém a função correspondente com base no módulo e ação
get_function_for_module_action() {
    local module=$1
    local action=$2

    local function="${module_action_functions["$module $action"]}"

    if [ -n "$function" ]; then
        echo "$function"
    else
        echo "Função desconhecida para módulo '$module' e ação '$action'"
        exit 1
    fi
}

# Função para extrair a versão do pom.xml
get_version_from_backend() {
    local version=$(grep -oP '<version>\K[^<]+' pom.xml | sed -n 2p)
    echo $version
}

# Função para executar a aplicação backend
install_backend_application() {
    mvn install
}

# Função para executar a aplicação frontend
install_frontend_application() {
    npm install
}

# Função para executar a aplicação backend
run_backend_application() {
    mvn spring-boot:run -Dspring-boot.run.profiles=$environment
}

# Função para executar a aplicação frontend
run_frontend_application() {
    npm run start
}

# Função para rodar os testes frontend
test_backend_application() {
    mvn clean test jacoco:report
}

# Função para rodar os testes no frontend
test_frontend_application() {
    npm run test
}

# Função para buildar a aplicação backend
build_backend_application() {
    mvn clean package -P$environment -DskipTests
}

# Função para buildar a aplicação frontend
build_frontend_application() {
    npm run build
}

# Função para construir a imagem Docker
docker_build_backend() {
    local environment=$1
    local version=$(get_version_from_backend)
    docker build --build-arg PROFILE=$environment --build-arg VERSION=$version -t $BACKEND_IMG_NAME-$environment:$version .
}

# Função para construir a imagem Docker
docker_build_frontend() {
    local environment=$1
    local version=latest
    docker build --build-arg PROFILE=$environment --build-arg VERSION=$version -t $FRONTEND_IMG_NAME-$environment:$version .
}

# Função para rodar a imagem Docker do Backend
docker_run_backend() {
    local environment=$1
    local version=$(get_version_from_backend)
    docker run -d -p 80:8080 --name calculator-api $BACKEND_IMG_NAME-$environment:$version
}

# Função para rodar a imagem Docker do Backend
docker_run_frontend() {
    local environment=$1
    local version=latest
    docker run -d -p 4200:80 --name calculator-frontend $FRONTEND_IMG_NAME-$environment:$version
}

# Função para publicar no Docker Hub
publish_backend_to_docker_hub() {
    local environment=$1
    local version=$(get_version_from_backend)
    docker push $BACKEND_IMG_NAME-$environment:$version
}



# Chama a função especificada
case "$action" in
    "run"|"test"|"install"|"build")
        function_to_run=$(get_function_for_module_action $module $action)
        cd $module
        $function_to_run $environment
        cd ..
        ;;
    "docker-build"|"docker-run")
        function_to_run=$(get_function_for_module_action $module $action)
        cd $module
        $function_to_run $environment
        cd ..
        ;;
    "docker-publish")
        publish_to_docker_hub $environment $pom_version
        ;;
    "publish")
        if run_tests \
            && package_application $environment \
            && build_docker_image $environment $pom_version \
            && publish_to_docker_hub $environment $pom_version; then
            echo "Publicação concluída com sucesso."
        else
            echo "Erro ao publicar a aplicação."
        fi
        ;;
    *)
        echo "Ação desconhecida: $action"
        exit 1
        ;;
esac

echo "Processo concluído."
