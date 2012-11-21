
package GenerarArchivo;

import LALR.VariablesGlobales;
import ParserClasses.Grammar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Paulette
 */
public class GenerarArchivo {

    public GenerarArchivo() {
    }
    
    public void CrearArchivo() throws IOException
    {
      
     try{
         
        String contenido = "";
        Grammar gm = new Grammar();
               
        File file = new File("src\\mycup\\MyParser.java");
       
        if (!file.exists()) 
        {
            file.createNewFile();
        }
        contenido = "\n\n\nimport Clases.*;\n\n\n public class MyParser{\n";
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
            contenido+="		t.addSimbolo(new Terminal(\""+ gm.getTermDef().getTerminales().get(x) +"\"));\n";
        }
        for(int x=0;x<gm.getNonTermDef().size();x++)
        {
            contenido+="		t.addSimbolo(new No Terminal(\""+ gm.getNonTermDef().get(x) +"\"));\n";
        }
         FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(contenido);
         bw.close();

         System.out.println("MyParser.java Creado Exitosamente");

       }catch (Exception e){
             System.err.println("Error Grammar->CrearArchivo(): " + e.getMessage());
       }  

    }
    
}
