/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;
import java.util.ArrayList;
/**
 *
 * @author NEKO
 */
public class Produccion {
   public noTerminal id; 
   public ArrayList<Line> lineas = new ArrayList<Line>();
   //public  Line ln;
   
    public Produccion() {
    }
    
    public void setId(noTerminal id) {
        this.id = id;
    }
    
    public Produccion(noTerminal id,ArrayList<Line> lineas) {
        this.id = id;
        this.lineas = lineas;
    }
    
    public ArrayList<Line> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Line> lineas) {
        this.lineas = lineas;
    }
    
    public void Print(int punto)
    {
        System.out.print(id.id.lexema +"->");
        for (int xy = 0; xy < lineas.size(); xy++)
        {
            for(int x=0;x<lineas.get(xy).terminos.size();x++)
            {
                if(punto == x)
                {
                    System.out.print(".");
                }
                System.out.println(lineas.get(xy).terminos.get(x).id.lexema);
            }
        }
    }
}