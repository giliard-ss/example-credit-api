# Sistema de Consulta de Créditos 
Sistema de exemplo que disponiliza uma interface web para consulta de creditos, envolvendo consumo de API RestFul, configuração com docker, e mensageria.

Todo ecossistema é carregado pelo docker.

A cada consulta do usuário na página, uma notificação é enviada para serviço de mensageria (Kafka).

## Tecnologias 
* Java 17
* Spring Boot 3.4.5 
* Angular 19
* Kafka
* Docker
* Postgres

## Instalação
 Ao clonar o projeto para sua máquina local, entre na raiz do projeto e execute os comandos

 > docker compose up --build

## Interface do sistema
Consulta de créditos
> http://localhost:4200

## Interface para acesso ao Kafka
Consulta de notificaçãoes emitidas pelo sistema. 
* Tópico "consultas"

> http://localhost:9021

