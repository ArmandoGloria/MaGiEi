/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.MDI;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import MySQLconn.ConexionMySQL;
import Resources.MessageBox;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.stream.Stream;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author SergioHome
 */
public class MDIController implements Initializable {

	
        ConexionMySQL MySqlJavaCon =new ConexionMySQL();
	
//	GlobalClass1 global;
	
	File fileImage; 
	
	String strMySqlQuery = "";
	
	protected ObservableList<Integer> DiaFecha =FXCollections.observableArrayList();
	protected ObservableList<MesObj> MesFecha =FXCollections.observableArrayList() ;
	protected ObservableList<Integer> AnioFecha =FXCollections.observableArrayList();
	
	private boolean bActivoBuscar=true;
	
	
	@FXML
	private Button btnMenuBar;
	@FXML
	private Label lblEstaciones;
	@FXML
	private Rectangle imgRectangulo;
	
	//protected ObservableList<CheckItem> items =FXCollections.observableArrayList(); ;//= fetchItems();

	public MDIController() {
		
	}
	
	
	

    /**
     * Initializes the controller class.
	 * @param url
	 * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
	try{
		cboEstatusPaciente.setItems(status);
		cboSexoPaciente.setItems(sexo);
		
		
			
		MenuItem[] col = null;
		
		CargarCombos();
		
		txtNoEmpleadoPaciente.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
		txtNombrePaciente.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
		cboDiaFechaNacPaciente.addEventFilter(KeyEvent.KEY_TYPED , numeric_ValidationCbo(2,31,1));
		cboAnioFechaNacPaciente.addEventFilter(KeyEvent.KEY_TYPED , numeric_ValidationCbo(5,cboAnioFechaNacPaciente.getItems().get(cboAnioFechaNacPaciente.getItems().size()-1),cboAnioFechaNacPaciente.getItems().get(0)));
		cboMesFechaNacPaciente.addEventFilter(KeyEvent.KEY_TYPED , letter_ValidationCbo(11));
		//********FxUtil.autoCompleteComboBox(cboAnioFechaNacPaciente, FxUtil.AutoCompleteMode.STARTS_WITH);
		new AutoCompleteComboBoxListener<>(cboAnioFechaNacPaciente);
		new AutoCompleteComboBoxListener<>(cboMesFechaNacPaciente);
		
		//new ComboBoxAutoComplete2<>(cboMesFechaNacPaciente);
		
		
	}
	catch(Exception ex){
			MessageBox.show("Error","","Error al Inicializar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
	    }
    } 
    
    
    
    
	@FXML
	private VBox dataPane;
    
    

    
    /**
     * 
    
     */
//<editor-fold defaultstate="collapsed" desc="Paciente">
    
        
	@FXML
	private Label lblIdPaciente;
	@FXML
	private Button btnBuscarPaciente;
	@FXML
	private TextField txtNombrePaciente;
	@FXML
	private TextField txtNoEmpleadoPaciente;
	@FXML
	private Button btnAgregarPaciente;
	@FXML
	private Button btnEditarPaciente;
	@FXML
	private TextField txtApellidoPaternoPaciente;
	@FXML
	private TextField txtApellidoMaternoPaciente;
	@FXML
	private ComboBox<Integer> cboDiaFechaNacPaciente;
	@FXML
	private ComboBox<MesObj> cboMesFechaNacPaciente;
	@FXML
	private ComboBox<Integer> cboAnioFechaNacPaciente;
	@FXML
	private TextField txtPosicionPaciente;
	@FXML
	private Button btnGuardarNvoPaciente;
	@FXML
	private Button btnCancelarAltaPaciente;
	@FXML
	private ImageView imgPaciente;
	
	protected ObservableList<String> status =FXCollections.observableArrayList("Activo","Inactivo");
	@FXML
	private ComboBox<String> cboEstatusPaciente;
	
	protected ObservableList<String> sexo =FXCollections.observableArrayList("Masculino","Femenino");
	@FXML
	protected ComboBox<String> cboSexoPaciente;
	
	@FXML
	private Button btnImgPaciente;
	
	
	@FXML
	private MenuButton cboEstacionesPaciente;
	
	
	
