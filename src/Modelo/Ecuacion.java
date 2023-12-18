package Modelo;

import Util.seed.Pila;

public class Ecuacion {

    
    String ecuacion;

    private Mensaje postfijo;
    private Mensaje prefijo;

    private static final String OPERADORES = "+-*/";

    public Ecuacion() {
        postfijo = new Mensaje();
        prefijo = new Mensaje();
    }

    public void setEcuacion(String ecuacion) {
        this.ecuacion = ecuacion;
    }

    public String transformarAPostfijo() {
        Mensaje temp = new Mensaje();
        String ec = this.ecuacion;
        Pila<Character> pila = new Pila<>();
        StringBuilder salida = new StringBuilder();

        for (int i = 0; i < ec.length(); i++) {
            char token = ec.charAt(i);

            if (Character.isDigit(token) || token == '.') {
                // Si el caracter actual es un dígito o un punto decimal, agrégalo al número actual
                salida.append(token);
                // Verificar si el próximo caracter no es un dígito
                if (i < ec.length() - 1 && (!Character.isDigit(ec.charAt(i + 1)) && ec.charAt(i + 1) != '.')) {
                    // Agregar espacio para separar los números
                    salida.append(' ');
                }
            } else if (OPERADORES.indexOf(token) != -1) {
                while (!pila.isEmpty() && precedencia(token) < precedencia(pila.peek())) {
                    salida.append(pila.pop()).append(' ');
                }
                pila.push(token);
            } else if (token == '(') {
                pila.push(token);
            } else if (token == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    salida.append(pila.pop()).append(' ');
                }
                pila.pop();
            }
            temp.addMensaje("Operación: " + token);
            temp.addMensaje(pila.toString());
            temp.addMensaje(salida.toString() + "\n");
        }

        while (!pila.isEmpty()) {
            salida.append(pila.pop()).append(' ');
        }

        temp.addMensaje("Operación final: ");
        temp.addMensaje(pila.toString());
        temp.addMensaje(salida.toString());

        this.postfijo = temp;

        return salida.toString();
    }

    public String transformarAPrefijo() {
        Mensaje temp = new Mensaje();
        String ec = this.ecuacion;
        Pila<Character> pila = new Pila<>();
        StringBuilder expresionPrefija = new StringBuilder();

        ec = ec.replace(" ", "");

        for (int i = ec.length() - 1; i >= 0; i--) {
            char x = ec.charAt(i);

            if (Character.isDigit(x) || x == '.') {
                // Si el caracter actual es un dígito o un punto decimal, agrégalo al número actual
                expresionPrefija.insert(0, x);
                // Mientras el siguiente caracter sea un dígito o punto decimal, agrégalo al número actual
                temp.addMensaje("Operación: " + x);
                temp.addMensaje(pila.toString());
                temp.addMensaje(expresionPrefija.toString() + "\n");
                while (i > 0 && (Character.isDigit(ec.charAt(i - 1)) || ec.charAt(i - 1) == '.')) {
                    i--;
                    expresionPrefija.insert(0, ec.charAt(i));
                    temp.addMensaje("Operación: " + ec.charAt(i));
                    temp.addMensaje(pila.toString());
                    temp.addMensaje(expresionPrefija.toString() + "\n");
                }

                // Añade un espacio después del número solo si no es el último
                if (i > 0) {
                    expresionPrefija.insert(0, ' ');
                }
            } else if (x == ')') {
                pila.push(x);
                temp.addMensaje("Operación: " + x);
                temp.addMensaje(pila.toString());
                temp.addMensaje(expresionPrefija.toString() + "\n");
            } else if (OPERADORES.indexOf(x) != -1) {
                while (!pila.isEmpty() && precedencia(x) <= precedencia(pila.peek())) {
                    expresionPrefija.insert(0, pila.pop());
                }
                pila.push(x);
                temp.addMensaje("Operación: " + x);
                temp.addMensaje(pila.toString());
                temp.addMensaje(expresionPrefija.toString() + "\n");
            } else if (x == '(') {
                // Modifica esta sección para no agregar paréntesis al resultado prefijo
                while (!pila.isEmpty() && pila.peek() != ')') {
                    expresionPrefija.insert(0, pila.pop());
                }
                if (!pila.isEmpty() && pila.peek() == ')') {
                    pila.pop(); // Quita el paréntesis de cierre de la pila
                }
                temp.addMensaje("Operación: " + x);
                temp.addMensaje(pila.toString());
                temp.addMensaje(expresionPrefija.toString() + "\n");
            }
        }

        while (!pila.isEmpty()) {
            expresionPrefija.insert(0, pila.pop());
        }
        temp.addMensaje("Operación final: ");
        temp.addMensaje(pila.toString());
        temp.addMensaje(expresionPrefija.toString());
        this.prefijo = temp;
        return expresionPrefija.toString();
    }

    private int precedencia(char operador) {
        return OPERADORES.indexOf(operador);
    }

    public boolean validarEcuacionInfija(String ecu) throws RuntimeException {

        String ec = ecu;
        int countParentesis = 0;
        boolean correcta = true;

        // Verificar que los términos sean números y que la estructura sea (termino operador termino)
        for (int i = 0; i < ec.length(); i++) {
            char token = ec.charAt(i);

            if (Character.isDigit(token) || token == '.') {
                // Verificar que los dígitos o puntos estén seguidos por un operador, paréntesis o sean el último carácter
                if (i < ec.length() - 1) {
                    char nextChar = ec.charAt(i + 1);
                    if (!(Character.isDigit(nextChar) || nextChar == '.' || isOperador(nextChar) || nextChar == '(' || nextChar == ')')) {
                        correcta = false;
                        throw new RuntimeException("Error: Dígitos o puntos no seguidos por operador, paréntesis o último carácter");
                    }
                }
            } else if (token == '(') {
                countParentesis++;
            } else if (token == ')') {
                countParentesis--;
                // Verificar que los paréntesis estén equilibrados
                if (countParentesis < 0) {
                    correcta = false;
                    throw new RuntimeException("Error: Desbalanceo de paréntesis");
                }
            } else if (isOperador(token)) {
                // Verificar que los operadores estén seguidos por dígitos, paréntesis o puntos
                if (i == ec.length() - 1 || !(Character.isDigit(ec.charAt(i + 1)) || ec.charAt(i + 1) == '(' || ec.charAt(i + 1) == '.')) {
                    correcta = false;
                    throw new RuntimeException("Error: Operadores no seguidos por dígitos, paréntesis o puntos");
                }
            } else {
                // Verificar que el carácter actual sea un carácter permitido (operador, paréntesis o punto)
                if (!(token == '(' || token == ')' || token == '.' || isOperador(token))) {
                    correcta = false;
                    throw new RuntimeException("Error: Carácter no permitido en la expresión");
                }
            }
        }

        // Verificar que los paréntesis estén equilibrados al final
        if (countParentesis != 0) {
            correcta = false;
            throw new RuntimeException("Error: Desbalanceo de paréntesis");
        }

        return correcta;
    }

// Método auxiliar para verificar si un carácter es un operador
    private boolean isOperador(char token) {
        return OPERADORES.indexOf(token) != -1;
    }

    public String getEcuacion() {
        return ecuacion;
    }

    
    public Mensaje getPostfijo() {
        return postfijo;
    }

    public Mensaje getPrefijo() {
        return prefijo;
    }

}
