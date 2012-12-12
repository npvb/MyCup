public ArrayList<EstadoProd> Go_To(EstadoProd estado)
    {
      ArrayList<EstadoProd> temp = new ArrayList<EstadoProd>();
//      for (int xy = 0; xy < estado.prod.lineas.size(); xy++)
//      {
        if(estado.punto!=estado.prod.terminos.size())
        {
           if(estado.punto>estado.prod.terminos.size())
           {
               concatWith = "EMPTY";
           }else
           {
               estado.punto = estado.punto+1;
                concatWith = estado.prod.terminos.get(estado.punto-1).id.lexema;
           }
        }
      //}
      temp.add(estado);
      return ApCerradura(temp);
    }
	
	 public void GenerarTabla() throws Exception
    {
        try{
            termDef.terminales.add(new Terminal(new ID("$")));
            for(int x=0;x<Estados.size();x++)
            {
//                if(Estados.get(x).Producciones.size() == 1)
//                {
                for(int y = 0; y<Estados.get(x).Producciones.size();y++)
                {
                    if(Estados.get(x).Producciones.get(y).punto > Estados.get(x).Producciones.get(y).prod.terminos.size())
                    {
                        int where = getGoto(Estados.get(x), Estados.get(x).Producciones.get(y).prod.terminos.get(Estados.get(x).Producciones.get(y).punto));// ProdIgual(Estados.get(x).Producciones.get(0).prod);
                        LALR l = new LALR();
                        l.fin = where;
                        l.inicio = Estados.get(x).valor;

                        for(int y=0;y<Estados.get(x).Producciones.get(0).primero.size();y++)
                        {
                            l.simbolo = Estados.get(x).Producciones.get(0).primero.get(y);
                            varGlobal.Reducciones.add(l);
                        }   
                    }
                }
                //}
            }
            int state=0;
            String term="";
            int fin =0;
            for(int i=0;i<Estados.size();i++)
            {
                ArrayList<String> Est = new ArrayList<String>();

                for(int x=0;x<termDef.terminales.size();x++)
                {
                    term = termDef.terminales.get(x).id.lexema;
                    String r1,contenido="";
                    boolean found = false;

                    for(int y=0;y<varGlobal.Automata.size();y++)
                    {
                        if(varGlobal.Automata.get(y).simbolo.compareTo(term) == 0)
                        {
                            if(state==varGlobal.Automata.get(y).inicio)
                            {
                                fin = varGlobal.Automata.get(y).fin;
                                found = true;
                            }
                        }
                    }
                    if(found)
                    {
                        r1="d";
                        contenido=fin+"\n";
                        r1+=contenido;
                    }else
                    {
                        r1="-";
                    }
                    Est.add(r1);
                }
                varGlobal.Tabla.add(Est);
            }
         }catch (Exception e){
           throw new Exception("Error Grammar->GenerarTabla(): " + e.getMessage());
       }  
    }