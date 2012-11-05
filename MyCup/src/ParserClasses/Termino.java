
package ParserClasses;

/**
 *
 * @author NEKO
 */
public abstract class Termino {
    public ID id;
    public String alias;

    public Termino(ID id, String alias) {
        this.id = id;
        this.alias = alias;
    }

    public Termino() {
    }
    
     public Termino(ID id) {
        this.id = id;
    }
     
    public abstract String getAlias();

    public abstract void setAlias(String alias);

    public abstract ID getId();

    public abstract void setId(ID id);
    
    
}
