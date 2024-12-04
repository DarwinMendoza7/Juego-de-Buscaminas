package test;

import modelo.Casilla;
import excepciones.ExcepcionCasillaYaDescubierta;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CasillaTest {
    private Casilla casilla;

    @Before
    public void setUp() {
        casilla = new Casilla();
    }

    @Test
    public void testPonerMina() {
        casilla.ponerMina();
        assertTrue(casilla.tieneMina());
    }

    @Test
    public void testDescubrir() {
        casilla.descubrir();
        assertTrue(casilla.estaDescubierta());
    }

    @Test
    public void testMarcar() {
        casilla.marcar();
        assertTrue(casilla.estaMarcada());
        casilla.marcar(); // Desmarcar
        assertFalse(casilla.estaMarcada());
    }

    @Test
    public void testSetMinasAlrededor() {
        casilla.setMinasAlrededor(3);
        assertEquals(3, casilla.getMinasAlrededor());
    }
}