/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;

/**
 *
 * @author NEKO
 */
public class CodeBlock extends Termino {
    
   String  codigo;

    public CodeBlock(String codigo) {
        this.codigo = codigo;
    }

    public CodeBlock() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getAlias() {
        return "";
    }

    @Override
    public void setAlias(String alias) {
         alias = "";
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
