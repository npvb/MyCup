package ParserClasses;

import java.util.ArrayList;

/**
 *
 * @author Paulette
 */
public class Estado {

    public ArrayList<EstadoProd> Producciones = new ArrayList<EstadoProd>();
    public int valor;
    String nombreEstado;
    
    public Estado() {
    }
    
    public boolean Comparar(ArrayList<EstadoProd> ProduccionesNuevas) throws Exception
    {
        try{
            if(Producciones.get(0).punto == ProduccionesNuevas.get(0).punto)
            {
                if(Producciones.get(0).prod.id.id.lexema.compareTo(ProduccionesNuevas.get(0).prod.id.id.lexema) == 0)
                {
                    if(Producciones.size()!=ProduccionesNuevas.size())
                    {
                        return false;
                    }else{
                        //for (int x1 = 0; x1 < Producciones.get(0).prod.lineas.size()-1; x1++){
                            for(int x=0;x<Producciones.size();x++)
                            {
                                if(Producciones.get(x).primero.size() == ProduccionesNuevas.get(0).primero.size())
                                {
                                    for(int y = 0; y< Producciones.get(x).primero.size();y++)
                                    {
                                        if(Producciones.get(x).primero.get(y).compareTo(ProduccionesNuevas.get(0).primero.get(y))!=0)
                                        {
                                            return false;
                                        }
                                    }
                                }
                                else
                                    return false;
                            }
                        //}
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
        }catch (Exception e){
           throw new Exception("Error Estado->Comparar(): " + e.getMessage());
       }  
    }
        
    public int Unir(Estado estado)
    {
        ArrayList<EstadoProd> nuevo;
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
                           // for (int xy = 0; xy < Producciones.get(x).prod.lineas.size(); xy++){
                                for(int y = 0;y<Producciones.get(x).prod.terminos.size();y++)
                                {
                                    if(Producciones.get(x).prod.terminos.get(y).id.equals(nuevo.get(x).prod.terminos.get(y).id)==false)
                                    {
                                        join = false;
                                    }
                                }
                            //}
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
    
    public void Print() throws Exception
    {
        try
        {
            System.out.println("lalr_state ["+valor+"]: ");
            System.out.println();
            for(int x = 0;x<Producciones.size();x++)
            {
                Producciones.get(x).Print();
            }
            System.out.println();
            System.out.println("---------------");
        
        }catch (Exception e){
           throw new Exception("Error Estado->Print(): " + e.getMessage());
       } 
    }

    boolean Comparar(Estado In) {
        
        if (Producciones.size() != In.Producciones.size())
            return false;
        for( EstadoProd e : In.Producciones)
            if (!Buscar(e))
                return false;
        return true;
    }

    private boolean Buscar(EstadoProd e) {
        for (EstadoProd p : Producciones){
            if ( p.punto!= e.punto ) 
                continue;
               // return false;
            if (p.prod.EsIgual(e.prod))
                return true;
        }
        return false;
    }
    
    
    
    
}
