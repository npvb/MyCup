package Clases;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;


/**
 *
 * @author Paulette
 */
public class Tabla {
     ArrayList<Estado> Estados;
     ArrayList<Simbolo> Simbolos;
     ArrayList<ArrayList<Acciones>> Tabla;
     Hashtable<Integer,Hashtable<String,IrA>> ira;     
     
     public Tabla(){
         Estados = new ArrayList<Estado>();
         Simbolos = new ArrayList<Simbolo>();
         Tabla = new ArrayList<ArrayList<Acciones>>();
         ira = new Hashtable<Integer,Hashtable<String,IrA>>();
     }
     
     public void addIrA(int estadoFrom,String simbolo, int estadoTo ){
         Hashtable<String,IrA> t = new Hashtable<String,IrA>();
         t.put(simbolo,new IrA(estadoFrom, estadoTo, simbolo));
         this.ira.put(estadoFrom,t);     
     }
     
     public IrA getIrA(String estado, String simbolo){
         return ira.get(estado).get(simbolo);
     }
     
     public void addEstado(Estado nuevo){
         Estados.add(nuevo);
     }
     
     public void addSimbolo(Simbolo nuevo){
         Simbolos.add(nuevo);
     }

     public void addAccion(Acciones nueva){
         int fil=0, col=0;
         for(int i=0;i<Estados.size();i++){
             if(nueva.inicio.Id.equals(Estados.get(i).Id)){
                 fil =i;
                 break;
             }
         }
         for(int j=0;j<Simbolos.size();j++){
             if(nueva.simbolo.lexema.equals(Simbolos.get(j).lexema)){
                 col=j;
                 break;
             }
         }
         ArrayList<Acciones> temp = new ArrayList<Acciones>();
         temp = Tabla.get(fil);
         temp.set(col, nueva);
         Tabla.set(fil, temp);
     }

     public void CrearTabla(){
         for(int i=0;i<Estados.size();i++){
             ArrayList<Acciones> n = new ArrayList<Acciones>();
             for(int j=0;j<Simbolos.size();j++){
                 Acciones acc = new Acciones(new Estado(""),new Simbolo(""), new Accion("") );
                 n.add(acc);
             }
             Tabla.add(n);
         }
     }
     
     
      public Accion value(String estado, String term){
         int f=0,c=0;
        for(int i=0;i<Estados.size();i++){
            if(Estados.get(i).Id.compareTo(estado)==0){
                f=i;
                break;
            }
        }
        for(int i=0;i<Simbolos.size();i++){
            if(Simbolos.get(i).lexema.compareTo(term)==0){
                c=i;
                break;
            }
         }
         return Tabla.get(f).get(c).accion;
     }
}
