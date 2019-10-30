/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author joshu
 */
public class Casillero {
    private int numero;
    private boolean estado;
    private ArrayList<Entregable> listaEntregables;
    private ArrayList<Entregable> listaRetirados;

    public Casillero(int numero, boolean estado, ArrayList<Entregable> listaEntregables) {
        this.numero = numero;
        this.estado = estado;
        this.listaEntregables = listaEntregables;
    }

    //false = libre || true = ocupado
    public Casillero(int numero, boolean estado) {
        this.numero = numero;
        this.estado = estado;        
    }
    
    

    public Casillero() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public ArrayList<Entregable> getListaRetirados() {
        return listaRetirados;
    }

    public void setListaRetirados(ArrayList<Entregable> listaRetirados) {
        this.listaRetirados = listaRetirados;
    }

    public ArrayList<Entregable> getListaEntregables() {
        return listaEntregables;
    }

    public void setListaEntregables(ArrayList<Entregable> listaEntregables) {
        this.listaEntregables = listaEntregables;
    }
    
    public boolean annadirARetirados(Entregable entrega){
        listaRetirados.add(entrega);
        return true;

    }
    
    
    public void annadirEntregable(Entregable entrega){
        for (int i = 0; i < listaEntregables.size(); i++) {
            Entregable repetido = listaEntregables.get(i);
            if (repetido.getId()== entrega.getId()){
             }
        }   
        listaEntregables.add(entrega);
    }

    
    @Override
    public String toString() {
        return "Casillero{" +
                "numero=" + numero 
                + ", estado=" + estado 
                + ", listaEntregables=" + listaEntregables + '}';   //Hola Karencita :3
    }



}