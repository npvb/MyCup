

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
		t.addEstado(new Estado("7"));
		t.addEstado(new Estado("8"));
		t.addEstado(new Estado("9"));
		t.addEstado(new Estado("10"));
		t.addEstado(new Estado("11"));
		t.addEstado(new Estado("12"));
		t.addEstado(new Estado("13"));
		t.addEstado(new Estado("14"));
		t.addEstado(new Estado("15"));



		t.addAccion(new Acciones(new Estado("Q1"), new Simbolo("$"), new Aceptacion("Q1")));
		t.addAccion(new Acciones(new Estado("Q0"), new Simbolo("PRINT"), new Desplazar("Q4")));
		t.addAccion(new Acciones(new Estado("Q0"), new Simbolo("ID"), new Desplazar("Q5")));
		t.addAccion(new Acciones(new Estado("Q4"), new Simbolo("EQUALS"), new Desplazar("Q9")));
		t.addAccion(new Acciones(new Estado("Q6"), new Simbolo("SEMI"), new Desplazar("Q10")));
		t.addAccion(new Acciones(new Estado("Q6"), new Simbolo("PLUS"), new Desplazar("Q11")));
		t.addAccion(new Acciones(new Estado("Q6"), new Simbolo("MINUS"), new Desplazar("Q12")));
		t.addAccion(new Acciones(new Estado("Q12"), new Simbolo("SEMI"), new Desplazar("Q16")));



		t.addAccion(new Acciones(new Estado("Q2"), new Simbolo("$"), new Reducir("2",02)));
		t.addAccion(new Acciones(new Estado("Q5"), new Simbolo("$"), new Reducir("3",01)));



		t.addIrA("0", new IrA("2")));
		t.addIrA("0", new IrA("3")));
		t.addIrA("0", new IrA("4")));
		t.addIrA("0", new IrA("5")));
		t.addIrA("1", new IrA("6")));
		t.addIrA("3", new IrA("7")));
		t.addIrA("3", new IrA("8")));
		t.addIrA("4", new IrA("9")));
		t.addIrA("6", new IrA("10")));
		t.addIrA("6", new IrA("11")));
		t.addIrA("6", new IrA("12")));
		t.addIrA("8", new IrA("13")));
		t.addIrA("10", new IrA("14")));
		t.addIrA("11", new IrA("15")));
		t.addIrA("12", new IrA("16")));
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
        public Simbolo Execute(int reduccion, Stack<Simbolo> pila) 
         {
              switch(reduccion)
              {
                case 2:
                  case 3:
                }
        }
}