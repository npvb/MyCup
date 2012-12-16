
package ParserClasses;
import LALR.LALR;
import LALR.VariablesGlobales;
import Lexico.Parser;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
//        Estado e = new Estado();
//        e.Producciones = I;
//        return e;       
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
        for(int x=0;x<productions.size();x++)
        {
            if(productions.get(x).id.id.lexema.equals(p.id.id.lexema) == true)
            {
//                for (int xy = 0; xy < Grammar.get(x).lineas.size(); xy++)
//                {
                    if(productions.get(x).terminos.size() == p.terminos.size())
                    {
                       // if(productions.get(x).terminos.size() == p.terminos.size())
                       // {
                            for(int y=0;y<productions.get(x).terminos.size();y++)
                            {
                                if(productions.get(x).terminos.get(y).id.lexema.compareTo(p.terminos.get(y).id.lexema) !=0)
                                {
                                    return -1;
                                }
                            }
                        //}
                        return x;
                    }
               // }
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
    
    public void numerarProducciones()
    {
        for(int i = 0; i< productions.size();i++)
        {
            productions.get(i).setIndex(i);
        }
    }
    public void CrearAutomata() throws Exception
    {
        numerarProducciones();
        String cadena = "";
        String fin="";
        try{
            
            ArrayList<Termino> f =  new ArrayList<Termino>();
            
            for(noTerminalesDef t : nonTermDef )
            {
                for ( noTerminal s : t.noTerminales)
                {
                    f.add(s);   
                }
            }
            f.addAll(termDef.terminales);
            Estados.clear();
            EstadoProd estadoInicial = new EstadoProd();
            int NumeroEstado = 0;

            estadoInicial.prod = productions.get(0);
            estadoInicial.punto = 0;
            ArrayList<EstadoProd> produccion = new ArrayList<EstadoProd>();
            produccion.add(estadoInicial);
            Estado q0 = new Estado();
            q0.nombreEstado = "Q0";
            q0.valor = 0;
            produccion.get(0).primero.add("$");
            q0.Producciones = ApCerradura(produccion);
            Estados.add(q0);
            System.out.println("==== AUTOMATA ====");
            System.out.println();
            Estados.get(0).Print();


            
            
            for(int x=0;x<Estados.size();x++)
            {
                for(int y=0; y< f.size();y++)
                {
                    LALR s = new LALR();
                    concatWith = "EMPTY";
                    Estado In = new Estado();
                    In = Go_To(Estados.get(x),f.get(y));
                    if ( In.Producciones.isEmpty())
                        continue;
                    if ( !Buscar(Estados,In))
                    {
                       
                            In.nombreEstado = "Q" + Estados.size();
                            In.valor = Estados.size();
                            Estados.add(In);
                            cadena+=Estados.get(x).nombreEstado;
                            s.inicio = Estados.get(x).valor;
                            s.fin = Estados.size();
                            s.simbolo = concatWith;
                            //s.InicialS="I"+cadena;
                            varGlobal.Automata.add(s);
                            In.Print();
                         
                    }
                    
                    
                    
                    //??inicio = Estados.get(x).valor;
                    //??NumeroEstado = EsIgual(produccion);

//                    if(NumeroEstado == -1)
//                    {
//                        In.Producciones = produccion;
//                        fin = contador;
//                        In.valor = contador++;
//                        Estados.add(In);
//                        In.nombreEstado = "Q"+In.valor;
//                        s.inicio = inicio;
//                        s.fin = fin;
//                        s.simbolo = concatWith;
//                        s.InicialS = "I"+Estados.get(x).valor;
//                        s.FinalS = "I"+fin;
//                        varGlobal.Automata.add(s);
//                        In.Print();
//                    }else
//                    {
//                        if(concatWith.compareTo("EMPTY") !=0)
//                        {
//                            s.inicio = inicio;
//                            s.fin = NumeroEstado;
//                            s.InicialS = "I"+inicio;
//                            s.FinalS = "I"+NumeroEstado;
//                            s.simbolo = concatWith;
//                            varGlobal.Automata.add(s);
//                        }
//                    }

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
    public Estado Go_To(Estado estado, Termino term)
    {
        Estado est = new Estado();
        
        for(int i = 0; i< estado.Producciones.size();i++)
        {
            if(estado.Producciones.get(i).punto!= estado.Producciones.get(i).prod.terminos.size())
            {
                if(estado.Producciones.get(i).punto>estado.Producciones.get(i).prod.terminos.size())
                {
                    concatWith = "EMPTY";
                }
                else
                {
                    if(estado.Producciones.get(i).prod.terminos.get(estado.Producciones.get(i).punto).id.lexema.equals(term.id.lexema))
                    {
                        EstadoProd p = estado.Producciones.get(i).createCopy();
                        p.punto ++;
                        est.Producciones.add(p);
                        if ( p.punto != p.prod.terminos.size())
                        concatWith = p.prod.terminos.get(p.punto).id.lexema;

                    }             
                }
            }
        }
       est.Producciones = ApCerradura(est.Producciones);
       return est;
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
    
    public int getGoto(Estado e, Termino t)
    {
        for(LALR l: varGlobal.Automata)
        {
            if(l.inicio == e.valor && l.simbolo.equals(t.id.lexema))
                return l.fin;
            
        }
        
        return -1;
    }
    
   public String getAutomata(int donde){
	for(int i=0;i<varGlobal.Automata.size();i++)
        {
            if(varGlobal.Automata.get(i).inicio == donde)
		return varGlobal.Automata.get(i).InicialS;
		else if(varGlobal.Automata.get(i).fin == donde)
		return varGlobal.Automata.get(i).FinalS;
	}
		return "";
    }
    
   public void GenerarTabla() throws Exception
   {
        try{
          
            for(int x=0;x<termDef.terminales.size();x++)
            {
                varGlobal.listTerm.add(termDef.terminales.get(x).id.lexema);
            }
            
            ArrayList<EstadoProd> produccion = new ArrayList<EstadoProd>();
            int NumEstado = 0;
            int cont = -1;
            int e;
            int fila;
            int columna;
            boolean entro = false;
            String content;
            String term = "";
            String temp;
            String fin = "";
            
            varGlobal.listTerm.add("$");
            boolean found = true;
            
            for(int x=2;x<Estados.size();x++)
            {
                cont = 0;
                found = false;
                while(cont<Estados.get(x).Producciones.size())
                {
                    found = false;
                    if(Estados.get(x).Producciones.get(cont).punto == Estados.get(x).Producciones.get(cont).prod.terminos.size())//¿¿SIMBOLOS??
                    {
                        found = true;
                    }
                    
                    if(found)
                    {
                        int where = ProdIgual(Estados.get(x).Producciones.get(cont).prod);
                        LALR la = new LALR();
                        
                        if(where!=-1)
                        {
                            la.fin = where;
                            la.inicio = Estados.get(x).valor;
                            la.InicialS = Estados.get(x).nombreEstado;
                            la.FinalS = getAutomata(where);
                            
                            for(int y=0;y<Estados.get(x).Producciones.get(cont).primero.size();y++)
                            {
                                String simbolo = Estados.get(x).Producciones.get(cont).primero.get(y);
                                la.simbolo = simbolo;
                                varGlobal.Reducciones.add(la);
                            }
                        }
                    }
                    cont++;
                }
            }
            for(int i=0;i<Estados.size();i++)
            {
                ArrayList<String> Est = new ArrayList<String>();
                e = Estados.get(i).valor;
                boolean encontrado = false;
                for(int j = 0;j<varGlobal.listTerm.size();j++)
                {
                    term = varGlobal.listTerm.get(j);
                    String r ="";
                    String r2="";
                    encontrado = false;
                    for(int x=0;x<varGlobal.Automata.size();x++)
                    {
                        if(varGlobal.Automata.get(x).simbolo.compareTo(term)==0)
                        {
                            if(e == varGlobal.Automata.get(x).inicio)
                            {
                                fin = varGlobal.Automata.get(x).FinalS;
                                encontrado = true;
                            }
                        }
                    }
                    if(encontrado)
                    {
                        r+="d"+fin;
                    }else
                    {
                        r = "-";
                    }
                    Est.add(r);
                    
                }
                Tabla.add(Est);
                
            }
            
           /* for(int i=0;i<varGlobal.Reducciones.size();i++)
            {
                columna = -1;
                fila = -1;
                for(int j=0;j<Estados.size();j++)
                {
                    if(Estados.get(j).nombreEstado.compareTo(varGlobal.Reducciones.get(i).InicialS)==0)
                    {
                        fila =j;
                    }
                }
                for(int j=0;j<varGlobal.listTerm.size();j++)
                {
                     if(varGlobal.listTerm.get(j).compareTo(varGlobal.Reducciones.get(i).simbolo)==0)
                     {
                         columna = j;
                     }
                }
                temp = "r"+varGlobal.Reducciones.get(i).fin;
                if(varGlobal.Tabla.get(fila).get(columna).compareTo("-")!=0 && Tabla.get(fila).get(columna).compareTo(term)!=0)
                {
                    throw new Exception("Error Grammar->GenerarTabla(): Se encontro un Error confusion entre desplazar y reducir");
                }else
                {
                  String valor ="";
                  valor+="r"+varGlobal.Reducciones.get(i).FinalS;
                  //Tabla.get(fila).get(columna)=valor;
                }
            }*/
          
         }catch (Exception e){
           throw new Exception("Error Grammar->GenerarTabla(): " + e.getMessage());
       }  
    }
   
   private boolean Buscar(ArrayList<Estado> Estados, Estado In) {
        boolean b = false;
        for(Estado E : Estados)
        {
            if ( E.Comparar(In))
                b = true;
        }
        return b;
    }
  
   public void CrearArchivo() throws Exception
   {  
     try{
         
        String contenido = "";
                      
        File file = new File("C:\\Users\\Paulette\\MyCup\\MyCup-Generated\\src\\MyParser.java");
       
        if (!file.exists()) 
        {
            file.createNewFile();
        }
        
        contenido+= "\n\nimport java.util.HashMap;\nimport Clases.*;\nimport java.io.*;\nimport java.util.ArrayList;\n public class MyParser {\n";
        contenido+= "    Tabla t = new Tabla();\n";
	contenido+= "    Yylex lexico;\n";
	contenido+= "    ArrayList<Integer> Entradas;\n";
	contenido+= "    ArrayList<String> Producciones;\n";
	contenido+= "    HashMap<Integer, String> Hash;\n\n";
	contenido+= "    public MyParser(Yylex jf) throws Exception{\n";
	contenido+= "                lexico = jf;\n"; 
	contenido+= "                Entradas = new ArrayList<Integer>();\n";      
	contenido+= "                Producciones = new ArrayList<String>();\n";
	contenido+= "                Hash = new HashMap<Integer, String>();\n";
        
        
        for(int x=0;x<getEstados().size();x++)
        {
            String estados ="";
            estados+=getEstados().get(x).valor;
            contenido+="		t.addEstado(new Estado(\""+ estados +"\"));\n";
        }
        for(int x=0;x<getTermDef().getTerminales().size();x++)
        {
            contenido+="		t.addSimbolo(new Terminal(\""+ getTermDef().getTerminales().get(x).id.lexema +"\"));\n";
        }
        for(int x=0;x<nonTermDef.size();x++)
        {
            for(int y=0;y<nonTermDef.get(x).getNoTerminales().size();y++){
                 contenido+="		t.addSimbolo(new NoTerminal(\""+ getNonTermDef().get(x).getNoTerminales().get(y).id.lexema +"\"));\n";
            }
        }
        contenido+="		t.CrearTabla();\n";
	contenido+="		t.addAccion(new Acciones(new Estado(\"I1\"), new Simbolo(\"$\"), new Aceptacion(\"I1\")));\n";
        
        for(int x=0;x<varGlobal.Automata.size();x++)
        {
            boolean found = false;
            String c1="",c2="";
            for(int y=0;y<varGlobal.listTerm.size();y++)
            {
                if(varGlobal.listTerm.get(y).compareTo(varGlobal.Automata.get(x).simbolo)==0)
                {
                    found = true;
                }
            }
            c1+=varGlobal.Automata.get(x).inicio;
            c2+=varGlobal.Automata.get(x).fin;
            
           if(found){
            contenido+="		t.addAccion(new Acciones(new Estado(\""+ c1 +"\"), new Simbolo(\""+ varGlobal.Automata.get(x).simbolo +"\"), new Desplazar(\""+c2+"\")));\n";
	   }else{
	    contenido+="		t.addAccion(new Acciones(new Estado(\""+ c1 +"\"), new Simbolo(\""+ varGlobal.Automata.get(x).simbolo+"\"), new IrA(\""+c2+"\")));\n";
           }
        }
        
        for(int x=0;x<varGlobal.Reducciones.size();x++)
        {
            String c1="",c2="";
            int pos;
            c1+=varGlobal.Automata.get(x).fin;
            c2+= varGlobal.Automata.get(x).inicio;
            pos = varGlobal.Reducciones.get(x).fin;
           // pos = Grammar.get(x).lineas.get(pos).terminos.size();
            c2+=pos;
            
            contenido = "		t.addAccion(new Acciones(new Estado(\""+ varGlobal.Reducciones.get(x).InicialS +"\"), new Simbolo(\""+ varGlobal.Reducciones.get(x).simbolo +"\"), new Reducir(\""+ c1 +"\","+ c2 +")));\n";
        }
        
        contenido+="     }\n";
	contenido+="	public void Parse() throws IOException{\n";
	contenido+="		Yytoken n = lexico.yylex();\n";
	contenido+="		int i=0;\n";
	contenido+="		while(n!=null){\n";
	contenido+="			i=n.m_index;\n";
	contenido+="			Entradas.add(i);\n";
	contenido+="            if(n!=null)\n";
        contenido+="                    Hash.put(i, n.m_lexema);\n";
		
	contenido+="			n = lexico.yylex();\n";
	contenido+="		}\n";
        
        for(int i=0;i<Grammar.size();i++){
                contenido+="		Producciones.add(\""+ Grammar.get(i).id.id.getLexema() +"\");\n";
            
        }
        
	contenido+="            Stack pila = new Stack(Entradas, Producciones, Hash, t);\n";
        contenido+="            System.out.println(pila.Accepted());\n";
	contenido+="	}\n";
	contenido+="}\n";
        
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(contenido);
        bw.close();

        System.out.println("MyParser.java Creado Exitosamente");

       }catch (Exception e){
             throw  new Exception("Error Grammar->CrearArchivo(): " + e.getMessage());
       }  

    }
    
}
