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
public class AdministradorEntregables {
    private ArrayList<Entregable> listaEntregables = new ArrayList<Entregable>();
    private ArrayList<Entregable> listaFechaIngreso;
    private ArrayList<Entregable> listaFechaRetiro;
    private ArrayList<Entregable> listaPorRetirar;
        
    
    public ArrayList<Entregable> MostrarEntregables(int id_cliente){
        ArrayList<Entregable> buscarCliente = new ArrayList<>();
        for(int i = 0; i<listaEntregables.size();i++){
            if(listaEntregables.get(i).getIdRemitente() == id_cliente){
                buscarCliente.add(listaEntregables.get(i));
            }
        }
        return buscarCliente;
    }
    
    
    
    public AdministradorEntregables(ArrayList<Entregable> listaEntregables) {
        this.listaEntregables = listaEntregables;
    }

    public AdministradorEntregables() {
    }

    public ArrayList<Entregable> getListaEntregables() {
        return listaEntregables;
    }

    public void setListaEntregables(ArrayList<Entregable> listaEntregables) {
        this.listaEntregables = listaEntregables;
    }

    public void AnnadirEntrega(Entregable entrega){
        for (int i = 0; i < listaEntregables.size(); i++) {
            Entregable entregables = listaEntregables.get(i);
            if (entregables.getId()== entrega.getId()){
                entrega.setId(entregables.getId() + 1);
             }
        }   
        listaEntregables.add(entrega);
        System.out.println("Ingresado al inventario");
    }
    
    public ArrayList<Entregable> MostrarFechaIngreso(String fechaIngreso){
        for (int i = 0; i < listaEntregables.size(); i++) {
            Entregable entregables = listaEntregables.get(i);
            if (entregables.getFechaIngreso().equals(fechaIngreso) ){
                listaFechaIngreso.add(entregables);                
             }
        }
        ArrayList<Entregable> auxIngreso = listaFechaIngreso;
        listaFechaIngreso.clear();
        return auxIngreso;
    }
    
    public ArrayList<Entregable> mostrarFechaRetiro(String fechaRetiro){
        for (int i = 0; i < listaEntregables.size(); i++) {
            Entregable entregables = listaEntregables.get(i);
            if (entregables.getFechaRetiro().equals(fechaRetiro) ){
                listaFechaRetiro.add(entregables);
                
             }
        }
        ArrayList<Entregable> auxRetiro = listaFechaRetiro;
        listaFechaRetiro.clear();
        return auxRetiro;
    }
     public ArrayList<Entregable> mostrarPorRetirar(){
        for (int i = 0; i < listaEntregables.size(); i++) {
            Entregable entregables = listaEntregables.get(i);
            if (entregables.getEstado()) {
                listaPorRetirar.add(entregables);
                
             }
        }
        ArrayList<Entregable> auxPorRetirar = listaPorRetirar;
        listaPorRetirar.clear();
        return auxPorRetirar;
    }
 
    @Override
    public String toString() {
        return "AdministradorEntregables{" + "listaEntregables=" + listaEntregables + '}';
    }
    
    
}
