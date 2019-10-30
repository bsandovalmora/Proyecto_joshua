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
public class Revista {
    private String nombre;
    private String tema;
    private boolean catalogo;

    public Revista() {
    }

    public Revista(String nombre, String tema, boolean catalogo) {
        this.nombre = nombre;
        this.tema = tema;
        this.catalogo = catalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public boolean isCatalogo() {
        return catalogo;
    }

    public void setCatalogo(boolean catalogo) {
        this.catalogo = catalogo;
    }

    @Override
    public String toString() {
        return "Revista{" + "nombre=" + nombre + ", tema=" + tema + ", catalogo=" + catalogo + '}';
    }


    
}
