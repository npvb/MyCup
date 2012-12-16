

import java.util.HashMap;
import Clases.*;
import java.io.*;
import java.util.*;
 public class MyParser extends ParserStack {
    Tabla t;
    Yylex lexico;
    ArrayList<Integer> Entradas;
    ArrayList<String> Producciones;
    HashMap<Integer, String> Hash;

    public MyParser(Yylex jf) throws Exception{
                lexico = jf;
                Entradas = new ArrayList<Integer>();
                Producciones = new ArrayList<String>();
                Hash = new HashMap<Integer, String>();


    t = new Tabla();

		t.addEstado(new Estado("0"));
		t.addEstado(new Estado("1"));
		t.addEstado(new Estado("2"));
		t.addEstado(new Estado("3"));
		t.addEstado(new Estado("4"));
		t.addEstado(new Estado("5"));
		t.addEstado(new Estado("6"));



		t.addAccion(new Acciones(new Estado("Q1"), new Simbolo("$"), new Aceptacion("Q1")));
		t.addAccion(new Acciones(new Estado("Q0"), new Simbolo("c"), new Desplazar("Q4")));
		t.addAccion(new Acciones(new Estado("Q0"), new Simbolo("d"), new Desplazar("Q5")));



		t.addAccion(new Acciones(new Estado("Q4"), new Simbolo("d"), new Reducir("2",03)));
		t.addAccion(new Acciones(new Estado("Q4"), new Simbolo("d"), new Reducir("3",03)));
		t.addAccion(new Acciones(new Estado("Q5"), new Simbolo("$"), new Reducir("4",01)));
		t.addAccion(new Acciones(new Estado("Q6"), new Simbolo("d"), new Reducir("5",02)));
		t.addAccion(new Acciones(new Estado("Q6"), new Simbolo("d"), new Reducir("6",22)));



		t.addIrA("0", new IrA("2")));
		t.addIrA("0", new IrA("3")));
		t.addIrA("0", new IrA("4")));
		t.addIrA("0", new IrA("5")));
		t.addIrA("2", new IrA("6")));
		t.addIrA("3", new IrA("7")));
     }
	public void Parse() throws IOException{
		yyToken n = lexico.yylex();
		int i=0;
		while(n!=null){
			i=n.m_index;
			Entradas.add(i);
            if(n!=null)
                    Hash.put(i, n.m_lexema);
			n = lexico.yylex();
		}
    }
        @Override
        public Simbolo Execute(String reduccion, Stack<Simbolo> pila) 
         {
            throw new UnsupportedOperationException("Not supported yet.");
          }
  }
