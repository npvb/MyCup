

package Clases;


/**
 *
 * @author Paulette
 */
public class IrA extends Accion{
    
    int to, from;

    public IrA(int to, int from, String id) {
        super(id);
        this.to = to;
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
    
}
