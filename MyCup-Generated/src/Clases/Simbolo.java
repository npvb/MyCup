

package Clases;


/**
 *
 * @author Paulette
 */
public class Simbolo {
    String lexema;
    Object value;

    public Simbolo(String lexema, Object value) {
        this.lexema = lexema;
        this.value = value;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    public Simbolo(String l){
        lexema =l;
    }
}