	@FXML
	private void BuscarPaciente(ActionEvent event) {
		try
		{
		    //limpiarCamposPaciente();
		    deshabilitarCamposPaciente(true);
		    btnGuardarNvoPaciente.setDisable(true);
		    btnCancelarAltaPaciente.setDisable(true);

			bActivoBuscar=true;
		    //Global.checkEmptyFields(txtNoEmpleadoPaciente);


		    DefaultTableModel dt;
		    dt=(MySqlJavaCon.GetTable("SELECT * FROM magiei_db.t_paciente where NoEmpleado like'" + txtNoEmpleadoPaciente.getText() + "%' and Nombre like'" + txtNombrePaciente.getText() + "%' and ApPat like'" + txtApellidoPaternoPaciente.getText() + "%' and ApMat like'" + txtApellidoMaternoPaciente.getText() + "%'  limit 1;  "));
    //	    table = new JTable(test.GetTable("SELECT * FROM magiei_db.t_paciente where nombre like'%" + txtNoPaciente.getText() + "%'; "));
		    if(dt.getRowCount()>0)
		    {
			    lblIdPaciente.setText(dt.getValueAt(0,0).toString());
			    txtNombrePaciente.setText(dt.getValueAt(0,1).toString());
			    txtApellidoPaternoPaciente.setText(dt.getValueAt(0,2).toString());
			    txtApellidoMaternoPaciente.setText(dt.getValueAt(0,3).toString());
			    cboSexoPaciente.setValue(dt.getValueAt(0,4).toString());
			    txtPosicionPaciente.setText(dt.getValueAt(0,5).toString());

			    Calendar calendar = Calendar.getInstance();
			    calendar.setTime(Date.valueOf( dt.getValueAt(0,6).toString()));

//			    cboEstacionesPaciente.setText(ObtenerEstacionesPaciente(lblIdPaciente.getText()));
			    lblEstaciones.setText(ObtenerEstacionesPaciente(lblIdPaciente.getText()));

			    cboAnioFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[0])));
			    cboMesFechaNacPaciente.setValue(MesFecha.get(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[1]))-1));
			    //cboMesFechaNacPaciente.getItems().lastIndexOf(dt).setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[1])));
			    cboDiaFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[2])));
			    
			  //CheckBox x= ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(1)).getContent()));
			    
