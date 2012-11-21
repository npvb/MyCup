package Lexico;

import ParserClasses.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author NEKO
 */
public class Parser {
    
    private Lexer lex;
    private Token currentToken;
    private boolean printBool;
     ArrayList<Terminal> terminales = new ArrayList<Terminal>();
     ArrayList<noTerminal> noTerminales = new ArrayList<noTerminal>();
     HashMap<String, Terminal> terminalesMap = new HashMap<String, Terminal>();
     HashMap<String, noTerminal> noTerminalesMap = new HashMap<String, noTerminal>();
     
    public Parser() {
        
    }
    
    
      public Parser(String path) throws Exception {
        lex = new Lexer(path);
      nextToken();



    }
      
      public Boolean isTerminal(String lexema)
      {
          return terminalesMap.containsKey(lexema);
          
      }
      
        public Boolean isNonTerminal(String lexema)
      {
         return noTerminalesMap.containsKey(lexema);
          
      }
              
      
    public Token getCurrentToken() {
        return currentToken;
    }
       
       
    private void nextToken() throws Exception {
        currentToken = lex.NextToken();
    }

    private boolean checkToken(Token.TokenType tipo) {
        return (currentToken.Type == tipo);
    }

    private void matchToken(Token.TokenType tipo) throws Exception {
        
        if (currentToken.Type != tipo) {
            throw new Exception("Error de compilación, se esperaba Token: " + tipo.toString() + " en línea: " + currentToken.fila + " columna: " + currentToken.columna + " Y se obtuvo " + currentToken.Type);
        }
      
        nextToken();
    }
    
    
    public Cup cup() throws Exception {
       // Program p = Program.getInstancia();
       Cup c = new Cup();
       Header head = header();
       Grammar gram = gramatica();
       
       c.setHead(head);
       c.setGram(gram);
       
       return c;
            
    }
    
