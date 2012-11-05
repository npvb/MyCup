/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;

/**
 *
 * @author NEKO
 */
public class ID {

    public String getLexema() {
        return lexema;
    }

    public void setId(String lexema) {
        this.lexema = lexema;
    }

    public ID() {
    }

    public String lexema;
    
    public ID(String lexema) {
        this.lexema = lexema;
    }
    
}
