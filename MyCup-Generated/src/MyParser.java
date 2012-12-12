

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
		t.addSimbolo(new Terminal("MAS"));
		t.addSimbolo(new Terminal("POR"));
		t.addSimbolo(new Terminal("PARD"));
		t.addSimbolo(new Terminal("PARI"));
		t.addSimbolo(new Terminal("ID"));
		t.addSimbolo(new Terminal("$"));
		t.addSimbolo(new NoTerminal("E"));
		t.addSimbolo(new NoTerminal("T"));
		t.addSimbolo(new NoTerminal("F"));
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
		Producciones.add("E");
		Producciones.add("T");
		Producciones.add("F");
            Stack pila = new Stack(Entradas, Producciones, Hash, t);
            System.out.println(pila.Accepted());
	}
}
