package test;

import modelo.Tablero;
import modelo.Casilla;
import excepciones.ExcepcionCasillaYaDescubierta;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TableroTest {
    private Tablero tablero;

    @Before
    public void setUp() {
        tablero = new Tablero(8, 10, 10); 
    }

    @Test
    public void testDescubrirCasillaSinMina() throws Exception {
        tablero.descubrirCasilla(0, 0); 
        assertTrue(tablero.getCasillas()[0][0].estaDescubierta());
    }

    @Test(expected = ExcepcionCasillaYaDescubierta.class)
    public void testDescubrirCasillaYaDescubierta() throws Exception {
        tablero.descubrirCasilla(0, 0);
        tablero.descubrirCasilla(0, 0); 
    }

    @Test
    public void testContarMinasAlrededor() throws Exception {
        // Colocar una mina manualmente en (1, 1)
        tablero.getCasillas()[1][1].ponerMina(); 
        // Llamar a calcularMinasAlrededor para actualizar los conteos
        tablero.calcularMinasAlrededor();

        // Verificamos que la casilla (0, 0) tiene 1 mina alrededor
        assertEquals(1, tablero.getCasillas()[0][0].getMinasAlrededor());

        // Ahora descubrimos la casilla (0, 0)
        tablero.descubrirCasilla(0, 0); 

        // Verificamos que la casilla (0, 0) ha sido descubierta
        assertTrue(tablero.getCasillas()[0][0].estaDescubierta());
    }

    @Test
    public void testMarcarYDesmarcarCasilla() {
        Casilla casilla = tablero.getCasillas()[0][0];
        casilla.marcar();
        assertTrue(casilla.estaMarcada()); // Verifica que la casilla esté marcada

        casilla.marcar(); // Desmarcar
        assertFalse(casilla.estaMarcada()); // Verifica que la casilla no esté marcada
    }

    @Test
    public void testContarMinasTotales() {
        assertEquals(10, tablero.contarMinasTotales()); 
    }
}