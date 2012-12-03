

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
		t.addEstado(new Estado("I0"));
		t.addEstado(new Estado("I1"));
		t.addEstado(new Estado("I2"));
		t.addEstado(new Estado("I3I6"));
		t.addEstado(new Estado("I4I7"));
		t.addEstado(new Estado("I5"));
		t.addEstado(new Estado("I8I9"));
		t.addSimbolo(new Terminal("c"));
		t.addSimbolo(new Terminal("d"));
		t.addSimbolo(new Terminal("$"));
		t.addSimbolo(new NoTerminal("S"));
		t.addSimbolo(new NoTerminal("C"));
		t.CrearTabla();
		t.addAccion(new Acciones(new Estado("I1"), new Simbolo("$"), new Aceptacion("I1")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("S"), new IrA("I1")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("C"), new IrA("I2")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("c"), new Desplazar("I3I6")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("d"), new Desplazar("I4I7")));
		t.addAccion(new Acciones(new Estado("I2"), new Simbolo("C"), new IrA("I5")));
		t.addAccion(new Acciones(new Estado("I2"), new Simbolo("c"), new Desplazar("I3I6")));
		t.addAccion(new Acciones(new Estado("I2"), new Simbolo("d"), new Desplazar("I4I7")));
		t.addAccion(new Acciones(new Estado("I3I6"), new Simbolo("C"), new IrA("I8I9")));
		t.addAccion(new Acciones(new Estado("I3I6"), new Simbolo("c"), new Desplazar("I3I6")));
		t.addAccion(new Acciones(new Estado("I3I6"), new Simbolo("d"), new Desplazar("I4I7")));
		t.addAccion(new Acciones(new Estado("I4I7"), new Simbolo("c"), new Reducir("3",1)));
		t.addAccion(new Acciones(new Estado("I4I7"), new Simbolo("d"), new Reducir("3",1)));
		t.addAccion(new Acciones(new Estado("I4I7"), new Simbolo("$"), new Reducir("3",1)));
		t.addAccion(new Acciones(new Estado("I5"), new Simbolo("$"), new Reducir("1",2)));
		t.addAccion(new Acciones(new Estado("I8I9"), new Simbolo("c"), new Reducir("2",2)));
		t.addAccion(new Acciones(new Estado("I8I9"), new Simbolo("d"), new Reducir("2",2)));
		t.addAccion(new Acciones(new Estado("I8I9"), new Simbolo("$"), new Reducir("2",2)));
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
		Producciones.add("Inicial");
		Producciones.add("S");
		Producciones.add("C");
		Producciones.add("C");
                Entradas.add(0);
            Stack p = new Stack(Entradas, Producciones, Hash, t);
            System.out.println(p.Accepted());
	}
}
