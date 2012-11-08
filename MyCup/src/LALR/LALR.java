package LALR;
/**
 *
 * @author Paulette
 */
public class LALR {
  
    public int inicio, fin;
    public String simbolo;
    
    public LALR(int inicio, int fin, String simbolo) {
        this.inicio = inicio;
        this.fin = fin;
        this.simbolo = simbolo;
    }
    
    public LALR()
    {
        this.inicio = 0;
        this.fin = 0;
        this.simbolo = "EMPTY";
    }
    
    public void Print()
    {
        System.out.println("Inicial: "+inicio);
        System.out.println("Final: "+fin);
        System.out.println("Simbolo: "+simbolo);
    }
   
    
    
    
}
