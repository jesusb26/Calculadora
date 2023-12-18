package Negocio;

import Modelo.Ecuacion;
import Modelo.Mensaje;
import Util.seed.Pila;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculadora {

    
    private Ecuacion ecuacion;
    private Mensaje calculoPos;

    public Calculadora() {
        this.ecuacion = new Ecuacion();
        calculoPos = new Mensaje();
    }

    public boolean cargar(String ecuacion) throws RuntimeException {
        boolean valida = this.ecuacion.validarEcuacionInfija(ecuacion);
        if (valida == true) {
            this.ecuacion.setEcuacion(ecuacion);
        }
        return valida;
    }

    public float getCalculo(String ecuacion) throws ArithmeticException {
        Mensaje temp = new Mensaje();
        Pila<Double> pila = new Pila();

        try {
            StringBuilder numeroActual = new StringBuilder();

            for (char token : ecuacion.toCharArray()) {
                if (Character.isDigit(token) || token == '.') {
                    // Si el token es un dígito o un punto decimal, construir el número actual
                    numeroActual.append(token);
                } else if (token == ' ') {
                    // Si es un espacio en blanco, verificar si hay un número acumulado y agregarlo a la pila
                    if (numeroActual.length() > 0) {
                        pila.push(Double.parseDouble(numeroActual.toString()));
                        numeroActual.append(token);
                        temp.addMensaje("Operación: " + numeroActual);
                        temp.addMensaje(pila.toString());
                        numeroActual.setLength(0); // Resetear el StringBuilder
                    }
                } else {
                    // Si el token es un operador, agregar el número actual a la pila y resetear el StringBuilder
                    if (numeroActual.length() > 0) {
                        pila.push(Double.parseDouble(numeroActual.toString()));
                        temp.addMensaje("Operación: " + numeroActual);
                        temp.addMensaje(pila.toString());
                        numeroActual.setLength(0); // Resetear el StringBuilder
                    }

                    // Realizar operación según el operador
                    switch (token) {
                        case '+':
                            if (pila.size() < 2) {
                                throw new ArithmeticException("Error: Operación inválida.");
                            }
                            pila.push(pila.pop() + pila.pop());
                            break;
                        case '-':
                            if (pila.size() < 2) {
                                throw new ArithmeticException("Error: Operación inválida.");
                            }
                            Double sustraendo = pila.pop();
                            pila.push(pila.pop() - sustraendo);
                            break;
                        case '*':
                            if (pila.size() < 2) {
                                throw new ArithmeticException("Error: Operación inválida.");
                            }
                            pila.push(pila.pop() * pila.pop());
                            break;
                        case '/':
                            if (pila.size() < 2) {
                                throw new ArithmeticException("Error: Operación inválida.");
                            }
                            Double divisor = pila.pop();
                            if (divisor == 0) {
                                throw new ArithmeticException("Error: División entre cero.");
                            }
                            pila.push(pila.pop() / divisor);
                            break;
                        default:
                            throw new ArithmeticException("Error: Operador no reconocido.");
                    }

                    temp.addMensaje("Operación: " + token);
                    temp.addMensaje(pila.toString());
                }
            }

            // Manejar el último número en la expresión
            if (numeroActual.length() > 0) {
                pila.push(Double.parseDouble(numeroActual.toString()));
            }

            if (pila.isEmpty()) {
                throw new ArithmeticException("Error: La expresión no tiene resultados.");
            }

            this.calculoPos = temp;

            // Agrega mensaje final con el resultado
            return pila.pop().floatValue();
        } catch (NumberFormatException e) {
            throw new ArithmeticException("Error: La expresión contiene caracteres no válidos.");
        }
    }

    public void crearPDF() {
        String resultadoPosfijo = this.ecuacion.transformarAPostfijo();
        this.ecuacion.transformarAPrefijo();
        getCalculo(resultadoPosfijo);

        try {
            Impresora imp = new Impresora();
            imp.imprimirTitulo("TRANSFORMACIÓN INFIJO PASO A PASO");
            imp.imprimirParrafo("\nTransformación paso a paso de infijo a posfijo de la expresion: " + ecuacion.getEcuacion());
            imp.imprimirPasos(ecuacion.getPostfijo());
            imp.imprimirParrafo("\nTransformación paso a paso de infijo a prefijo de la expresion: " + ecuacion.getEcuacion());
            imp.imprimirPasos(ecuacion.getPrefijo());
            imp.imprimirParrafo("\nCálculo posfijo de la expresion dada : " + ecuacion.transformarAPostfijo());
            imp.imprimirCalculo(calculoPos);
            imp.closeDocument();
        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(Calculadora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Ecuacion getEcuacion() {
        return ecuacion;
    }

    public Mensaje getCalculoPos() {
        return calculoPos;
    }
    

}
