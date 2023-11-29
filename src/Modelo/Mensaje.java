
package Modelo;

import Util.seed.ListaCD;


public class Mensaje {
    
    protected ListaCD<String> mensajes;

    public Mensaje() {
        mensajes=new ListaCD();
    }
    
    public void addMensaje(String mensaje){
    mensajes.insertarFinal(mensaje);
    }
    

    
    

}
