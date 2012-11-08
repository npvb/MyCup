
package ParserClasses;
import LALR.LALR;
import LALR.VariablesGlobales;
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
    
    public Grammar() {
        Estados.clear();
        contador = 1;
    }
     public Grammar(TerminalesDef termDef, ArrayList<noTerminalesDef> nonTermDef, ArrayList<Produccion> producciones) 
     {
        this.termDef = termDef;
        this.nonTermDef = nonTermDef;
        this.Grammar = producciones;
    }
    public ArrayList<noTerminalesDef> getNonTermDef() {
        return nonTermDef;
    }

    public void setNonTermDef(ArrayList<noTerminalesDef> nonTermDef) {
        this.nonTermDef = nonTermDef;
    }

    public ArrayList<Produccion> getProducciones() {
        return Grammar;
    }

    public void setProducciones(ArrayList<Produccion> producciones) {
        this.Grammar = producciones;
    }

    public TerminalesDef getTermDef() {
        return termDef;
    }

    public void setTermDef(TerminalesDef termDef) {
        this.termDef = termDef;
    }
    public void Print()
    {
        System.out.println("==== PRODUCCIONES ====");
        for(int x=0;x<Grammar.size();x++)
        {
            Grammar.get(x).PrintP();
        }
        System.out.println();
        System.out.println("---------------");
    }
    
    public void ExtenderGramatica()
    {
        Estados.clear();
        contador = 1;
        termDef.Print();
        for(int x=0;x<nonTermDef.size();x++)
        {
            nonTermDef.get(x).Print();
        }
        
        Produccion produccion = new Produccion();
        produccion.id = new noTerminal(new ID("Inicial"), " ");
        ArrayList<Termino> simbolo = new ArrayList<Termino>();
        simbolo.add(Grammar.get(0).id);
        //produccion.lineas.get(0).setTerminos(simbolo);
        ArrayList<Produccion> temp = new ArrayList<Produccion>();
        temp.add(produccion);
        
        for(int x=0;x<Grammar.size();x++)
        {
            temp.add(Grammar.get(x));
        }
        
        Grammar.equals(temp);
                
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
    public void GenerarPrimeros()
    {
        ArrayList<String> v1;
        ArrayList<String> v2;
        ArrayList<String> utilizados = new ArrayList<String>();
        
        for(int x=0;x<Grammar.size();x++)
        {
            v1 = new ArrayList<String>();
            v2 = new ArrayList<String>();
                      
            if(Buscar(utilizados,Grammar.get(x).id.id.lexema))
            {
                v1.clear();
                v2.clear();
                ArrayList<String> arr = Primero(Grammar.get(x).id.id.lexema,v1,v2);
                ListaPrimeros.put(Grammar.get(x).id.id.lexema, arr);
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
    public ArrayList<EstadoProd> Cerradura(String Id, ArrayList<EstadoProd> cerradura,ArrayList<String> primero)
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
    }
    public ArrayList<EstadoProd> ApCerradura(ArrayList<EstadoProd> I)
    {
        Calculados.clear();
        ArrayList<String> prim;
        
        for(int x=0;x<I.size();x++)
        {
            for (int xy = 0; xy < I.get(x).prod.lineas.size(); xy++)
            {
                if(I.get(x).prod.lineas.get(xy).terminos.size()>I.get(x).punto)
                {
                    if(Buscar(Calculados, I.get(x).prod.lineas.get(xy).terminos.get(I.get(x).punto).id.lexema))
                    {
                        for(int y=0;y<Grammar.size();y++)
                        {
                            String s1 = Grammar.get(y).id.id.lexema;
                            String s2 =I.get(x).prod.lineas.get(xy).terminos.get(I.get(x).punto).id.lexema;
                           
                            if(s1.equals(s2) == true)
                            {
                                Calculados.add(Grammar.get(y).id.id.lexema);
                                EstadoProd nueva = new EstadoProd();
                                nueva.prod = Grammar.get(y);

                                if((I.get(x).punto+1) < I.get(x).prod.lineas.get(xy).terminos.size())
                                {
                                    prim = ListaPrimeros.get(I.get(x).prod.lineas.get(xy).terminos.get(I.get(x).punto+1).id.lexema);
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
            }
        }
        return I;        
    }
    public int EsIgual(ArrayList<EstadoProd> valor)
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
    public int ProdIgual(Produccion p)
    {
        for(int x=0;x<Grammar.size();x++)
        {
            if(Grammar.get(x).id.id.lexema.equals(p.id.id.lexema) == true)
            {
                for (int xy = 0; xy < Grammar.get(x).lineas.size(); xy++)
                {
                    if(Grammar.get(x).lineas.get(xy).terminos.size() == p.lineas.get(xy).terminos.size())
                    {
                        for(int y=0;y<Grammar.get(x).lineas.get(xy).terminos.size();x++)
                        {
                            if(Grammar.get(x).lineas.get(xy).terminos.get(y).id.lexema.equals(p.lineas.get(xy).terminos.get(y).id.lexema) == false)
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
    public void IgualAutomata()
    {
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
    }
    public void CrearAutomata()
    {
        Estados.clear();
        EstadoProd estadoInicial = new EstadoProd();
        int NumeroEstado = 0;
        
        estadoInicial.prod = Grammar.get(0);
        estadoInicial.punto = 0;
        ArrayList<EstadoProd> produccion = new ArrayList<EstadoProd>();
        produccion.add(estadoInicial);
        Estado q0 = new Estado();
        q0.valor = 0;
        produccion.get(0).primero.add("$");
        q0.Producciones = ApCerradura(produccion);
        Estados.add(q0);
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
        
    }
    public ArrayList<EstadoProd> Go_To(EstadoProd estado)
    {
      ArrayList<EstadoProd> temp = new ArrayList<EstadoProd>();
      for (int xy = 0; xy < estado.prod.lineas.size(); xy++)
      {
        if(estado.punto!=estado.prod.lineas.get(xy).terminos.size())
        {
           if(estado.punto>estado.prod.lineas.get(xy).terminos.size())
           {
               concatWith = "EMPTY";
           }else
           {
               estado.punto = estado.punto+1;
                concatWith = estado.prod.lineas.get(xy).terminos.get(estado.punto-1).id.lexema;
           }
        }
      }
      temp.add(estado);
      return ApCerradura(temp);
    }
     public void Minimizar()
    {
        ArrayList<Estado> temp = new ArrayList<Estado>();
        int EstadoporReductir,inicio,fin,estadonuevo;
        
        for(int x=0;x<Estados.size();x++)
        {
            for(int y=x+1;y<Estados.size();y++)
            {
                temp.clear();
                EstadoporReductir = Estados.get(x).Unir(Estados.get(y));
                if(EstadoporReductir!=-1)
                {
                    estadonuevo = (Estados.get(x).valor*10)+EstadoporReductir;
                    for(int i=0;i<varGlobal.Automata.size();i++)
                    {
                        if(varGlobal.Automata.get(i).inicio == Estados.get(i).valor)
                        {
                            varGlobal.Automata.get(i).inicio = estadonuevo;
                        }
                        if(varGlobal.Automata.get(i).fin == Estados.get(i).valor)
                        {
                            varGlobal.Automata.get(i).fin = estadonuevo;
                        }
                        if(varGlobal.Automata.get(i).inicio == EstadoporReductir)
                        {
                            varGlobal.Automata.get(i).inicio = estadonuevo;
                        }
                        if(varGlobal.Automata.get(i).fin == EstadoporReductir)
                        {
                            varGlobal.Automata.get(i).fin = estadonuevo;
                        }
                        
                    }
                    Estados.get(x).valor = estadonuevo;
                    for(int n=0;n<Estados.size();n++)
                    {
                        temp.add(Estados.get(n));
                    }
                    Estados.clear();
                    Estados=temp;
                }
            }
        }
        for(int a=0;a<Estados.size();a++)
        {
            System.out.println();
            System.out.println();
            Estados.get(a).Print();
        }
        IgualAutomata();
        for(int a=0;a<varGlobal.Automata.size();a++)
        {
            System.out.println();
            System.out.println();
            varGlobal.Automata.get(a).Print();
        }
    }
}
