/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

/**
 *
 * @author luisf
 */
public class Controlador implements ActionListener{
    private Controlador singlenton;
    
    //Vistas
    
    public AdministracionEntregables vista_AdmiEntregable = new AdministracionEntregables();
    public Principal p;
    public CreacionCounter vista_counter; 
    public vista.AdministradorClientes vista_AdminClientes = new vista.AdministradorClientes(); 
    public Interfaz vista_interfaz = new Interfaz();
    public TablaCounters vista_tablaCounter = new TablaCounters();
    public RecepcionArticulos vista_RecepcionArticulos = new RecepcionArticulos();
    
    //Modelos           
    public Counter model_counter;
    public modelo.AdministradorClientes model_AdminClientes = new modelo.AdministradorClientes();   
    public AdministradorEntregables model_Admin_Entre = new AdministradorEntregables();
        
    private ArrayList<Cliente> listaClientes;
    private String Venta;
    private String Compra;
    
    public Web_Service service = new Web_Service();
    
    /*
    *Este metodo retorna la instancia del controlador
    *@return singlenton: retorna la instancia
    */
    
    /*
    public Controlador getInstance(){
        if(singlenton == null){
            singlenton = new Controlador();
            return singlenton;
        }
        return singlenton;
    }
    */
    
    public Controlador(Principal p,CreacionCounter vista_counter, Counter model_counter){
        this.p = p;
        this.model_counter = model_counter;
        this.vista_counter = vista_counter;
    }
    
    /*
    *Este metodo registra un counter al programa
    *@param pNombre : Nombre del counter
    *@param pId : Identificacion del counter a crear
    *@param pDireccion : Direccion fisica del counter
    *@param pCantCasilleros : Numero de casilleros del counter
    *@return true: Si el counter se agrego correctamente
    *@return false: Si el counter ya existia, no se agrega
    */
    
