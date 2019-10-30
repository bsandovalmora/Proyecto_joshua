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
public class Sobre {
     
     private boolean tipo;//False aereo, True manila
     private String contenido;

    public Sobre(boolean tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    public Sobre() {
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Sobre{" + "tipo=" + tipo + ", contenido=" + contenido + '}';
    }

 
}