    public Header header() throws Exception
    {
        Header head = new Header();
        ArrayList<CodeBlock> imports = new ArrayList<CodeBlock>();
        while(checkToken(Token.TokenType.KW_IMPORT))
        {
            matchToken(Token.TokenType.KW_IMPORT);
            imports.add(new CodeBlock("import " + currentToken.lexema));
            matchToken(Token.TokenType.ID);
            matchToken(Token.TokenType.SIGN_PUNTOYCOMA);
        }
        Import imp = new Import();
        imp.setCode(imports);
        head.setImports(imp);
        ParserH parse = new ParserH();
        if(checkToken(Token.TokenType.KW_PARSE))
        {
            matchToken(Token.TokenType.KW_PARSE);
            matchToken(Token.TokenType.KW_CODE);
            matchToken(Token.TokenType.OPEN_BRACKET);
            parse.setCode(new CodeBlock(currentToken.lexema));
            matchToken(Token.TokenType.JAVA_CODE);
            matchToken(Token.TokenType.CLOSE_BRACKET);
            matchToken(Token.TokenType.SIGN_PUNTOYCOMA);
        }
        head.setParserh(parse);
        
        return head;
    }   
    public Grammar gramatica() throws Exception
    {
        TerminalesDef termDef = new TerminalesDef();
        ArrayList<noTerminalesDef> nonTermDef = new ArrayList<noTerminalesDef>();
        ArrayList<Produccion> producciones = new ArrayList<Produccion>();
        while(checkToken(Token.TokenType.KW_TERMINAL) || checkToken(Token.TokenType.KW_NONTERMINAL))
        {
            if(checkToken(Token.TokenType.KW_TERMINAL))
            {
                matchToken(Token.TokenType.KW_TERMINAL);
                termDef = defTerminales();
               
            }
            else if(checkToken(Token.TokenType.KW_NONTERMINAL))
            {
                matchToken(Token.TokenType.KW_NONTERMINAL);
               // matchToken(Token.TokenType.KW_TERMINAL);
                nonTermDef.add(defNoTerminales());
                noTerminales = new ArrayList<noTerminal>();
            }
        }
         
        while(checkToken(Token.TokenType.ID))
        {
            producciones.add(prod());
        }
            
        return new Grammar(termDef,nonTermDef,producciones);
    }
    public TerminalesDef defTerminales() throws Exception
    {
       
        if(checkToken(Token.TokenType.ID))
        {
            Terminal t = new Terminal();
            t.setId(new ID(currentToken.lexema));
            terminales.add(t);
            terminalesMap.put(currentToken.lexema, t);
            matchToken(Token.TokenType.ID);
        
            while(checkToken(Token.TokenType.SIGN_COMA))
            {
                matchToken(Token.TokenType.SIGN_COMA);
                t = new Terminal();
                t.setId(new ID(currentToken.lexema));
                terminales.add(t);
                terminalesMap.put(currentToken.lexema, t);
                matchToken(Token.TokenType.ID);
            }
            matchToken(Token.TokenType.SIGN_PUNTOYCOMA);
        }
        return new TerminalesDef(terminales);
    }    
    public noTerminalesDef defNoTerminales() throws Exception
    {
        ID id = new ID();
        if(checkToken(Token.TokenType.ID))
        {
            id.setId(currentToken.lexema);
            matchToken(Token.TokenType.ID);
            noTerminal t = new noTerminal();
            t.setId(new ID(currentToken.lexema));
            noTerminales.add(t);
            noTerminalesMap.put(currentToken.lexema, t);
            matchToken(Token.TokenType.ID);
                      
            while(checkToken(Token.TokenType.SIGN_COMA))
            {
                matchToken(Token.TokenType.SIGN_COMA);
                t = new noTerminal();
                t.setId(new ID(currentToken.lexema));
                noTerminales.add(t);
                noTerminalesMap.put(currentToken.lexema, t);
                matchToken(Token.TokenType.ID);
            }
            
            matchToken(Token.TokenType.SIGN_PUNTOYCOMA);
        }
        return new noTerminalesDef(id,noTerminales);
    }   
    public Produccion prod() throws Exception
    {
        Produccion production = new Produccion();
        ArrayList<Line> lineas = new ArrayList<Line>();
        noTerminal not;
        if(checkToken(Token.TokenType.ID))
        {
            not = new noTerminal(new ID(currentToken.lexema));
            production.setId(not);
            matchToken(Token.TokenType.ID);
            matchToken(Token.TokenType.PRODUCTION);
            lineas.add(line(not)); 
            while(checkToken(Token.TokenType.SIGN_PIPE))
            {
                matchToken(Token.TokenType.SIGN_PIPE);
                if(checkToken(Token.TokenType.ID))
                {
                   lineas.add(line(not)); 
                }
                //else error
            }
            matchToken(Token.TokenType.SIGN_PUNTOYCOMA);
            
        }
        
        production.setLineas(lineas);
        return production;
    }    
    public Line line(noTerminal not) throws Exception
    {
        ArrayList<CodeBlock> codigos = new ArrayList<CodeBlock>();
        ArrayList<Termino> terminos = new ArrayList<Termino>();
        if(checkToken(Token.TokenType.ID))
        {
            terminos.add(termino());
        
        }
        while(!checkToken(Token.TokenType.SIGN_PIPE) && (!checkToken(Token.TokenType.SIGN_PUNTOYCOMA)))
        {
            if(checkToken(Token.TokenType.ID))
                terminos.add(termino());
            else if (checkToken(Token.TokenType.OPEN_BRACKET))
                codigos.add(Code());
            //Else error
        }
        
        return new Line(terminos,codigos,not);
    }
    public CodeBlock Code() throws Exception
    {
        CodeBlock codigo = new CodeBlock();
        matchToken(Token.TokenType.OPEN_BRACKET);
        if(checkToken(Token.TokenType.JAVA_CODE))
        {
            codigo.setCodigo(currentToken.lexema);
            matchToken(Token.TokenType.JAVA_CODE);
        }
        //else error
        matchToken(Token.TokenType.CLOSE_BRACKET);
        return codigo;
        
    }
    public Termino termino() throws Exception
    {
        Termino term;
        if(checkToken(Token.TokenType.ID))
        {
            if(isTerminal(currentToken.lexema))
            { 
                term = new Terminal();
                term.setId(new ID(currentToken.lexema));
                matchToken(Token.TokenType.ID);
            
            
                if(checkToken(Token.TokenType.SIGN_DOSPUNTOS))
                {
                    matchToken(Token.TokenType.SIGN_DOSPUNTOS);
                    if(checkToken(Token.TokenType.ID))
                    {
                        term.setAlias(currentToken.lexema);
                        matchToken(Token.TokenType.ID);
                    }
                }
                return term;
            }
            else if(isNonTerminal(currentToken.lexema))
            {
               term = new noTerminal(); 
               term.setId(new ID(currentToken.lexema));
                matchToken(Token.TokenType.ID);
            
            
                if(checkToken(Token.TokenType.SIGN_DOSPUNTOS))
                {
                    matchToken(Token.TokenType.SIGN_DOSPUNTOS);
                    if(checkToken(Token.TokenType.ID))
                    {
                        term.setAlias(currentToken.lexema);
                        matchToken(Token.TokenType.ID);
                    }
                }
                return term;
            }
            //else error
        }
        //else error
       return new Terminal();
    }
    
    
}
