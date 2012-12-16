		t.addAccion(new Acciones(new Estado("Q6"), new Simbolo("d"), new Reducir("7",32)));
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
		Producciones.add("S");
		Producciones.add("C");
            Stack pila = new Stack(Entradas, Producciones, Hash, t);
            System.out.println(pila.Accepted());
	}
}
