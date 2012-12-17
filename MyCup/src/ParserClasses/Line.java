package ParserClasses;
import java.util.*;
/**
 *
 * @author NEKO
 */
public class Line {
    
   noTerminal id;
   public ArrayList<Termino> terminos = new ArrayList<Termino>();
   boolean isGenerated;   
   int index;

    

   
    public boolean isIsGenerated() {
        return isGenerated;
    }

    public void setIsGenerated(boolean isGenerated) {
        this.isGenerated = isGenerated;
    }


    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
    
    public Line(ArrayList<Termino> terminos,  noTerminal not) {
        this.terminos = terminos;
        
        this.id = not;
    }
    public Line(ArrayList<Termino> terminos, boolean isGenerated, noTerminal not) {
        this.terminos = terminos;
        this.isGenerated = isGenerated;
        this.id = not;
    }
    
    
   public void Print()
   {
       if(!id.isGenerated)
       {
       System.out.print(id.id.lexema +"::= ");
       for (int xy = 0; xy < terminos.size(); xy++)
       {
         if(!(terminos.get(xy) instanceof CodeBlock))
                    { 
                       
                        if(terminos.get(xy) instanceof noTerminal)
                        {
                            noTerminal t = (noTerminal)terminos.get(xy);
                            if(!t.isGenerated)
                                System.out.print(terminos.get(xy).id.lexema  + " ");
                        }
                        else
                            System.out.print(terminos.get(xy).id.lexema  + " ");
                    }
                
       }
        System.out.println();
       }
   }
   
   
   public void PrintP(int punto) throws Exception
    {
        try
            {
            if(!id.isGenerated)
            {
            System.out.print(id.id.lexema +"-> ");

                for(int x=0;x<terminos.size();x++)
                {
                    if(!(terminos.get(x) instanceof CodeBlock))
                    { 
                        if(punto == x)
                        {
                            System.out.print(".");
                        }
                        if(terminos.get(x) instanceof noTerminal)
                        {
                            noTerminal t = (noTerminal)terminos.get(x);
                            if(!t.isGenerated)
                                System.out.print(terminos.get(x).id.lexema  + " ");
                        }
                        else
                            System.out.print(terminos.get(x).id.lexema  + " ");
                    }
                }
                if(punto == terminos.size())
                     System.out.print(".");
                System.out.print( "\n");
            }
            }catch (Exception e){
           throw new Exception("Error Line->PrintP():  " + e.getMessage());
       } 
    }
   
   public Line copy()
   {
       Line l = new Line();
      
      l.terminos = terminos;
            
      return l;
   }

    boolean EsIgual(Line e) {
        if (terminos.size() != e.terminos.size())
            return false;
        if ( !this.id.id.lexema.equals(e.id.id.lexema))
                return false;
        for (int i = 0; i < this.terminos.size(); i++) {
            if(!(terminos.get(i) instanceof CodeBlock))
            { 
                if(!(e.terminos.get(i) instanceof CodeBlock))
                {
                    if ( !terminos.get(i).id.lexema.equals(e.terminos.get(i).id.lexema))
                        return false;
                }
                else
                    return false;
            }
            else
            {
                if(e.terminos.get(i) instanceof CodeBlock)
                {
                CodeBlock cb = (CodeBlock)terminos.get(i);
                CodeBlock cb2 = (CodeBlock)e.terminos.get(i);
                if (!cb.codigo.equals(cb2.codigo))
                    return false;
                }
                else return false;
            }
        }
        return true;
    }
}
