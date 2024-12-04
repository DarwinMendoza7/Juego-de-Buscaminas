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