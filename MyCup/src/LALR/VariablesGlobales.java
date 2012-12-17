
package LALR;

import ParserClasses.*;
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
   public ArrayList<ArrayList<String>> Tabla;
   public ArrayList<String> listTerm;
   public ArrayList<String> listNoTerm;
   public ArrayList<GoTo> IrA;
    
    public VariablesGlobales() {
      TipoTerminales  = new HashMap<String, Terminal>();
      TipoNoTerminales  = new HashMap<String, noTerminal>();
      Automata = new ArrayList<LALR>();
      Reducciones = new ArrayList<LALR>();
      Tablas = new ArrayList<Tabla>();
      listNoTerm = new ArrayList<String>();
      listTerm = new ArrayList<String>();
      Tabla = new ArrayList<ArrayList<String>>();
      IrA = new ArrayList<GoTo>();
    }
    
    
}
