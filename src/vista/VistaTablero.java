package vista;

import modelo.Tablero;
import modelo.Casilla;

public class VistaTablero {

   public void mostrar(Tablero tablero) { 
       // Imprimir encabezado de la tabla 
       System.out.print("   |"); 
       for (int j = 1; j <= 10; j++) { // Imprimir números del 1 al 10 
           System.out.printf(" %2d|", j); // Formato para números 
       } 
       System.out.println();
       
       // Imprimir línea superior
       System.out.println("---+----------------------------------------");

       // Filas del tablero (A a H)
       for (int i = 0; i < 8; i++) { // Solo hasta H, que es la fila 7 (índice 0 a 7)
           System.out.printf(" %c |", (char) ('A' + i)); // Imprimir letra de fila (A a H)
           for (int j = 0; j < 10; j++) { // Solo hasta la columna 10
               Casilla casilla = tablero.getCasillas()[i][j];
               if (casilla.estaMarcada()) { 
                   System.out.print(" M |"); // Mostrar marcado
               } else if (casilla.estaDescubierta()) { 
                   if (casilla.tieneMina()) { 
                       System.out.print(" X |"); // Mostrar mina
                   } else { 
                       System.out.printf(" %2d|", casilla.getMinasAlrededor()); // Mostrar número de minas alrededor
                   } 
               } else { 
                   System.out.print("   |"); // Casilla cubierta
               } 
           } 
           System.out.println(); 
           // Imprimir línea inferior para cada fila
           System.out.println("---+----------------------------------------");
       } 
   }

   public void mostrarMensaje(String mensaje) { 
       System.out.println(mensaje); 
   } 
}