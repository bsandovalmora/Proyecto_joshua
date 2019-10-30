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
public class Paquete {
    
    private boolean fragilidad; // F Para No Fragil V Para Fragil
    private boolean empaque; //F Para bolsa V Para Caja 
    private boolean electronico;//F para No V Para Sí

    public Paquete() {
    }

    public Paquete(boolean fragilidad, boolean empaque, boolean electronico) {
        this.fragilidad = fragilidad;
        this.empaque = empaque;
        this.electronico = electronico;
    }


    public void setFragilidad(boolean fragilidad) {
        this.fragilidad = fragilidad;
    }

    public void setEmpaque(boolean empaque) {
        this.empaque = empaque;
    }

    public void setElectronico(boolean electronico) {
        this.electronico = electronico;
    }

    
    
    
    
}
