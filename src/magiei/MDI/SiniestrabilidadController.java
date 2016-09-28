/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.MDI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Shadowsboy
 */
public class SiniestrabilidadController implements Initializable {

	@FXML
	private Label lblIdPaciente;
	@FXML
	private TextField txtVisitasNoPaciente;
	@FXML
	private Label lblVistasPuestoEstaArea;
	@FXML
	private Label lblCantSiniestra;

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

	
}
