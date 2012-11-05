package ParserClasses;

import java.util.ArrayList;

/**
 *
 * @author Paulette
 */
public class EstadoProd {
    
   public Produccion prod;
   public int punto;
   public ArrayList<String> primero = new ArrayList<String>();
    
    public EstadoProd() {
        this.punto = 0;
    }
    
    public void Print()
    {
        prod.Print(this.punto);
        System.out.print(",");
        for (int x=0;x<primero.size();x++)
        {
            System.out.print(primero.get(x));
        }
        System.out.println("");
    }
    
    
}
