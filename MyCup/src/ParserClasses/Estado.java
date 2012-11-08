package ParserClasses;

import java.util.ArrayList;

/**
 *
 * @author Paulette
 */
public class Estado {

    public ArrayList<EstadoProd> Producciones = new ArrayList<EstadoProd>();
    public int valor;
    
    public Estado() {
    }
    
    public boolean Comparar(ArrayList<EstadoProd> ProduccionesNuevas)
    {
        if(Producciones.get(0).punto == ProduccionesNuevas.get(0).punto)
        {
            if(Producciones.get(0).prod.id.id.lexema.compareTo(ProduccionesNuevas.get(0).prod.id.id.lexema) == 0)
            {
                if(Producciones.size()!=ProduccionesNuevas.size())
                {
                    return false;
                }else{
                    for (int x1 = 0; x1 < Producciones.get(0).prod.lineas.size()-1; x1++){
                        for(int x=0;x<Producciones.get(0).prod.lineas.get(x1).terminos.size();x++)
                        {
                            if(Producciones.get(x).primero.get(x).compareTo(ProduccionesNuevas.get(0).primero.get(x))!=0)
                            {
                                return false;
                            }
                        }
                    }
                    for(int x=0;x<Producciones.get(0).primero.size();x++)
                    {
                        if(Producciones.get(0).primero.get(x).compareTo(ProduccionesNuevas.get(0).primero.get(x))!=0)
                        {
                            return false;
                        }
                    }
                    
                    return true;
                 }
            }else
            {
               return false;
            }
        }else
        {
            return false;
        }
    }
        
    public int Unir(Estado estado)
    {
        ArrayList<EstadoProd> nuevo = new ArrayList<EstadoProd>();
        nuevo = estado.Producciones;
        
        boolean join = true;
        
        for(int x=0;x<Producciones.size();x++)
        {
            if(Producciones.size() == nuevo.size())
            {
                if(Producciones.get(x).punto == nuevo.get(x).punto)
                {
                    if(Producciones.get(x).prod.id.equals(nuevo.get(x).prod.id)==true)
                    {
                        if(Producciones.size()!=nuevo.size())
                        {
                            join = false;
                        }else
                        {
                            for (int xy = 0; xy < Producciones.get(x).prod.lineas.size(); xy++){
                                for(int y = 0;y<Producciones.get(x).prod.lineas.get(xy).terminos.size();y++)
                                {
                                    if(Producciones.get(x).prod.lineas.get(xy).terminos.get(y).id.equals(nuevo.get(x).prod.lineas.get(xy).terminos.get(y).id)==false)
                                    {
                                        join = false;
                                    }
                                }
                            }
                        }
                    }else
                    {
                        join = false;
                    }
                }else
                {
                    join = false;
                }
            }else
            {
                join = false;
            }
            
            if(join)
            {
                for(int z=0;z<nuevo.get(x).primero.size();z++)
                {
                    if(!Buscar(nuevo.get(x).primero.get(z),x))
                    {
                        Producciones.get(x).primero.add(nuevo.get(x).primero.get(z));
                    }
                }
                return estado.valor;
            }
        }
        return -1;
    }
      
    public boolean Buscar(String simbolo, int posicion)
    {
        boolean found = false;
        for(int x=0;x<Producciones.get(posicion).primero.size();x++)
        {
            if(Producciones.get(posicion).primero.get(x).equals(simbolo) == true)
            {
                found = true;
            }
        }
        return found;
    }
    
    public void Print()
    {
        System.out.println("==== AUTOMATA ====");
        System.out.print("lalr_state ["+valor+"]: ");
        System.out.println();
        for(int x = 0;x<Producciones.size();x++)
        {
            Producciones.get(x).Print();
        }
        System.out.println();
        System.out.print("---------------");
    }
    
    
    
    
}
