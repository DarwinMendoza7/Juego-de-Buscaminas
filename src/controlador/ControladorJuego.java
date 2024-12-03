package controlador;

import modelo.Tablero;
import vista.VistaTablero;
import excepciones.ExcepcionCasillaYaDescubierta;
import java.util.Scanner;

public class ControladorJuego {

   private Tablero tablero;
   private VistaTablero vista;

   public ControladorJuego() { 
       this.tablero = new Tablero(8, 10, 10); // Tablero de 8x10 con 10 minas
       this.vista = new VistaTablero(); 

       cargarJuego(); // Cargar el juego si hay un estado guardado al iniciar.
   }

   public void iniciarJuego() { 
       Scanner scanner = new Scanner(System.in);
       
       while (true) { 
           vista.mostrar(tablero); 
           System.out.print("Ingrese coordenadas (Ejemplo A5), 'marcar A5' para marcar, o 'guardar' para guardar: "); 

           String input = scanner.nextLine().toUpperCase();

           if ("GUARDAR".equals(input)) { // Comando para guardar el estado
               tablero.guardarEstado("estado_juego.dat");
               continue; // Volver al inicio del bucle
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

               continue; // Volver al inicio del bucle

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

   private void cargarJuego() {  
       Tablero loadedTablero = Tablero.cargarEstado("estado_juego.dat");  
       if (loadedTablero != null) {  
           this.tablero = loadedTablero;  
           System.out.println("Juego cargado exitosamente.");  
       } else {  
           System.out.println("No se encontró un estado guardado.");  
       }  
   }

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