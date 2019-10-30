/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import java.util.Date;


public class Counter {
    
    
    private String nombre;
    private int cedulaJuridica;
    private String direccion;
    private int cantCasilleros;    
    private ArrayList<Casillero> listaCasillero;
    private ArrayList<AdministradorClientes> listaAdmi;

    public ArrayList<Counter> lista_counter = new ArrayList<Counter>();

    public Counter() {
        
    }

    public Counter(String nombre, int cedulaJuridica, String direccion, int cantCasilleros, ArrayList<Casillero> listaCasillero, ArrayList<AdministradorClientes> listaAdmi) {
        this.nombre = nombre;
        this.cedulaJuridica = cedulaJuridica;
        this.direccion = direccion;
        this.cantCasilleros = cantCasilleros;
        this.listaCasillero = listaCasillero;
        this.listaAdmi = listaAdmi;
    }
    
    public Counter(String nombre, int cedulaJuridica, String direccion, int cantCasilleros){
        this.nombre = nombre;
        this.cedulaJuridica = cedulaJuridica;
        this.direccion = direccion;
        this.cantCasilleros = cantCasilleros;
    }
    
    public void InsertarCounter(Counter c){
                
        lista_counter.add(c);
                 
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(int cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCantCasilleros() {
        return cantCasilleros;
    }

    public void setCantCasilleros(int cantCasilleros) {
        this.cantCasilleros = cantCasilleros;
    }

    public ArrayList<Casillero> getListaCasillero() {
        return listaCasillero;
    }

    public void setListaCasillero(ArrayList<Casillero> listaCasillero) {
        this.listaCasillero = listaCasillero;
    }

    public ArrayList<AdministradorClientes> getListaAdmi() {
        return listaAdmi;
    }

    public void setListaAdmi(ArrayList<AdministradorClientes> listaAdmi) {
        this.listaAdmi = listaAdmi;
    }

    public void annadir_AdmiCliente_Casilero(int id, AdministradorClientes AdminClientes){
        for(int i = 0; i<lista_counter.size();i++){               
                if(lista_counter.get(i).getCedulaJuridica() == id){                    
                    lista_counter.get(i).setListaAdmi(AdminClientes.Lista_AdminClientes);                            
                    lista_counter.get(i).setListaCasillero(AdminClientes.TodosCasilleros());                                                                                                                                             
               }
        }
        
    }
    
    public void Eliminar(int id_cliente, int id_counter){
        for(int i= 0; i < lista_counter.size();i++){
            if(lista_counter.get(i).getCedulaJuridica() == id_counter){
                for(int j = 0; j<lista_counter.get(i).listaAdmi.size();j++){
                    if(lista_counter.get(i).listaAdmi.get(j).getCliente().getpId() == id_cliente){                        
                        lista_counter.get(i).getListaAdmi().remove(j);
                        lista_counter.get(i).getListaCasillero().remove(j);                        
                    }                    
                }
            }
        }
    }
    
    public void Modificar(int id_cliente, String Nombre,String Correo,String Telefono,String Direccion,Date Fecha,boolean sexo_boolean, int id_counter){
        for(int i= 0; i < lista_counter.size();i++){
            if(lista_counter.get(i).getCedulaJuridica() == id_counter){
                for(int j = 0; j<lista_counter.get(i).listaAdmi.size();j++){
                    if(lista_counter.get(i).listaAdmi.get(j).getCliente().getpId() == id_cliente){                        
                       lista_counter.get(i).listaAdmi.get(j).getCliente().setpNombre(Nombre);
                       lista_counter.get(i).listaAdmi.get(j).getCliente().setpCorreo(Correo);
                       lista_counter.get(i).listaAdmi.get(j).getCliente().setpTelefono(Telefono); 
                       lista_counter.get(i).listaAdmi.get(j).getCliente().setpDireccion(Direccion); 
                       lista_counter.get(i).listaAdmi.get(j).getCliente().setpSexo(sexo_boolean); 
                       lista_counter.get(i).listaAdmi.get(j).getCliente().setFechaDeNacimiento(Fecha);                        
                    }                    
                }
            }
        }
    }
    
    public int AsignarCasillero(int id_counter){
        int cas = 1000;
        
        for(int i= 0; i < lista_counter.size();i++){  
            if(lista_counter.get(i).getCedulaJuridica() == id_counter){                                              
                for(int j = 0; j<lista_counter.get(i).listaAdmi.size();j++){                                            
                        cas = (lista_counter.get(i).listaAdmi.get(j).getCasillero().getNumero()) + 1;                        
                }
                
            }
        } 
        return cas;
    }
      
    
    public ArrayList<Cliente> ObtenerClientes(int id_counter,int id_cliente){
        ArrayList<Cliente> cl = new ArrayList<Cliente>();
        
        for(int i = 0; i<lista_counter.size();i++){               
                if(lista_counter.get(i).getCedulaJuridica() == id_counter){
                   
                    ArrayList<AdministradorClientes> admin = lista_counter.get(i).getListaAdmi();
                    
                    if(id_cliente == 0){
                        for(int j = 0; j <admin.size();j++){
                            cl.add(admin.get(j).getCliente());                        
                        }
                    }else{
                        for(int j = 0; j <admin.size();j++){
                            if(admin.get(j).getCliente().getpId() == id_cliente){
                                cl.add(admin.get(j).getCliente());
                                break;
                            }                                                    
                        }
                    }                                        
               }
        }        
        return cl;
    }
    
    
    public ArrayList<Casillero> ObtenerCasilleros(int id_counter,int id_cliente){
        ArrayList<Casillero> cl = new ArrayList<Casillero>();
        
        for(int i = 0; i<lista_counter.size();i++){               
                if(lista_counter.get(i).getCedulaJuridica() == id_counter){
                   
                    ArrayList<AdministradorClientes> admin = lista_counter.get(i).getListaAdmi();
                    
                    if(id_cliente == 0){
                        for(int j = 0; j <admin.size();j++){
                            cl.add(admin.get(j).getCasillero());                        
                        }
                    }else{
                        for(int j = 0; j <admin.size();j++){
                            if(admin.get(j).getCliente().getpId() == id_cliente){
                                cl.add(admin.get(j).getCasillero());
                                break;
                            }                                                    
                        }
                    }                                        
               }
        }        
        return cl;
    }
    
    
 
    public AdministradorClientes buscarCliente(int id, String id_counter){                   
           for (int i = 0; i < lista_counter.size(); i++) {
               
               if(lista_counter.get(i).getNombre().equalsIgnoreCase(id_counter)){
                   
               }
            AdministradorClientes buscado = listaAdmi.get(i);
            if (buscado.getCliente().getpId() == id){
                
                System.out.println("Encontrado: ");
                System.out.println(buscado);
                return buscado;
            }
       }
      
               
        
               
            System.out.println("No se encontro cliente solicitado");
            return null;    
    }
    
    public boolean quitarAdmiCliente( int id){
       
        for (int i = 0; i < listaAdmi.size(); i++) {
            AdministradorClientes buscado = listaAdmi.get(i);
            if (buscado.getCliente().getpId() == id){
                listaAdmi.remove(i);
                return true;
            }
        }
                
         System.out.println("No se encontro Cliente solicitado");
         return false; 
    }
    
 
//    public ArrayList<Cliente> mostrarClientes() {
//        ArrayList<Cliente> auxClient = null;
//        for(int i=0; i<listaAdmi.size();i++){
//            AdministradorClientes imprimir= listaAdmi.get(i);
//            listaClientes.add(imprimir.getCliente());
//        }
//        auxClient = listaClientes;
//        listaClientes.clear();
//        return listaClientes;
//    }
    
    public boolean registrarEntregable(Entregable entrega){
        String duenno= entrega.getRemitente();

        for (int i = 0; i < listaAdmi.size(); i++) {
            AdministradorClientes sacado = listaAdmi.get(i);
            if (sacado.getCliente().getpId()== entrega.getIdRemitente()){
               int recibidos = sacado.getCantidadRecibidos();
               sacado.setCantidadRecibidos(recibidos+1);
               sacado.getCasillero().annadirEntregable(entrega);
               //AQUI VA LA FUNCION PARA ENVIAR EL CORREO
               return true;
             }
        }   
        return false;
        
    }
    
    public boolean retirarEntregable(int idCliente, int id) {
        for (int i = 0; i < listaAdmi.size(); i++) {
            AdministradorClientes sacado = listaAdmi.get(i);
            if (sacado.getCliente().getpId()== idCliente){
               for (int j = 0; j<(sacado.getCasillero().getListaEntregables()).size(); i++){
                   Entregable aRetirar= sacado.getCasillero().getListaEntregables().get(j);
                   if (aRetirar.getId()== id){
                       aRetirar.setEstado(false);
                   }
               }
               //sacado.getCasillero().annadirEntregable(entrega);
               //AQUI VA LA FUNCION PARA ENVIAR EL CORREO
               return true;
             }
        }   
        return false;
    }
    
    public int ObtenerIDCasilleros(int id_counter,int id_cliente){        
        int idcasillero = 0;
        for(int i = 0; i<lista_counter.size();i++){               
                if(lista_counter.get(i).getCedulaJuridica() == id_counter){                   
                    ArrayList<AdministradorClientes> admin = lista_counter.get(i).getListaAdmi();                   
                        for(int j = 0; j <admin.size();j++){
                            if(admin.get(j).getCliente().getpId() == id_cliente){                                
                                idcasillero = admin.get(j).getCasillero().getNumero();                                
                            }                                                    
                        }                                                           
               }
        }        
        return idcasillero;
    }

/*
    public ArrayList<Cliente> mostrarClientePendiente(){
        ArrayList<Cliente> auxCliente;
        for (int i = 0; i < listaAdmi.size(); i++) {
            AdministradorClientes client = listaAdmi.get(i);
            if (client.) ){
                listaFechaIngreso.add(entregables);
                
             }
        }
        ArrayList<Entregable> auxIngreso = listaFechaIngreso;
        listaFechaIngreso.clear();
        return auxIngreso;
    }    
    
*/
    @Override
    public String toString() {
        return "Counter{" + "nombre=" + nombre + ", cedulaJuridica=" + cedulaJuridica + ", direccion=" + direccion + ", cantCasilleros=" + cantCasilleros + ", listaCasillero=" + listaCasillero + ", listaAdmi=" + listaAdmi + '}';
    }


//    public ArrayList<Cliente> mostrarClientePendiente(){
//        ArrayList<Cliente> auxCliente;
//        
//        for (int i = 0; i < listaAdmi.size(); i++) {
//            AdministradorClientes client = listaAdmi.get(i);
//          
//            }
//                
//             }
//        }
//        ArrayList<Entregable> auxIngreso = listaFechaIngreso;
//        listaFechaIngreso.clear();
//        return auxIngreso;
//    }    
//    
//
//    @Override
//    public String toString() {
//        return "Counter{" + "nombre=" + nombre + ", cedulaJuridica=" + cedulaJuridica + ", direccion=" + direccion + ", cantCasilleros=" + cantCasilleros + ", listaCasillero=" + listaCasillero + ", listaAdmi=" + listaAdmi + '}';
//    }

    
    
    
}
        
        

    


    
        

