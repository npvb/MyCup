package Default;

import Clases.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

 public class MyParser{
     
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
		t.addEstado(new Estado("I3I8"));
		t.addEstado(new Estado("I4I9"));
		t.addEstado(new Estado("I5"));
		t.addEstado(new Estado("I6I11"));
		t.addEstado(new Estado("I7"));
		t.addEstado(new Estado("I10I12"));
		t.addSimbolo(new Terminal("mas"));
		t.addSimbolo(new Terminal("por"));
		t.addSimbolo(new Terminal("id"));
		t.addSimbolo(new Terminal("$"));
		t.addSimbolo(new NoTerminal("E"));
		t.addSimbolo(new NoTerminal("T"));
		t.addSimbolo(new NoTerminal("F"));
		t.CrearTabla();
		t.addAccion(new Acciones(new Estado("I1"), new Simbolo("$"), new Aceptacion("I1")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("E"), new IrA("I1")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("T"), new IrA("I2")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("F"), new IrA("I3I8")));
		t.addAccion(new Acciones(new Estado("I0"), new Simbolo("id"), new Desplazar("I4I9")));
		t.addAccion(new Acciones(new Estado("I1"), new Simbolo("mas"), new Desplazar("I5")));
		t.addAccion(new Acciones(new Estado("I2"), new Simbolo("por"), new Desplazar("I6I11")));
		t.addAccion(new Acciones(new Estado("I5"), new Simbolo("T"), new IrA("I7")));
		t.addAccion(new Acciones(new Estado("I5"), new Simbolo("F"), new IrA("I3I8")));
		t.addAccion(new Acciones(new Estado("I5"), new Simbolo("id"), new Desplazar("I4I9")));
		t.addAccion(new Acciones(new Estado("I7"), new Simbolo("por"), new Desplazar("I6I11")));
		t.addAccion(new Acciones(new Estado("I6I11"), new Simbolo("F"), new IrA("I10I12")));
		t.addAccion(new Acciones(new Estado("I6I11"), new Simbolo("id"), new Desplazar("I4I9")));
		t.addAccion(new Acciones(new Estado("I3I8"), new Simbolo("$"), new Reducir("4",1)));
		t.addAccion(new Acciones(new Estado("I3I8"), new Simbolo("mas"), new Reducir("4",1)));
		t.addAccion(new Acciones(new Estado("I3I8"), new Simbolo("por"), new Reducir("4",1)));
		t.addAccion(new Acciones(new Estado("I3I8"), new Simbolo("id"), new Reducir("4",1)));
		t.addAccion(new Acciones(new Estado("I4I9"), new Simbolo("$"), new Reducir("5",1)));
		t.addAccion(new Acciones(new Estado("I4I9"), new Simbolo("mas"), new Reducir("5",1)));
		t.addAccion(new Acciones(new Estado("I4I9"), new Simbolo("por"), new Reducir("5",1)));
		t.addAccion(new Acciones(new Estado("I4I9"), new Simbolo("id"), new Reducir("5",1)));
		t.addAccion(new Acciones(new Estado("I10I12"), new Simbolo("$"), new Reducir("3",3)));
		t.addAccion(new Acciones(new Estado("I10I12"), new Simbolo("mas"), new Reducir("3",3)));
		t.addAccion(new Acciones(new Estado("I10I12"), new Simbolo("por"), new Reducir("3",3)));
		t.addAccion(new Acciones(new Estado("I10I12"), new Simbolo("id"), new Reducir("3",3)));
     }

    public MyParser() {
        throw new UnsupportedOperationException("Not yet implemented");
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
		Producciones.add("E");
		Producciones.add("E");
		Producciones.add("T");
		Producciones.add("T");
		Producciones.add("F");
            ParserStack p = new ParserStack(Entradas, Producciones, Hash, t);
            System.out.println(p.Accepted());
	}
     public void Execute(String produccion)
    {
        if (produccion == "0")
        {
            //Codigo para la produccion 0
        }
        else if ( produccion == "1"){
            //Codigo para la produccion 1
        }
        
    }
}
