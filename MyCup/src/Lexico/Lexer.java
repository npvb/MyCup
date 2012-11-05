
package Lexico;
import Lexico.Token;
import Lexico.Token.TokenType;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashMap;

/**
 *
 * @author Paulette
 */
public class Lexer 
{
  public long posicion;
  public int fila;
  public long size;
  public int columna;
  public int estado;
  public String path;
  public File archivo;
  public char simbolo;
  public RandomAccessFile accessFile;
  HashMap<String, TokenType> MapOperadores = new HashMap<String, TokenType>();
  HashMap<String, TokenType> MapPalabrasReservadas=new HashMap<String, TokenType>();
  

 private void InitMaps()
 {
   MapOperadores.put(";", TokenType.SIGN_PUNTOYCOMA);
   MapOperadores.put(",", TokenType.SIGN_COMA);
   MapOperadores.put("|", TokenType.SIGN_PIPE);
   MapOperadores.put(":", TokenType.SIGN_DOSPUNTOS);
   MapOperadores.put("::=", TokenType.PRODUCTION);
   MapOperadores.put("{:",TokenType.OPEN_BRACKET);
   MapOperadores.put(":}",TokenType.CLOSE_BRACKET);
   MapOperadores.put(".",TokenType.SIGN_PUNTO);
   
   MapPalabrasReservadas.put("non terminal", TokenType.KW_NONTERMINAL);
   MapPalabrasReservadas.put("terminal", TokenType.KW_TERMINAL);
   MapPalabrasReservadas.put("parser",TokenType.KW_PARSE);
   MapPalabrasReservadas.put("code",TokenType.KW_CODE);  
   MapPalabrasReservadas.put("import",TokenType.KW_IMPORT);  
 }
 
 public Lexer(String file)
 {
     InitMaps();
     estado = 0;   
     posicion = 0;
     fila = 1;
     columna = 1;
     archivo=new File(file);
        try
        {
            accessFile=new RandomAccessFile(archivo,"r");
            size=accessFile.length();
        }
        catch(Exception e)
        {
            
        }
    }
    
 public char NextSymbol() throws Exception
 {
   try{
     if(posicion<size)
     {
         accessFile.seek(posicion);
         int sym=accessFile.read();
         simbolo=(char)sym;
         posicion++;
            
         if (simbolo == 10)
         {
           columna = 1;
           fila++;
         }
           return simbolo;
           
      }else{
         return '\0';
      }
    }catch(Exception e)
    {
            throw new Exception("NextSymbol->",e);
    }
 }
 
 public char CurrentSymbol() throws Exception
 {
   try{
     if(posicion<size)
     {
         accessFile.seek(posicion);
         int sym=accessFile.read();
         simbolo=(char)sym;
         
          return simbolo;
           
      }else{
         return '\0';
      }
    }catch(Exception e)
    {
            throw new Exception("CurrentSymbol->",e);
    }
 }

 public boolean isChar()
 {
     if(simbolo == ',' || simbolo == ';' || simbolo == '|')
     {
         return true;
     }else{
        return false;
    }
 }
 
 public Token NextToken() throws Exception
 {
     String lexema = "";
     String javaCode = "";
     
     while(true){
         simbolo = CurrentSymbol();
         switch(estado)
         {
             case 0:
                 if(simbolo == ' ' || simbolo == '\n' || simbolo == '\t' | simbolo == '\r')
                 {
                    estado = 0;
                    simbolo = NextSymbol();
                 }else if (Character.isLetter(simbolo))
                 {
                     estado = 1;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                 }else if(isChar())
                 {
                     estado = 2;
                     lexema+=simbolo;
                     //simbolo = NextSymbol();
                 }else if(simbolo == ':')
                 {
                     estado = 3;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                     
                 }else if(simbolo == '{')
                 {
                     estado = 5;
                     lexema+=simbolo;
                     simbolo = NextSymbol();     
                 }else if (simbolo == '\0')
                 {
                    return new Token("EOFF",TokenType.EOFF,fila,columna); 
                 }else
                 {   String mensaje = "Caracter Invalido: "+simbolo+"\n Fila: "+fila+" Columna: "+columna;
                     throw new Exception(mensaje.toString());
                 }
               break;  
                 
                 
             case 1:
                 if(Character.isLetter(simbolo) || simbolo == '_' || simbolo == '*' || simbolo == '.')
                 {
                     estado = 1;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                     
                 }else if(lexema.compareTo("non")==0)
                 {
                     estado = 0;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                 }else if(MapPalabrasReservadas.containsKey(lexema))
                 {
                     estado = 0;
                     return new Token(lexema,MapPalabrasReservadas.get(lexema),fila,columna);
                 }else
                 {
                     estado = 0;
                     return new Token(lexema,TokenType.ID,fila,columna);
                 }
               break;
                 
             case 2:
                 if(simbolo=='.')
                 {
                     estado = 0;
                     simbolo = NextSymbol();
                     return new Token(lexema,TokenType.SIGN_PUNTO,fila,columna);
                 }else if(simbolo == '|')
                 {
                     estado = 0;
                     simbolo = NextSymbol();
                     return new Token(lexema,TokenType.SIGN_PIPE,fila,columna);
                 }else if (simbolo == ';')
                 {
                     estado = 0;
                     simbolo = NextSymbol();
                     return new Token(lexema,TokenType.SIGN_PUNTOYCOMA,fila,columna);
                 }else if(simbolo == ',')
                 {
                     estado = 0;
                     simbolo = NextSymbol();
                     return new Token(lexema,TokenType.SIGN_COMA,fila,columna);
                 }
                 break;
              
             case 3:
                 if(simbolo == ':')
                 {
                     estado = 4;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                 }else
                 {
                    estado = 0;
                    //simbolo = NextSymbol();
                    return new Token(lexema,TokenType.SIGN_DOSPUNTOS,fila,columna);
                 }
                 break;
                        
             case 4:
                 if(simbolo == '=')
                 {
                     estado = 0;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                     return new Token(lexema,TokenType.PRODUCTION,fila,columna);
                 }else
                     throw new Exception("Se Esperaba = para crear secuencia ::= ");
                 
             case 5:
                 if (simbolo == ':')
                 {
                     estado = 6;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                     return new Token(lexema,TokenType.OPEN_BRACKET,fila,columna);
                 }else
                     throw new Exception("Se Esperaba : para crear secuencia {: ");
                              
             case 6:
                  //simbolo = NextSymbol();
                  if(simbolo == ':')
                  {
                      estado = 7;
                      lexema+=simbolo;
                      simbolo = NextSymbol();
                  }else
                  {
                      estado = 6;
                      javaCode+=simbolo;
                      simbolo = NextSymbol();
                  }
              break;                
                 
             case 7:
                 if(simbolo == '}')
                 {
                     estado = 8;
                     lexema+=simbolo;
                     simbolo = NextSymbol();
                 }else
                 {
                     estado = 6;
                     javaCode+=simbolo;
                 }
                 break;
                 
             case 8:
                 estado = 9;
                 return new Token(javaCode,TokenType.JAVA_CODE,fila,columna);
                 
             case 9:
                 estado = 0; 
                 return new Token(":}",TokenType.CLOSE_BRACKET,fila,columna);
         }
     }
 }
 
  
  
}//close
