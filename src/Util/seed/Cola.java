/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.seed;

/**
 *
 * @author DOCENTE
 */
public class Cola<T> {

    private ListaCD<T> lista = new ListaCD<>();

    public Cola() {
    }

    public void enColar(T info) {

        this.lista.insertarFinal(info);

    }

    public T deColar() {

        return this.lista.remove(0);

    }

    public boolean isEmpty() {

        return this.lista.isEmpty();

    }

}


