/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Transformar;
import Negocio.Calculadora;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CalculadoraController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnCalcularPosfijo;

    @FXML
    private Button btnCalcularPrefijo;

    @FXML
    private Button btnCargar;

    @FXML
    private Button btnPdf;

    @FXML
    private TextField txtEcuacion;

    @FXML
    private TableColumn<Transformar, String> columOperacion;

    @FXML
    private TableColumn<Transformar, String> columPila;

    @FXML
    private TableColumn<Transformar, String> columSalida;

    @FXML
    private TableView<Transformar> tabla;

    private Calculadora c;

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void calcular(ActionEvent event) {
        try {
            columSalida.setVisible(false);
            columPila.setPrefWidth(316);
            c.getCalculo(c.getEcuacion().transformarAPostfijo());
            String mensajes = c.getCalculoPos().toString();
            String[] m = mensajes.split("&");

            // Crear la lista de objetos Transformar
            ObservableList<Transformar> listaTransformaciones = FXCollections.observableArrayList();

            // Iterar sobre las partes de la cadena mensajes
            for (int i = 0; i < m.length - 1; i += 2) {
                Transformar columnas = new Transformar(m[i], m[i + 1]);
                listaTransformaciones.add(columnas);
            }

            // Asignar la lista de objetos Transformar a la tabla
            tabla.setItems(listaTransformaciones);

        } catch (Exception e) {
            mostrarAlerta("Error al calcular posfijo", e.getMessage());
        }

    }

    @FXML
    void calcularPosfijo(ActionEvent event) {
        try {
            c.getEcuacion().transformarAPostfijo();
            columSalida.setVisible(true);
            columPila.setPrefWidth(96);

            String mensajes = c.getEcuacion().getPostfijo().toString();
            String[] m = mensajes.split("&");

            // Crear la lista de objetos Transformar
            ObservableList<Transformar> listaTransformaciones = FXCollections.observableArrayList();

            // Iterar sobre las partes de la cadena mensajes
            for (int i = 0; i < m.length - 2; i += 3) {
                Transformar columnas = new Transformar(m[i], m[i + 1], m[i + 2]);
                listaTransformaciones.add(columnas);
            }

            // Asignar la lista de objetos Transformar a la tabla
            tabla.setItems(listaTransformaciones);

        } catch (Exception e) {
            mostrarAlerta("Error al calcular posfijo", "Asegúrate de que la ecuación sea válida.");
        }

    }

    @FXML
    void calcularPrefijo(ActionEvent event) {
        try {
            c.getEcuacion().transformarAPrefijo();
            columSalida.setVisible(true);
            columPila.setPrefWidth(96);
            String mensajes = c.getEcuacion().getPrefijo().toString();
            String[] m = mensajes.split("&");

            // Crear la lista de objetos Transformar
            ObservableList<Transformar> listaTransformaciones = FXCollections.observableArrayList();

            // Iterar sobre las partes de la cadena mensajes
            for (int i = 0; i < m.length - 2; i += 3) {
                Transformar columnas = new Transformar(m[i], m[i + 1], m[i + 2]);
                listaTransformaciones.add(columnas);
            }

            // Asignar la lista de objetos Transformar a la tabla
            tabla.setItems(listaTransformaciones);

        } catch (Exception e) {
            mostrarAlerta("Error al calcular posfijo", "Asegúrate de que la ecuación sea válida.");
        }

    }

    @FXML
    void cargar(ActionEvent event) {
        try {
            c.cargar(txtEcuacion.getText());
        } catch (Exception e) {
            mostrarAlerta("Error al cargar la expresion", e.getMessage());
        }
    }

    @FXML
    void generarPDF(ActionEvent event) {

        try {
            c.crearPDF();
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("PDF generado");
            alerta.setContentText("PDF generado con exito");
            alerta.showAndWait();
        } catch (Exception e) {
            mostrarAlerta("Error al generar PDF", "Error al generar PDF");
        }

    }

    @FXML
    void initialize() {

        assert btnCalcular != null : "fx:id=\"btnCalcular\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnCalcularPosfijo != null : "fx:id=\"btnCalcularPosfijo\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnCalcularPrefijo != null : "fx:id=\"btnCalcularPrefijo\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnCargar != null : "fx:id=\"btnCargar\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnPdf != null : "fx:id=\"btnPdf\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert txtEcuacion != null : "fx:id=\"txtEcuacion\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert txtEcuacion != null : "fx:id=\"txtEcuacion\" was not injected: check your FXML file 'calculadoraVista.fxml'.";

        c = new Calculadora();
        columOperacion.setCellValueFactory(new PropertyValueFactory<>("operacion"));
        columPila.setCellValueFactory(new PropertyValueFactory<>("pila"));
        columSalida.setCellValueFactory(new PropertyValueFactory<>("salida"));

        tabla.getColumns().forEach(column -> {
            column.setReorderable(false);
//        column.setResizable(false);
        });

//        Agregar un listener para manejar el evento cuando se ingresa texto
//        txtEcuacion.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                // Verificar si el texto ha cambiado y si es así, quitar el prompt
//                if (!newValue.isEmpty()) {
//                    txtEcuacion.setPromptText("");
//                }
//            }
//        });
    }

}
