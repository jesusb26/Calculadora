/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.seed;

/**
 *
 * @author DOCENTE
 */
public class Pila<T> {

    private ListaCD<T> lista = new ListaCD<>();

    public Pila() {
    }

    public void push(T info) {

        this.lista.insertarInicio(info);

    }

    public T pop() {

        return this.lista.remove(0);

    }
    
    public int size(){
    
    return lista.getSize();
    }

    public T peek() {
        
        return this.lista.get(0);
        
    }

    public boolean isEmpty() {

        return this.lista.isEmpty();

    }

    @Override
    public String toString() {
        return lista.toString();
    }
    
    

}
