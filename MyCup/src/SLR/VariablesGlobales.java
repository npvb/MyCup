
package SLR;

import ParserClasses.Terminal;
import ParserClasses.noTerminal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Paulette
 */
public class VariablesGlobales {

   public HashMap<String, Terminal> TipoTerminales;
   public HashMap<String, noTerminal> TipoNoTerminales;
    
   public ArrayList<SLR> Automata;
   public ArrayList<SLR> Reducciones;
   public ArrayList<Tabla> Tablas;
    
    public VariablesGlobales() {
      TipoTerminales  = new HashMap<String, Terminal>();
      TipoNoTerminales  = new HashMap<String, noTerminal>();
      Automata = new ArrayList<SLR>();
      Reducciones = new ArrayList<SLR>();
      Tablas = new ArrayList<Tabla>();
      
    }
    
    
}
