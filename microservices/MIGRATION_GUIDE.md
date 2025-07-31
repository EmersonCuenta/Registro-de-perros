# Guía de Migración: Monolito a Microservicios

## Resumen de la Migración

La aplicación Mascotas ha sido migrada exitosamente de una arquitectura monolítica modular a una arquitectura de microservicios.

## Cambios Principales

### 1. Separación de Servicios

**Antes (Monolito):**
```
src/main/java/com/perro/mascotas/
├── controller/
│   ├── AuthController.java
│   ├── PerroController.java
│   └── DashboardController.java
├── service/
│   ├── UsuarioService.java
│   ├── PerroService.java
│   └── DashboardService.java
├── model/
│   ├── Usuario.java
│   └── Perro.java
└── repository/
    ├── UsuarioRepository.java
    └── PerroRepository.java
```

**Después (Microservicios):**
```
microservices/
├── auth-service/          # Gestión de usuarios
├── pet-service/           # Gestión de mascotas
├── dashboard-service/      # Estadísticas y reportes
├── discovery-service/      # Service discovery
└── api-gateway/           # Punto de entrada único
```

### 2. Bases de Datos Separadas

**Antes:**
- Una sola base de datos `perrosbd` con todas las tablas

**Después:**
- `auth_db`: Base de datos para usuarios
- `pet_db`: Base de datos para mascotas

### 3. Comunicación entre Servicios

**Antes:**
- Llamadas directas entre componentes del mismo monolitico

**Después:**
- Comunicación REST entre servicios
- Feign Client para llamadas síncronas
- Eureka para service discovery

## Detalles Técnicos de la Migración

### Auth Service
- **Responsabilidad**: Gestión de usuarios y autenticación
- **Base de datos**: `auth_db`
- **Endpoints**: `/api/auth/login`, `/api/auth/register`
- **Puerto**: 8081

### Pet Service
- **Responsabilidad**: Gestión de mascotas
- **Base de datos**: `pet_db`
- **Endpoints**: `/api/perros/*`
- **Puerto**: 8082

### Dashboard Service
- **Responsabilidad**: Estadísticas y reportes
- **Comunicación**: Usa Feign Client para comunicarse con Pet Service
- **Endpoints**: `/api/dashboard/*`
- **Puerto**: 8083

### Discovery Service
- **Responsabilidad**: Service discovery con Eureka
- **Puerto**: 8761
- **Dashboard**: http://localhost:8761

### API Gateway
- **Responsabilidad**: Punto de entrada único y enrutamiento
- **Puerto**: 8080
- **Funcionalidades**: 
  - Enrutamiento automático basado en paths
  - Balanceo de carga
  - Punto de entrada único para clientes

## Beneficios de la Migración

### 1. Escalabilidad
- Cada servicio puede escalar independientemente
- Posibilidad de escalar solo los servicios con mayor carga

### 2. Mantenibilidad
- Código más pequeño y enfocado por servicio
- Equipos pueden trabajar en servicios diferentes sin conflictos

### 3. Tecnología
- Posibilidad de usar diferentes tecnologías por servicio
- Actualizaciones independientes

### 4. Resiliencia
- Fallos en un servicio no afectan a otros
- Mejor aislamiento de errores

### 5. Despliegue
- Despliegues independientes por servicio
- Rollback granular

## Configuración de Redes

### Docker Network
- Todos los servicios están en la red `mascotas-network`
- Comunicación interna entre contenedores
- Puertos expuestos para acceso externo

### Variables de Entorno
- Configuración de bases de datos por servicio
- URLs de Eureka para service discovery
- Configuración de Feign Client

## Monitoreo y Observabilidad

### Eureka Dashboard
- Registro automático de servicios
- Estado de salud de cada servicio
- Información de instancias

### Logs
- Logs separados por servicio
- Fácil identificación de problemas
- Debugging más eficiente

## Próximos Pasos Recomendados

### 1. Observabilidad
- Implementar distributed tracing (Zipkin/Jaeger)
- Centralizar logs (ELK Stack)
- Métricas con Prometheus + Grafana

### 2. Seguridad
- Implementar JWT real en lugar de tokens fake
- OAuth2/OpenID Connect
- Rate limiting en API Gateway

### 3. Resiliencia
- Circuit breakers (Hystrix/Resilience4j)
- Retry policies
- Fallback mechanisms

### 4. Testing
- Tests de integración entre servicios
- Contract testing (Pact)
- Chaos engineering

### 5. CI/CD
- Pipeline separado por servicio
- Automated testing
- Blue-green deployments

## Comandos Útiles

### Desarrollo
```bash
# Construir todos los servicios
./build.sh

# Ejecutar en modo desarrollo
docker-compose up

# Ver logs de un servicio específico
docker-compose logs auth-service
```

### Producción
```bash
# Ejecutar en background
docker-compose up -d

# Escalar un servicio
docker-compose up -d --scale pet-service=3

# Parar todos los servicios
./stop.sh
```

## Troubleshooting

### Problemas Comunes

1. **Servicios no se registran en Eureka**
   - Verificar que discovery-service esté ejecutándose
   - Revisar configuración de Eureka en cada servicio

2. **Error de conexión entre servicios**
   - Verificar que los servicios estén en la misma red Docker
   - Revisar nombres de servicios en Feign Client

3. **API Gateway no enruta**
   - Verificar configuración de rutas
   - Revisar que los servicios estén registrados en Eureka

### Debugging

```bash
# Ver estado de todos los servicios
docker-compose ps

# Ver logs en tiempo real
docker-compose logs -f

# Entrar a un contenedor
docker-compose exec auth-service sh
```

## Conclusión

La migración a microservicios ha sido exitosa, manteniendo toda la funcionalidad original mientras se obtienen los beneficios de la arquitectura distribuida. La aplicación ahora es más escalable, mantenible y resiliente.