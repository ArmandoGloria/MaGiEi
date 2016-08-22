/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.VisitasEnfermeria;

import Resources.MessageBox;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author SergioHome
 */
public class VisitasEnfermeriaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }



    @FXML
	private void BuscarPaciente(ActionEvent event) {
////		try
////		{
////		    //limpiarCamposPaciente();
////		    deshabilitarCamposPaciente(true);
////		    btnGuardarNvoPaciente.setDisable(true);
////		    btnCancelarAltaPaciente.setDisable(true);
////
////			bActivoBuscar=true;
////		    //Global.checkEmptyFields(txtNoEmpleadoPaciente);
////
////
////		    DefaultTableModel dt;
////		    dt=(MySqlJavaCon.GetTable("SELECT * FROM magiei_db.t_paciente where NoEmpleado like'" + txtNoEmpleadoPaciente.getText() + "%' and Nombre like'" + txtNombrePaciente.getText() + "%' and ApPat like'" + txtApellidoPaternoPaciente.getText() + "%' and ApMat like'" + txtApellidoMaternoPaciente.getText() + "%' order by NoEmpleado  limit 1;  "));
////    //	    table = new JTable(test.GetTable("SELECT * FROM magiei_db.t_paciente where nombre like'%" + txtNoPaciente.getText() + "%'; "));
////		    if(dt.getRowCount()>0)
////		    {
////			    lblIdPaciente.setText(dt.getValueAt(0,0).toString());
////			    txtNombrePaciente.setText(dt.getValueAt(0,1).toString());
////			    txtApellidoPaternoPaciente.setText(dt.getValueAt(0,2).toString());
////			    txtApellidoMaternoPaciente.setText(dt.getValueAt(0,3).toString());
////			    cboSexoPaciente.setValue(dt.getValueAt(0,4).toString());
////			    txtPosicionPaciente.setText(dt.getValueAt(0,5).toString());
////
////			    Calendar calendar = Calendar.getInstance();
////			    calendar.setTime(Date.valueOf( dt.getValueAt(0,6).toString()));
////
//////			    cboEstacionesPaciente.setText(ObtenerEstacionesPaciente(lblIdPaciente.getText()));
////			    lblEstaciones.setText(ObtenerEstacionesPaciente(lblIdPaciente.getText()));
////
////			    cboAnioFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[0])));
////			    cboMesFechaNacPaciente.setValue(MesFecha.get(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[1]))-1));
////			    //cboMesFechaNacPaciente.getItems().lastIndexOf(dt).setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[1])));
////			    cboDiaFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[2])));
////			    
////			  //CheckBox x= ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(1)).getContent()));
////			    
//////			    imgPaciente.setPreserveRatio(true);
////			    
////
////			    txtNoEmpleadoPaciente.setText(dt.getValueAt(0,8).toString());
////			    cboEstatusPaciente.setValue(dt.getValueAt(0,9).toString());
////			    
////			    byte[] readAllBytes = (byte[])dt.getValueAt(0,7);
////			Image imagen = null;
////			BufferedImage read ;
////			try{
////    //InputStream is=new ByteArrayInputStream(readAllBytes);
////				//BufferedImage imag=ImageIO.read(is);
////
////
////			    ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
////	    
////			    read = ImageIO.read(bis);
////    //            System.out.println(read);
////			//Image convertToJavaFXImage = convertToJavaFXImage(readAllBytes, 1024, 768);
////		       // ImageView view = new ImageView(convertToJavaFXImage);
////			    imagen = SwingFXUtils.toFXImage(read, null);
////		    }catch(Exception ex){}
////		  
////		    btnImgPaciente.setText("");
////			    imgPaciente.setImage(imagen);
////			    imgPaciente.setVisible(true);
////		    }
////		    //MySqlJavaCon.CerrarConexion();
////		}
////		catch(Exception ex){
////		    MessageBox.show("Error","","Error al Buscar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
////		}
////
////	}
////
////	private void limpiarCamposPaciente() {
////		try
////		{
////		    lblIdPaciente.setText("");
////		    txtNombrePaciente.clear();
////		    txtApellidoPaternoPaciente.clear();
////		    txtApellidoMaternoPaciente.clear();
////		    cboSexoPaciente.setValue(null);
////		    cboSexoPaciente.setPromptText("Genero");
////		    txtPosicionPaciente.clear();
////		    cboDiaFechaNacPaciente.setValue(null);
////		    cboDiaFechaNacPaciente.setPromptText("Dia");
////		    //cboDiaFechaNacPaciente.set
////		    cboMesFechaNacPaciente.setValue(null);
////		    cboMesFechaNacPaciente.setPromptText("Mes");
////		    cboAnioFechaNacPaciente.setValue(null);
////		    cboAnioFechaNacPaciente.setPromptText("A\u00F1o");
////		    cboEstatusPaciente.setValue(null);
////		    cboEstatusPaciente.setPromptText("Estatus");
////		    lblEstaciones.setText("");
////		    btnImgPaciente.setText("Agregar Foto");
////		    
////		    cboEstatusPaciente.setId("CBstatus");
////		   //cboEstatusPaciente.setValue("Estaciones"); 
////		    txtNoEmpleadoPaciente.clear();
////		    imgPaciente.setImage(null);
////		    //cboEstatusPaciente.getValue(dt.getValueAt(0,9));
////		    fileImage=null;
////		    
////		    cboEstacionesPaciente.textProperty().set("Estaciones");
////		    
////
////			 for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
////				((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(false);
////				 }
////			
////		}
////		catch(Exception ex){
////			    MessageBox.show("Error","","Error en funcion limpiar campos \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
////		}
////	}
////
////	private void deshabilitarCamposPaciente(boolean deshabilitar) {
////		try
////		{
////
//////		  txtNombrePaciente.disableProperty().set(deshabilitar);
//////		  txtApellidoPaternoPaciente.disableProperty().set(deshabilitar);
//////		  txtApellidoMaternoPaciente.disableProperty().set(deshabilitar);
////		  cboSexoPaciente.disableProperty().set(deshabilitar);
////		  txtPosicionPaciente.disableProperty().set(deshabilitar);
////		  cboDiaFechaNacPaciente.disableProperty().set(deshabilitar);
////		  cboMesFechaNacPaciente.disableProperty().set(deshabilitar);
////		  cboAnioFechaNacPaciente.disableProperty().set(deshabilitar);
////		  //txtNombrePaciente.disableProperty().set(deshabilitar);     
////		  //txtNoEmpleadoPaciente.disableProperty().set(deshabilitar);
////		  cboEstatusPaciente.disableProperty().set(deshabilitar);
////		  imgPaciente.disableProperty().set(deshabilitar);
////		  btnImgPaciente.disableProperty().set(deshabilitar);
////		  
////		  cboEstacionesPaciente.disableProperty().set(deshabilitar);
////		}
////		catch(Exception ex){
////		    MessageBox.show("Error","","Error en funcion deshabilirar botones \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
////		}
	}
    
}
