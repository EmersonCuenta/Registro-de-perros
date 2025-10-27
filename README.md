# 🐕 Sistema de Registro Canino Municipal

Sistema web para el registro y gestión de perros a nivel municipal, con dashboard público para visualización de estadísticas.

## 🚀 Funcionalidades

### Para Registradores (con login)
- ✅ Registro de perros y sus dueños
- ✅ Clasificación por raza, tamaño, comportamiento y ubicación
- ✅ Gestión completa de registros (CRUD)

### Para el Público (sin login)
- ✅ Dashboard público con estadísticas generales
- ✅ Visualización de densidad canina en mapa
- ✅ Búsqueda de perros por dueño
- ✅ Gráficos de distribución por raza y edad

## 📊 Dashboard Público

El dashboard incluye:
- **Total de perros registrados**
- **Gráfico de pie**: Distribución por razas
- **Gráfico de barras**: Categorías de edades
- **Mapa interactivo**: Densidad canina por ubicación
- **Buscador**: Perros por dueño

## 🛠️ Tecnologías

- **Backend**: Spring Boot 3.x con WebFlux
- **Base de Datos**: MySQL con R2DBC
- **Frontend**: HTML5, CSS3, JavaScript
- **Gráficos**: Chart.js
- **Mapas**: Google Maps API

## 📋 Requisitos

- Java 17 o superior
- MySQL 8.0 o superior
- Maven 3.6+

## 🚀 Instalación y Ejecución

### 1. Configurar Base de Datos

```sql
-- Crear base de datos
CREATE DATABASE perrosbd;
USE perrosbd;
```

### 2. Configurar Variables de Entorno

Editar `src/main/resources/application.yml`:

```yaml
spring:
  r2dbc:
    url: r2dbc:mysql://localhost:3306/perrosbd
    username: tu_usuario
    password: tu_password
```

### 3. Ejecutar la Aplicación

```bash
# Compilar y ejecutar
./mvnw spring-boot:run

# O alternativamente
mvn clean install
java -jar target/mascotas-0.0.1-SNAPSHOT.jar
```

### 4. Acceder a la Aplicación

- **Dashboard Público**: http://localhost:8080
- **API REST**: http://localhost:8080/api/dashboard/estadisticas

## 📡 Endpoints API

### Dashboard Público (sin autenticación)

```
GET /api/dashboard/estadisticas
GET /api/dashboard/perros-duenio?duenio={nombre}
GET /api/dashboard/perros-duenio/{duenio}
```

### Gestión de Perros (con autenticación)

```
GET /api/perros
POST /api/perros
PUT /api/perros/{id}
DELETE /api/perros/{id}
```

## 🗺️ Configuración del Mapa

Para que el mapa funcione correctamente, necesitas una API key de Google Maps:

1. Ve a [Google Cloud Console](https://console.cloud.google.com/)
2. Crea un proyecto y habilita Maps JavaScript API
3. Genera una API key
4. Reemplaza `YOUR_API_KEY` en `src/main/resources/static/index.html`

## 📊 Estructura de Datos

### Tabla: perros
- `id`: Identificador único
- `nombre`: Nombre del perro
- `raza`: Raza del perro
- `tamano`: Tamaño (Pequeño, Mediano, Grande)
- `ubicacion`: Ubicación geográfica
- `comportamiento`: Comportamiento del perro
- `duenio`: Nombre del dueño
- `edad`: Edad en años
- `usuario_id`: ID del usuario registrador

### Tabla: usuarios
- `id`: Identificador único
- `email`: Email del usuario
- `password`: Contraseña (encriptada)

## 🎨 Características del Dashboard

### Diseño Responsivo
- Adaptable a dispositivos móviles
- Interfaz moderna y atractiva
- Gradientes y animaciones suaves

### Visualizaciones Interactivas
- Gráficos dinámicos con Chart.js
- Mapa interactivo con marcadores
- Búsqueda en tiempo real

### Experiencia de Usuario
- Carga asíncrona de datos
- Indicadores de carga
- Manejo de errores elegante

## 🔧 Desarrollo

### Estructura del Proyecto

```
src/main/java/com/perro/mascotas/
├── controller/
│   ├── AuthController.java
│   ├── DashboardController.java
│   └── PerroController.java
├── dto/
│   └── EstadisticasDashboard.java
├── model/
│   ├── Perro.java
│   └── Usuario.java
├── repository/
│   ├── PerroRepository.java
│   └── UsuarioRepository.java
├── service/
│   ├── DashboardService.java
│   ├── PerroService.java
│   └── UsuarioService.java
└── MascotasApplication.java
```

### Agregar Nuevas Funcionalidades

1. **Nuevos endpoints**: Agregar en `controller/`
2. **Lógica de negocio**: Implementar en `service/`
3. **Acceso a datos**: Crear en `repository/`
4. **DTOs**: Definir en `dto/`

## 🐛 Solución de Problemas

### Error de Conexión a Base de Datos
- Verificar que MySQL esté ejecutándose
- Confirmar credenciales en `application.yml`
- Verificar que la base de datos `perrosbd` exista

### Mapa No Se Carga
- Verificar API key de Google Maps
- Revisar consola del navegador para errores
- Confirmar conexión a internet

### Gráficos No Se Muestran
- Verificar que Chart.js se cargue correctamente
- Revisar datos de respuesta de la API
- Verificar formato de datos en el frontend

## 📝 Licencia

Este proyecto está desarrollado para uso municipal y educativo.

## 🤝 Contribuciones

Para contribuir al proyecto:

1. Fork el repositorio
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

---

**Desarrollado para la gestión municipal de mascotas** 🏛️🐕