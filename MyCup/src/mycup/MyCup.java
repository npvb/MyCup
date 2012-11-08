package mycup;
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
          Cup tasa = parser.cup();
          tasa.getGram().ExtenderGramatica();
          tasa.getGram().Print();
          tasa.getGram().GenerarPrimeros();
          tasa.getGram().CrearAutomata();
          
       
       } catch (Exception ex) {
            throw new Exception(ex.toString());
       }
    }
}
