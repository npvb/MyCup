package ParserClasses;

import java.util.ArrayList;

/**
 *
 * @author Paulette
 */
public class EstadoProd {
    
   public Line prod;
   public int punto;
   public ArrayList<String> primero = new ArrayList<String>();
    
    public EstadoProd() {
        this.punto = 0;
    }
    
    public void Print() throws Exception
    {
        prod.PrintP(punto);
        //prod.Print(this.punto);
//        System.out.print(",");
//        for (int x=0;x<primero.size();x++)
//        {
//            System.out.print(primero.get(x));
//        }
//       System.out.println();
    }
    
    public EstadoProd createCopy()
    {
        EstadoProd ep = new EstadoProd();
        for(String s:primero)
        {
            ep.primero.add(s);
        }
        
        ep.prod = prod;
        ep.punto = punto;
        
        return ep;
                
    }
}
