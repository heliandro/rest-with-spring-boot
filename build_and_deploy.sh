#!/bin/bash

# Função para extrair a versão do pom.xml
get_version_from_pom() {
    local pom_file=$1
    local version=$(grep -oP '<version>\K[^<]+' $pom_file | sed -n 2p)
    echo $version
}

# Função para executar a aplicação
run_application() {
    local environment=$1
    mvn spring-boot:run -Dspring-boot.run.profiles=$environment
}

# Função para empacotar a aplicação
package_application() {
    local environment=$1
    mvn clean package -P$environment -DskipTests
}

# Função para rodar os testes
run_tests() {
    mvn clean test jacoco:report
}

# Função para construir a imagem Docker
build_docker_image() {
    local environment=$1
    local version=$2
    docker build --build-arg PROFILE=$environment --build-arg VERSION=$version -t heliandro/rest-api-spring-boot-$environment:$version .
}

# Função para rodar a imagem Docker
run_docker_image() {
    local environment=$1
    local version=$2
    docker run -d -p 8080:8080 --name rest-api-spring-boot heliandro/rest-api-spring-boot-$environment:$version
}

# Função para publicar no Docker Hub
publish_to_docker_hub() {
    local environment=$1
    local version=$2
    docker push heliandro/rest-api-spring-boot-$environment:$version
}

# Recebe o nome da função como argumento de linha de comando
action=$1

# Recebe o ambiente (dev, uat, prod) como argumento de linha de comando
environment=$2

# Obtém a versão do pom.xml
pom_version=$(get_version_from_pom pom.xml)

# Chama a função especificada
case "$action" in
    "run")
        run_application $environment
        ;;
    "install")
        package_application $environment
        ;;
    "test")
        run_tests
        ;;
    "docker-build")
        build_docker_image $environment $pom_version
        ;;
    "docker-run")
        run_docker_image $environment $pom_version
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