    public void Iniciar(){
        this.vista_counter.setTitle("Creación Counter");
        this.vista_counter.setLocationRelativeTo(null);
        
        this.vista_counter.jButtonCrearCounter.addActionListener(this);
        this.vista_tablaCounter.jButton1.addActionListener(this);     
        this.p.jButton1.addActionListener(this); 
        this.vista_interfaz.BtnAdministracionClientes.addActionListener(this);
        this.vista_AdminClientes.Registrar.addActionListener(this);
        this.vista_AdminClientes.Buscar.addActionListener(this);
        this.vista_AdminClientes.Actualizar.addActionListener(this);
        this.vista_AdminClientes.Eliminar.addActionListener(this);
        this.vista_AdminClientes.Modificar.addActionListener(this);
        
        this.vista_interfaz.BtnRecepcionArticulos.addActionListener(this);
        this.vista_RecepcionArticulos.registrar.addActionListener(this);
        this.vista_AdmiEntregable.btnIdCliente.addActionListener(this);
        this.vista_interfaz.BtnAdministracionEntregables.addActionListener(this);
        this.vista_AdmiEntregable.btnFIngreso.addActionListener(this);
        this.vista_AdmiEntregable.btnFRetiro.addActionListener(this);
        this.vista_AdmiEntregable.btnPendientes.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e){
        //Evento de la vista principal boton (Ver Counter) para mostrar la vista tabla counter 
        if(e.getSource() == p.jButton1){
            vista_tablaCounter.show();
        }
        
        // Vista Creacion, al dar clic al boton CREAR se aplica la accion siguiente
        
        if(e.getSource() == vista_counter.jButtonCrearCounter){
            String nombre = vista_counter.txt_nombre.getText();
            int cedula = Integer.parseInt(vista_counter.txt_CedulaJuridica.getText());
            String dir = vista_counter.txt_Dir.getText();
            int cantidad = Integer.parseInt(vista_counter.txt_cantidad.getText());                
            
            Date date1 = null;
            try {
                
               date1=new SimpleDateFormat("dd/MMM/yyyy").parse("31/oct/1998");
            } catch (ParseException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
            Casillero casillero = new Casillero(1000, true);
             
            Cliente cliente = new Cliente(305110632, "Brandon", "bsandovalmora@gmail.com", "88888888", "Tejar", true, date1);
            
            model_AdminClientes.Insertar(new modelo.AdministradorClientes(cliente, casillero, 0, 0));
            
            model_counter.InsertarCounter(new Counter(nombre, cedula, dir, cantidad,model_AdminClientes.TodosCasilleros(),model_AdminClientes.Lista_AdminClientes));                                     
            
            vista_interfaz.txt_titulo.setText(nombre);
            vista_interfaz.txt_titulo_id.setText(String.valueOf(cedula));
            vista_interfaz.show();                        
            
        }
               
        //Evento de la vista tabla counter boton Buscar para mostrar la informacion en la tabla
        if(e.getSource() == vista_tablaCounter.jButton1){
            Tabla_tablaCounter(vista_tablaCounter.tabla_counter);            
        }
        
        //Evento de vista Interfaz, boton Administrador de clientes, muestra vista vista_AdminClientes
        if(e.getSource() == vista_interfaz.BtnAdministracionClientes){
            vista_AdminClientes.show();
        }
        //Eventos de la vista Administrador clientes
        //Evento # 1 - RegistrarCliente
        if(e.getSource() == vista_AdminClientes.Registrar){
            
            RegistarCliente();
            
            //mostrar los datos clientes en la tabla
            Tabla_AdminClientes(vista_AdminClientes.jTable1,0);
                                    
        }          
        //Evento #2 buscar por id_cliente
        if(e.getSource() == vista_AdminClientes.Buscar){
            //int id_cliente = Integer.parseInt(vista_AdminClientes.txt_cedula.getText());
            String id_cliente = JOptionPane.showInputDialog(null,"ID Cliente");
            Tabla_AdminClientes(vista_AdminClientes.jTable1,Integer.valueOf(id_cliente));
        }
        
        //Evento #3 Actualizar tabla
        if(e.getSource() == vista_AdminClientes.Actualizar){
            Tabla_AdminClientes(vista_AdminClientes.jTable1, 0);
        }
        
        //Evento #4 Eliminar cliente por id solo seleccionando la fila de tabla vista Administrador clientes
        if(e.getSource() == vista_AdminClientes.Eliminar){
            Eliminar();    
            Tabla_AdminClientes(vista_AdminClientes.jTable1, 0);
        }
        
        //Evento #5 Modificar cliente por id solo seleccionando la fila de tabla vista Administrador clientes
        if(e.getSource() == vista_AdminClientes.Modificar){
            Modificar();
            Tabla_AdminClientes(vista_AdminClientes.jTable1, 0);
        }
        //Fin de eventos de la vista Administrador de clientes
        
        //Eventos de la vista Recepcion de articulos
        //Mostrar formulario recepcion de articulos
        if(e.getSource() == vista_interfaz.BtnRecepcionArticulos){
            vista_RecepcionArticulos.show();
        }
        
        //Evento #1 Registro de articulo recepcion de articulos
        if(e.getSource() == vista_RecepcionArticulos.registrar){
            Agregar_Articulo();
        }
        //mostrar Entregables por id del cliente en la tabla
        if(e.getSource()== vista_AdmiEntregable.btnIdCliente){
            int idClient = Integer.parseInt(vista_AdmiEntregable.txtIdCliente.getText());
            MostrarPorIdCliente(vista_AdmiEntregable.jTable1, idClient);
        }
        //Mostrar vista Admi entregable
        if(e.getSource()==vista_interfaz.BtnAdministracionEntregables){
            vista_AdmiEntregable.show();
        }
        //Mostrar por fecha de ingreso
        if(e.getSource() == vista_AdmiEntregable.btnFIngreso){
            Date date = vista_AdmiEntregable.dateIngreso.getDate();  
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
            String strDate = dateFormat.format(date);
            MostarPorFechaIngreso(vista_AdmiEntregable.jTable1, strDate);
        }   
        //mostrar paquetes por fecha de retiro
        if(e.getSource() == vista_AdmiEntregable.btnFRetiro){
            Date date = vista_AdmiEntregable.dateRetiro.getDate();  
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
            String strDate = dateFormat.format(date);
            MostarPorFechaIngreso(vista_AdmiEntregable.jTable1, strDate);
        }   
        if (e.getSource()==vista_AdmiEntregable.btnPendientes){
            MostrarPendientes(vista_AdmiEntregable.jTable1);
        }
    }
    
    public void MostrarPorIdCliente(JTable tableAdmiEntre, int idCliente ) {
        ArrayList<Entregable> buscarPorCliente = model_Admin_Entre.MostrarEntregables(idCliente);
        String filas [][]= new String [buscarPorCliente.size()][5];
        for (int i = 0; i < buscarPorCliente.size(); i++) {
            Entregable getEntrega = buscarPorCliente.get(i);
            filas[i][0] = String.valueOf(getEntrega.getIdRemitente());
            filas[i][1] = String.valueOf(getEntrega.getRemitente());
            filas[i][2] = String.valueOf(getEntrega.getId());
            if (getEntrega.getEstado()){
                filas[i][3] = "NO RETIRADO";
            }else{
                filas[i][3] = "RETIRADO";
            }
            
            filas[i][4] = String.valueOf(getEntrega.getCostoArticulo());
            System.out.println(getEntrega.getRemitente());
        }
        vista_AdmiEntregable.jTable1.setModel(new javax.swing.table.DefaultTableModel(
            filas,
            new String [] {
                "ID Cliente", "Casillero", "ID Entregable", "Estado", "Costo"
            }
        ));
                
    }
    
    
    
    public void Agregar_Articulo(){
        String descripcion = vista_RecepcionArticulos.txt_des.getText();
        int id_Remitente = Integer.parseInt(vista_RecepcionArticulos.txt_idremitente.getText());
        Date date = vista_RecepcionArticulos.txt_fecharetiro.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
        String FechaRetiro = dateFormat.format(date);
        long peso = Long.parseLong(vista_RecepcionArticulos.txt_peso.getText());
        long costo = Long.parseLong(vista_RecepcionArticulos.txt_costo.getText());
        
        //Sobre
        String Contenido = vista_RecepcionArticulos.txt_contenido_sobre.getText();
        String sobre_tipo = vista_RecepcionArticulos.txt_tiposobre.getSelectedItem().toString();
        //Paquete
        String paquete_fragil = vista_RecepcionArticulos.txt_paquete_fragil.getSelectedItem().toString();
        String paquete_empaque = vista_RecepcionArticulos.txt_paquete_Tipoentrega.getSelectedItem().toString();
        String paquete_electronico = vista_RecepcionArticulos.txt_paquete_electronico.getSelectedItem().toString();
        //Revista
        String nombre_revista = vista_RecepcionArticulos.txt_revista_nombre.getText();
        String tema_revista = vista_RecepcionArticulos.txt_revista_tema.getSelectedItem().toString();
        String catalogo_revista = vista_RecepcionArticulos.txt_revista_catalogo.getSelectedItem().toString();
        String tipo_articulo = vista_RecepcionArticulos.txt_tipoarticulo.getSelectedItem().toString();
        
        boolean tipo= false;
        
        //Aereo, Manila
        if(sobre_tipo.equalsIgnoreCase("Aereo")){
            tipo = false;
        }else{
            tipo= true;
        }                                
        
        boolean paquete_fragil_boolean = false;
        if(paquete_fragil.equalsIgnoreCase("NO")){
            paquete_fragil_boolean = false;
        }else{
            paquete_fragil_boolean= true;
        }
        
        boolean paquete_empaque_boolean = false;
        if(paquete_empaque.equalsIgnoreCase("Bolsa")){
            paquete_empaque_boolean = false;
        }else{
            paquete_empaque_boolean= true;
        }
        
        boolean paquete_electronico_boolean = false;
        if(paquete_electronico.equalsIgnoreCase("NO")){
            paquete_electronico_boolean = false;
        }else{
            paquete_electronico_boolean= true;
        }
                        
        boolean revista_catalogo_boolean = false;
        if(catalogo_revista.equalsIgnoreCase("NO")){
            revista_catalogo_boolean = false;
        }else{
            revista_catalogo_boolean= true;
        }        
        
        Paquete paquete = new Paquete(paquete_fragil_boolean, paquete_empaque_boolean, paquete_electronico_boolean);
        Revista revista = new Revista(nombre_revista, tema_revista, revista_catalogo_boolean);
        Sobre sobre = new Sobre(true, Contenido);
        
        int id_cliente_casillero = model_counter.ObtenerIDCasilleros(Integer.parseInt(vista_interfaz.txt_titulo_id.getText()), id_Remitente);
        
        Entregable entregable = new Entregable(String.valueOf(id_cliente_casillero),1, tipo_articulo, true, descripcion, id_Remitente, getDate(), FechaRetiro, peso, sobre, paquete, revista, 0);
        
        model_Admin_Entre.AnnadirEntrega(entregable);
        
    }
    
    public void Modificar(){
        if(vista_AdminClientes.txt_cedula.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_correo.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_direccion.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_nombre.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_telefono.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Error campos vaciós");
        }else{
            int Cedula = Integer.parseInt(vista_AdminClientes.txt_cedula.getText());
            String Nombre = vista_AdminClientes.txt_nombre.getText();
            String C1 = vista_AdminClientes.txt_correo.getText();
            String C2 = vista_AdminClientes.txt_tipoCorreo.getSelectedItem().toString();
            String Correo = (C1+""+C2);
            String Telefono = vista_AdminClientes.txt_telefono.getText();
            String Direccion = vista_AdminClientes.txt_direccion.getText();
            String Sexo = vista_AdminClientes.txt_sexo.getSelectedItem().toString();            
            Date Fecha = vista_AdminClientes.txt_fecha.getDate();            
             
            boolean sexo_boolean;
            if(Sexo.equalsIgnoreCase("Hombre"))
                sexo_boolean = true;
            else
                sexo_boolean = false;
            
            //metodo modificar
            model_counter.Modificar(Cedula, Nombre, Correo, Telefono, Direccion, Fecha, sexo_boolean, Integer.parseInt(vista_interfaz.txt_titulo_id.getText()));
            
            vista_AdminClientes.Modificar.setEnabled(false);
            
        }
    }    
    //Metodo eliminar cliente
    public void Eliminar(){
        int id_cliente = Integer.parseInt(vista_AdminClientes.SelectFila);        
        int id_counter = Integer.parseInt(vista_interfaz.txt_titulo_id.getText());
        model_counter.Eliminar(id_cliente, id_counter);
        
        vista_AdminClientes.Eliminar.setEnabled(false);
    }
    
    //Metodo para llenar la tabla de la vista Administrar clientes
    public void Tabla_AdminClientes(JTable table,int id_cliente){
        ArrayList<Cliente> cl = model_counter.ObtenerClientes(Integer.parseInt(vista_interfaz.txt_titulo_id.getText()),id_cliente);
        ArrayList<Casillero> ca = model_counter.ObtenerCasilleros(Integer.parseInt(vista_interfaz.txt_titulo_id.getText()),id_cliente);
        String filas [] [] = new String[cl.size()][8];        
        for(int i = 0;i<cl.size();i++){
            filas[i][0] = String.valueOf(cl.get(i).getpId());
            filas[i][1] = cl.get(i).getpNombre();
            filas[i][2] = cl.get(i).getpCorreo();
            filas[i][3] = String.valueOf(cl.get(i).getpTelefono());
            filas[i][4] = cl.get(i).getpDireccion();            
            if(cl.get(i).getpSexo() == true)
                filas[i][5] = "Hombre";
            else
                filas[i][5] = "Mujer";
            
            Date date = cl.get(i).getFechaNacimiento();  
            DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");  
            String strDate = dateFormat.format(date); 
            
            filas[i][6] = strDate;
            filas[i][7] = String.valueOf(ca.get(i).getNumero());
        }
        
        table.setModel(new javax.swing.table.DefaultTableModel(
            filas,
            new String [] {
                "Cédula", "Nombre", "Correo", "Teléfono", "Dirección", "Sexo", "Fecha Nacimiento", " # Casillero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
    }
    
    //Metodo para registrar cliente
    public void RegistarCliente(){
        
        if(vista_AdminClientes.txt_cedula.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_correo.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_direccion.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_nombre.getText().equalsIgnoreCase("") || vista_AdminClientes.txt_telefono.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Error campos vaciós");
        }else{
            int Cedula = Integer.parseInt(vista_AdminClientes.txt_cedula.getText());
            String Nombre = vista_AdminClientes.txt_nombre.getText();
            String C1 = vista_AdminClientes.txt_correo.getText();
            String C2 = vista_AdminClientes.txt_tipoCorreo.getSelectedItem().toString();
            String Correo = (C1+""+C2);
            String Telefono = vista_AdminClientes.txt_telefono.getText();
            String Direccion = vista_AdminClientes.txt_direccion.getText();
            String Sexo = vista_AdminClientes.txt_sexo.getSelectedItem().toString();            
            Date Fecha = vista_AdminClientes.txt_fecha.getDate();            
             
            boolean sexo_boolean;
            if(Sexo.equalsIgnoreCase("Hombre"))
                sexo_boolean = true;
            else
                sexo_boolean = false;
                        
            Cliente cliente = new Cliente(Cedula, Nombre, Correo, Telefono, Direccion, sexo_boolean, Fecha);
            
            int id_cas = model_counter.AsignarCasillero(Integer.parseInt(vista_interfaz.txt_titulo_id.getText()));
            Casillero casillero = new Casillero(id_cas, true);            
            
            model_AdminClientes.Insertar(new modelo.AdministradorClientes(cliente, casillero, 0, 0));
            
            int Titulo_interfaz_id = Integer.parseInt(vista_interfaz.txt_titulo_id.getText());
            
            model_counter.annadir_AdmiCliente_Casilero(Titulo_interfaz_id, model_AdminClientes);
            
            vista_AdminClientes.txt_cedula.setText("");
            vista_AdminClientes.txt_correo.setText("");
            vista_AdminClientes.txt_direccion.setText("");
            vista_AdminClientes.txt_nombre.setText("");
            vista_AdminClientes.txt_telefono.setText("");
            
            //se le avisa al usuario del numero de casillero            
            JOptionPane.showMessageDialog(null, "Datos correctos \n "+Nombre+" \n "+Cedula+"\n  Numero de casillero: "+id_cas);
            
        }
        
        
            
                      
    }
    
    
    //Metodo para llenar la tabla de la vista Tablas_Counters
    public void Tabla_tablaCounter(JTable table){
        String filas [][] = new String[model_counter.lista_counter.size()][4];
        
        for(int i = 0; i < model_counter.lista_counter.size();i++){
            filas[i][0] = model_counter.lista_counter.get(i).getNombre();
            filas[i][1] = String.valueOf(model_counter.lista_counter.get(i).getCedulaJuridica());
            filas[i][2] = model_counter.lista_counter.get(i).getDireccion();
            filas[i][3] = String.valueOf(model_counter.lista_counter.get(i).getCantCasilleros());
        }
        
        table.setModel(new javax.swing.table.DefaultTableModel(
            filas,
            new String [] {
                "Nombre", "Cédula Juridica", "Dirección", "Casilleros Disponibles"
            }
        ));
    }
    
    public ArrayList<Counter> MostrarCounter(){
        
        return model_counter.lista_counter;
        
    }
    
    public void Inicar_Mostrar_Counter(){
        vista_tablaCounter = new TablaCounters();
        vista_tablaCounter.show();
        
    }
    
    
    public void MostarPorFechaIngreso(JTable tabla, String date){
        ArrayList<Entregable> buscar = model_Admin_Entre.MostrarFechaIngreso(date);
        String filas [][]= new String [buscar.size()][5];
        for (int i = 0; i < buscar.size(); i++) {
            Entregable getEntrega = buscar.get(i);
            filas[i][0] = String.valueOf(getEntrega.getIdRemitente());
            filas[i][1] = String.valueOf(getEntrega.getRemitente());
            filas[i][2] = String.valueOf(getEntrega.getId());
            if (getEntrega.getEstado()){
                filas[i][3] = "NO RETIRADO";
            }else{
                filas[i][3] = "RETIRADO";
            }
            
            filas[i][4] = String.valueOf(getEntrega.getCostoArticulo());
            System.out.println(getEntrega.getRemitente());
        }
        vista_AdmiEntregable.jTable1.setModel(new javax.swing.table.DefaultTableModel(
            filas,
            new String [] {
                "ID Cliente", "Casillero", "ID Entregable", "Estado", "Costo"
            }
        ));
    }
    
     public void MostarPorFechaRetiro(JTable tabla, String date){
        ArrayList<Entregable> buscar = model_Admin_Entre.mostrarFechaRetiro(date);
        String filas [][]= new String [buscar.size()][5];
        for (int i = 0; i < buscar.size(); i++) {
            Entregable getEntrega = buscar.get(i);
            filas[i][0] = String.valueOf(getEntrega.getIdRemitente());
            filas[i][1] = String.valueOf(getEntrega.getRemitente());
            filas[i][2] = String.valueOf(getEntrega.getId());
            if (getEntrega.getEstado()){
                filas[i][3] = "NO RETIRADO";
            }else{
                filas[i][3] = "RETIRADO";
            }
            
            filas[i][4] = String.valueOf(getEntrega.getCostoArticulo());
            System.out.println(getEntrega.getRemitente());
        }
        vista_AdmiEntregable.jTable1.setModel(new javax.swing.table.DefaultTableModel(
            filas,
            new String [] {
                "ID Cliente", "Casillero", "ID Entregable", "Estado", "Costo"
            }
        ));
    }
    
    /*
    *Este metodo registra un cliente en el programa
    *@param pId: Identificacion del cliente
    *@param pNombre: Nombre del cliente
    *@param pCorreo: Correo del cliente
    *@param pTelefono: Telefono del cliente
    *@param pDireccion: Direccion del cliente
    *@param esMujer: Si la persona es mujer
    *@param fechaDeNacimiento: Fecha de nacimiento de persona
    *@return true: El cliente se registro correctamente
    *@return false: El cliente no se pudo registrar porque ya existe
    */
    public boolean registrarCliente(int pId, String pNombre, String pCorreo,
            int pTelefono, int pDireccion, boolean esMujer, String fechaDeNacimiento){
       
        
        Cliente posible = new Cliente(pId,pNombre,pCorreo,pTelefono,pDireccion,esMujer,fechaDeNacimiento);
        for(int i = 0; i < listaClientes.size();i++){
            if(posible.equals(listaClientes.get(i))){
                return false;
            }
        }
        listaClientes.add(posible);
        return true;
    }
    /*
    *Este metodo valida que el formato del numero de telefono sea correcto
    *@param pTelefono: Numero de telefono a verificar
    *@return true: Si el numero tiene 8 digitos
    *@return false: Si el numero no tiene 8 digitos
    */
    private boolean validarTelefono(int pTelefono){
        return ((9999999 < pTelefono) && (pTelefono< 100000000));
    }
    /*
    *Metodo en desarrollo
    */
    private boolean validarCorreo(String pCorreo){
        return true;
        
        
    }
    /*
    *Este metodo modifica el correo de un cliente
    *@param pId: Es el ID del cliente a modificar
    *@param pCorreo: Es el nuevo correo del cliente
    *@return true: En caso de modificar correctamente los datos
    *@return false: En caso de no modificar los datos debido a que no se encontro
    *el ID
    */
    public boolean modificarCorreoCliente(int pId, String pCorreo){
        for(int i = 0; i<listaClientes.size();i++){
            if(listaClientes.get(i).getpId() == pId){
                listaClientes.get(i).setpCorreo(pCorreo);
                return true;
            }
        }
        return false;
    }
    /*
    *Este metodo modifica la direccion de un cliente
    *@param pId: Es el ID del cliente a modificar
    *@param pDireccion: Es la nueva direccion del cliente
    *@return true: En caso de modificar correctamente los datos
    *@return false: En caso de no modificar los datos debido a que no se encontro
    *el ID
    */
    public boolean modificarDireccionCliente(int pId, String pDireccion){
        for(int i = 0; i<listaClientes.size();i++){
            if(listaClientes.get(i).getpId() == pId){
                listaClientes.get(i).setpDireccion(pDireccion);
                return true;
            }
        }
        return false;
    }
    /*
    *Este metodo modifica el telefono de un cliente
    *@param pId: Es el ID del cliente a modificar
    *@param pTelefono: Es el nuevo telefono del cliente
    *@return true: En caso de modificar correctamente los datos
    *@return false: En caso de no modificar los datos debido a que no se encontro
    *el ID
    */
    public boolean modificarTelefonoCliente(int pId, int pTelefono){
        for(int i = 0; i<listaClientes.size();i++){
            if(listaClientes.get(i).getpId() == pId){
                //listaClientes.get(i).setpTelefono(pTelefono);
                return true;
            }
        }
        return false;
    }
    /*
    *Este metodo consulta los datos de un cliente especifico
    *@param pId: Es el ID de la persona a consultar
    *@return : Retorna un string con los datos del cliente o retorna null si
    *no se encuentra el cliente
    */
    public String consultarDatosCliente(int pId){
        for(int i = 0; i<listaClientes.size();i++){
            if(listaClientes.get(i).getpId() == pId){
                return listaClientes.get(i).toString();
            }
        }
        return null;
        
    }
    /*
    *Este metodo elimina un cliente
    *@param pId: La identificacion del cliente a eliminar
    *@return true: En caso de eliminar correctamente 
    *@return false: En caso de no encontrar al cliente a eliminar
    */
    public boolean eliminarCliente(int pId){
        for(int i = 0; i<listaClientes.size();i++){
            if(listaClientes.get(i).getpId() == pId){
                listaClientes.remove(i);
                return true;
                
            }
        }
        return false;
    }
    /*
    *Este metodo retorna los clientes registrados
    *@return listaClientes: Lista de clientes registrados
    */
    public ArrayList<Cliente> obtenerClientesRegistrados(){
        return listaClientes;
    }

    /**
     * @return the Venta
     */
    public String getVenta() {
        
        return service.Send_Request_Soap("318", getDate(), getDate(), "Venta");
    }

    /**
     * @return the Compra
     */
    public String getCompra() {
        return service.Send_Request_Soap("317", getDate(), getDate(), "Compra");
    }
    
    public String getDate(){
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");        
        
        return formatter.format(date);
    }

    private void MostrarPendientes(JTable jTable1) {
        
        ArrayList<Entregable> buscar = model_Admin_Entre.mostrarPorRetirar();
        String filas [][]= new String [buscar.size()][5];
        for (int i = 0; i < buscar.size(); i++) {
            Entregable getEntrega = buscar.get(i);
            filas[i][0] = String.valueOf(getEntrega.getIdRemitente());
            filas[i][1] = String.valueOf(getEntrega.getRemitente());
            filas[i][2] = String.valueOf(getEntrega.getId());
            if (getEntrega.getEstado()){
                filas[i][3] = "NO RETIRADO";
            }else{
                filas[i][3] = "RETIRADO";
            }
            
            filas[i][4] = String.valueOf(getEntrega.getCostoArticulo());
            System.out.println(getEntrega.getRemitente());
        }
        vista_AdmiEntregable.jTable1.setModel(new javax.swing.table.DefaultTableModel(
            filas,
            new String [] {
                "ID Cliente", "Casillero", "ID Entregable", "Estado", "Costo"
            }
        ));
                
    }
    
  
}
