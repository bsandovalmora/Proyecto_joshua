/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.*;
import java.net.*;
import java.util.List;      
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;    
import org.jdom2.input.SAXBuilder;

public class Web_Service {

    public Web_Service() {
    }
    
     public String Send_Request_Soap(String Indicador, String FechaInicio, String FechaFinal, String Nombre){
        
         String moneda = "";                  
         
         try{
            String url = "http://indicadoreseconomicos.bccr.fi.cr/indicadoreseconomicos/WebServices/wsIndicadoresEconomicos.asmx?op=ObtenerIndicadoresEconomicosXML";
            
            URL obj = new URL(url);
            
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
            //con.setRequestProperty("Content-Length", "length");
                                    
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                            "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                            "  <soap12:Body>\n" +
                            "    <ObtenerIndicadoresEconomicosXML xmlns=\"http://ws.sdde.bccr.fi.cr\">\n" +
                            "      <tcIndicador>"+Indicador+"</tcIndicador>\n" +
                            "      <tcFechaInicio>"+FechaInicio+"</tcFechaInicio>\n" +
                            "      <tcFechaFinal>"+FechaFinal+"</tcFechaFinal>\n" +
                            "      <tcNombre>"+Nombre+"</tcNombre>\n" +
                            "      <tnSubNiveles>N</tnSubNiveles>\n" +
                            "    </ObtenerIndicadoresEconomicosXML>\n" +
                            "  </soap12:Body>\n" +
                            "</soap12:Envelope>";
            
            con.setDoOutput(true);
            
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            
            out.writeBytes(xml);
            out.flush();
            out.close();            
                        
            String Status = con.getResponseMessage();            
            
            BufferedReader input = new BufferedReader(new InputStreamReader(con.getInputStream()));
            
            con.getInputStream();
            
            String inputLine;
            
            StringBuilder response = new StringBuilder();
                                    
            while((inputLine = input.readLine()) != null ){
                response.append(inputLine);
            }
            
            input.close();                                       
                        
            CrearXml(String_Repair(response.toString()));
            
            moneda = Moneda();
                                    
            
        }catch(Exception e){
            System.out.println("Error"+e.getMessage());
        }
        
        return moneda;
    }
    
    public static void CrearXml(String xml){
        File f = new File("Soap_Moneda.xml");
        try{
            if(!f.exists()){
                f.createNewFile();
            }
            
            BufferedWriter in = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f,false),"utf-8"));
            
            in.write(xml);
            in.close();
        }catch(Exception e){
            System.err.println("ERROR"+e.getMessage());
        }
    }       
    
    public static String String_Repair(String xml){
        
        String repair = "";
        
        String paso_1 = xml.replace("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><ObtenerIndicadoresEconomicosXMLResponse xmlns=\"http://ws.sdde.bccr.fi.cr\"><ObtenerIndicadoresEconomicosXMLResult>", "");
                
        String paso_2 = paso_1.replace("&lt;","<");                        
            
        String paso_3 = paso_2.replace("&gt;",">");                    
            
        repair = paso_3.replace("</ObtenerIndicadoresEconomicosXMLResult></ObtenerIndicadoresEconomicosXMLResponse></soap:Body></soap:Envelope>", "");
                            
        return repair;
    }      
    
    public static String Moneda(){
                       
        String moneda = "";
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("Soap_Moneda.xml");
        try{
            
            Document document = (Document) builder.build( xmlFile );
        
            Element rootNode = document.getRootElement();
            
            List list = rootNode.getChildren("INGC011_CAT_INDICADORECONOMIC");            
            
            for(int i = 0; i < list.size();i++)
            {              
                Element datos = (Element) list.get(i);
                
                moneda = datos.getChildTextTrim("NUM_VALOR");
                
            }
            
        }catch ( IOException io ) {
        System.out.println( io.getMessage() );
        }catch ( JDOMException jdomex ) {
            System.out.println( jdomex.getMessage() );
        }
        return moneda;
    }
    
}
