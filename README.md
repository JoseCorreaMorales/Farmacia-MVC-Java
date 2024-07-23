# Farmacia

Este proyecto es una aplicación de gestión para una farmacia, desarrollada utilizando Java y JavaFX. La aplicación permite gestionar clientes, productos, trabajadores y ventas, proporcionando una interfaz gráfica para interactuar con la base de datos SQL Lite.

> **⚠️ Advertencia:** Este proyecto fue desarrollado entre 2016 y 2017 y subido a GitHub en 2022. Este proyecto fue desarrollado entre 2016 y 2017 como parte de mi programa de estudios. Fue subido a GitHub en 2022 cuando comencé a utilizar la plataforma para gestionar mis proyectos.


## Estructura del Proyecto

El proyecto está organizado en varios paquetes, cada uno con una responsabilidad específica:

- `clientes`: Maneja la gestión de clientes.
- `productos`: Maneja la gestión de productos.
- `trabajadores`: Maneja la gestión de trabajadores.
- `ventas`: Maneja la gestión de ventas.
- `dialogos`: Maneja los diálogos de la aplicación.
- `farmacia`: Contiene la clase principal y controladores de la interfaz.

## Estrucura MVC
```
📦src
 ┣ 📂clientes
 ┃ ┣ 📂model
 ┃ ┃ ┗ 📜Cliente.java
 ┃ ┣ 📂view
 ┃ ┃ ┣ 📜agregar.fxml
 ┃ ┃ ┣ 📜agregarControlador.java
 ┃ ┃ ┣ 📜clientes.fxml
 ┃ ┃ ┣ 📜clientesControlador.java
 ┃ ┃ ┣ 📜editar.fxml
 ┃ ┃ ┗ 📜editarControlador.java
 ┃ ┗ 📜Clientes.java
 ┣ 📂dialogos
 ┃ ┣ 📂view
 ┃ ┃ ┣ 📜dialogos.fxml
 ┃ ┃ ┗ 📜dialogosControlador.java
 ┃ ┗ 📜Dialogos.java
 ┣ 📂farmacia
 ┃ ┣ 📂util
 ┃ ┃ ┣ 📜Fechas.java
 ┃ ┃ ┗ 📜SQLite.java
 ┃ ┣ 📂view
 ┃ ┃ ┣ 📜acerca.fxml
 ┃ ┃ ┣ 📜acercaControlador.java
 ┃ ┃ ┣ 📜raiz.fxml
 ┃ ┃ ┗ 📜raizControlador.java
 ┃ ┗ 📜Farmacia.java
 ┣ 📂META-INF
 ┃ ┗ 📜MANIFEST.MF
 ┣ 📂productos
 ┃ ┣ 📂model
 ┃ ┃ ┗ 📜Producto.java
 ┃ ┣ 📂view
 ┃ ┃ ┣ 📜agregar.fxml
 ┃ ┃ ┣ 📜agregarControlador.java
 ┃ ┃ ┣ 📜editar.fxml
 ┃ ┃ ┣ 📜editarControlador.java
 ┃ ┃ ┣ 📜productos.fxml
 ┃ ┃ ┗ 📜productosControlador.java
 ┃ ┗ 📜Productos.java
 ┣ 📂res
 ┃ ┣ 📜error.png
 ┃ ┣ 📜informacion.png
 ┃ ┗ 📜pregunta.png
 ┣ 📂trabajadores
 ┃ ┣ 📂model
 ┃ ┃ ┗ 📜Trabajador.java
 ┃ ┣ 📂view
 ┃ ┃ ┣ 📜agregar.fxml
 ┃ ┃ ┣ 📜agregarControlador.java
 ┃ ┃ ┣ 📜editar.fxml
 ┃ ┃ ┣ 📜editarControlador.java
 ┃ ┃ ┣ 📜trabajadores.fxml
 ┃ ┃ ┗ 📜trabajadoresControlador.java
 ┃ ┗ 📜Trabajadores.java
 ┗ 📂ventas
 ┃ ┣ 📂model
 ┃ ┃ ┣ 📜Producto.java
 ┃ ┃ ┗ 📜Venta.java
 ┃ ┣ 📂view
 ┃ ┃ ┣ 📜agregar.fxml
 ┃ ┃ ┣ 📜agregarControlador.java
 ┃ ┃ ┣ 📜editar.fxml
 ┃ ┃ ┣ 📜editarControlador.java
 ┃ ┃ ┣ 📜productos.fxml
 ┃ ┃ ┣ 📜productosControlador.java
 ┃ ┃ ┣ 📜ventas.fxml
 ┃ ┃ ┗ 📜ventasControlador.java
 ┃ ┗ 📜Ventas.java
 ```


## Clases Principales

### Farmacia

La clase principal que inicia la aplicación y carga la interfaz gráfica.

```
java:src/farmacia/Farmacia.java
La clase principal que inicia la aplicación y carga la interfaz gráfica.
```

### Dialogos

Clase para manejar los diálogos de información, error y confirmación.

```
java:src/dialogos/Dialogos.java
Clase para manejar los diálogos de información, error y confirmación.
```

### Clientes

Clase para gestionar la lista de clientes y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

```
java:src/clientes/Clientes.java
Clase para gestionar la lista de clientes y realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar).
```

### Productos

Clase para gestionar la lista de productos y realizar operaciones CRUD.

```java:src/productos/Productos.java
Clase para gestionar la lista de productos y realizar operaciones CRUD.

```

### Ventas

Clase para gestionar la lista de ventas y realizar operaciones CRUD.

```
java:src/ventas/Ventas.java
Clase para gestionar la lista de ventas y realizar operaciones CRUD.
```

## Controladores

Los controladores manejan la lógica de la interfaz gráfica y las interacciones del usuario.


## Base de Datos

La aplicación utiliza SQLite para la gestión de la base de datos `SQLite` 

## Ejecución

Para ejecutar la aplicación, asegúrate de tener Java instalado en tu sistema y ejecuta el comando `java -jar Farmacia.jar` en la carpeta raíz del proyecto.