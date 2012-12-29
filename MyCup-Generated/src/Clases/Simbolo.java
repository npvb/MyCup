

package Clases;


/**
 *
 * @author Paulette
 */
public class Simbolo{
    String lexema;
    int sym;

    public int getSym() {
        return sym;
    }

    public void setSym(int sym) {
        this.sym = sym;
    }
    Object value;
    
    public Simbolo(int sym,String lexema, Object value) {
        this.lexema = lexema;
        this.value = value;
        this.sym = sym;
    }
    public Simbolo(String l){
        lexema =l;
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
    
    
}
