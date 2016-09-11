/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.VisitasEnfermeria;

import MySQLconn.ConexionMySQL;
import Resources.MessageBox;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author SergioHome
 */
public class VisitasEnfermeriaController implements Initializable {

	
        ConexionMySQL MySqlJavaCon =new ConexionMySQL();
	
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
	private Button btnVisitasBuscarP;
	@FXML
	private ComboBox<Date> cboFechaVisitasP;
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
//	Date today;
	String result;
	SimpleDateFormat formatter;
	Calendar calendar = Calendar.getInstance();
	
	formatter = new SimpleDateFormat("dd . MMMM . yyyy");
//	today = new Date();
	result = formatter.format(calendar.getTime());
	
	cboFechaVisitasP.setPromptText(result.replace(".", "de"));
//	cboFechaVisitasP.setPromptText(calendar.getTime().toLocaleString());
	txtVisitasNoPaciente.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
	txtVisitasNombreP.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(100));
    }


    
	@FXML
	private void BuscarVisitasPacienteN(KeyEvent event) {
	}

	@FXML
	private void CargarVisitaPaciente(ActionEvent event) {
		BuscarPaciente();
	}

	@FXML
	private void CargarVisitaPacienteN(ActionEvent event) {
	}

	private void BuscarPaciente() {
		try
		{
////		    limpiarCamposPaciente();
//		    deshabilitarCamposPaciente(true);
//		    btnGuardarNvoPaciente.setDisable(true);
//		    btnCancelarAltaPaciente.setDisable(true);

//			bActivoBuscar=true;
		    //Global.checkEmptyFields(txtNoEmpleadoPaciente);


		    DefaultTableModel dt;
		    dt=(MySqlJavaCon.GetTable("SELECT * FROM magiei_db.t_paciente where NoEmpleado like'" + txtVisitasNoPaciente.getText() + "' or (Nombre + ' ' + ApPat + ' ' + ApMat) like'%" + txtVisitasNombreP.getText() + "%' order by NoEmpleado  limit 1;  "));
    //	    table = new JTable(test.GetTable("SELECT * FROM magiei_db.t_paciente where nombre like'%" + txtNoPaciente.getText() + "%'; "));
		    if(dt.getRowCount()>0)
		    {
			    lblIdPaciente.setText(dt.getValueAt(0,0).toString());
			    txtVisitasNombreP.setText(dt.getValueAt(0,1).toString() + ' '+ dt.getValueAt(0,2).toString() +' ' + dt.getValueAt(0,3).toString());
			    lblVisitasEdadSexo.setText(calcularEdad(dt.getValueAt(0,6).toString()) + " a\u00F1os, " + dt.getValueAt(0,4).toString());

			    //Calendar calendar = Calendar.getInstance();
////			    calendar.setTime(Date.valueOf( dt.getValueAt(0,6).toString()));

//			    cboEstacionesPaciente.setText(ObtenerEstacionesPaciente(lblIdPaciente.getText()));
			    lblVistasPuestoEstaArea.setText(dt.getValueAt(0,5).toString() + ", " + ObtenerEstacionesPaciente(lblIdPaciente.getText()));

//			    cboAnioFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[0])));
//			    cboMesFechaNacPaciente.setValue(MesFecha.get(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[1]))-1));
//			    cboDiaFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[2])));
			    
			  //CheckBox x= ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(1)).getContent()));
			    
//			    imgPaciente.setPreserveRatio(true);
			    

			    txtVisitasNoPaciente.setText(dt.getValueAt(0,8).toString());
			    //cboEstatusPaciente.setValue(dt.getValueAt(0,9).toString());
			    
			    byte[] readAllBytes = (byte[])dt.getValueAt(0,7);
			Image imagen = null;
			BufferedImage read ;
			try{
    //InputStream is=new ByteArrayInputStream(readAllBytes);
				//BufferedImage imag=ImageIO.read(is);


			    ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
	    
			    read = ImageIO.read(bis);
			    imagen = SwingFXUtils.toFXImage(read, null);
		    }catch(Exception ex){}
		  
//		    btnImgPaciente2.setText("");
ImagePattern pattern = new ImagePattern(imagen);
imgCirculo2.setFill(pattern);
//			    imgVisitasPaciente.setImage(imagen);
//			    imgVisitasPaciente.setVisible(true);
		    }
		    //MySqlJavaCon.CerrarConexion();
		}
		catch(Exception ex){
		    MessageBox.show("Error","","Error al Buscar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}

	}

	private void limpiarCamposPaciente() {
		try
		{
		    lblIdPaciente.setText("");
		    txtVisitasNombreP.setText("");
		    txtVisitasNoPaciente.setText("");
		    lblVisitasEdadSexo.setText("");
		    lblVistasPuestoEstaArea.setText("");
	
imgCirculo2.setFill(null);
//		    txtNombrePaciente.clear();
//		    txtApellidoPaternoPaciente.clear();
//		    txtApellidoMaternoPaciente.clear();
//		    cboSexoPaciente.setValue(null);
//		    cboSexoPaciente.setPromptText("Genero");
//		    txtPosicionPaciente.clear();
//		    cboDiaFechaNacPaciente.setValue(null);
//		    cboDiaFechaNacPaciente.setPromptText("Dia");
//		    //cboDiaFechaNacPaciente.set
//		    cboMesFechaNacPaciente.setValue(null);
//		    cboMesFechaNacPaciente.setPromptText("Mes");
//		    cboAnioFechaNacPaciente.setValue(null);
//		    cboAnioFechaNacPaciente.setPromptText("A\u00F1o");
//		    cboEstatusPaciente.setValue(null);
//		    cboEstatusPaciente.setPromptText("Estatus");
//		    lblEstaciones.setText("");
//		    btnImgPaciente.setText("Agregar Foto");
//		    
//		    cboEstatusPaciente.setId("CBstatus");
//		   //cboEstatusPaciente.setValue("Estaciones"); 
//		    txtNoEmpleadoPaciente.clear();
//		    imgPaciente.setImage(null);
//		    //cboEstatusPaciente.getValue(dt.getValueAt(0,9));
//		    fileImage=null;
//		    
//		    cboEstacionesPaciente.textProperty().set("Estaciones");
//		    
//
//			 for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
//				((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(false);
//				 }
			
		}
		catch(Exception ex){
			    MessageBox.show("Error","","Error en funcion limpiar campos \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}
//
//	private void deshabilitarCamposPaciente(boolean deshabilitar) {
//		try
//		{
//
////		  txtNombrePaciente.disableProperty().set(deshabilitar);
////		  txtApellidoPaternoPaciente.disableProperty().set(deshabilitar);
////		  txtApellidoMaternoPaciente.disableProperty().set(deshabilitar);
//		  cboSexoPaciente.disableProperty().set(deshabilitar);
//		  txtPosicionPaciente.disableProperty().set(deshabilitar);
//		  cboDiaFechaNacPaciente.disableProperty().set(deshabilitar);
//		  cboMesFechaNacPaciente.disableProperty().set(deshabilitar);
//		  cboAnioFechaNacPaciente.disableProperty().set(deshabilitar);
//		  //txtNombrePaciente.disableProperty().set(deshabilitar);     
//		  //txtNoEmpleadoPaciente.disableProperty().set(deshabilitar);
//		  cboEstatusPaciente.disableProperty().set(deshabilitar);
//		  imgPaciente.disableProperty().set(deshabilitar);
//		  btnImgPaciente.disableProperty().set(deshabilitar);
//		  
//		  cboEstacionesPaciente.disableProperty().set(deshabilitar);
//		}
//		catch(Exception ex){
//		    MessageBox.show("Error","","Error en funcion deshabilirar botones \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
//		}
//	}
	
	public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
		    TextField txt_TextField = (TextField) e.getSource();                
		    if (txt_TextField.getText().length() >= max_Lengh) {                    
			e.consume();
		    }
		    if(e.getCharacter().matches("[0-9.]")){ 
			if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
			    e.consume();
			}else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
			    e.consume(); 
			}
		    }else{
			e.consume();
		    }
		}
	    };
	}
    
	public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
		    TextField txt_TextField = (TextField) e.getSource();                
		    if (txt_TextField.getText().length() >= max_Lengh) {                    
			e.consume();
		    }
		    if(e.getCharacter().matches("[A-Za-z ]||\u00F1||\u00D1")){ 
		    }else{
			e.consume();
		    }
		}
	    };
	}
	
	public static int calcularEdad(String fecha) {
		String datetext = fecha;
		try {
			Calendar birth = new GregorianCalendar();
			Calendar today = new GregorianCalendar();
			int age=0;
			int factor=0;
			Date birthDate=new SimpleDateFormat("yyyy-MM-dd").parse(datetext);
			Date currentDate=new Date(); //current date
			birth.setTime(birthDate);
			today.setTime(currentDate);
			if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) {
				if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
					if (today.get(Calendar.DATE) > birth.get(Calendar.DATE)) {
						factor = -1; //Aun no celebra su cumpleaÃ±os
					}
				} else {
					factor = -1; //Aun no celebra su cumpleaÃ±os
				}
			}
			age=(today.get(Calendar.YEAR)-birth.get(Calendar.YEAR))+factor;
			return age;
		} catch (ParseException e) {
		return -1;
		}
	}
	
	protected String ObtenerEstacionesPaciente(String id)
	{
		
		String StrStations="";
		try{
			DefaultTableModel dt;
			dt=(MySqlJavaCon.GetTable("SELECT t_area.Nombre,t_Estacion.Nombre FROM magiei_db.t_Estacion\n" +
							"left join magiei_db.t_EstacionPaciente\n" +
							"on t_EstacionPaciente.idEstacion=t_Estacion.IdEstacion\n" +
							"left join magiei_db.t_paciente on t_EstacionPaciente.idPaciente=t_paciente.idPaciente\n" +
							"left join magiei_db.t_area on magiei_db.t_area.IdArea=t_Estacion.idArea\n" +
							"where t_paciente.idPaciente="+ id +" ; "));
			
		for (Integer a=0;a<dt.getRowCount() ;a++){
		StrStations += dt.getValueAt(a, 0).toString() + "-" + dt.getValueAt(a, 1).toString() + "; ";
		}
		StrStations=StrStations.substring(0, StrStations.length()-2);
		}
		catch(Exception ex) {
			MessageBox.show("Error","", ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
		return StrStations;
	}
	
	
	@FXML
	private void limpiarBusqueda(MouseEvent event) {
//		if(bActivoBuscar){
			limpiarCamposPaciente();
//		}
	}

}
