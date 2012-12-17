
package Clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


/**
 *
 * @author Paulette
 */
public abstract class ParserStack {
    Stack<String> pila;
    ArrayList<Integer> Entradas;
    ArrayList<String> Producciones;
    Tabla tabla;
    HashMap<Integer, String> hash;
    
   //MyParser parser = new MyParser();
   
    public ParserStack() {
    }
    
    public ParserStack(ArrayList<Integer> Entradas, ArrayList<String> Producciones,HashMap<Integer, String> H, Tabla tabla) {
        this.pila = new Stack<String>();
        pila.add("Q0");
        this.Entradas = Entradas;
        this.Producciones = Producciones;
        this.tabla = tabla;
        this.hash = H;
        
        
    }
    
    public String Accepted()
    {
        Accion action;
        String e;
        Desplazar move;
        Reducir reduce;
        hash.put(0, "$");
        
        //action = tabla.value(pila.peek(), hash.get(Entradas.get(0)));
        pila.push("0");
        while(true)
        {
            e = hash.get(Entradas.get(0));
            
            action = tabla.value(pila.peek(), hash.get(Entradas.get(0)));
        
            
            if(action instanceof Desplazar)
            {
                move = (Desplazar)action;
                pila.push(hash.get(Entradas.get(0)));
                pila.push(move.Id);
                
                Entradas.remove(0);
                e = hash.get(Entradas.get(0));
                //action = tabla.value(pila.get(pila.size()-1),e);
                
            }else if(action instanceof Reducir)
            {
                reduce = (Reducir)action;
                int reduceSize = reduce.cantidad;
                
                for(int x=0;x<reduceSize;x++)
                {
                    pila.pop();
                    //pila.remove(pila.size()-1);
                }
                //action = tabla.value(pila.get(pila.size()-1),Producciones.get(Integer.parseInt(reduce.Id)));
                String sp = pila.peek();
                
                pila.push(reduce.Id);
                pila.push(tabla.getIrA(sp, reduce.Id).Id);
              
                
               // this.Execute(reduce.numeroProd,pila);
                
                //e = hash.get(Entradas.get(0));
                //action = tabla.value(action.Id, e);
                
            }else if(action instanceof Aceptacion)
            {
                return "Finalizado Exitosamente";
            }else
                return "ERROR: \n Token Inválido";       
        }
        
        
        
    }

  public abstract Simbolo Execute(int reduccion, Stack<Simbolo> pila);
    
    
    
    
}
