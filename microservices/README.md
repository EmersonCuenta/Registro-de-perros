# Mascotas Microservices Architecture

Esta es la versión migrada de la aplicación Mascotas de arquitectura monolítica a microservicios.

## Arquitectura

### Servicios

1. **Discovery Service** (Puerto: 8761)
   - Eureka Server para registro y descubrimiento de servicios
   - Dashboard: http://localhost:8761

2. **Auth Service** (Puerto: 8081)
   - Gestión de usuarios y autenticación
   - Base de datos: `auth_db`
   - Endpoints: `/api/auth/login`, `/api/auth/register`

3. **Pet Service** (Puerto: 8082)
   - Gestión de perros/mascotas
   - Base de datos: `pet_db`
   - Endpoints: `/api/perros/*`

4. **Dashboard Service** (Puerto: 8083)
   - Estadísticas y reportes
   - Comunica con Pet Service para obtener datos
   - Endpoints: `/api/dashboard/*`

5. **API Gateway** (Puerto: 8080)
   - Punto de entrada único para todos los servicios
   - Enrutamiento y balanceo de carga
   - Endpoint principal: http://localhost:8080

### Bases de Datos

- **auth_db**: Base de datos para usuarios (MySQL en puerto 3307)
- **pet_db**: Base de datos para mascotas (MySQL en puerto 3308)

## Tecnologías Utilizadas

- **Spring Boot 3.5.4** con WebFlux (reactivo)
- **Spring Cloud** para microservicios
- **Eureka** para service discovery
- **Spring Cloud Gateway** para API Gateway
- **R2DBC** con MySQL para acceso a datos reactivo
- **Docker** y **Docker Compose** para containerización
- **Java 21**

## Instalación y Ejecución

### Prerrequisitos

- Docker y Docker Compose
- Java 21
- Maven

### Pasos para ejecutar

1. **Clonar el repositorio**
   ```bash
   git clone <repository-url>
   cd microservices
   ```

2. **Ejecutar todos los servicios**
   ```bash
   ./start.sh
   ```

3. **Verificar que los servicios estén funcionando**
   - Eureka Dashboard: http://localhost:8761
   - API Gateway: http://localhost:8080

### Scripts disponibles

- `./build.sh` - Compila todos los microservicios
- `./start.sh` - Construye y ejecuta todos los servicios
- `./stop.sh` - Detiene todos los servicios

## Endpoints Disponibles

### A través del API Gateway (http://localhost:8080)

#### Auth Service
- `POST /api/auth/login` - Iniciar sesión
- `POST /api/auth/register` - Registrar usuario

#### Pet Service
- `GET /api/perros` - Listar perros del usuario
- `POST /api/perros` - Registrar nuevo perro
- `PUT /api/perros/{id}` - Actualizar perro
- `DELETE /api/perros/{id}` - Eliminar perro

#### Dashboard Service
- `GET /api/dashboard/estadisticas` - Obtener estadísticas
- `GET /api/dashboard/perros-duenio?duenio={nombre}` - Perros por dueño
- `GET /api/dashboard/perros-duenio/{duenio}` - Perros por dueño (path)

## Ejemplos de Uso

### 1. Registrar un usuario
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"email":"usuario@test.com","password":"123456"}'
```

### 2. Iniciar sesión
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"usuario@test.com","password":"123456"}'
```

### 3. Registrar un perro
```bash
curl -X POST http://localhost:8080/api/perros \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer fake-jwt-token-1" \
  -d '{"nombre":"Rex","raza":"Pastor Alemán","tamano":"Grande","edad":3}'
```

### 4. Obtener estadísticas
```bash
curl -X GET http://localhost:8080/api/dashboard/estadisticas
```

## Estructura de Directorios

```
microservices/
├── discovery-service/     # Eureka Server
├── auth-service/         # Servicio de autenticación
├── pet-service/          # Servicio de mascotas
├── dashboard-service/     # Servicio de dashboard
├── api-gateway/          # API Gateway
├── docker-compose.yml    # Orquestación de servicios
├── build.sh             # Script de compilación
├── start.sh             # Script de inicio
├── stop.sh              # Script de parada
└── README.md            # Este archivo
```

## Monitoreo

- **Eureka Dashboard**: http://localhost:8761
  - Muestra todos los servicios registrados
  - Estado de salud de cada servicio

## Troubleshooting

### Problemas comunes

1. **Servicios no se registran en Eureka**
   - Verificar que el Discovery Service esté ejecutándose
   - Revisar logs de cada servicio

2. **Error de conexión a base de datos**
   - Verificar que los contenedores MySQL estén ejecutándose
   - Revisar variables de entorno en docker-compose.yml

3. **API Gateway no enruta correctamente**
   - Verificar configuración de rutas en application.yml
   - Revisar que todos los servicios estén registrados en Eureka

### Logs

Para ver logs de un servicio específico:
```bash
docker-compose logs <service-name>
```

Ejemplo:
```bash
docker-compose logs auth-service
```

## Migración desde Monolito

Esta arquitectura mantiene toda la funcionalidad de la aplicación original pero distribuida en microservicios:

- **Separación de responsabilidades**: Cada servicio tiene una función específica
- **Bases de datos separadas**: Cada servicio tiene su propia base de datos
- **Comunicación síncrona**: Los servicios se comunican vía REST
- **Service Discovery**: Eureka maneja el registro y descubrimiento
- **API Gateway**: Punto de entrada único con enrutamiento automático