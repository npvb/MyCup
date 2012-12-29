

import java.util.HashMap;
import Clases.*;
import java.io.*;
import java.util.*;
 public class MyParser extends ParserStack {
    Tabla t;
    Lexer lexico;
    ArrayList<Integer> Entradas;
    ArrayList<Producciones> Producciones;
    HashMap<Integer, String> Hash;

    public MyParser(Lexer jf) throws Exception{
                lexico = jf;
                Entradas = new ArrayList<Integer>();
                Producciones = new ArrayList<Producciones>();
                Hash = new HashMap<Integer, String>();


    t = new Tabla();

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
		t.addSimbolo(new Terminal("PLUS"));
		t.addSimbolo(new Terminal("TIMES"));
		t.addSimbolo(new Terminal("LPAREN"));
		t.addSimbolo(new Terminal("RPAREN"));
		t.addSimbolo(new Terminal("ID"));
		t.addSimbolo(new Terminal("$"));
		t.addSimbolo(new NoTerminal("E"));
		t.addSimbolo(new NoTerminal("T"));
		t.addSimbolo(new NoTerminal("F"));
		t.CrearTabla();
		t.addAccion(new Acciones(new Estado("1"), new Simbolo("$"), new Aceptacion("1")));
		t.addAccion(new Acciones(new Estado("0"), new Simbolo("E"), new IrA("2")));
		t.addAccion(new Acciones(new Estado("0"), new Simbolo("T"), new IrA("3")));
		t.addAccion(new Acciones(new Estado("0"), new Simbolo("F"), new IrA("4")));
		t.addAccion(new Acciones(new Estado("0"), new Simbolo("LPAREN"), new Desplazar("5")));
		t.addAccion(new Acciones(new Estado("0"), new Simbolo("ID"), new Desplazar("6")));
		t.addAccion(new Acciones(new Estado("1"), new Simbolo("PLUS"), new Desplazar("7")));
		t.addAccion(new Acciones(new Estado("2"), new Simbolo("TIMES"), new Desplazar("8")));
		t.addAccion(new Acciones(new Estado("4"), new Simbolo("E"), new IrA("9")));
		t.addAccion(new Acciones(new Estado("6"), new Simbolo("T"), new IrA("10")));
		t.addAccion(new Acciones(new Estado("7"), new Simbolo("F"), new IrA("11")));
		t.addAccion(new Acciones(new Estado("8"), new Simbolo("RPAREN"), new Desplazar("12")));
		t.addAccion(new Acciones(new Estado("Q1"), new Simbolo("$"), new Reducir("2",2)));
		t.addAccion(new Acciones(new Estado("Q2"), new Simbolo("$"), new Reducir("3",3)));
		t.addAccion(new Acciones(new Estado("Q3"), new Simbolo("$"), new Reducir("4",4)));
		t.addAccion(new Acciones(new Estado("Q5"), new Simbolo("$"), new Reducir("5",5)));
		t.addAccion(new Acciones(new Estado("Q9"), new Simbolo("$"), new Reducir("6",6)));
		t.addAccion(new Acciones(new Estado("Q10"), new Simbolo("$"), new Reducir("7",7)));
		t.addAccion(new Acciones(new Estado("Q11"), new Simbolo("$"), new Reducir("8",8)));
		Producciones.add(new Producciones("E",""));
		Producciones.add(new Producciones("T",""));
		Producciones.add(new Producciones("F",""));
     }
	public void Parse() throws IOException{
		Simbolo n = lexico.next_token();
		int i=0;
		while(n!=null){
			i=n.getSym();
			Entradas.add(i);
            if(n!=null)
                    Hash.put(i, n.getLexema());
			n = lexico.next_token();
		}
      ParserStack stack =new ParserStack() {

    @Override
    public Simbolo Execute(int reduccion, Stack<Simbolo> pila) {
       throw new UnsupportedOperationException("Not supported yet.");
      }
    };
            stack.Init(Entradas, Producciones, Hash, t);
            System.out.print(stack.Accepted());
     }
        @Override
        public Simbolo Execute(int reduccion, Stack<Simbolo> pila) 
         {
             throw new UnsupportedOperationException("Not supported yet.");
         }
}
