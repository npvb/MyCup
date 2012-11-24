package mycup;
import GenerarArchivo.GenerarArchivoJava;
import Lexico.Lexer;
import Lexico.Parser;
import Lexico.Token;
import Lexico.Token.TokenType;
import ParserClasses.*;
import java.io.IOException;
/**
 *
 * @author Paulette
 */
public class MyCup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception 
    {
      try {
        
        /*Lexer lex = new Lexer("ejemplo.cup");
        lex.simbolo = lex.CurrentSymbol();
        Token token = lex.NextToken();
       
        while(token.getTipo()!=TokenType.EOFF)
        {
            System.out.print("Lexema: "+token.lexema);
            System.out.print(" Tipo: "+token.Type+"\n");
            token = lex.NextToken();
        }*/
         Parser parser = new Parser("ejemplo.cup");
         // Parser parser = new Parser("et.txt");
          Cup tasa = parser.cup();
          GenerarArchivoJava ga = new GenerarArchivoJava();
          
          tasa.getGram().ExtenderGramatica();
          tasa.getGram().PrintProduction();
          tasa.getGram().GenerarPrimeros();
          tasa.getGram().CrearAutomata();
         /* tasa.getGram().Minimizar();
          tasa.getGram().GenerarTabla();
          ga.CrearArchivo();*/
       
       } catch (Exception ex) {
            throw new Exception(ex.toString());
       }
    }
}
