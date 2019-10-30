/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author joshu
 */
public class Contable {
    private float compra;
    private float venta;

    public Contable() {
    }

    public Contable(float compra, float venta) {
        this.compra = compra;
        this.venta = venta;
    }

    public float getCompra() {
        return compra;
    }

    public void setCompra(float compra) {
        this.compra = compra;
    }

    public float getVenta() {
        return venta;
    }

    public void setVenta(float venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "Contable{" 
                + "compra=" + compra 
                + ", venta=" + venta + '}';
    }
    
    
}
