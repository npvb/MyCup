package mycup;
import Lexico.Parser;
import ParserClasses.Cup;
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
          tasa.getGram().PrintProduction();
          tasa.getGram().GenerarPrimeros();
          tasa.getGram().CrearAutomata();
          //tasa.getGram().Minimizar();
          tasa.getGram().GenerarTabla();
          tasa.getGram().CrearArchivo();
          tasa.getGram().CrearSym();
         // ga.CrearArchivo(tasa.getGram());
       
       } catch (Exception ex) {
            throw new Exception(ex.toString());
       }
    }
}
