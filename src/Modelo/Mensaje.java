package Modelo;

import Util.seed.ListaCD;

public class Mensaje {

    
    protected ListaCD<String> mensajes;

    public Mensaje() {
        mensajes = new ListaCD();
    }

    public void addMensaje(String mensaje) {
        mensajes.insertarFinal(mensaje);
    }

    public ListaCD<String> getMensajes() {
        return mensajes;
    }

    @Override
    public String toString() {
        String msg = "";
        for (String m : mensajes) {
            msg += m + "&";
        }
        return msg;
    }
}
