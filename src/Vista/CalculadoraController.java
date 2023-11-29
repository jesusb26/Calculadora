/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Vista;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    void calcular(ActionEvent event) {

    }

    @FXML
    void calcularPosfijo(ActionEvent event) {

    }

    @FXML
    void calcularPrefijo(ActionEvent event) {

    }

    @FXML
    void cargar(ActionEvent event) {

    }

    @FXML
    void generarPDF(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCalcular != null : "fx:id=\"btnCalcular\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnCalcularPosfijo != null : "fx:id=\"btnCalcularPosfijo\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnCalcularPrefijo != null : "fx:id=\"btnCalcularPrefijo\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnCargar != null : "fx:id=\"btnCargar\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert btnPdf != null : "fx:id=\"btnPdf\" was not injected: check your FXML file 'calculadoraVista.fxml'.";
        assert txtEcuacion != null : "fx:id=\"txtEcuacion\" was not injected: check your FXML file 'calculadoraVista.fxml'.";

    }

}

