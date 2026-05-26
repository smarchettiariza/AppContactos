<div align="center">

# 📱 Gestor de Contactos Android

**Aplicación móvil para la gestión de contactos personales**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)
![Material Design](https://img.shields.io/badge/Material_Design-757575?style=for-the-badge&logo=material-design&logoColor=white)

*App Android desarrollada en Java con SQLite, gestión de sesión y diseño Material Design*

![Estado](https://img.shields.io/badge/Estado-Finalizado%20✔-brightgreen?style=for-the-badge)

</div>

---

## 📌 Descripción

**Gestor de Contactos** es una aplicación móvil nativa para Android desarrollada en **Java** con **Android Studio**. Permite administrar una agenda de contactos personales con persistencia local mediante **SQLite**, incluyendo autenticación de usuario, búsqueda en tiempo real y operaciones CRUD completas.

---

## ✨ Funcionalidades

### 🔐 Autenticación
- Login con validación de credenciales (`admin` / `1234`)
- Persistencia de sesión con **SharedPreferences**
- Inicio automático si la sesión ya está activa
- Opción de cerrar sesión desde el menú

### 📋 Gestión de Contactos
- Listado de contactos con **RecyclerView**
- Búsqueda en tiempo real
- Agregar, editar y eliminar contactos
- Carga de contactos predeterminados al iniciar la app

### 🧾 Datos por contacto
| Campo | Tipo |
|---|---|
| Nombre | Texto |
| Teléfono | Numérico |
| Correo electrónico | Texto |
| Domicilio | Texto |
| Género | Selección |

### ✅ Validaciones
- Verificación de campos vacíos
- El campo teléfono acepta únicamente valores numéricos

---

## 🗄️ Base de datos

La app utiliza **SQLite** para almacenamiento local en el dispositivo. Se implementaron todas las operaciones CRUD a través de la clase `ContactoDbHelper`:

| Operación | Descripción |
|---|---|
| **Create** | Insertar nuevo contacto |
| **Read** | Leer y listar contactos |
| **Update** | Actualizar datos de un contacto |
| **Delete** | Eliminar contacto individual |

---

## 🎨 Diseño de interfaz

- Fondos con degradé personalizados
- Botones redondeados y estilizados
- Diseño responsive definido en XML
- Componentes **Material Design** (`FloatingActionButton`, `Toolbar`, etc.)

---

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| **Java** | Lenguaje principal |
| **Android Studio** | IDE de desarrollo |
| **SQLite** | Base de datos local |
| **XML** | Diseño de layouts |
| **RecyclerView** | Lista dinámica de contactos |
| **SharedPreferences** | Persistencia de sesión |
| **Material Components** | Componentes visuales modernos |

---

## 📂 Estructura del proyecto

```
com.example.marchetti399/
│
├── 📄 LoginActivity.java           # Pantalla de inicio de sesión
├── 📄 ContactosActivity.java       # Lista principal de contactos
├── 📄 NuevoContactoActivity.java   # Formulario agregar / editar
├── 📄 Contacto.java                # Modelo de datos
├── 📄 ContactoDbHelper.java        # Gestor de base de datos SQLite
├── 📄 ContactosAdapter.java        # Adaptador del RecyclerView
├── 📄 OnContactoClick.java         # Interfaz click simple
├── 📄 OnContactoLongClick.java     # Interfaz click largo
│
├── res/layout/
│   ├── activity_login.xml
│   ├── activity_contactos.xml
│   ├── activity_nuevo_contacto.xml
│   └── item_contacto.xml
│
└── res/drawable/
    ├── fondo_degrade.xml
    └── boton_transparente.xml
```

---

## ⚙️ Funcionamiento técnico

### 🔹 SharedPreferences — Persistencia de sesión

```java
getSharedPreferences("MisPreferencias", MODE_PRIVATE)
    .edit()
    .putBoolean("isLoggedIn", true)
    .apply();
```

Al iniciar la app se verifica si `isLoggedIn` es `true`. De ser así, se omite la pantalla de login y se navega directamente a la lista de contactos.

---

### 🔹 SQLiteOpenHelper — Base de datos local

```java
public class ContactoDbHelper extends SQLiteOpenHelper { ... }
```

Esta clase gestiona el ciclo de vida de la base de datos y expone métodos para:
- Crear la tabla de contactos
- Insertar, actualizar y eliminar registros
- Consultar y retornar la lista de contactos

---

### 🔹 RecyclerView + Adapter — Lista dinámica

```java
recyclerView.setAdapter(new ContactosAdapter(contactos, clickListener, longClickListener));
```

Los contactos se obtienen desde SQLite y se renderizan dinámicamente. El adaptador notifica cambios en tiempo real al agregar, editar o eliminar un contacto.

---

## 📸 Capturas de pantalla

### 🔑 Pantalla de Login


### 📋 Lista de Contactos


### ➕ Formulario Nuevo Contacto


### ✏️ Edición de Contacto


---

## ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio:
```bash
git clone https://github.com/smarchettiariza/gestor-contactos-android.git
```

2. Abrir el proyecto en **Android Studio**

3. Sincronizar dependencias con **Gradle**

4. Ejecutar en un emulador o dispositivo físico con **Android 7.0+**

5. Iniciar sesión con las credenciales:
   - **Usuario:** `admin`
   - **Contraseña:** `1234`

---

## 👨‍💻 Autor

<div align="center">

**Santiago Marchetti Ariza**

Estudiante de Desarrollo de Aplicaciones Informáticas

*Apasionado por el desarrollo web y móvil 🚀*

[![GitHub](https://img.shields.io/badge/GitHub-smarchettiariza-181717?style=for-the-badge&logo=github)](https://github.com/smarchettiariza)

</div>

---

## 📄 Licencia

Este proyecto es de carácter **educativo y personal**. Libre para usar como referencia o inspiración.

---

<div align="center">
  <sub>Desarrollado con ❤️ por Santiago Marchetti Ariza</sub>
</div>
