# Arquitectura de Microservicios - Sistema de Mascotas

## Descripción General

Este proyecto ha sido refactorizado para seguir una arquitectura de microservicios, donde cada servicio tiene su propia responsabilidad y puede ser desplegado independientemente.

## Estructura de Microservicios

### 1. Pet Service (Puerto 8081)
**Responsabilidad**: Gestión de perros/mascotas
- **Endpoints**:
  - `GET /api/perros` - Listar perros del usuario
  - `POST /api/perros` - Registrar nuevo perro
  - `PUT /api/perros/{id}` - Actualizar perro
  - `DELETE /api/perros/{id}` - Eliminar perro

**Componentes**:
- `PerroController` - Controlador REST
- `PerroService` - Lógica de negocio
- `PerroRepository` - Acceso a datos
- `Perro` - Modelo de datos

### 2. Auth Service (Puerto 8082)
**Responsabilidad**: Autenticación y gestión de usuarios
- **Endpoints**:
  - `POST /api/auth/login` - Iniciar sesión
  - `POST /api/auth/register` - Registrar usuario

**Componentes**:
- `AuthController` - Controlador REST
- `UsuarioService` - Lógica de negocio
- `UsuarioRepository` - Acceso a datos
- `Usuario` - Modelo de datos

### 3. Dashboard Service (Puerto 8083)
**Responsabilidad**: Estadísticas y dashboard
- **Endpoints**:
  - `GET /api/dashboard/estadisticas` - Obtener estadísticas generales
  - `GET /api/dashboard/perros-duenio?duenio={nombre}` - Perros por dueño
  - `GET /api/dashboard/perros-duenio/{duenio}` - Perros por dueño (path)

**Componentes**:
- `DashboardController` - Controlador REST
- `DashboardService` - Lógica de estadísticas
- `EstadisticasDashboard` - DTO para estadísticas

### 4. API Gateway (Puerto 8080)
**Responsabilidad**: Punto de entrada único para todos los servicios
- Enruta las peticiones a los microservicios correspondientes
- Maneja autenticación y autorización
- Proporciona un punto de entrada unificado

### 5. Discovery Service (Puerto 8761)
**Responsabilidad**: Registro y descubrimiento de servicios
- Eureka Server para el registro de microservicios
- Permite que los servicios se encuentren entre sí

## Ventajas de la Nueva Arquitectura

### 1. **Separación de Responsabilidades**
- Cada servicio tiene una responsabilidad específica
- Facilita el mantenimiento y desarrollo
- Permite escalar servicios independientemente

### 2. **Independencia de Despliegue**
- Cada servicio puede ser desplegado independientemente
- No hay dependencias entre servicios (excepto por comunicación HTTP)
- Facilita el desarrollo en paralelo

### 3. **Tecnologías Específicas**
- Cada servicio puede usar la tecnología más apropiada
- Facilita la evolución tecnológica
- Permite optimizaciones específicas

### 4. **Escalabilidad**
- Los servicios pueden escalar independientemente
- Mejor utilización de recursos
- Facilita el manejo de carga

## Cómo Ejecutar

### 1. Iniciar Discovery Service
```bash
cd microservices/discovery-service
./mvnw spring-boot:run
```

### 2. Iniciar Auth Service
```bash
cd microservices/auth-service
./mvnw spring-boot:run
```

### 3. Iniciar Pet Service
```bash
cd microservices/pet-service
./mvnw spring-boot:run
```

### 4. Iniciar Dashboard Service
```bash
cd microservices/dashboard-service
./mvnw spring-boot:run
```

### 5. Iniciar API Gateway
```bash
cd microservices/api-gateway
./mvnw spring-boot:run
```

### 6. Usar Docker Compose (Recomendado)
```bash
cd microservices
docker-compose up -d
```

## Comunicación Entre Servicios

### Dashboard Service → Pet Service
El dashboard service consume datos del pet service usando WebClient:
```java
webClientBuilder.build()
    .get()
    .uri("http://pet-service/api/perros")
    .retrieve()
    .bodyToFlux(Perro.class)
```

### API Gateway → Todos los Servicios
El API Gateway enruta las peticiones según el path:
- `/api/auth/**` → Auth Service
- `/api/perros/**` → Pet Service  
- `/api/dashboard/**` → Dashboard Service

## Base de Datos

Todos los servicios comparten la misma base de datos PostgreSQL:
- **Base de datos**: `mascotas_db`
- **Tablas**: `perros`, `usuarios`
- **Configuración**: PostgreSQL en puerto 5432

## Autenticación

El sistema usa tokens JWT simulados:
- Formato: `Bearer fake-jwt-token-{userId}`
- Ejemplo: `Bearer fake-jwt-token-1`
- El userId se extrae del token para filtrar datos por usuario

## Migración desde la Arquitectura Monolítica

### Cambios Principales:

1. **Eliminación del directorio `src` principal**
   - Todo el código se movió a los microservicios correspondientes
   - Cada servicio tiene su propia estructura de paquetes

2. **Separación de responsabilidades**
   - Pet Service: Solo maneja perros
   - Auth Service: Solo maneja usuarios y autenticación
   - Dashboard Service: Solo maneja estadísticas

3. **Comunicación HTTP**
   - Los servicios se comunican vía HTTP REST
   - WebClient para llamadas asíncronas
   - Discovery Service para encontrar servicios

4. **Configuración independiente**
   - Cada servicio tiene su propio `application.yml`
   - Puertos diferentes para evitar conflictos
   - Configuración específica por servicio

## Próximos Pasos

1. **Implementar autenticación real con JWT**
2. **Agregar circuit breakers para resiliencia**
3. **Implementar logging centralizado**
4. **Agregar métricas y monitoreo**
5. **Implementar tests de integración**
6. **Configurar CI/CD por servicio**