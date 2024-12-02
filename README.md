# Servicio SOAP de Pokemon

Servicio SOAP para gestionar información de Pokemon usando Spring Boot, implementando principios de arquitectura limpia y siguiendo las mejores prácticas.

## Requisitos Previos

- Java JDK 17
- Gradle
- Docker & Docker Compose
- Git

## Stack Tecnológico

- Spring Boot 3.4.0
- Spring WS (SOAP)
- Base de datos H2
- JUnit & Cucumber para pruebas
- SonarQube para calidad de código
- Docker para containerización
- Swagger para documentación de API REST

## Primeros Pasos

### Clonar el repositorio
```bash
git clone https://github.com/MarioCortesDev/soap-service.git
cd pokemon-soap-service
```

### Construir el proyecto
```bash
./gradlew clean build
```

### Ejecutar pruebas
```bash
# Pruebas unitarias
./gradlew test

# Pruebas Cucumber
./gradlew cucumber
```

### Análisis de Calidad de Código
```bash
# Iniciar contenedor de SonarQube
docker-compose up -d sonarqube

# Esperar a que SonarQube inicie (usualmente toma 30-60 segundos)
# Luego ejecutar el análisis
./gradlew sonar
```

Accede al dashboard de SonarQube en http://localhost:9000

### Ejecutar la Aplicación

Usando Docker:
```bash
# Construir e iniciar todos los servicios
docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener servicios
docker-compose down
```

Usando Gradle:
```bash
./gradlew bootRun
```

El servicio estará disponible en:
- Endpoint SOAP: http://localhost:8080/ws
- Consola H2: http://localhost:8080/h2-console
- Swagger UI: http://localhost:8080/swagger-ui.html

## Estrategia de Ramas

Seguimos un flujo de trabajo GitFlow modificado:

### Ramas Principales
- `main`: Código listo para producción
- `develop`: Rama de integración para características

### Ramas de Soporte
- `feature/*`: Nuevas características (ramificadas desde `develop`)
- `release/*`: Preparación de release
- `hotfix/*`: Correcciones de producción (ramificadas desde `main`)

### Convención de Nombres de Ramas
- Características: `feature/breve-descripcion`
- Releases: `release/numero-version`
- Hotfixes: `hotfix/descripcion-problema`

### Flujo de Trabajo
1. Crear rama de característica desde develop
   ```bash
   git checkout develop
   git checkout -b feature/nueva-caracteristica
   ```

2. Trabajar en la característica y hacer commits
   ```bash
   git add .
   git commit -m "feat: mensaje descriptivo"
   ```

3. Subir rama de característica y crear PR
   ```bash
   git push origin feature/nueva-caracteristica
   ```

4. Después de revisión y aprobación, fusionar con develop
   ```bash
   git checkout develop
   git merge feature/nueva-caracteristica
   ```

## Convención de Commits
Seguimos [Conventional Commits](https://www.conventionalcommits.org/):

- `feat`: Nueva característica
- `fix`: Corrección de error
- `refactor`: Refactorización de código
- `docs`: Documentación
- `test`: Adición/modificación de pruebas
- `chore`: Tareas de mantenimiento

Ejemplo:
```bash
git commit -m "feat: agregar búsqueda de pokemon por tipo"
```

## Documentación de API
- SOAP WSDL: http://localhost:8080/ws/pokemon.wsdl
- Swagger UI: http://localhost:8080/swagger-ui.html

## Pruebas de Endpoints

### Pruebas SOAP
El servicio SOAP está disponible en `/ws`. Para probar los endpoints SOAP:

1. Usando Postman:
   ```
   URL: http://localhost:8080/ws
   Método: POST
   Headers:
   - Content-Type: text/xml
   - SOAPAction: ""
   
   Body (raw, XML):
   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pok="http://pokemon.com/soap">
       <soapenv:Header/>
       <soapenv:Body>
           <pok:getPokemonNameRequest>
               <pok:name>pikachu</pok:name>
           </pok:getPokemonNameRequest>
       </soapenv:Body>
   </soapenv:Envelope>
   ```

2. WSDL Disponible en:
   ```
   http://localhost:8080/ws/pokemon.wsdl
   ```

### Documentación API REST
La documentación de la API REST está disponible a través de Swagger UI:

1. Acceder a Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

2. Endpoints disponibles:
   - GET /api/docs/pokemon/name
   - [Listar aquí otros endpoints REST]

### Resultados de Pruebas

Las pruebas fueron ejecutadas y verificadas usando:
- Postman para endpoints SOAP
- Swagger UI para documentación y pruebas REST
- Tests automatizados (unitarios y de integración)

Cobertura de pruebas y calidad de código disponible en SonarQube:
```
http://localhost:9000/dashboard?id=pokemon-soap-service
```