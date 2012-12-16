
package Clases;

/**
 *
 * @author Paulette
 */
public class Reducir extends Accion{
    int cantidad;
    public String numeroProd;

    public Reducir(String id, int cantidad, String numeroProd) {
        super(id);
        this.cantidad = cantidad;
        this.numeroProd = numeroProd;
    }
    public Reducir(String id, int cant) {
        super(id);
        cantidad = cant;
    }
}
