
package LALR;

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
    
   public ArrayList<LALR> Automata;
   public ArrayList<LALR> Reducciones;
   public ArrayList<Tabla> Tablas;
    
    public VariablesGlobales() {
      TipoTerminales  = new HashMap<String, Terminal>();
      TipoNoTerminales  = new HashMap<String, noTerminal>();
      Automata = new ArrayList<LALR>();
      Reducciones = new ArrayList<LALR>();
      Tablas = new ArrayList<Tabla>();
      
    }
    
    
}
