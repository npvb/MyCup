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
    public boolean isGenerated;
    
    public noTerminal(ID id, String alias) {
        super(id, alias);
    }

    
    public boolean isIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(boolean isGenerated) {
        this.isGenerated = isGenerated;
    }

    public noTerminal(boolean isGenerated, ID id) {
        super(id);
        this.isGenerated = isGenerated;
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
