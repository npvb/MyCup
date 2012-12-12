package ParserClasses;
import java.util.*;
/**
 *
 * @author NEKO
 */
public class Line {
    
   noTerminal id;
   public ArrayList<Termino> terminos = new ArrayList<Termino>();
   public ArrayList<CodeBlock> code = new ArrayList<CodeBlock>();
   int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

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

    public noTerminal getId() {
        return id;
    }

    public void setId(noTerminal cabeza) {
        this.id = cabeza;
    }

    public Line() {
    }
    
    public Line(ArrayList<Termino> terminos, ArrayList<CodeBlock> code, noTerminal not) {
        this.terminos = terminos;
        this.code = code;
        this.id = not;
    }
    
    
   public void Print()
   {
       System.out.print(id.id.lexema +"::= ");
       for (int xy = 0; xy < terminos.size(); xy++)
       {
          System.out.print(terminos.get(xy).id.lexema + " "); 
       }
        System.out.println();
   }
   
   
   public void PrintP(int punto) throws Exception
    {
        try
            {
            System.out.print(id.id.lexema +"-> ");

                for(int x=0;x<terminos.size();x++)
                {
                    if(punto == x)
                    {
                        System.out.print(".");
                    }
                    System.out.print(terminos.get(x).id.lexema  + " ");
                }
                if(punto == terminos.size())
                     System.out.print(".");
                System.out.print( "\n");
            }catch (Exception e){
           throw new Exception("Error Line->PrintP(): " + e.getMessage());
       } 
    }
   
   public Line copy()
   {
       Line l = new Line();
       l.code = code;
      l.terminos = terminos;
            
      return l;
   }

    boolean EsIgual(Line e) {
        if (terminos.size() != e.terminos.size())
            return false;
        if ( !this.id.id.lexema.equals(e.id.id.lexema))
                return false;
        for (int i = 0; i < this.terminos.size(); i++) {
            
            if ( !terminos.get(i).id.lexema.equals(e.terminos.get(i).id.lexema))
                return false;
        }
        return true;
    }
}
