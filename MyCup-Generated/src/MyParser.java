

import java.util.HashMap;
import Clases.*;
import java.io.*;
import java.util.ArrayList;
 public class MyParser {
    Tabla t = new Tabla();
    Yylex lexico;
    ArrayList<Integer> Entradas;
    ArrayList<String> Producciones;
    HashMap<Integer, String> Hash;

    public MyParser(Yylex jf) throws Exception{
                lexico = jf;
                Entradas = new ArrayList<Integer>();
                Producciones = new ArrayList<String>();
                Hash = new HashMap<Integer, String>();
		t.addEstado(new Estado("0"));
		t.addEstado(new Estado("1"));
		t.addEstado(new Estado("2"));
		t.addEstado(new Estado("3"));
		t.addEstado(new Estado("4"));
		t.addEstado(new Estado("5"));
		t.addEstado(new Estado("6"));
		t.addEstado(new Estado("7"));
		t.addEstado(new Estado("8"));
		t.addEstado(new Estado("9"));
		t.addEstado(new Estado("10"));
		t.addEstado(new Estado("11"));
		t.addEstado(new Estado("12"));
		t.addEstado(new Estado("13"));
		t.addEstado(new Estado("14"));
		t.addEstado(new Estado("15"));
		t.addEstado(new Estado("16"));
		t.addEstado(new Estado("17"));
		t.addEstado(new Estado("18"));
		t.addEstado(new Estado("19"));
		t.addEstado(new Estado("20"));
		t.addEstado(new Estado("21"));
		t.addEstado(new Estado("22"));
		t.addEstado(new Estado("23"));
		t.addEstado(new Estado("24"));
		t.addEstado(new Estado("25"));
		t.addSimbolo(new Terminal("SEMI"));
		t.addSimbolo(new Terminal("PLUS"));
		t.addSimbolo(new Terminal("MINUS"));
		t.addSimbolo(new Terminal("TIMES"));
		t.addSimbolo(new Terminal("DIVIDE"));
		t.addSimbolo(new Terminal("LPAREN"));
		t.addSimbolo(new Terminal("RPAREN"));
		t.addSimbolo(new Terminal("EQUALS"));
		t.addSimbolo(new Terminal("PRINT"));
		t.addSimbolo(new Terminal("NUMBER"));
		t.addSimbolo(new Terminal("ID"));
		t.addSimbolo(new Terminal("$"));
		t.addSimbolo(new NoTerminal("expr_list"));
		t.addSimbolo(new NoTerminal("expr_part"));
		t.addSimbolo(new NoTerminal("expr"));
		t.addSimbolo(new NoTerminal("factor"));
		t.addSimbolo(new NoTerminal("term"));
		t.CrearTabla();
		t.addAccion(new Acciones(new Estado("I1"), new Simbolo("$"), new Aceptacion("I1")));
     }
	public void Parse() throws IOException{
		Yytoken n = lexico.yylex();
		int i=0;
		while(n!=null){
			i=n.m_index;
			Entradas.add(i);
            if(n!=null)
                    Hash.put(i, n.m_lexema);
			n = lexico.yylex();
		}
		Producciones.add("expr_list");
		Producciones.add("expr_part");
		Producciones.add("expr");
		Producciones.add("factor");
		Producciones.add("term");
            Stack pila = new Stack(Entradas, Producciones, Hash, t);
            System.out.println(pila.Accepted());
	}
}
