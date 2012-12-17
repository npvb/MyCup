/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParserClasses;

/**
 *
 * @author Paulette
 */
public class Reduccion {
    
    String id; // estado
    int cantidad; //cuantos vas a sacar
    public int numeroProd;
    String InicialS;

    public Reduccion(String id, int cantidad, int numeroProd, String state) {
        this.id = id;
        this.cantidad = cantidad;
        this.numeroProd = numeroProd;
        this.InicialS = state;
    }

    public String getInicialS() {
        return InicialS;
    }

    public void setInicialS(String InicialS) {
        this.InicialS = InicialS;
    }
    

    public Reduccion() {
    }
    

    public Reduccion(String id, int cantidad, int numeroProd) {
        this.id = id;
        this.cantidad = cantidad;
        this.numeroProd = numeroProd;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumeroProd() {
        return numeroProd;
    }

    public void setNumeroProd(int numeroProd) {
        this.numeroProd = numeroProd;
    }
    
    
}
