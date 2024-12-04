package controlador;

import modelo.Tablero;
import vista.VistaTablero;
import excepciones.ExcepcionCasillaYaDescubierta;
import java.util.Scanner;

/*Clase responsable de manejar la lógica del juego, incluyendo interacciones
 * con el usuario y gestión del estado del juego*/
public class ControladorJuego {

   private Tablero tablero;
   private VistaTablero vista;

   public ControladorJuego() { 
       this.tablero = new Tablero(8, 10, 10); 
       this.vista = new VistaTablero(); 

       cargarJuego(); 
   }

   /*Inicia el bucle principal del juego, permitiendo al usuario interactuar con él hasta 
    * que termine o gane*/
   public void iniciarJuego() { 
       Scanner scanner = new Scanner(System.in);
       
       while (true) { 
           vista.mostrar(tablero); 
           System.out.print("Ingrese una de las siguientes opciones:\n"
           		+ "- Para descubrir una casilla, ingrese la coordenada (ejemplo A5).\n"
           		+ "- Para marcar una casilla, ingrese marcar seguido de la coordenada (ejemplo marcar A5),\n"
           		+ "- Para guardar el progreso, ingrese guardar.\n"
           		+ "Ingrese la opcion: "); 

           String input = scanner.nextLine().toUpperCase();

           if ("GUARDAR".equals(input)) { // Comando para guardar el estado
               tablero.guardarEstado("estado_juego.dat");
               continue; 
           }

           if (input.startsWith("MARCAR ")) { // Comando para marcar una casilla
               String coordenadas = input.substring(7).trim(); // Obtener las coordenadas después de "MARCAR "
               if (!coordenadas.matches("[A-H][1-9]|[A-H]10")) { 
                   vista.mostrarMensaje("Entrada inválida. Intente nuevamente.");
                   continue;
               }
               
               int x = coordenadas.charAt(0) - 'A';  
               int y = Integer.parseInt(coordenadas.substring(1)) - 1;

               tablero.getCasillas()[x][y].marcar(); // Marcar o desmarcar la casilla

               continue; 

           } else if (!input.matches("[A-H][1-9]|[A-H]10")) {  
               vista.mostrarMensaje("Entrada inválida. Intente nuevamente.");  
               continue;  
           }

           int x = input.charAt(0) - 'A';  
           int y = Integer.parseInt(input.substring(1)) - 1;  

           try {  
               tablero.descubrirCasilla(x, y);

               if (tablero.getCasillas()[x][y].tieneMina()) {  
                   vista.mostrar(tablero);  
                   vista.mostrarMensaje("¡Has perdido! Has descubierto una mina.");  
                   break;  
               }

               if (verificarVictoria()) {  
                   vista.mostrar(tablero);  
                   vista.mostrarMensaje("¡Felicidades! Has descubierto todas las casillas seguras. ¡Has ganado!");  
                   break;  
               }

           } catch (ExcepcionCasillaYaDescubierta e) {  
               vista.mostrarMensaje(e.getMessage());  
           } catch (ArrayIndexOutOfBoundsException e) {  
               vista.mostrarMensaje("Coordenadas fuera de los límites. Intente nuevamente.");  
           } catch (Exception e) {  
               vista.mostrarMensaje("Ocurrió un error inesperado: " + e.getMessage());  
           }
           
           vista.mostrar(tablero);  
       }
       
       scanner.close();  
   }

   //Carga un estado previamente guardado desde un archivo
   private void cargarJuego() {  
       Tablero loadedTablero = Tablero.cargarEstado("estado_juego.dat");  
       if (loadedTablero != null) {  
           this.tablero = loadedTablero;  
           System.out.println("Juego cargado exitosamente.");  
       } else {  
           System.out.println("No se encontró un estado guardado.");  
       }  
   }

   //Verifica si se ha ganado el juego comprobando si todas las celdas seguras han sido descubiertas
   private boolean verificarVictoria() {  
       int totalCasillasSeguras = tablero.getCasillas().length * tablero.getCasillas()[0].length - 10;  
       int casillasDescubiertas = 0;

       for (int i = 0; i < tablero.getCasillas().length; i++) {  
           for (int j = 0; j < tablero.getCasillas()[i].length; j++) {  
               if (tablero.getCasillas()[i][j].estaDescubierta() && !tablero.getCasillas()[i][j].tieneMina()) {  
                   casillasDescubiertas++;  
               }  
           }  
       } 

       return casillasDescubiertas == totalCasillasSeguras;   
   }   
}