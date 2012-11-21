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
        System.out.println("Estado Inicial: "+inicio);
        System.out.println("Estado Final: "+fin);
        System.out.println("Simbolo de Transicion: "+simbolo);
    }
   
    
    
    
}
