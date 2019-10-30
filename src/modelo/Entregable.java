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
public class Entregable {
    private int id;
    private String tipo;
    private boolean estado; //F para No entregado V Para entregado
    private String descripcion;
    private String remitente;
    private int idRemitente;
    private String fechaIngreso;
    private String fechaRetiro;
    private long peso;
    private Sobre sobre;
    private Paquete paquete;
    private Revista revista;
    private long costoArticulo;

    public Entregable() {
    }

    public Entregable(int id, String tipo, boolean estado, String descripcion, String remitente, String fechaIngreso, String fechaRetiro, long peso, Sobre sobre, Paquete paquete, Revista revista, long costoArticulo) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;//TRUE SI NO HA SIDO RETIRADO, FALSE SI YA SE RETIRO
        this.descripcion = descripcion;
        this.remitente = remitente;
        this.fechaIngreso = fechaIngreso;
        this.fechaRetiro = fechaRetiro;
        this.peso = peso;
        this.sobre = sobre;
        this.paquete = paquete;
        this.revista = revista;
        this.costoArticulo = costoArticulo;
    }
    
    public Entregable(String remitente, int id,String tipo, boolean estado, String descripcion, int idRemitente, String fechaIngreso, String fechaRetiro, long peso, Sobre sobre, Paquete paquete, Revista revista, long costoArticulo) {
        this.remitente=remitente;
        this.id = id;    
        this.tipo = tipo;
        this.estado = estado;//TRUE SI NO HA SIDO RETIRADO, FALSE SI YA SE RETIRO
        this.descripcion = descripcion;
        this.idRemitente = idRemitente;
        this.fechaIngreso = fechaIngreso;
        this.fechaRetiro = fechaRetiro;
        this.peso = peso;
        this.sobre = sobre;
        this.paquete = paquete;
        this.revista = revista;
        this.costoArticulo = costoArticulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(int idRemitente) {
        this.idRemitente = idRemitente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public long getPeso() {
        return peso;
    }

    public void setPeso(long peso) {
        this.peso = peso;
    }

    public Sobre getSobre() {
        return sobre;
    }

//    public void setSobre(boolean tipoSobre, String contenido) {
//        if (this.tipo == String.Sobre){
//            Sobre sobr= new Sobre(tipoSobre, contenido);
//            this.sobre=sobr;
//        }
//        else{
//            this.sobre = null;
//                    
//        }
//    }
       
    

    public Paquete getPaquete() {
        return paquete;
    }

//    public void setPaquete(boolean fragil,boolean empaque,boolean electro) {    //F Para No Fragil V Para Fragil
//        if (this.tipo == String.Paquete){                               //F Para bolsa V Para Caja 
//            Paquete paque= new Paquete(fragil, empaque, electro);                    //F para No V Para Sí
//            this.paquete=paque;
//        }
//        else{
//            this.paquete = null;
//                    
//        }
//    }

    public Revista getRevista() {
        return revista;
    }

//    public void setRevista(String nombre, String tema, boolean catalogo) {
//        if (this.tipo == String.Revista){                               //F Para bolsa V Para Caja 
//            Revista revist = new Revista(nombre, tema, catalogo);                    //F para No V Para Sí
//            this.revista= revist;
//        }
//        else{
//            this.paquete = null;
//                    
//        }
//    }

    public long getCostoArticulo() {
        return costoArticulo;
    }

    public void setCostoArticulo(long costoArticulo) {
        this.costoArticulo = costoArticulo;
    }
    
    
    @Override
    public String toString() {
        return "Entregable{" 
                + "id=" + id 
                + ", tipo=" + tipo 
                + ", estado=" + estado 
                + ", descripcion=" + descripcion 
                + ", remitente=" + remitente 
                + ", fechaIngreso=" + fechaIngreso 
                + ", fechaRetiro=" + fechaRetiro 
                + ", peso=" + peso 
                + ", sobre=" + getSobre() 
                + ", paquete=" + getPaquete() 
                + ", revista=" + getRevista() 
                + ", costoArticulo=" + costoArticulo + '}';
    }

    /**
     * @param sobre the sobre to set
     */
    public void setSobre(Sobre sobre) {
        this.sobre = sobre;
    }

    /**
     * @param paquete the paquete to set
     */
    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    /**
     * @param revista the revista to set
     */
    public void setRevista(Revista revista) {
        this.revista = revista;
    }
 
    
}