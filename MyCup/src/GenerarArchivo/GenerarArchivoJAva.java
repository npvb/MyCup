
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
       // Grammar gm = new Grammar();
        VariablesGlobales varGlob = new VariablesGlobales();
               
        File file = new File("C:\\Users\\Paulette\\MyCup\\MyCup\\MyParser.java");
       
        if (!file.exists()) 
        {
            file.createNewFile();
        }
        contenido+= "\n\n\nimport Clases.*;\n\n\n public class MyParser{\n";
        contenido+= "Tabla t = new Tabla();\nMyParser(){\n";
	contenido+="    public MyParser() {\n";
        
        
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
                 contenido+="		t.addSimbolo(new No Terminal(\""+ gm.getNonTermDef().get(x).getNoTerminales().get(y).id.lexema +"\"));\n";
            }
        }
        contenido+="		t.addAccion(new Acciones(new Estado(\"0\"), new Simbolo(\"$\"), new Aceptacion(\"1\")));\n";
        
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
            c1+=varGlob.Automata.get(x).inicio;
            c2+=varGlob.Automata.get(x).fin;
            
            contenido = "		t.addAccion(new Acciones(new Estado(\""+ c1 +"\"), new Simbolo(\""+ varGlob.Automata.get(x).simbolo +"\"), new Reducir(\""+c2+"\")));\n";
        }
        
        contenido+="     }\n";
        contenido+="}";
        
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
