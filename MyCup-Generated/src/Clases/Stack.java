
package Clases;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Paulette
 */
public class Stack {
    ArrayList<String> pila;
    ArrayList<Integer> Entradas;
    ArrayList<String> Producciones;
    Tabla tabla;
    HashMap<Integer, String> hash;
    
    public Stack() {
    }
    
    public Stack(ArrayList<Integer> Entradas, ArrayList<String> Producciones,HashMap<Integer, String> H, Tabla tabla) {
        this.pila = new ArrayList<String>();
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
        
        action = tabla.value(pila.get(pila.size()-1), hash.get(Entradas.get(0)));
        
        while(true)
        {
            e = hash.get(Entradas.get(0));
            
            if(action instanceof Desplazar)
            {
                move = (Desplazar)action;
                pila.add(move.Id);
                Entradas.remove(0);
                e = hash.get(Entradas.get(0));
                action = tabla.value(pila.get(pila.size()-1),e);
                
            }else if(action instanceof Reducir)
            {
                reduce = (Reducir)action;
                int reduceSize = reduce.cantidad;
                
                for(int x=0;x<reduceSize;x++)
                {
                    pila.remove(pila.size()-1);
                }
                action = tabla.value(pila.get(pila.size()-1),Producciones.get(Integer.parseInt(reduce.Id)));
                pila.add(action.Id);
                e = hash.get(Entradas.get(0));
                action = tabla.value(action.Id, e);
                
            }else if(action instanceof Aceptacion)
            {
                return "Finalizado Exitosamente";
            }else
                return "ERROR: \n Token Inválido";       
        }
        
        
        
    }

    
    
    
    
    
}
