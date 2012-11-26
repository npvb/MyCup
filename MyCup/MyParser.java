


import Clases.*;


 public class MyParser{
Tabla t = new Tabla();
MyParser(){
    public MyParser() {
		t.addSimbolo(new Terminal("c"));
		t.addSimbolo(new Terminal("d"));
		t.addSimbolo(new No Terminal("S"));
		t.addSimbolo(new No Terminal("C"));
		t.addAccion(new Acciones(new Estado("0"), new Simbolo("$"), new Aceptacion("1")));
     }
}