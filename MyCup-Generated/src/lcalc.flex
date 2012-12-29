
import Clases.*;
      
%%

%class Lexer
%type Simbolo
%function next_token
%line
%column
   
%{   
    private Simbolo symbol(int type,String lexema) {
        return new Simbolo(type, lexema, null);
    }
     private Simbolo symbol(int type,String lexema,Object value) {
        return new Simbolo(type, lexema, value);
    }
   // private Simbolo symbol(int type, Object value) {
     //   return new Simbolo(type, yyline, yycolumn, value);
   // }
%}
   
LineTerminator = \r|\n|\r\n
   
WhiteSpace     = {LineTerminator} | [ \t\f]

dec_int_lit = 0 | [1-9][0-9]*
   
dec_int_id = [A-Za-z_][A-Za-z_0-9]*
   
%%
   
<YYINITIAL> {
   
   /* "c"                { return symbol (sym.c,"c"); }
    "d"                {  return symbol (sym.d,"d"); }*/
    "+"              {  return symbol (sym.PLUS,"+"); }
    //"-"                {  return symbol (sym.MINUS,"-"); }
    "*"                {  return symbol (sym.TIMES,"*"); }
   // "/"                {  return symbol (sym.DIVIDE,"/"); }
    "("                {  return symbol (sym.LPAREN,"("); }
    ")"                {  return symbol (sym.RPAREN,")"); }
    //"print"            {  return symbol (sym.PRINT,"print"); }
   
    /*{dec_int_lit}      { return symbol(sym.NUMBER,yytext(), new Integer(yytext())); }*/
   
    {dec_int_id}       { return symbol (sym.ID, yytext(),yytext());}
   
    {WhiteSpace}       { /* just skip what was found, do nothing */ }   
}

[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
