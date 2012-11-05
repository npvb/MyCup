package ParserClasses;
import java.util.*;
/**
 *
 * @author NEKO
 */
public class Line {
    
   public ArrayList<Termino> terminos = new ArrayList<Termino>();
   public ArrayList<CodeBlock> code = new ArrayList<CodeBlock>();

    public ArrayList<CodeBlock> getCode() {
        return code;
    }

    public void setCode(ArrayList<CodeBlock> code) {
        this.code = code;
    }

    public ArrayList<Termino> getTerminos() {
        return terminos;
    }

    public void setTerminos(ArrayList<Termino> terminos) {
        this.terminos = terminos;
    }

    public Line() {
    }
    
    public Line(ArrayList<Termino> terminos, ArrayList<CodeBlock> code) {
        this.terminos = terminos;
        this.code = code;
    }
    
}