//			    imgPaciente.setPreserveRatio(true);
			    

			    txtNoEmpleadoPaciente.setText(dt.getValueAt(0,8).toString());
			    cboEstatusPaciente.setValue(dt.getValueAt(0,9).toString());
			    
			    byte[] readAllBytes = (byte[])dt.getValueAt(0,7);
			Image imagen = null;
			BufferedImage read ;
			try{
    //InputStream is=new ByteArrayInputStream(readAllBytes);
				//BufferedImage imag=ImageIO.read(is);


			    ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
	    
			    read = ImageIO.read(bis);
    //            System.out.println(read);
			//Image convertToJavaFXImage = convertToJavaFXImage(readAllBytes, 1024, 768);
		       // ImageView view = new ImageView(convertToJavaFXImage);
			    imagen = SwingFXUtils.toFXImage(read, null);
		    }catch(Exception ex){}
		  
		    btnImgPaciente.setText("");
			    imgPaciente.setImage(imagen);
			    imgPaciente.setVisible(true);
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
		    txtNombrePaciente.clear();
		    txtApellidoPaternoPaciente.clear();
		    txtApellidoMaternoPaciente.clear();
		    cboSexoPaciente.setValue(null);
		    cboSexoPaciente.setPromptText("Genero");
		    txtPosicionPaciente.clear();
		    cboDiaFechaNacPaciente.setValue(null);
		    cboDiaFechaNacPaciente.setPromptText("Dia");
		    //cboDiaFechaNacPaciente.set
		    cboMesFechaNacPaciente.setValue(null);
		    cboMesFechaNacPaciente.setPromptText("Mes");
		    cboAnioFechaNacPaciente.setValue(null);
		    cboAnioFechaNacPaciente.setPromptText("A\u00F1o");
		    cboEstatusPaciente.setValue(null);
		    cboEstatusPaciente.setPromptText("Estatus");
		    lblEstaciones.setText("");
		    btnImgPaciente.setText("Agregar Foto");
		    
		    cboEstatusPaciente.setId("CBstatus");
		   //cboEstatusPaciente.setValue("Estaciones"); 
		    txtNoEmpleadoPaciente.clear();
		    imgPaciente.setImage(null);
		    //cboEstatusPaciente.getValue(dt.getValueAt(0,9));
		    fileImage=null;
		    
		    cboEstacionesPaciente.textProperty().set("Estaciones");
		    

			 for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
				((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(false);
				 }
			
		}
		catch(Exception ex){
			    MessageBox.show("Error","","Error en funcion limpiar campos \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}

	private void deshabilitarCamposPaciente(boolean deshabilitar) {
		try
		{

//		  txtNombrePaciente.disableProperty().set(deshabilitar);
//		  txtApellidoPaternoPaciente.disableProperty().set(deshabilitar);
//		  txtApellidoMaternoPaciente.disableProperty().set(deshabilitar);
		  cboSexoPaciente.disableProperty().set(deshabilitar);
		  txtPosicionPaciente.disableProperty().set(deshabilitar);
		  cboDiaFechaNacPaciente.disableProperty().set(deshabilitar);
		  cboMesFechaNacPaciente.disableProperty().set(deshabilitar);
		  cboAnioFechaNacPaciente.disableProperty().set(deshabilitar);
		  //txtNombrePaciente.disableProperty().set(deshabilitar);     
		  //txtNoEmpleadoPaciente.disableProperty().set(deshabilitar);
		  cboEstatusPaciente.disableProperty().set(deshabilitar);
		  imgPaciente.disableProperty().set(deshabilitar);
		  btnImgPaciente.disableProperty().set(deshabilitar);
		  
		  cboEstacionesPaciente.disableProperty().set(deshabilitar);
		}
		catch(Exception ex){
		    MessageBox.show("Error","","Error en funcion deshabilirar botones \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}

	@FXML
	private void AgregarPaciente(ActionEvent event) {
		try
		{
		    limpiarCamposPaciente();
		    deshabilitarCamposPaciente(false);
		    btnGuardarNvoPaciente.setDisable(false);
		    btnCancelarAltaPaciente.setDisable(false);

			bActivoBuscar=false;
		    //CargarCombos();
		}
		catch(Exception ex){
		    MessageBox.show("Error","","Error en fucion agrgar \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}

	@FXML
	private void EditarPaciente(ActionEvent event) {
		try
		{
		    if(lblIdPaciente.getText()!=""){
			    deshabilitarCamposPaciente(false);
			    btnGuardarNvoPaciente.setDisable(false);
			    btnCancelarAltaPaciente.setDisable(false);


			bActivoBuscar=false;


		    //CargarCombos();
		    }
		    else{
			   MessageBox msj = new MessageBox();
			 msj.show("Error","","Debe seleccionar un registro prmero ",Alert.AlertType.WARNING,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
			 //msj.show("fddgfffffffffffffffffffffffffff\n fddgfffffffffffffffffffffffffff\n fddgfffffffffffffffffffffffffff\n fddgfffffffffffffffffffffffffff\n fddgfffffffffffffffffffffffffff", "fgghhhhhhhhhhhhhhhhhhhhhhhhhhhg",getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
			 

//			 Stage primaryStage2 = null;
//
//			 
//			 showConfirmDialog(primaryStage2,"","");
//			 
//			 Stage primaryStage = null;
//			 int answer = jfx.messagebox.MessageBox.show(primaryStage, 
// 						"Ejemplo de un DialogBox.\n\nLas opciones son las siguientes.\n[MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL]", 
//						"Information dialog",  
// 						jfx.messagebox.MessageBox.ICON_INFORMATION| jfx.messagebox.MessageBox.OK | jfx.messagebox.MessageBox.CANCEL); 
			 
			 			     
		    }



		    //limpiarCamposPaciente();
		}
		catch(Exception ex){
//			    global.MessageBox.show("Error","Error al Editar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
           MessageBox.show("Error","","Error al Editar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}

	@FXML
	private void GuardarPaciente(ActionEvent event) {
		try{
			
			
			boolean FechaValida=false;
			Calendar calendar = Calendar.getInstance();        
			calendar.setLenient(false);
			SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");

			
			try{
				cboAnioFechaNacPaciente.setValue(Integer.parseInt(cboAnioFechaNacPaciente.getEditor().getText()));
				cboDiaFechaNacPaciente.setValue(Integer.parseInt(cboDiaFechaNacPaciente.getEditor().getText()));
				
			calendar.set(cboAnioFechaNacPaciente.getValue(), cboMesFechaNacPaciente.getValue().Value()-1 , cboDiaFechaNacPaciente.getValue());
			
			
			//calendar.set(2016, 01, 21);
			calendar.getTime();
			ft.format(calendar.getTime());
			FechaValida=true;
//			if(isFechaValida(ft.format(calendar.getTime()) )){
//				
//			FechaValida=true;
//			}else{
//				FechaValida=false;
//				cboDiaFechaNacPaciente.setValue(null);
//				cboDiaFechaNacPaciente.setPromptText("Dia");
//				cboMesFechaNacPaciente.setValue(null);
//				cboMesFechaNacPaciente.setPromptText("Mes");
//				cboAnioFechaNacPaciente.setValue(null);
//				cboAnioFechaNacPaciente.setPromptText("A\u00F1o");}

			}catch(Exception ex){
				FechaValida=false;
				cboDiaFechaNacPaciente.setValue(null);
				cboDiaFechaNacPaciente.setPromptText("Dia");
				cboMesFechaNacPaciente.setValue(null);
				cboMesFechaNacPaciente.setPromptText("Mes");
				cboAnioFechaNacPaciente.setValue(null);
				cboAnioFechaNacPaciente.setPromptText("A\u00F1o");
				
				MessageBox.show("Error","","Error Fecha incorrecta",Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css")); 
				return;

			
			}
			
			
					MySqlJavaCon.mySqlConn.setAutoCommit(true);

			if(FechaValida && this.txtNombrePaciente.getText()!="" && this.txtApellidoPaternoPaciente.getText()!="" && this.txtApellidoMaternoPaciente.getText()!="" && this.txtNoEmpleadoPaciente.getText()!="" && this.txtPosicionPaciente.getText()!="" && this.cboSexoPaciente.getValue()!="" && this.cboEstatusPaciente.getValue()!="" ){
			if(this.lblIdPaciente.getText()==""){
				strMySqlQuery="INSERT INTO `magiei_db`.`t_paciente` (`Nombre`, `ApPat`, `ApMat`, `Sexo`, `Posicion`, `FechNac`, `NoEmpleado`, `Activo`) VALUES ('" + txtNombrePaciente.getText() + "', '" + txtApellidoPaternoPaciente.getText() + "', '" + txtApellidoMaternoPaciente.getText() + "', '" + cboSexoPaciente.getValue() + "', '" + txtPosicionPaciente.getText() + "', '" +  ft.format(calendar.getTime()) + "', '" + txtNoEmpleadoPaciente.getText() + "', '" + cboEstatusPaciente.getValue() + "'); ";
			} 
			else{
				strMySqlQuery="UPDATE `magiei_db`.`t_paciente` SET `Nombre`='" + txtNombrePaciente.getText() + "', `ApPat`='" + txtApellidoPaternoPaciente.getText() + "', `ApMat`='" + txtApellidoMaternoPaciente.getText() + "', `Sexo`='" + cboSexoPaciente.getValue() + "', `Posicion`='" + txtPosicionPaciente.getText() + "', `FechNac`='" + ft.format(calendar.getTime()) + "', `NoEmpleado`='" + txtNoEmpleadoPaciente.getText() + "', `Activo`='" + cboEstatusPaciente.getValue() + "' WHERE idPaciente=" + lblIdPaciente.getText() + ";";
			}
			if(MySqlJavaCon.updateTable(strMySqlQuery)){
				
////				lblIdPaciente.setText(txtNoEmpleadoPaciente.getText());
//					MySqlJavaCon.mySqlConn.setAutoCommit(false);
//			
//				      File file = new File("C:\\x.jpg");      
//				      FileInputStream fis = null;
//				      fis = new FileInputStream(file);
//				      MySqlJavaCon.ps = MySqlJavaCon.mySqlConn.prepareStatement("UPDATE magiei_db.t_paciente SET  Foto=? WHERE idPaciente=" + lblIdPaciente.getText() + ";");
//				      MySqlJavaCon.ps.setBlob(1,  fis, (int) file.length());
//				MySqlJavaCon.InsertGuardar("");

				      //File file = new File(fileImage);
				if(fileImage!=null)
				{
					      
					MySqlJavaCon.mySqlConn.setAutoCommit(false);
				MySqlJavaCon.strQueryMySQL="";
					InputStream fis = null;
					fis = new FileInputStream(fileImage);
					MySqlJavaCon.ps = MySqlJavaCon.mySqlConn.prepareStatement("UPDATE `magiei_db`.`t_paciente` SET  `Foto`=?WHERE NoEmpleado=" + txtNoEmpleadoPaciente.getText() + ";");
//					MySqlJavaCon.ps.setBinaryStream(1,  fis, (int) fileImage.length());
					MySqlJavaCon.ps.setBlob(1,  fis, (int) fileImage.length());
//					MySqlJavaCon.ps.setString(2, "xxx");
					//MySqlJavaCon.ps.setBlob(1,  bis, (int) bis.toString().length());
					MySqlJavaCon.InsertGuardar("");
				}
				
				String insertStation="";
				try{
					//StrStations += dt.getValueAt(a, 0).toString() + ", ";
					
					MySqlJavaCon.mySqlConn.setAutoCommit(true);
					MySqlJavaCon.updateTable("Delete from magiei_db.t_EstacionPaciente where idPaciente=(SELECT idPaciente FROM magiei_db.t_paciente where noEmpleado='"+ txtNoEmpleadoPaciente.getText() +"') ; ");
					for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
					CheckBox chkEstacionC= ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent()));
					if(chkEstacionC.selectedProperty().get()){
				//((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(true);
				
						String sta=((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).getText();
						insertStation =" insert into magiei_db.t_EstacionPaciente(idEstacion,idPaciente) values((SELECT IdEstacion FROM magiei_db.t_Estacion where nombre='"+ sta +"'),(SELECT idPaciente FROM magiei_db.t_paciente where noEmpleado='"+ txtNoEmpleadoPaciente.getText() +"')); ";
						MySqlJavaCon.updateTable(insertStation);
				
				
				 }
			 }
//					if(!"".equals(insertStation)){
//						
////						MySqlJavaCon.updateTable("Delete from magiei_db.t_EstacionPaciente where idPaciente="+ lblIdPaciente.getText() +" ; "+insertStation);
//					}
				}
				catch(Exception ex) {
					MessageBox.show("Error","","Error al guargar estacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
				}
				
				MessageBox.show("Guardar","","Informacion Almacenada ",Alert.AlertType.INFORMATION,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
				
				this.btnGuardarNvoPaciente.setDisable(true);
				this.btnCancelarAltaPaciente.setDisable(true);
				deshabilitarCamposPaciente(true);
				btnGuardarNvoPaciente.setDisable(true);
				btnCancelarAltaPaciente.setDisable(true);
				limpiarCamposPaciente();
			
			bActivoBuscar=true;
			}else{
				MessageBox.show("Error","","Error al guardar Informacion ",Alert.AlertType.INFORMATION,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
			}
			
		}
		else{
			MessageBox.show("Error","","Flantan datos Obligatorios",Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
		}
		catch (Exception ex) {
			MessageBox.show("Error","","Error al guargar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}

	@FXML
	private void CancelarPaciente(ActionEvent event) {
		try{
			limpiarCamposPaciente();
			deshabilitarCamposPaciente(true);
			this.btnGuardarNvoPaciente.setDisable(true);
			this.btnCancelarAltaPaciente.setDisable(true);
			bActivoBuscar=true;
		}
		catch (Exception ex) {
			MessageBox.show("Error","","Error al cancelar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}
	
	
	@FXML
	private void AgregarImagenPacinete(ActionEvent event) {
		
		try {
			FileChooser AbreArchivo=new FileChooser();
			File archivo ;
			AbreArchivo.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png")
			    );
//			AbreArchivo.setSelectedExtensionFilter(filtro);
			archivo =AbreArchivo.showOpenDialog(null);
			
			
			if(archivo!=null){

			Path get = Paths.get(archivo.getAbsolutePath());
			byte[] readAllBytes;
			readAllBytes = Files.readAllBytes(get);
				
			    
			ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
			BufferedImage read = ImageIO.read(bis);

			Image imagen = SwingFXUtils.toFXImage(read, null);
			
			imgPaciente.setImage(imagen);
			
			imgPaciente.setVisible(true);
			fileImage=new File(get.toString());
			
			
//			    imgPaciente.setPreserveRatio(true);
		    btnImgPaciente.setText("");
		
		
			}
		} catch(Exception ex) {
					
		}
	}
	
	protected String ObtenerEstacionesPaciente(String id)
	{
		
		String StrStations="";
		try{
			DefaultTableModel dt;
			dt=(MySqlJavaCon.GetTable("SELECT t_Estacion.Nombre FROM magiei_db.t_Estacion\n" +
							"left join magiei_db.t_EstacionPaciente\n" +
							"on t_EstacionPaciente.idEstacion=t_Estacion.IdEstacion\n" +
							"left join magiei_db.t_paciente on t_EstacionPaciente.idPaciente=t_paciente.idPaciente\n" +
							"left join magiei_db.t_area on magiei_db.t_area.IdArea=t_Estacion.idArea\n" +
							"where t_paciente.idPaciente="+ id +" ; "));
			
			for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
				 ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(false);
			 }	
			
			
		for (Integer a=0;a<dt.getRowCount() ;a++){
		StrStations += dt.getValueAt(a, 0).toString() + ", ";
			
			 for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
				 String sta=((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).getText();
				 if(sta.equals(dt.getValueAt(a, 0).toString())){
//					 CheckBox ck=new CheckBox
//					ck=((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent()));
//					ck.setSelected(true);
					((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(true);
				 }
//				 else{
//					 ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(false);
//				 }
			 }
			 
			   // ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(1)).content.get())).text.get()
		
		}
		StrStations=StrStations.substring(0, StrStations.length()-2);
		}
		catch(Exception ex) {
			MessageBox.show("Error","", ex.getMessage(),Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
		return StrStations;
	}
    
//</editor-fold>
    
    
	
	
    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

	
	protected void CargarCombos()
	{
		try{
			
		//Date x;
			
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		Integer year = now.get(Calendar.YEAR);      // The current year as an int

		//Integer a=(Calendar.getInstance().get(Calendar.YEAR)-70);
		for(Integer a=(year-70);a<(year-16);a++){
		AnioFecha.add(a);
		}
		//AnioFecha =FXCollections.observableArrayList();
		
//		for (Integer a=1;a<=12 ;a++){
//		MesFecha.add(a);
//		}
		MesFecha.add(new MesObj("Enero",1));
		MesFecha.add(new MesObj("Febrero",2));
		MesFecha.add(new MesObj("Marzo",3));
		MesFecha.add(new MesObj("Abril",4));
		MesFecha.add(new MesObj("Mayo",5));
		MesFecha.add(new MesObj("Junio",6));
		MesFecha.add(new MesObj("Julio",7));
		MesFecha.add(new MesObj("Agosto",8));
		MesFecha.add(new MesObj("Septiembre",9));
		MesFecha.add(new MesObj("Octubre",10));
		MesFecha.add(new MesObj("Noviembre",11));
		MesFecha.add(new MesObj("Diciembre",12));
		
		for (Integer a=1;a<=31 ;a++){
		DiaFecha.add(a);
		}
		
		
		cboAnioFechaNacPaciente.setItems(AnioFecha);
		cboMesFechaNacPaciente.setItems(MesFecha);		
		cboDiaFechaNacPaciente.setItems(DiaFecha);
		
		
		DefaultTableModel dt;
		dt=(MySqlJavaCon.GetTable("SELECT * FROM magiei_db.t_Estacion;  "));

		    if(dt.getRowCount()>0)
		    {
			    for(Integer c=0;c<dt.getRowCount();c++)
			    {
				    CustomMenuItem item1 = new CustomMenuItem(new CheckBox(dt.getValueAt(c,2).toString()));
				    item1.setHideOnClick(false);
				    item1.setOnAction(new EventHandler<ActionEvent>() {
					@Override public void handle(ActionEvent e) {
					       // lblEstaciones.setText("testtt");
					    String insertStation="";
						try{
						for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
						CheckBox chkEstacionC= ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent()));
							if(chkEstacionC.selectedProperty().get()){
									    //((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(true);

							String sta=((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).getText();
							insertStation +=sta +",";
						}
						}
						lblEstaciones.setText(insertStation.substring(0, insertStation.length()-1));
						}
						catch (Exception ex){lblEstaciones.setText("");}
					    }
					});

				    cboEstacionesPaciente.getItems().add(item1);
				    //cboEstacionesPaciente.getItems().get(c).setHideOnClick(false);
			    }
			    
//			    lblIdPaciente.setText(dt.getValueAt(0,0).toString());
//			    txtNombrePaciente.setText(dt.getValueAt(0,1).toString());
//		CheckBox cb0 = new CheckBox("x");  
//		CustomMenuItem item0 = new CustomMenuItem(new CheckBox("x"));  
//		CheckBox cb1 = new CheckBox("y");  
//		CustomMenuItem item1 = new CustomMenuItem(cb1);  
//		item0.setHideOnClick(false);  
//		item1.setHideOnClick(false);
//		
//		cboEstacionesPaciente.getItems().setAll(item0,item1);
		    }
		
		}
		catch(Exception ex) {
			MessageBox.show("Error","No se pudieron cargar catalogos", "No se pudieron cargar catalogos",Alert.AlertType.ERROR,getClass().getResource("/magiei/Principal/Magie1_Estilo.css"));
		}
	}
	
	
	
	
public void setDataPane(Node node) {
        // update VBox with new form(FXML) depends on which button is clicked
        dataPane.getChildren().setAll(node);
    }
	

    public VBox fadeAnimate(String url) throws IOException {
        VBox v = (VBox) FXMLLoader.load(getClass().getResource(url));
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        return v;
    }

	@FXML
    public void loadPane(ActionEvent event) throws IOException {
        setDataPane(fadeAnimate("/magiei/ExamenNvoIngreso/ExamenNvoIngreso.fxml"));
    }

//    public void loadPane2(ActionEvent event) throws IOException {
//        setDataPane(fadeAnimate("/samplefx/view/FXML2.fxml"));
//    }
//
//    public void loadPane3(ActionEvent event) throws IOException {
//        setDataPane(fadeAnimate("/samplefx/view/FXML3.fxml"));
//    }

	private void ActivarControl(MouseEvent event) {
		TextField x =(TextField) event.getSource();
		x.setDisable(false);
	}
//    

	public static boolean isNumeric(String str)
	{
	  try
	  {
	    double d = Double.parseDouble(str);
	  }
	  catch(NumberFormatException nfe)
	  {
	    return false;
	  }
	  return true;
	}
	
	
	
	/* Numeric Validation Limit the  characters to maxLengh AND to ONLY DigitS *************************************/
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
	
	public EventHandler<KeyEvent> numeric_ValidationCbo(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
		    ComboBox txt_TextField = (ComboBox) e.getSource();                
		    if (txt_TextField.getEditor().getText().length() >= max_Lengh) {                    
			e.consume();
		    }
		    if(e.getCharacter().matches("[0-9.]")){ 
			if(txt_TextField.getEditor().getText().contains(".") && e.getCharacter().matches("[.]")){
			    e.consume();
			}else if(txt_TextField.getEditor().getText().length() == 0 && e.getCharacter().matches("[.]")){
			    e.consume(); 
			}
		    }else{
			e.consume();
		    }
		}
	    };
	}
	
	public EventHandler<KeyEvent> numeric_ValidationCbo(final Integer max_Lengh,final Integer maxVal,final Integer minVal) {
	    return new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
		    ComboBox txt_TextField = (ComboBox) e.getSource();                
		    if (txt_TextField.getEditor().getText().length() >= max_Lengh ) {                    
			e.consume();
		    }
		    if(e.getCharacter().matches("[0-9]")){ 
			if(!Objects.equals(txt_TextField.getEditor().getText(), "")){
				if(!Objects.equals(Integer.parseInt(txt_TextField.getEditor().getText()), null)){
					if(Integer.parseInt(txt_TextField.getEditor().getText()+e.getCharacter())>maxVal){
						txt_TextField.getEditor().setText(maxVal.toString());
						e.consume(); 
					}else if (Integer.parseInt(txt_TextField.getEditor().getText()+e.getCharacter())<=minVal && txt_TextField.getEditor().getText().length() == max_Lengh-1 ){
						txt_TextField.getEditor().setText(minVal.toString());
						e.consume(); }
				}
			}
		    }else{
			e.consume();
		    }
		}
	    };
	} 
	/*****************************************************************************************/

	 /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
	public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
		    TextField txt_TextField = (TextField) e.getSource();                
		    if (txt_TextField.getText().length() >= max_Lengh) {                    
			e.consume();
		    }
		    if(e.getCharacter().matches("[A-Za-z]||\u00F1||\u00D1")){ 
		    }else{
			e.consume();
		    }
		}
	    };
	}
	
	public EventHandler<KeyEvent> letter_ValidationCbo(final Integer max_Lengh) {
	    return new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent e) {
		    ComboBox txt_TextField = (ComboBox) e.getSource();                
		    if (txt_TextField.getEditor().getText().length() >= max_Lengh) {                    
			e.consume();
		    }
		    if(e.getCharacter().matches("[A-Za-z]||\u00F1||\u00D1")){ 
		    }else{
			e.consume();
		    }
		}
	    };
	}

	@FXML
	private void CambiarColor(ActionEvent event) {
		if(null != cboEstatusPaciente.getValue()) //this.cboEstatusPaciente.
		switch (cboEstatusPaciente.getValue()) {
			case "Inactivo":
				//			cboEstatusPaciente.setStyle("-fx-text-fill: #ff0000;");
				cboEstatusPaciente.setId("CBstatusI");
				break;
			case "Activo":
				//			cboEstatusPaciente.setStyle("-fx-text-fill: #00ff00;");
//			cboEstatusPaciente.setStyle("-fx-text-fill: red;");
				cboEstatusPaciente.setId("CBstatusA");
//		    cboEstacionesPaciente.textProperty
				break;
			default:
				cboEstatusPaciente.setId("CBstatus");
				break;
		}
	}

	@FXML
	private void limpiarBusqueda(MouseEvent event) {
		if(bActivoBuscar){
			limpiarCamposPaciente();
		}
	}

	@FXML
	private void coboClickLimpiarAnio(MouseEvent event) {
		cboAnioFechaNacPaciente.getEditor().setText("");
		cboAnioFechaNacPaciente.setItems(AnioFecha);
	}
	
	@FXML
	private void coboClickLimpiarMes(MouseEvent event) {
		cboMesFechaNacPaciente.getEditor().setText("");
		cboMesFechaNacPaciente.setItems(MesFecha);
	}
	
	@FXML
	private void coboClickLimpiarDia(MouseEvent event) {
		cboDiaFechaNacPaciente.getEditor().setText("");
		
		Integer DiasLimite =31;
			try{
				
			Calendar cal = new GregorianCalendar(cboAnioFechaNacPaciente.getValue(),  cboMesFechaNacPaciente.getValue().Value()-1, 1);
			DiasLimite = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			}catch(Exception ex){
				try{
			Calendar cal = new GregorianCalendar(2001,  cboMesFechaNacPaciente.getValue().Value()-1, 1);
			DiasLimite = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
			}catch(Exception exx){
				DiasLimite =31;
				}
			}
		
		DiaFecha.clear();
		for (Integer a=1;a<=DiasLimite ;a++){
		DiaFecha.add(a);
		}
		
		cboDiaFechaNacPaciente.setItems(DiaFecha);
	}

	
	public static class MesObj {

		private  String TMesNombre;
		private  Integer TMesNumero;        

		private MesObj(String MesNombre,Integer MesNumero) {
		    this.TMesNombre = MesNombre;         
		    this.TMesNumero = MesNumero;             
		}  
		public Integer getTMesNumero() {
		    return TMesNumero;
		}
		public void setTMesNumero(Integer fNum) {
		    TMesNumero= fNum;
		}         
		public String getTMesNombre() {
		    return TMesNombre;
		}
		public void setMesNombre(String fNom) {
		    TMesNombre = fNom;
		}                 
		@Override
		public String toString() {
		    return TMesNombre;
		}
		
		public Integer Value() {
		    return TMesNumero;
		}

	} 
	
	
public class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

    private ComboBox comboBox;
    private StringBuilder sb;
    private ObservableList<T> data;
    private boolean moveCaretToPos = false;
    private int caretPos;

    public AutoCompleteComboBoxListener(final ComboBox comboBox) {
	    
        this.comboBox = comboBox;
        sb = new StringBuilder();
        data = comboBox.getItems();

        this.comboBox.setEditable(true);
	
        this.comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                comboBox.hide();
            }
        });
        this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
    }

    @Override
    public void handle(KeyEvent event) {

        if(event.getCode() == KeyCode.UP) {
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else if(event.getCode() == KeyCode.DOWN) {
            if(!comboBox.isShowing()) {
                comboBox.show();
            }
            caretPos = -1;
            moveCaret(comboBox.getEditor().getText().length());
            return;
        } else if(event.getCode() == KeyCode.BACK_SPACE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        } else if(event.getCode() == KeyCode.DELETE) {
            moveCaretToPos = true;
            caretPos = comboBox.getEditor().getCaretPosition();
        }

        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                || event.isControlDown() || event.getCode() == KeyCode.HOME
                || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
            return;
        }

        ObservableList list = FXCollections.observableArrayList();
        for (int i=0; i<data.size(); i++) {
            if(data.get(i).toString().toLowerCase().startsWith(
                AutoCompleteComboBoxListener.this.comboBox
                .getEditor().getText().toLowerCase())) {
                list.add(data.get(i));
            }
        }
        String t = comboBox.getEditor().getText();

        comboBox.setItems(list);
		if(list.size()>0 )
		{
        comboBox.getEditor().setText(t);}
		else
		{comboBox.getEditor().setText("");}
        //comboBox.getEditor().setText(t);
        if(!moveCaretToPos) {
            caretPos = -1;
        }
        moveCaret(t.length());
        if(!list.isEmpty()) {
            comboBox.show();
        }
    }

    private void moveCaret(int textLength) {
        if(caretPos == -1) {
            comboBox.getEditor().positionCaret(textLength);
        } else {
            comboBox.getEditor().positionCaret(caretPos);
        }
        moveCaretToPos = false;
    }

}
	
	

public class ComboBoxAutoComplete2<T> { 
  
 	private ComboBox<T> cmb; 
 	String filter = ""; 
 	private ObservableList<T> originalItems; 
  
 	public ComboBoxAutoComplete2(ComboBox<T> cmb) { 
 		this.cmb = cmb; 
 		originalItems = FXCollections.observableArrayList(cmb.getItems()); 
 		cmb.setTooltip(new Tooltip()); 
 		cmb.setOnKeyPressed(this::handleOnKeyPressed); 
 		cmb.setOnHidden(this::handleOnHiding); 
 	} 
  
 	public void handleOnKeyPressed(KeyEvent e) { 
 		ObservableList<T> filteredList = FXCollections.observableArrayList(); 
 		KeyCode code = e.getCode(); 
  
 		if (code.isLetterKey()) { 
 			filter += e.getText(); 
 		} 
 		if (code == KeyCode.BACK_SPACE && filter.length() > 0) { 
 			filter = filter.substring(0, filter.length() - 1); 
 			cmb.getItems().setAll(originalItems); 
 		} 
 		if (code == KeyCode.ESCAPE) { 
 			filter = ""; 
 		} 
 		if (filter.length() == 0) { 
 			filteredList = originalItems; 
 			cmb.getTooltip().hide(); 
 		} else { 
 			Stream<T> itens = cmb.getItems().stream(); 
 			String txtUsr = filter.toString().toLowerCase(); 
 			itens.filter(el -> el.toString().toLowerCase().contains(txtUsr)).forEach(filteredList::add); 
 			cmb.getTooltip().setText(txtUsr); 
 			Window stage = cmb.getScene().getWindow(); 
 			double posX = stage.getX() + cmb.getBoundsInParent().getMinX(); 
 			double posY = stage.getY() + cmb.getBoundsInParent().getMinY(); 
 			cmb.getTooltip().show(stage, posX, posY); 
 			cmb.show(); 
 		} 
 		cmb.getItems().setAll(filteredList); 
 	} 
  
 	public void handleOnHiding(Event e) { 
 		filter = ""; 
 		cmb.getTooltip().hide(); 
 		T s = cmb.getSelectionModel().getSelectedItem(); 
 		cmb.getItems().setAll(originalItems); 
 		cmb.getSelectionModel().select(s); 
 	} 

}


	
}
