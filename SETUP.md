# 🚀 Configuración del Sistema de Registro Canino

## Requisitos Previos

1. **Java 17+** instalado
2. **MySQL 8.0+** instalado y ejecutándose
3. **Maven 3.6+** instalado

## Paso 1: Configurar Base de Datos

### Crear la base de datos:
```sql
CREATE DATABASE perrosbd;
USE perrosbd;
```

### Configurar credenciales en `src/main/resources/application.yml`:
```yaml
spring:
  r2dbc:
    url: r2dbc:mysql://localhost:3306/perrosbd
    username: tu_usuario_mysql
    password: tu_password_mysql
```

## Paso 2: Ejecutar la Aplicación

### Opción A: Usando Maven Wrapper
```bash
./mvnw spring-boot:run
```

### Opción B: Usando Maven directamente
```bash
mvn clean install
java -jar target/mascotas-0.0.1-SNAPSHOT.jar
```

## Paso 3: Verificar la Instalación

1. **Dashboard Público**: http://localhost:8080
2. **API de Estadísticas**: http://localhost:8080/api/dashboard/estadisticas
3. **API de Búsqueda**: http://localhost:8080/api/dashboard/perros-duenio?duenio=Juan

## Paso 4: Configurar Google Maps (Opcional)

Para que el mapa funcione completamente:

1. Ve a [Google Cloud Console](https://console.cloud.google.com/)
2. Crea un proyecto nuevo
3. Habilita la API de Maps JavaScript
4. Genera una API key
5. Reemplaza la línea en `src/main/resources/static/index.html`:
   ```html
   <script src="https://maps.googleapis.com/maps/api/js?key=TU_API_KEY_AQUI&libraries=places"></script>
   ```

## Datos de Prueba

El sistema incluye datos de ejemplo que se cargan automáticamente:

### Usuarios Registradores:
- Email: `admin@municipalidad.com` / Password: `admin123`
- Email: `registrador@municipalidad.com` / Password: `registrador123`

### Perros de Ejemplo:
- Luna (Labrador, 3 años, Juan Pérez)
- Max (Golden Retriever, 2 años, María García)
- Bella (Chihuahua, 1 año, Carlos López)
- Rocky (Pitbull, 4 años, Ana Rodríguez)
- Coco (Poodle, 5 años, Luis Torres)
- Thor (Pastor Alemán, 6 años, Patricia Silva)
- Milo (Beagle, 2 años, Roberto Díaz)
- Nina (Husky, 3 años, Carmen Vega)

## Funcionalidades Disponibles

### Dashboard Público (sin login):
- ✅ Total de perros registrados
- ✅ Gráfico de distribución por razas
- ✅ Gráfico de categorías de edad
- ✅ Mapa de densidad canina
- ✅ Búsqueda de perros por dueño

### API Endpoints:
- `GET /api/dashboard/estadisticas` - Estadísticas generales
- `GET /api/dashboard/perros-duenio?duenio={nombre}` - Buscar perros por dueño
- `GET /api/dashboard/perros-duenio/{duenio}` - Buscar perros por dueño (path)

## Solución de Problemas

### Error de Conexión a Base de Datos:
```bash
# Verificar que MySQL esté ejecutándose
sudo systemctl status mysql

# Verificar credenciales
mysql -u tu_usuario -p
```

### Error de Puerto en Uso:
```bash
# Cambiar puerto en application.yml
server:
  port: 8081
```

### Error de Compilación:
```bash
# Limpiar y recompilar
./mvnw clean compile
```

## Estructura del Proyecto

```
src/main/java/com/perro/mascotas/
├── controller/
│   ├── AuthController.java          # Autenticación
│   ├── DashboardController.java     # Dashboard público
│   └── PerroController.java        # Gestión de perros
├── dto/
│   └── EstadisticasDashboard.java  # DTOs para estadísticas
├── model/
│   ├── Perro.java                  # Modelo de perro
│   └── Usuario.java                # Modelo de usuario
├── repository/
│   ├── PerroRepository.java        # Repositorio de perros
│   └── UsuarioRepository.java      # Repositorio de usuarios
├── service/
│   ├── DashboardService.java       # Lógica del dashboard
│   ├── PerroService.java           # Lógica de perros
│   └── UsuarioService.java         # Lógica de usuarios
└── config/
    └── CorsConfig.java             # Configuración CORS
```

## Tecnologías Utilizadas

- **Backend**: Spring Boot 3.x con WebFlux
- **Base de Datos**: MySQL con R2DBC
- **Frontend**: HTML5, CSS3, JavaScript vanilla
- **Gráficos**: Chart.js
- **Mapas**: Google Maps API
- **Build Tool**: Maven

## Próximos Pasos

1. **Configurar autenticación real** (JWT, OAuth2)
2. **Agregar validaciones** de datos
3. **Implementar tests** unitarios e integración
4. **Agregar logging** estructurado
5. **Configurar CI/CD** pipeline
6. **Implementar cache** para mejorar rendimiento
7. **Agregar documentación** con Swagger/OpenAPI

---

**¡El sistema está listo para usar!** 🎉