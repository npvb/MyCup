package ParserClasses;
import java.util.ArrayList;

/**
 *
 * @author NEKO
 */
public class TerminalesDef {
    ArrayList<Terminal> terminales;

    public TerminalesDef(ArrayList<Terminal> terminales) {
        this.terminales = terminales;
    }

    public TerminalesDef() {
    }

    public ArrayList<Terminal> getTerminales() {
        return terminales;
    }

    public void setTerminales(ArrayList<Terminal> terminales) {
        this.terminales = terminales;
    }
    
    public void Print()
    {
        System.out.println("==== TERMINALES ====");
        for(int x=0;x<terminales.size();x++)
        {
            System.out.print("["+x+"]"+terminales.get(x).id.lexema);
        }
        System.out.println();
        System.out.print("---------------");
    }
    
    
}
