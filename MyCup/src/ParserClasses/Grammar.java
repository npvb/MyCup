
package ParserClasses;
import GenerarArchivo.GenerarArchivoJava;
import LALR.LALR;
import LALR.VariablesGlobales;
import java.io.*;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author NEKO
 */
public class Grammar {
    
    public TerminalesDef termDef;
    public ArrayList<noTerminalesDef> nonTermDef;
    public ArrayList<Produccion> Grammar = new ArrayList<Produccion>();
    
    HashMap<String, ArrayList<String>> ListaPrimeros =new HashMap<String, ArrayList<String>>();
    ArrayList<Estado> Estados = new ArrayList<Estado>();
    ArrayList<String> Calculados = new ArrayList<String>();
    ArrayList<ArrayList<String>> Tabla = new ArrayList<ArrayList<String>>();
    VariablesGlobales varGlobal = new VariablesGlobales();
    int contador,inicio,fin;
    String concatWith;
    ArrayList<Line> productions = new ArrayList<Line>();
    
    public ArrayList<Estado> getEstados() {
        return Estados;
    }

    public void setEstados(ArrayList<Estado> Estados) {
        this.Estados = Estados;
    }
        
    public Grammar() {
        Estados.clear();
        contador = 1;
    }
    
    public ArrayList<Line> getLineas()
    {
            ArrayList<Line> productions = new ArrayList<Line>();
            for(Produccion p : Grammar)
            {
                for(Line l:p.lineas)
                {
                    productions.add(l);
                }
            }

            return productions;
        
    }
    public Grammar(TerminalesDef termDef, ArrayList<noTerminalesDef> nonTermDef, ArrayList<Produccion> producciones) 
    {
        this.termDef = termDef;
        this.nonTermDef = nonTermDef;
        this.Grammar = producciones;
    }
    public ArrayList<noTerminalesDef> getNonTermDef() 
    {
        return nonTermDef;
    }
    public void setNonTermDef(ArrayList<noTerminalesDef> nonTermDef)
    {
        this.nonTermDef = nonTermDef;
    }
    public ArrayList<Produccion> getProducciones() 
    {
        return Grammar;
    }
    public void setProducciones(ArrayList<Produccion> producciones) 
    {
        this.Grammar = producciones;
    }
    public TerminalesDef getTermDef() 
    {
        return termDef;
    }
    public void setTermDef(TerminalesDef termDef) 
    {
        this.termDef = termDef;
    }
    public void PrintProduction()
    {
        System.out.println("==== PRODUCCIONES ====");
        for(int x=0;x<productions.size();x++)
        {
            productions.get(x).Print();
        }
        System.out.println();
        System.out.println("---------------");
    }  
    public void ExtenderGramatica() throws Exception
    {
        try{
            Estados.clear();
            contador = 1;
            termDef.Print();
            productions = getLineas();
            for(int x=0;x<nonTermDef.size();x++)
            {
                nonTermDef.get(x).Print();
            }

            Line inicial = new Line();
            inicial.setId(new noTerminal(new ID("Inicial"), " "));
            ArrayList<Termino> simbolo = new ArrayList<Termino>();
            simbolo.add(productions.get(0).id);
            inicial.setTerminos(simbolo);
            productions.add(0, inicial);

            //Navi code
    //        ArrayList<Termino> simbolo = new ArrayList<Termino>();
    //        simbolo.add(Grammar.get(0).id);
    //        produccion.lineas.get(0).setTerminos(simbolo);
    //        ArrayList<Produccion> temp = new ArrayList<Produccion>();
    //        temp.add(produccion);
    //        
    //        for(int x=0;x<Grammar.size();x++)
    //        {
    //            temp.add(Grammar.get(x));
    //        }
    //       
    //        Grammar.equals(temp);
       }catch (Exception e){
          throw new Exception( "Error Grammar->ExtenderGramatica(): " + e.getMessage());
       }     
    }
    public boolean Buscar(ArrayList<String> pasadas, String Id)
    {
        for(int x=0;x<pasadas.size();x++)
        {
            if(pasadas.get(x).equals(Id))
            {
                return false;
            }
        }
        return true;
    }
    public void GenerarPrimeros() throws Exception
    {
        
        ArrayList<String> v1;
        ArrayList<String> v2;
        ArrayList<String> utilizados = new ArrayList<String>();
        try{
            for(int x=0;x<productions.size();x++)
            {
                v1 = new ArrayList<String>();
                v2 = new ArrayList<String>();

                if(Buscar(utilizados,productions.get(x).id.id.lexema))
                {
                    v1.clear();
                    v2.clear();
                    ArrayList<String> arr = Primero(productions.get(x).id.id.lexema,v1,v2);
                    ListaPrimeros.put(productions.get(x).id.id.lexema, arr);
                }
            }

            ArrayList<String> Terminal = new ArrayList<String>();
            for(int x=0;x<Grammar.size();x++)
            {
                for (int xy = 0; xy < Grammar.get(x).lineas.size(); xy++)
                {
                    for(int y=0;y<Grammar.get(x).lineas.get(xy).terminos.size();y++)
                    {
                        Terminal.clear();
                        if(Grammar.get(x).lineas.get(xy).terminos.get(y) instanceof Terminal)
                        {
                            Terminal.add(Grammar.get(x).lineas.get(xy).terminos.get(y).id.lexema);
                            ListaPrimeros.put(Grammar.get(x).lineas.get(xy).terminos.get(y).id.lexema,Terminal);
                        }
                    }
                }
            }
            Terminal.clear();
            Terminal.add("$");
            ListaPrimeros.put("$",Terminal);
        }catch (Exception e){
          throw new Exception("Error Grammar->GenerarPrimeros(): " + e.getMessage());
       }   
    }
    public ArrayList<String> Primero(String Id, ArrayList<String> first, ArrayList<String> noTerminalesCalculados )
    {
        noTerminalesCalculados.add(Id);
        for(int x=0;x<Grammar.size();x++)
        {
            if(Grammar.get(x).id.id.lexema.equals(Id))
            {
                for (int xy = 0; xy < Grammar.get(x).lineas.size(); xy++){
                    if(Grammar.get(x).lineas.get(xy).terminos.get(0) instanceof noTerminal && !Grammar.get(x).lineas.get(xy).terminos.get(0).id.lexema.equals(Id))
                    {
                            if(Buscar(noTerminalesCalculados, Grammar.get(x).lineas.get(xy).terminos.get(0).id.lexema))
                            {
                                first = Primero(Grammar.get(x).lineas.get(xy).terminos.get(0).id.lexema,first,noTerminalesCalculados);
                            }
                    }else if(Grammar.get(x).lineas.get(xy).terminos.get(0) instanceof Terminal)
                    {
                        first.add(Grammar.get(x).lineas.get(xy).terminos.get(0).id.lexema);
                    }
                }
            }
        }
        return first;
    }
  /*  public ArrayList<EstadoProd> Cerradura(String Id, ArrayList<EstadoProd> cerradura,ArrayList<String> primero)
    {
       // Id = Id;
        for(int x=0;x<Grammar.size();x++)
        {
            if(Grammar.get(x).id.id.lexema.equals(Id))
            {
                EstadoProd nuevo_estado = new EstadoProd();
                nuevo_estado.prod = Grammar.get(x);
                nuevo_estado.primero = primero;
                cerradura.add(nuevo_estado);
            }
        }
        return cerradura;
    }*/
    
