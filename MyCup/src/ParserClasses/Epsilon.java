/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;

/**
 *
 * @author NEKO
 */
public class Epsilon extends Termino {
    
    private static final Epsilon value = new Epsilon();

    public static Epsilon getValue() {
        return value;
    }
    
    private Epsilon(){
        super();
    }

    @Override
    public String getAlias() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAlias(String alias) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ID getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(ID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
