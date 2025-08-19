# School Microservice

## Description
Ce projet est un microservice Spring Boot pour la gestion des **Classes** et des **Sectors** dans un contexte scolaire.  
Il utilise Spring Data JPA, MySQL, MapStruct pour le mapping DTO <-> Entity, et SpringDoc OpenAPI pour la documentation des endpoints.

---

## Architecture

- **Spring Boot 3.5.4**
- **Java 21**
- **MySQL** comme base de données
- **MapStruct** pour les mappers DTO <-> Entity
- **Lombok** pour générer getters, setters, constructeurs
- **Spring Data JPA** pour la persistance
- **Spring Cache** pour le caching des données
- **SpringDoc OpenAPI** pour générer automatiquement la documentation Swagger
- Microservice expose ses endpoints via **REST API**

---

## Dépendances Maven

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.30</version>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- SpringDoc OpenAPI UI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>

    <!-- Jakarta Validation API -->
    <dependency>
        <groupId>jakarta.validation</groupId>
        <artifactId>jakarta.validation-api</artifactId>
        <version>3.0.2</version>
    </dependency>

    <!-- MapStruct -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.5.5.Final</version>
    </dependency>

    <!-- Spring Boot Starter Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## Structure du projet
```school/
 ├── controller/        # API REST (expose les endpoints REST)
 │    ├── ClassesController.java
 │    └── SectorsController.java
 ├── dto/               # DTO (Data Transfer Objects)
 │    ├── Classes.java
 │    └── Sectors.java
 ├── entities/          # Entités JPA (base de données)
 │    ├── ClassesEntity.java
 │    └── SectorsEntity.java
 ├── dao/               # DAO/Repository JPA (accès BDD)
 │    ├── IClassesRepository.java
 │    └── ISectorsRepository.java
 ├── mapping/           # MapStruct (mapping DTO ↔ Entity)
 │    ├── ClassesMapper.java
 │    └── SectorsMapper.java
 ├── services/          # Logique métier
 │    ├── ClassesService.java
 │    └── SectorsService.java
 ├── exception/         # Gestion centralisée des erreurs
 │    └── RequestException.java
 ├── resources/
 │    ├── application.properties  # config DB, port, etc.
 │    ├── messages.properties     # messages d'erreur (localisation)
 └── SchoolApplication.java       # Classe principale Spring Boot

```
## Notes

Les classes DTO sont mappées vers les entités JPA via MapStruct.

Les validations de request body utilisent Jakarta Validation (@Valid).

Le caching est géré via Spring Cache (@Cacheable, @CacheEvict).

Les exceptions personnalisées sont gérées via APIException et RequestException.

## 📡 API Endpoints (CRUD Classes)


