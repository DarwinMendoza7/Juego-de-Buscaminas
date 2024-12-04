 # Juego de Buscaminas  
 Este es un proyecto de implementación del clásico juego de Buscaminas en Java. El juego permite a los jugadores descubrir casillas en un tablero mientras evitan minas ocultas.  
 ## Uso del Juego ##  
 Una vez que el juego está en ejecución, se presentará un tablero en la consola. Los jugadores pueden interactuar con el juego ingresando comandos en la línea de comandos:  
 - **Descubrir una casilla:** Ingresa las coordenadas de la casilla que deseas descubrir (por ejemplo, 'A5').
 - **Marcar una casilla:** Para marcar una casilla como sospechosa, ingresa por ejemplo ´marcar A5´.
 - **Guardar el juego:** Si hay un estado guardado, se cargará automáticamente al iniciar el juego.
## Ejemplos de Ejecución ##  
Al iniciar el juego, verás algo similar a esto:

              |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 |
           ---+---------------------------------------------------
            A |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
            B |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
            C |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
            D |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
            E |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
            F |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
            G |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
            H |    |    |    |    |    |    |    |    |    |    |
           ---+---------------------------------------------------
           Ingrese una de las siguientes opciones:
	       - Para descubrir una casilla, ingrese la coordenada (ejemplo A5).
           - Para marcar una casilla, ingrese marcar seguido de la coordenada (ejemplo marcar A5).
	       - Para guardar el progreso, ingrese guardar.
           Ingrese la opcion:  
**Comandos:**  
- Para descubrir la casilla A1: ingresa A1  
- Para marcar la casilla B2: ingresa marcar B2  
- Para guardar el estado del juego: ingresa guardar    
## Estructura del Programa ##  
El programa está estructurado en varios paquetes para mantener una organización clara:  

    >src
    |── buscaminas/
    |   └── Main.java            # Clase principal para iniciar el juego
    ├── controlador/             # Paquete que contiene la lógica del juego
    │   └── ControladorJuego.java
    ├── modelo/                  # Paquete que contiene las clases del modelo del juego
    │   ├── Casilla.java
    │   |── Descubrible.java
    |   |──ElementoJuego.java
    |   └──Tablero.java 
    ├── vista/                   # Paquete que maneja la presentación en consola
    │   └── VistaTablero.java
    |── excepciones/             # Paquete que contiene excepciones personalizadas
    |    └── ExcepcionCasillaYaDescubierta.java
    >test
    |──test/                     # Paquete que contiene las pruebas unitarias
    |  |──CasillaTest.java
    |  └──TableroTest.java
    
## Descripción de Clases ##   
- **Main:** Clase principal que inicia el juego.    
- **ControladorJuego:** Maneja la lógica y la interacción con el usuario.  
- **Tablero:** Representa el tablero y gestiona las casillas.  
- **Casilla:** Representa cada celda del tablero y su estado (mina,descubierta,marcada).  
- **VistaTablero:** Se encarga de mostrar el estado actual del tablero al usuario.  
- **ExcepcionCasillaYaDescubierta:** Excepción personalizada que se lanza cuando se intenta descubrir una casilla ya descubierta.
- **CasillaTest:** Clase para la prueba unitaria de la clase Casilla.  
- **TableroTest:** Clase para la prueba unitaria de la clase Tablero.    
## Instrucciones para Clonar y Ejecutar el Proyecto ##
**1.** Asegurate de tener Git instalado en tu sistema. Puedes verificarlo abriendo una terminal y ejecutando el siguiente comando:
     
	 git -- version
Si git está instalado, verás la versión correspondiente. Si no está instalado, descárgalo e instálalo desde https://git-scm.com/.  
**2.** Navega a la carpeta donde deseas clonar el proyecto.  
**3.** Haz clic derecho en la carpeta y selecciona "Open Git Bash Here". Esto abrirá una terminal de Git Bash en la ubicación seleccionada.  
**4.** Ejecuta el siguiente comando:
    
	git clone https://github.com/DarwinMendoza7/Juego-de-Buscaminas.git
**5.** Para importar el proyecto a Eclipse haz lo siguiente:
- Abre Eclipse.
- Selecciona File luego Import y luego Existing Projects Into Workspace y pulsa en Next.
- Navega hasta la carpeta donde clonaste el proyecto y haz clic en Finish.  

**6.** En la vista de Proyecto, encuentra la clase Main, haz clic derecho sobre la clase y selecciona Run As y luego Java Application.

**Para clonar directamente desde Eclipse sigue estos pasos:**

**1.** Inicia Eclipse.  
**2.** Ve al menú File y selecciona la opción Import. Luego expande la carpeta Git y selecciona Projects from Git. A continuación, elige Clone URI y haz clic en Next.  
**3.** En el campo URI pega el enlace del repositorio que vas a clonar: https://github.com/DarwinMendoza7/Juego-de-Buscaminas.git y completa el campo de Authentication con tus datos y haz clic en Next.  
**4.** En la selección de ramas (Branch Selection), marca la rama main o master según corresponda y haz click en Next.  
**5.** En local Destination, selecciona la carpeta donde deseas clonar el proyecto y asigna un nombre al proyecto en el campo correspondiente. Luego haz clic en Next.  
**6.** En la ventana Select a wizard to use for importing projects, selecciona Import using the New Project wizard y haz clic en Finish.  
**7.** Se abrirá una ventana donde debes seleccionar el tipo de proyecto, elige Java Project en la carpeta de Java.  
**8.** Aparecerá una ventana para crear el nuevo proyecto. Ingresa el nombre del proyecto que desees, desmarca la opción que dice Use default location, y escoge la carpeta donde se clonó el proyecto. Luego haz clic en Finish.  
**9.** Por último, navega a la clase Main, haz clic derecho sobre ella, selecciona Run As, y luego elige Java Application.  
## Instrucciones para ejecutar las Pruebas Unitarias ##
**1.** Inicia Eclipse.  
**2.** Asegurate de que JUnit esté configurado, si no está configurada, puedes agregarla siguiendo estos pasos:  
- Haz clic derecho en tu proyecto en el Package Explorer.
- Seleccione Build Path luego Add Libraries.  
- Elige JUnit y haz clic en Next.  
- Selecciona la versión de JUnit que deseas utilizar (por ejemplo, JUnit 5) y haz clic en Finish.
    
**3.** Para ejecutar las pruebas desde Eclipse puedes hacerlo de las siguientes maneras:    
  - Abre la clase que deseas realizar la prueba en la carpeta test. Haz clic derecho en el archivo y selecciona Run As y luego JUnit Test.    
  - También puedes ejecutar todas las pruebas existentes desde el proyecto completo. Haz clic derecho en el nombre del proyecto y selecciona Run As y luego JUnit Test.
  
**4.** Después de ejecutar las pruebas, Eclipse abrirá una vista llamada JUnit que mostrará los resultados de las pruebas. Las pruebas exitosas aparecerán en verde, mientras que las fallidas aparecerán en rojo. Si falla alguna prueba puedes hacer clic en ella para ver detalles sobre el error. 
