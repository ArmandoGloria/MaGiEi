/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.MDI;

import MySQLconn.ConexionMySQL;
import Resources.MessageBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author Shadowsboy
 */
public class graficaPrincipalController implements Initializable {
	
        ConexionMySQL MySqlJavaCon =new ConexionMySQL();

	@FXML
	private Label lblIdPaciente;
	@FXML
	private TextField txtVisitasNoPaciente;
	@FXML
	private Label lblVistasPuestoEstaArea;
	@FXML
	private PieChart graficaPrincipal;
	
	private ObservableList data; 
	
//	ObservableList<Data> pieChartData = FXCollections.observableArrayList();


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		try
		{
			data = FXCollections.observableArrayList();
			
////		    limpiarCamposPaciente();
//		    deshabilitarCamposPaciente(true);
			
		    DefaultTableModel dt;
		    dt=(MySqlJavaCon.GetTable("SELECT sexo,sum(NoEmpleado) FROM magiei_db.t_paciente group by sexo  order by sexo  ;  "));
    //	    table = new JTable(test.GetTable("SELECT * FROM magiei_db.t_paciente where nombre like'%" + txtNoPaciente.getText() + "%'; "));
		    if(dt.getRowCount()>0)
		    {
			    //lblIdPaciente.setText(dt.getValueAt(0,0).toString());
			    
//			    data.add(new PieChart.Data(rs.getString(2),rs.getDouble(1))); 
			    for (Integer a=0;a<dt.getRowCount() ;a++){

			    data.add(new PieChart.Data(dt.getValueAt(a,0).toString() + " " + dt.getValueAt(a,1).toString(),Double.valueOf(dt.getValueAt(a,1).toString())));
			    }
//			    data.add(dt);
			graficaPrincipal.setLabelsVisible(true);
			graficaPrincipal.setLegendVisible(true);

			    graficaPrincipal.setData(data);

//			    graficaPrincipal.getData().addAll(data);
		    }
		    
		}
		catch(Exception ex){
		    MessageBox.show("Error","","Error al Buscar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}	

	@FXML
	private void BuscarVisitasPacienteN(KeyEvent event) {
	}

	@FXML
	private void limpiarBusqueda(MouseEvent event) {
	}

	@FXML
	private void CargarVisitaPacienteN(ActionEvent event) {
	}
	
}
