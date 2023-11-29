/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util.seed;

/**
 *
 * @author Docente
 */
public class ListaS<T> {

    private Nodo<T> cabeza;
    private int size;

    public ListaS() {
        this.cabeza = null; //no es necesario
        this.size = 0;
    }

    public void insertarInicio(T info) {
        Nodo<T> nuevo = new Nodo(info, this.cabeza);
        this.cabeza = nuevo;
        this.size++;

    }

    public void insertarFin(T info) {
        if (this.isEmpty()) {
            this.insertarInicio(info);
        } else {
            Nodo<T> x = getPos(this.size - 1); //mètodo encuentra la direcciòn de memoria del nodo que està de ùltimo
            Nodo<T> nuevo = new Nodo(info, null);
            x.setSig(nuevo);
            this.size++;
        }
    }

    public T get(int i) {
        return this.getPos(i).getInfo();
    }

    public void set(int i, T info) {
        this.getPos(i).setInfo(info);
    }

    private Nodo<T> getPos(int pos) {
        if (this.isEmpty()) {
            throw new RuntimeException("Lista vacìa, no hay posiciones");
        }
        if (pos < 0 || pos >= this.size) {
            throw new RuntimeException("Posición fuera de rango, esta debe ser entre:0 y " + (this.size - 1));
        }

        Nodo<T> temp = this.cabeza;
        //int i=0;
        while (pos > 0) //i<pos
        {
            temp = temp.getSig();
            pos--; //i++
        }
        return temp;
    }

    @Override
    public String toString() {
        String msg = "";
        for (Nodo<T> x = this.cabeza; x != null; x = x.getSig()) {
            msg += x.toString() + "->";
        }
        return "cab->" + msg + "null\n Contiene:" + this.size + " elementos";
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void insertarOrdenado(T info){
        if (this.isEmpty())
            this.insertarInicio(info);
        else{
            Nodo<T> x=this.cabeza;
            Nodo<T> y=x;
                while(x!=null){
                    Comparable comparador=(Comparable)info;
                    int rta=comparador.compareTo(x.getInfo());
                    if(rta<0)
                        break;
                    y=x;
                    x=x.getSig();
                }
            if(x==y)
                this.insertarInicio(info);
            else{
                y.setSig(new Nodo(info, x));
                this.size++;
                }
            }
    }

    
}
