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
                System.out.print( "\n");
            }catch (Exception e){
           throw new Exception("Error Line->PrintP(): " + e.getMessage());
       } 
    }
}
