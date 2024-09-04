# Prueba Técnica: Login y Listado de Empleados

## Descripción del Proyecto

Esta aplicación Android es una prueba técnica que implementa un  login y visualización de empleados. 
Utiliza Retrofit para realizar llamadas API, maneja tokens de autenticación y presenta una interfaz de usuario con las siguientes características principales:

- Pantalla de login
- Autenticación mediante API usando Retrofit y POST
- Almacenamiento del token de autenticación
- Pantalla principal con listado de empleados
- Obtención de datos de empleados mediante API autenticada (POST)
- Menú de barra lateral

## Características

### 1. Login

- Interfaz de usuario para ingreso de credenciales
- Manejo de errores y mensajes de respuesta
- Almacenamiento del token de autenticación

### 2. Listado de Empleados

- Visualización de empleados en formato de lista
- Obtención de datos mediante API 

### 3. Menú Lateral

- Implementación de ModalNavigationDrawer de Jetpack Compose

## Tecnologías Utilizadas

- Kotlin
- Jetpack Compose para la interfaz de usuario
- Retrofit para llamadas API
- ModalNavigationDrawer para el menú lateral
- ViewModel y StateFlow para la arquitectura MVVM
- Coroutines para operaciones asíncronas

## Capturas de Pantalla


Login             |  Dialogo
:-------------------------:|:-------------------------:
![login_1](https://github.com/user-attachments/assets/7f8421f5-90ce-4a5b-b96a-ff164387065f) | ![dialog_1](https://github.com/user-attachments/assets/c537a9fc-1525-48b6-8861-79de8b95b76a) 


Home             |  Menu
:-------------------------:|:-------------------------:
![lista_1](https://github.com/user-attachments/assets/2deb3a45-ee06-45b8-82e7-d3c4c78b700c) | ![menu_1](https://github.com/user-attachments/assets/cd77fd3f-703a-48ea-a5f1-b23f87058c3a)
