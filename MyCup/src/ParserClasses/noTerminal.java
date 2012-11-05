/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;

/**
 *
 * @author NEKO
 */
public class noTerminal extends Termino{

    public noTerminal() {
    }

    public noTerminal(ID id, String alias) {
        super(id, alias);
    }
    
    public noTerminal(ID id) {
        super(id);
    }
     @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }
    
   
   
    
   
    
}