    public ArrayList<EstadoProd> ApCerradura(ArrayList<EstadoProd> I)
    {
        Calculados.clear();
        ArrayList<String> prim;
        
        for(int x=0;x<I.size();x++)
        {
//            for (int xy = 0; xy < I.get(x).prod.lineas.size(); xy++)
//            {
                if(I.get(x).prod.terminos.size()>I.get(x).punto)
                {
                    if(Buscar(Calculados, I.get(x).prod.terminos.get(I.get(x).punto).id.lexema))
                    {
                        for(int y=0;y<productions.size();y++)
                        {
                            String s1 = productions.get(y).id.id.lexema;
                            String s2 =I.get(x).prod.terminos.get(I.get(x).punto).id.lexema;
                           
                            if(s1.equals(s2) == true)
                            {
                                Calculados.add(productions.get(y).id.id.lexema);
                                EstadoProd nueva = new EstadoProd();
                                nueva.prod = productions.get(y);

                                if((I.get(x).punto+1) < I.get(x).prod.terminos.size())
                                {
                                    prim = ListaPrimeros.get(I.get(x).prod.terminos.get(I.get(x).punto+1).id.lexema);
                                    nueva.primero = prim;
                                    I.add(nueva);
                                }else
                                {
                                    nueva.primero = I.get(x).primero;
                                    I.add(nueva);
                                }
                            }else{}
                        }
                    }
                }
            //}
        }
        return I;        
    }
    public int EsIgual(ArrayList<EstadoProd> valor) throws Exception
    {
        for(int x=0;x<Estados.size();x++)
        {
            if(Estados.get(x).Comparar(valor))
            {
                return Estados.get(x).valor;
            }
        }
        return -1;
    }
    public int ProdIgual(Line p)
    {
        for(int x=0;x<Grammar.size();x++)
        {
            if(Grammar.get(x).id.id.lexema.equals(p.id.id.lexema) == true)
            {
                for (int xy = 0; xy < Grammar.get(x).lineas.size(); xy++)
                {
                    if(Grammar.get(x).lineas.get(xy).terminos.size() == p.terminos.size())
                    {
                        for(int y=0;y<Grammar.get(x).lineas.get(xy).terminos.size();x++)
                        {
                            if(Grammar.get(x).lineas.get(xy).terminos.get(y).id.lexema.compareTo(p.terminos.get(y).id.lexema) !=0)
                            {
                                return -1;
                            }
                        }
                        return x;
                    }
                }
            }
        }
        return -1;
    }
    public void IgualAutomata() throws Exception
    {
        try{
            ArrayList<LALR> temp = new ArrayList<LALR>();
            boolean encontrado = false;

            for(int x=0;x<varGlobal.Automata.size();x++)
            {
                for(int y =x+1;y<varGlobal.Automata.size();y++)
                {
                    if(varGlobal.Automata.get(x).inicio == varGlobal.Automata.get(y).inicio)
                    {
                        if(varGlobal.Automata.get(x).fin == varGlobal.Automata.get(y).fin)
                        {
                            encontrado = true;
                        }
                    }
                }
                if(!encontrado)
                {
                    temp.add(varGlobal.Automata.get(x));
                }
            }
            varGlobal.Automata = temp;
        }catch (Exception e){
          throw new Exception("Error Grammar->IgualAutomata(): " + e.getMessage());
       }  
    }
    public void CrearAutomata() throws Exception
    {
        try{
            Estados.clear();
            EstadoProd estadoInicial = new EstadoProd();
            int NumeroEstado = 0;

            estadoInicial.prod = productions.get(0);
            estadoInicial.punto = 0;
            ArrayList<EstadoProd> produccion = new ArrayList<EstadoProd>();
            produccion.add(estadoInicial);
            Estado q0 = new Estado();
            q0.valor = 0;
            produccion.get(0).primero.add("$");
            q0.Producciones = ApCerradura(produccion);
            Estados.add(q0);
            System.out.println("==== AUTOMATA ====");
            System.out.println();
            Estados.get(0).Print();


            for(int x=0;x<Estados.size();x++)
            {
                Estado In = new Estado();

                for(int y=0;y<Estados.get(x).Producciones.size();y++)
                {
                    LALR s = new LALR();
                    concatWith = "EMPTY";
                    produccion = Go_To(Estados.get(x).Producciones.get(y));
                    inicio = Estados.get(x).valor;
                    NumeroEstado = EsIgual(produccion);

                    if(NumeroEstado == -1)
                    {
                        In.Producciones = produccion;
                        fin = contador;
                        In.valor = contador++;
                        Estados.add(In);
                        s.inicio = inicio;
                        s.fin = fin;
                        s.simbolo = concatWith;
                        varGlobal.Automata.add(s);
                        In.Print();
                    }else
                    {
                        if(concatWith.compareTo("EMPTY") !=0)
                        {
                            s.inicio = inicio;
                            s.fin = NumeroEstado;
                            s.simbolo = concatWith;
                            varGlobal.Automata.add(s);
                        }
                    }

                }
            } 
            
        }catch (Exception e){
           throw new Exception("Error Grammar->CrearAutomata(): " + e.getMessage());
       }  
    }
   /* public ArrayList<EstadoProd> Go_To(ArrayList<EstadoProd> estado, Termino t) 
    {
        ArrayList<EstadoProd> temp = new ArrayList<EstadoProd>();
        for(EstadoProd est: estado)
        {
            if (est.punto != est.prod.terminos.size()) {
                    if (est.punto >=est.prod.terminos.size()) {
                        concatWith = "EMPTY";
                    } else {
                        EstadoProd e = est.createCopy();
                        e.punto ++;
                        temp.add(e);
                        concatWith = est.prod.terminos.get(est.punto-1).id.lexema;
                    }
                }
            
        }
        return ApCerradura(temp);
    }*/
    public ArrayList<EstadoProd> Go_To(EstadoProd estado)
    {
      ArrayList<EstadoProd> temp = new ArrayList<EstadoProd>();
//      for (int xy = 0; xy < estado.prod.lineas.size(); xy++)
//      {
        if(estado.punto!=estado.prod.terminos.size())
        {
           if(estado.punto>estado.prod.terminos.size())
           {
               concatWith = "EMPTY";
           }else
           {
               estado.punto = estado.punto+1;
                concatWith = estado.prod.terminos.get(estado.punto-1).id.lexema;
           }
        }
      //}
      temp.add(estado);
      return ApCerradura(temp);
    }
    public void Minimizar() throws Exception
    {
        try{
            ArrayList<Estado> temp;
            int EstadoporReductir,estadonuevo;

            for(int i=0;i<Estados.size();i++)
            {
                for(int j=i+1;j<Estados.size();j++)
                {
                    temp = new ArrayList<Estado>();
                    EstadoporReductir = Estados.get(i).Unir(Estados.get(j));
                    if(EstadoporReductir!=-1)
                    {
                        estadonuevo = (Estados.get(i).valor)+EstadoporReductir;
                        for(int x=0;x<varGlobal.Automata.size();x++)
                        {
                            if(varGlobal.Automata.get(x).inicio == Estados.get(i).valor)
                            {
                                varGlobal.Automata.get(x).inicio = estadonuevo;
                            }
                            if(varGlobal.Automata.get(x).fin == Estados.get(i).valor)
                            {
                                varGlobal.Automata.get(x).fin = estadonuevo;
                            }
                            if(varGlobal.Automata.get(x).inicio == EstadoporReductir)
                            {
                                varGlobal.Automata.get(x).inicio = estadonuevo;
                            }
                            if(varGlobal.Automata.get(x).fin == EstadoporReductir)
                            {
                                varGlobal.Automata.get(x).fin = estadonuevo;
                            }

                        }
                        Estados.get(i).valor = estadonuevo;
                        for(int n=0;n<Estados.size();n++)
                        {
                            temp.add(Estados.get(n));
                        }
                        Estados.clear();
                        Estados=temp;
                    }
                }
            }
            
            System.out.println("==== Automata Min. ====");
            for(int a=0;a<Estados.size();a++)
            {
                Estados.get(a).Print();
            }
            IgualAutomata();
            
            System.out.println("==== Transiciones ====");
            for(int a=0;a<varGlobal.Automata.size();a++)
            {
                System.out.println();
                System.out.println();
                varGlobal.Automata.get(a).Print();
            }
            System.out.println("---------------");
            
         }catch (Exception e){
           throw new Exception("Error Grammar->Minimizar(): " + e.getMessage());
       }  
    }
    public void GenerarTabla() throws Exception
    {
        try{
            termDef.terminales.add(new Terminal(new ID("$")));
            for(int x=2;x<Estados.size();x++)
            {
                if(Estados.get(x).Producciones.size() == 1)
                {
                    int where =  ProdIgual(Estados.get(x).Producciones.get(0).prod);
                    LALR l = new LALR();
                    l.fin = where;
                    l.inicio = Estados.get(x).valor;

                    for(int y=0;y<Estados.get(x).Producciones.get(x).primero.size();y++)
                    {
                        l.simbolo = Estados.get(x).Producciones.get(0).primero.get(y);
                        varGlobal.Reducciones.add(l);
                    }
                }
            }
            int state=0;
            String term="";
            int fin =0;
            for(int i=0;i<Estados.size();i++)
            {
                ArrayList<String> Est = new ArrayList<String>();

                for(int x=0;x<termDef.terminales.size();x++)
                {
                    term = termDef.terminales.get(i).id.lexema;
                    String r1,contenido="";
                    boolean found = false;

                    for(int y=0;y<varGlobal.Automata.size();y++)
                    {
                        if(varGlobal.Automata.get(y).simbolo.compareTo(term) == 0)
                        {
                            if(state==varGlobal.Automata.get(y).inicio)
                            {
                                fin = varGlobal.Automata.get(y).fin;
                                found = true;
                            }
                        }
                    }
                    if(found)
                    {
                        r1="d";
                        contenido=fin+"\n";
                        r1+=contenido;
                    }else
                    {
                        r1="-";
                    }
                    Est.add(r1);
                }
                varGlobal.Tabla.add(Est);
            }
         }catch (Exception e){
           throw new Exception("Error Grammar->GenerarTabla(): " + e.getMessage());
       }  
    }
  
}
