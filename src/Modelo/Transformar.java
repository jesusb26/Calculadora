/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class Transformar {
    
  
    private String operacion;
    private String pila;
    private String salida;

    public Transformar(String operacion, String pila, String salida) {
        this.operacion = operacion;
        this.pila = pila;
        this.salida = salida;
    }

    public Transformar(String operacion, String pila) {
        this.operacion = operacion;
        this.pila = pila;
    }

    public String getOperacion() {
        return operacion;
    }

    public String getPila() {
        return pila;
    }

    public String getSalida() {
        return salida;
    }
}
