package excepciones;

/*Excepción personalizada que se lanza cuando se intenta descubrir una casilla
 * que ya ha sido descubierta*/
public class ExcepcionCasillaYaDescubierta extends Exception {
    public ExcepcionCasillaYaDescubierta(String mensaje) {
        super(mensaje);
    }
}