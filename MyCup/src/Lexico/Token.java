package Lexico;

/**
 *
 * @author Paulette
 */

public class Token {
    
 public enum TokenType
 {
      //KeyWords
      KW_IMPORT,KW_PARSE,KW_CODE,KW_TERMINAL,
      KW_NONTERMINAL,
      //Punctuation
      SIGN_DOSPUNTOS,SIGN_COMA,SIGN_PUNTOYCOMA,SIGN_PIPE,SIGN_PUNTO,
      //Cup 
      OPEN_BRACKET,CLOSE_BRACKET,PRODUCTION,JAVA_CODE,ID,
      
      EOFF
  
 }
    
  public String lexema;
  public TokenType Type;
  public int fila;
  public int columna;
  
 Token(){}
  
 Token(String lex, TokenType tip, int fila, int col)
 {
      lexema = lex;
      Type = tip;
      this.fila = fila;
      columna = col;  
 }
 
  public TokenType getTipo()
  {
      return Type;
  }
}
