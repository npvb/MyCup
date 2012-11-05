package SLR;
/**
 *
 * @author Paulette
 */
public class SLR {
  
    public int inicio, fin;
    public String simbolo;
    
    public SLR(int inicio, int fin, String simbolo) {
        this.inicio = inicio;
        this.fin = fin;
        this.simbolo = simbolo;
    }
    
    public SLR()
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
