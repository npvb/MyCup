
package Clases;

/**
 *
 * @author Paulette
 */
public class Reducir extends Accion{
    int cantidad;
    public int numeroProd;

    public Reducir(String id, int cantidad, int numeroProd) {
        super(id);
        this.cantidad = cantidad;
        this.numeroProd = numeroProd;
    }
    
}
