/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.seed;

import java.util.Iterator;

/**
 *
 * @author MARCO ADARME
 */
class IteratorLCD<T> implements Iterator<T> {

    private NodoD<T> cabeza;
    private NodoD<T> pos;

    IteratorLCD(NodoD<T> cabeza) {
        this.cabeza = cabeza;
        this.pos=this.cabeza.getSig();
    }

    @Override
    public boolean hasNext() {
        return this.pos != this.cabeza;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new RuntimeException("No hay mas nodos por recorrer");
        }
        T info = this.pos.getInfo();
        this.pos = this.pos.getSig();
        return info;
    }

}
