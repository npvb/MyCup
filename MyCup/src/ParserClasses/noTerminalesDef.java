/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;
import java.util.*;
/**
 *
 * @author NEKO
 */
public class noTerminalesDef {
 
    ID tipo;
    ArrayList<noTerminal> noTerminales;

    public noTerminalesDef(ArrayList<noTerminal> noTerminales) {
        this.noTerminales = noTerminales;
    }
    
    public noTerminalesDef(ID tipo, ArrayList<noTerminal> noTerminales) {
        this.tipo = tipo;
        this.noTerminales = noTerminales;
    }
    
    public noTerminalesDef() {
    }

    public ArrayList<noTerminal> getNoTerminales() {
        return noTerminales;
    }

    public void setNoTerminales(ArrayList<noTerminal> noTerminales) {
        this.noTerminales = noTerminales;
    }
     public void setTipo(ID tipo) {
        this.tipo = tipo;
    }

    public ID getTipo() {
        return tipo;
    }

}
