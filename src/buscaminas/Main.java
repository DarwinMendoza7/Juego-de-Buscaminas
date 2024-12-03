package buscaminas;

import controlador.ControladorJuego;

public class Main {

    public static void main(String[] args) {
        ControladorJuego controlador = new ControladorJuego();
        controlador.iniciarJuego();
    }
}