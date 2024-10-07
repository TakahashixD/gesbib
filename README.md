# Índice 

* [Como rodar](#Como-rodar)
   * [BACK-END JAVA 21](#back-end-java-21)
   * [FRONT-END ANGULAR 18](#front-end-angular-18)

# Como rodar
Lembrar de alterar as configurações do Banco de dados no src/main/resources/application-dev.yml

Adicionar API KEY do Google Books no GoogleBooksService 

# BACK-END JAVA 21 

É necessário ter o java 21 e o maven instalados.

Execute os comandos na ordem abaixo:
```
mvn clean package -Pdev
```

```
java -jar target/gesbib-0.0.1-SNAPSHOT.jar
```


# FRONT-END ANGULAR 18

É necessário ter o node.js instalado nas versions ^18.19.1 || ^20.11.1 || ^22.0.0.

Execute os comandos na ordem abaixo:
```
cd gesbib-front
```

```
npm install
```

```
npm start
```

# TESTANDO API

Os testes podem ser feitos pelo swagger ou pelo FRONT-END (Recomendo utilizar o Front)

Swagger
```
http://localhost:8080/swagger-ui/index.html
```

FRONT-END
```
http://localhost:4200/
```


