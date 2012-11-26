package Clases;
import java.util.ArrayList;


/**
 *
 * @author Paulette
 */
public class Tabla {
     ArrayList<Estado> Estados;
     ArrayList<Simbolo> Simbolos;
     ArrayList<ArrayList<Acciones>> Tabla;
     
     public Tabla(){
         Estados = new ArrayList<Estado>();
         Simbolos = new ArrayList<Simbolo>();
         Tabla = new ArrayList<ArrayList<Acciones>>();
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
             }
         }
         for(int j=0;j<Simbolos.size();j++){
             if(nueva.simbolo.lexema.equals(Simbolos.get(j).lexema)){
                 col=j;
             }
         }
         ArrayList<Acciones> temp;
         temp = Tabla.get(fil);
         temp.set(col, nueva);
         Tabla.set(fil, temp);
     }

     public void CrearTabla(){
         for(int i=0;i<Estados.size();i++){
             ArrayList<Acciones> n = null;
             for(int j=0;j<Simbolos.size();j++){
                 Acciones acc = null;
                 acc.inicio = new Estado("");
                 acc.accion = new Accion("");
                 acc.simbolo = new Simbolo("");
                 n.add(acc);
             }
             Tabla.add(n);
         }
     }
}
