
package Clases;

/**
 *
 * @author Paulette
 */
public class Reducir extends Accion{
    int cantidad;
    public String numeroProd;

    public Reducir(String id, int cantidad) 
    {
        super(id);
        this.cantidad = cantidad;
    }
}
