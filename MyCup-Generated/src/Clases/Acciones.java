

package Clases;


/**
 *
 * @author Paulette
 */
public class Acciones {
    Estado inicio;
    Simbolo simbolo;
    Accion accion;
    public Acciones(Estado inicial, Simbolo sim, Accion act){
        inicio = inicial;
        simbolo = sim;
        accion = act;
    }
}
