package vista;

import modelo.Tablero;
import modelo.Casilla;

//Clase responsable de mostrar el estado del tablero en consola al usuario
public class VistaTablero {

   public void mostrar(Tablero tablero) { 
       
       System.out.print("   |"); 
       for (int j = 1; j <= 10; j++) { 
           System.out.printf(" %2d|", j); 
       } 
       System.out.println();
       
    
       System.out.println("---+----------------------------------------");

       // Filas del tablero (A a H)
       for (int i = 0; i < 8; i++) { 
           System.out.printf(" %c |", (char) ('A' + i)); // Imprimir letra de fila (A a H)
           for (int j = 0; j < 10; j++) { 
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
          
           System.out.println("---+----------------------------------------");
       } 
   }

   public void mostrarMensaje(String mensaje) { 
       System.out.println(mensaje); 
   } 
}