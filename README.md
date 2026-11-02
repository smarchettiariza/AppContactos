<div align="center">

# 📱 Gestor de Contactos Android

**Aplicación móvil para la gestión de contactos personales**

Agenda local con login, búsqueda en tiempo real y operaciones CRUD completas — todo en un solo lugar.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Android](https://img.shields.io/badge/Android-7.0+-3DDC84?style=flat&logo=android&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=flat&logo=androidstudio&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=flat&logo=sqlite&logoColor=white)
![Material Design](https://img.shields.io/badge/Material_Design-757575?style=flat&logo=materialdesign&logoColor=white)
![Estado](https://img.shields.io/badge/Estado-Finalizado-brightgreen?style=flat)

</div>

---

##  Funcionalidades

-  **Login con validación** — acceso con credenciales de usuario
-  **Sesión persistente** — inicio automático si la sesión ya está activa
-  **Cerrar sesión** — desde el menú de la app
-  **Listado de contactos** — lista dinámica con RecyclerView
-  **Búsqueda en tiempo real** — filtrado mientras escribís
-  **CRUD completo** — agregar, editar y eliminar contactos
-  **Contactos predeterminados** — se cargan al iniciar la app
-  **Validaciones** — campos vacíos y teléfono solo numérico

## 🧾 Datos por contacto

| Campo | Tipo |
|---|---|
| Nombre | Texto |
| Teléfono | Numérico |
| Correo electrónico | Texto |
| Domicilio | Texto |
| Género | Selección |

##  Stack técnico

| | |
|---|---|
| **Lenguaje** | Java · XML (layouts) |
| **Base de datos** | SQLite (`SQLiteOpenHelper`) |
| **Interfaz** | RecyclerView · Material Components · degradés y botones personalizados |
| **Sesión** | SharedPreferences |
| **IDE** | Android Studio · Gradle |

```
com.example.marchetti399/
├── LoginActivity.java           → Pantalla de inicio de sesión
├── ContactosActivity.java       → Lista principal de contactos
├── NuevoContactoActivity.java   → Formulario agregar / editar
├── Contacto.java                → Modelo de datos
├── ContactoDbHelper.java        → Gestor de base de datos SQLite
├── ContactosAdapter.java        → Adaptador del RecyclerView
├── OnContactoClick.java         → Interfaz de click simple
├── OnContactoLongClick.java     → Interfaz de click largo
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

## ⚙️ Funcionamiento técnico

### Persistencia de sesión

```java
getSharedPreferences("MisPreferencias", MODE_PRIVATE)
    .edit()
    .putBoolean("isLoggedIn", true)
    .apply();
```

Al iniciar la app se verifica `isLoggedIn`. Si es `true`, se omite el login y se navega directo a la lista de contactos.

### Base de datos local

```java
public class ContactoDbHelper extends SQLiteOpenHelper { ... }
```

`ContactoDbHelper` gestiona el ciclo de vida de la base y expone las operaciones:

| Operación | Descripción |
|---|---|
| **Create** | Insertar un nuevo contacto |
| **Read** | Leer y listar contactos |
| **Update** | Actualizar los datos de un contacto |
| **Delete** | Eliminar un contacto |

### Lista dinámica

```java
recyclerView.setAdapter(new ContactosAdapter(contactos, clickListener, longClickListener));
```

Los contactos se leen desde SQLite y se renderizan dinámicamente. El adaptador notifica los cambios al agregar, editar o eliminar.

## Instalación

### Requisitos

- [Android Studio](https://developer.android.com/studio)
- Emulador o dispositivo físico con **Android 7.0+**

### Pasos

```bash
git clone https://github.com/smarchettiariza/gestor-contactos-android.git
```

1. Abrí el proyecto en **Android Studio**.
2. Sincronizá las dependencias con **Gradle**.
3. Ejecutalo en un emulador o dispositivo físico.
4. Ingresá con las credenciales de prueba:

```
usuario:    admin
contraseña: 1234
```

## 📌 Notas

- Los datos se guardan **localmente** en el dispositivo (SQLite): no hay servidor ni datos compartidos entre instalaciones.
- Las credenciales `admin` / `1234` son de prueba y están definidas en la app.
- Proyecto de carácter **educativo y personal**, libre para usar como referencia o inspiración.

<div align="center">

Proyecto Academico.

</div>
