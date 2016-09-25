/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.MDI.graficaPrincipalidad;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Shadowsboy
 */
public class graficaPrincipalidadController implements Initializable {

	@FXML
	private Label lblIdPaciente;
	@FXML
	private TextField txtVisitasNombreP;
	@FXML
	private TextField txtVisitasNoPaciente;
	@FXML
	private ImageView imgVisitasPaciente;
	@FXML
	private Label lblVisitasEdadSexo;
	@FXML
	private Label lblVistasPuestoEstaArea;
	@FXML
	private ComboBox<?> cboFechaVisitasP;
	@FXML
	private Button btnVisitasBuscarP;
	@FXML
	private Button btnImgPaciente2;
	@FXML
	private Circle imgCirculo2;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	

	@FXML
	private void limpiarBusqueda(MouseEvent event) {
	}

	@FXML
	private void BuscarVisitasPacienteN(KeyEvent event) {
	}

	@FXML
	private void CargarVisitaPacienteN(ActionEvent event) {
	}

	@FXML
	private void CargarVisitaPaciente(ActionEvent event) {
	}
	
}
