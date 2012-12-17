/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;

/**
 *
 * @author NEKO
 */
public class GoTo {
    
    Estado estadoFrom;
    noTerminal not;
    Estado estadoTo;

    public GoTo(Estado estadoFrom, noTerminal not, Estado estadoTo) {
        this.estadoFrom = estadoFrom;
        this.not = not;
        this.estadoTo = estadoTo;
    }

    public GoTo() {
    }

    public Estado getEstadoFrom() {
        return estadoFrom;
    }

    public void setEstadoFrom(Estado estadoFrom) {
        this.estadoFrom = estadoFrom;
    }

    public Estado getEstadoTo() {
        return estadoTo;
    }

    public void setEstadoTo(Estado estadoTo) {
        this.estadoTo = estadoTo;
    }

    public noTerminal getNot() {
        return not;
    }

    public void setNot(noTerminal not) {
        this.not = not;
    }
    
    
            
    
    
    
}
