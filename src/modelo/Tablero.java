package modelo;

import java.io.*;
import java.util.Random;
import excepciones.ExcepcionCasillaYaDescubierta;

public class Tablero implements Serializable {
    private static final long serialVersionUID = 1L; 
    private Casilla[][] casillas;
    private int filas;
    private int columnas;
    private int minas;

    public Tablero(int filas, int columnas, int minas) {
        this.filas = filas;
        this.columnas = columnas;
        this.minas = minas;
        this.casillas = new Casilla[filas][columnas];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla();
            }
        }
        colocarMinas();
        calcularMinasAlrededor();
    }

    private void colocarMinas() {
        Random rand = new Random();
        for (int colocadas = 0; colocadas < minas;) {
            int x = rand.nextInt(filas);
            int y = rand.nextInt(columnas);
            if (!casillas[x][y].tieneMina()) {
                casillas[x][y].ponerMina();
                colocadas++;
            }
        }
    }

    public void calcularMinasAlrededor() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!casillas[i][j].tieneMina()) {
                    casillas[i][j].setMinasAlrededor(contarMinas(i, j));
                }
            }
        }
    }

    private int contarMinas(int fila, int columna) {
        int conteo = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue; // Ignorar la propia casilla
                if (fila + x >= 0 && fila + x < filas && columna + y >= 0 && columna + y < columnas) {
                    if (casillas[fila + x][columna + y].tieneMina()) conteo++;
                }
            }
        }
        return conteo;
    }

    public int contarMinasTotales() {
        int totalMinas = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (casillas[i][j].tieneMina()) {
                    totalMinas++;
                }
            }
        }
        return totalMinas;
    }
    
    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void descubrirCasilla(int x, int y) throws ExcepcionCasillaYaDescubierta {
        if (casillas[x][y].estaDescubierta()) {
            throw new ExcepcionCasillaYaDescubierta("La casilla ya ha sido descubierta.");
        }
        
        casillas[x][y].descubrir();

        // Si no hay minas alrededor, descubrir adyacentes
        if (casillas[x][y].getMinasAlrededor() == 0) {
            descubrirAdyacentes(x, y);
        }
    }

    private void descubrirAdyacentes(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // Ignorar la propia casilla
                int nuevoX = x + i;
                int nuevoY = y + j;

                // Verificar límites
                if (nuevoX >= 0 && nuevoX < filas && nuevoY >= 0 && nuevoY < columnas) {
                    try { 
                        descubrirCasilla(nuevoX, nuevoY); 
                    } catch (Exception ignored) { 
                        // Manejar excepciones si la casilla ya fue descubierta
                    }
                }
            }
        }
    }

    public void guardarEstado(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(this);
            System.out.println("Estado guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el estado: " + e.getMessage());
        }
    }

   public static Tablero cargarEstado(String nombreArchivo) { 
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) { 
           return (Tablero) ois.readObject(); 
       } catch (IOException | ClassNotFoundException e) { 
           System.err.println("Error al cargar el estado: " + e.getMessage()); 
           return null; 
       } 
   } 
}