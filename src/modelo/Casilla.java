package modelo;

import java.io.Serializable;

public class Casilla extends ElementoJuego implements Descubrible, Serializable {
    private static final long serialVersionUID = 1L;
    private boolean mina;
    private boolean descubierta;
    private boolean marcada; 
    private int minasAlrededor;

    public Casilla() {
        this.mina = false;
        this.descubierta = false;
        this.marcada = false; 
        this.minasAlrededor = 0;
    }

    public void ponerMina() {
        mina = true;
    }

    public boolean tieneMina() {
        return mina;
    }

    @Override
    public void descubrir() {
        descubierta = true;
    }

    public boolean estaDescubierta() {
        return descubierta;
    }

    public void marcar() {
        marcada = !marcada; 
    }

    public boolean estaMarcada() {
        return marcada;
    }

    public void setMinasAlrededor(int minas) {
        this.minasAlrededor = minas;
    }

    public int getMinasAlrededor() {
        return minasAlrededor;
    }
}