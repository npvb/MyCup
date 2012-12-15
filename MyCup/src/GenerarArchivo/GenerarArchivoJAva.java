
package GenerarArchivo;

import LALR.VariablesGlobales;
import ParserClasses.Grammar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Paulette
 */
public class GenerarArchivoJava{

    public GenerarArchivoJava() {
    }
    
    public void CrearArchivo(Grammar gm) throws Exception
    {
      
     try{
         
        String contenido = "";
        VariablesGlobales varGlob = new VariablesGlobales();
               
        File file = new File("C:\\Users\\Paulette\\MyCup\\MyCup\\src\\MyParser.java");
       
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
        
        
        for(int x=0;x<gm.getEstados().size();x++)
        {
            String estados ="";
            estados+=gm.getEstados().get(x).valor;
            contenido+="		t.addEstado(new Estado(\""+ estados +"\"));\n";
        }
        for(int x=0;x<gm.getTermDef().getTerminales().size();x++)
        {
            contenido+="		t.addSimbolo(new Terminal(\""+ gm.getTermDef().getTerminales().get(x).id.lexema +"\"));\n";
        }
        for(int x=0;x<gm.nonTermDef.size();x++)
        {
            for(int y=0;y<gm.nonTermDef.get(x).getNoTerminales().size();y++){
                 contenido+="		t.addSimbolo(new NoTerminal(\""+ gm.getNonTermDef().get(x).getNoTerminales().get(y).id.lexema +"\"));\n";
            }
        }
        contenido+="		t.CrearTabla();\n";
	contenido+="		t.addAccion(new Acciones(new Estado(\"I1\"), new Simbolo(\"$\"), new Aceptacion(\"I1\")));\n";
        
        for(int x=0;x<varGlob.Automata.size();x++)
        {
            boolean found = false;
            String c1="",c2="";
            for(int y=0;y<gm.getTermDef().getTerminales().size();y++)
            {
                if(gm.getTermDef().getTerminales().get(x).id.lexema.compareTo(varGlob.Automata.get(x).simbolo)==0)
                {
                    found = true;
                }
            }
            c1+=varGlob.Automata.get(x).inicio;
            c2+=varGlob.Automata.get(x).fin;
            
           if(found){
            contenido+="		t.addAccion(new Acciones(new Estado(\""+ c1 +"\"), new Simbolo(\""+ varGlob.Automata.get(x).simbolo +"\"), new Desplazar(\""+c2+"\")));\n";
	   }else{
	    contenido+="		t.addAccion(new Acciones(new Estado(\""+ c1 +"\"), new Simbolo(\""+ varGlob.Automata.get(x).simbolo+"\"), new IrA(\""+c2+"\")));\n";
           }
        }
        
        for(int x=0;x<varGlob.Reducciones.size();x++)
        {
            String c1="",c2="";
            int pos;
            c1+=varGlob.Automata.get(x).fin;
            pos = varGlob.Automata.get(x).fin;
            pos = gm.Grammar.get(pos).lineas.get(pos).terminos.size();
            c2+=pos;
            
            contenido = "		t.addAccion(new Acciones(new Estado(\""+ varGlob.Reducciones.get(x).InicialS +"\"), new Simbolo(\""+ varGlob.Reducciones.get(x).simbolo +"\"), new Reducir(\""+ c1 +"\","+ c2 +")));\n";
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
        
        for(int i=0;i<gm.Grammar.size();i++){
                contenido+="		Producciones.add(\""+ gm.Grammar.get(i).id.id.getLexema() +"\");\n";
            
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
