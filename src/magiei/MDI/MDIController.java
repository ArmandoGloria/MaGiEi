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
import javafx.scene.control.Button;

import MySQLconn.ConexionMySQL;
import Resources.MessageBox;
//import Resources.GlobalClass1;
//import Resources.Global;
import com.mysql.jdbc.Blob;

import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.OutputStream;
import static java.nio.file.Files.readAllBytes;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.property.StringProperty;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;

import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
//import javax.swing.JFrame;




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
	protected ObservableList<Integer> MesFecha =FXCollections.observableArrayList() ;
	protected ObservableList<Integer> AnioFecha =FXCollections.observableArrayList();
	@FXML
	private Button btnMenuBar;
	
	//protected ObservableList<CheckItem> items =FXCollections.observableArrayList(); ;//= fetchItems();

	public MDIController() {
		
	}
	
	
	

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
	try{
		cboEstatusPaciente.setItems(status);
		cboSexoPaciente.setItems(sexo);
		
		
		txtNoEmpleadoPaciente.addEventFilter(KeyEvent.KEY_TYPED , numeric_Validation(10));
		txtNombrePaciente.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(10));
		MenuItem[] col = null;
		
//		cboEstacionPaciente2 = createComboBox(items);
//		cboEstacionPaciente2.setItems(items);
		//cboEstacionesPaciente.getItems().addAll(0, clctn)
		
		CargarCombos();
	}
	catch(Exception ex){
			MessageBox.show("Error","","Error al Inicializar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
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
	private ComboBox<Integer> cboMesFechaNacPaciente;
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
	@FXML
	private Label btnAgregarImagenPaciente;
	
	protected ObservableList<String> status =FXCollections.observableArrayList("Activo","Inactivo");
	@FXML
	private ComboBox<String> cboEstatusPaciente;
	
	protected ObservableList<String> sexo =FXCollections.observableArrayList("M","F");
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


		    //Global.checkEmptyFields(txtNoEmpleadoPaciente);


		    DefaultTableModel dt;
		    dt=(MySqlJavaCon.GetTable("SELECT * FROM magiei_db.t_paciente where NoEmpleado like'" + txtNoEmpleadoPaciente.getText() + "%' limit 1;  "));
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

			    cboEstacionesPaciente.setText(ObtenerEstacionesPaciente(lblIdPaciente.getText()));

			    cboAnioFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[0])));
			    cboMesFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[1])));
			    cboDiaFechaNacPaciente.setValue(Integer.valueOf((dt.getValueAt(0,6).toString().split("-")[2])));
			    
			  CheckBox x= ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(1)).getContent()));
			    
			    
			    
			    
			     

			    //cboAnioFechaNacPaciente.setValue(Calendar.YEAR);
			    //cboMesFechaNacPaciente.setValue(Calendar.MONTH);
			    //cboAnioFechaNacPaciente.setValue(Calendar.DAY_OF_MONTH);

			    txtNoEmpleadoPaciente.setText(dt.getValueAt(0,8).toString());
			    cboEstatusPaciente.setValue(dt.getValueAt(0,9).toString());
    //	      byte[] imageBytes = dt.getValueAt(0,8).getBytes(1, dt.getValueAt(0,8).toString().length());
    //			byte[] imgData = (byte[])dt.getValueAt(0,7);//Here r1.getBytes() extract byte data from resultSet 
    //            System.out.println(imgData);
    //            response.setHeader("expires", "0");
    //            response.setContentType("image/jpg");
    // OutputStream fos = new FileOutputStream("woman2.jpg");
    //
    //            Blob blob = result.getBlob("Data");
    //            int len = (int) blob.length();

    //            OutputStream os = null ;// = imgData..getOutputStream(); // output with the help of outputStr
    //            os.write(imgData);
    //            os.flush();
    //            os.close();

    //Path get = Paths.get("c:\\x.jpg");
	    //byte[] readAllBytes = Files.readAllBytes(get);
			    byte[] readAllBytes = (byte[])dt.getValueAt(0,7);


    //InputStream is=new ByteArrayInputStream(readAllBytes);
				//BufferedImage imag=ImageIO.read(is);


			    ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
			    BufferedImage read = ImageIO.read(bis);
    //            System.out.println(read);
			//Image convertToJavaFXImage = convertToJavaFXImage(readAllBytes, 1024, 768);
		       // ImageView view = new ImageView(convertToJavaFXImage);
			    Image imagen = SwingFXUtils.toFXImage(read, null);
		//image.setImage(image);

    //	    Image convertToJavaFXImage = convertToJavaFXImage(readAllBytes, 1024, 768);
    //        ImageView viewr = new ImageView(convertToJavaFXImage);
    //        root.getChildren().add(viewr);

		  //imgPaciente.setImage(convertToJavaFXImage);
			    imgPaciente.setImage(imagen);
			    imgPaciente.setVisible(true);

		  //cboEstatusPaciente.getValue(dt.getValueAt(0,9));
		    }
		    //MySqlJavaCon.CerrarConexion();
		}
		catch(Exception ex){
		    MessageBox.show("Error","","Error al Buscar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
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
		    txtPosicionPaciente.clear();
		    cboDiaFechaNacPaciente.setValue(null);
		    cboMesFechaNacPaciente.setValue(null);
		    cboAnioFechaNacPaciente.setValue(null);
		    cboEstatusPaciente.setValue(null);     
		    txtNoEmpleadoPaciente.clear();
		    imgPaciente.setImage(null);
		    //cboEstatusPaciente.getValue(dt.getValueAt(0,9));
		    fileImage=null;
		}
		catch(Exception ex){
			    MessageBox.show("Error","","Error en funcion limpiar campos \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
		}
	}

	private void deshabilitarCamposPaciente(boolean deshabilitar) {
		try
		{

		  txtNombrePaciente.disableProperty().set(deshabilitar);
		  txtApellidoPaternoPaciente.disableProperty().set(deshabilitar);
		  txtApellidoMaternoPaciente.disableProperty().set(deshabilitar);
		  cboSexoPaciente.disableProperty().set(deshabilitar);
		  txtPosicionPaciente.disableProperty().set(deshabilitar);
		  cboDiaFechaNacPaciente.disableProperty().set(deshabilitar);
		  cboMesFechaNacPaciente.disableProperty().set(deshabilitar);
		  cboAnioFechaNacPaciente.disableProperty().set(deshabilitar);
		  txtNombrePaciente.disableProperty().set(deshabilitar);     
		  txtNoEmpleadoPaciente.disableProperty().set(deshabilitar);
		  cboEstatusPaciente.disableProperty().set(deshabilitar);
		  imgPaciente.disableProperty().set(deshabilitar);
		  btnImgPaciente.disableProperty().set(deshabilitar);
		}
		catch(Exception ex){
		    MessageBox.show("Error","","Error en funcion deshabilirar botones \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
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

		    //CargarCombos();
		}
		catch(Exception ex){
		    MessageBox.show("Error","","Error en fucion agrgar \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
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




		    //CargarCombos();
		    }
		    else{
//			 String backupDir = "/Users/al/backups";
//			     
//				// create a jframe
//				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
//			
//				// show a joptionpane dialog using showMessageDialog
//				MessageBox.show(frame,
//			        "fgh" + ": '" + "fjkkk" + "'.",
//			        "ggggg",
//			        Alert.AlertType.ERROR);
//	
//				frame.dispose();
			   
			 MessageBox.show("Error","","Debe seleccionar un registro prmero ",Alert.AlertType.WARNING);
//			 MessageBox.show("fdd", "fggg");
//			 Stage primaryStage = null;
//			 int answer = jfx.messagebox.MessageBox.show(primaryStage, 
// 						"Ejemplo de un DialogBox.\n\nLas opciones son las siguientes.\n[MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL]", 
//						"Information dialog",  
// 						jfx.messagebox.MessageBox.ICON_INFORMATION| jfx.messagebox.MessageBox.OK | jfx.messagebox.MessageBox.CANCEL); 
			 
			 
////			 Alert alert = new Alert(AlertType.ERROR);
////alert.setTitle("Error Dialog");
////alert.setHeaderText("Look, an Error Dialog");
////alert.setContentText("Ooops, there was an error!");
////
////alert.showAndWait();
           //MessageBox.show(null,"Debe seleccionar un registro prmero ");
			     
		    }



		    //limpiarCamposPaciente();
		}
		catch(Exception ex){
//			    global.MessageBox.show("Error","Error al Editar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
           MessageBox.show("Error","","Error al Editar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
		}
	}

	@FXML
	private void GuardarPaciente(ActionEvent event) {
		try{
			
			this.btnGuardarNvoPaciente.setDisable(true);
			this.btnCancelarAltaPaciente.setDisable(true);
			
			boolean FechaValida=false;
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat ft = new SimpleDateFormat("YYYY-MM-dd");

			
			try{
			calendar.set(cboAnioFechaNacPaciente.getValue(), cboMesFechaNacPaciente.getValue() , cboDiaFechaNacPaciente.getValue());
			FechaValida=true;
			}catch(Exception ex){FechaValida=false;}
			
			

			if(FechaValida && this.txtNombrePaciente.getText()!="" && this.txtApellidoPaternoPaciente.getText()!="" && this.txtApellidoMaternoPaciente.getText()!="" && this.txtNoEmpleadoPaciente.getText()!="" && this.txtPosicionPaciente.getText()!="" && this.cboSexoPaciente.getValue()!="" && this.cboEstatusPaciente.getValue()!="" ){
			if(this.lblIdPaciente.getText()==""){
				strMySqlQuery="INSERT INTO `magiei_db`.`t_paciente` (`Nombre`, `ApPat`, `ApMat`, `Sexo`, `Posicion`, `FechNac`, `NoEmpleado`, `Activo`) VALUES ('" + txtNombrePaciente.getText() + "', '" + txtApellidoPaternoPaciente.getText() + "', '" + txtApellidoMaternoPaciente.getText() + "', '" + cboSexoPaciente.getValue() + "', '" + txtPosicionPaciente.getText() + "', '" +  ft.format(calendar.getTime()) + "', '" + txtNoEmpleadoPaciente.getText() + "', '" + cboEstatusPaciente.getValue() + "'); ";
			} 
			else{
				strMySqlQuery="UPDATE `magiei_db`.`t_paciente` SET `Nombre`='" + txtNombrePaciente.getText() + "', `ApPat`='" + txtApellidoPaternoPaciente.getText() + "', `ApMat`='" + txtApellidoMaternoPaciente.getText() + "', `Sexo`='" + cboSexoPaciente.getValue() + "', `Posicion`='" + txtPosicionPaciente.getText() + "', `FechNac`='" + ft.format(calendar.getTime()) + "', `NoEmpleado`='" + txtNoEmpleadoPaciente.getText() + "', `Activo`='" + cboEstatusPaciente.getValue() + "' WHERE idPaciente=" + lblIdPaciente.getText() + ";";
			}
			if(MySqlJavaCon.updateTable(strMySqlQuery)){
				MessageBox.show(null,"Informacion Almacenada ");
				deshabilitarCamposPaciente(true);
				btnGuardarNvoPaciente.setDisable(true);
				btnCancelarAltaPaciente.setDisable(true);
				
				MySqlJavaCon.mySqlConn.setAutoCommit(false);
				
			
//      File file = new File("C:\\x.jpg");      
//      FileInputStream fis = null;
//      fis = new FileInputStream(file);
//      MySqlJavaCon.ps = MySqlJavaCon.mySqlConn.prepareStatement("UPDATE `magiei_db`.`t_paciente` SET  `Foto`=? WHERE idPaciente=" + lblIdPaciente.getText() + ";");
//      MySqlJavaCon.ps.setBinaryStream(1,  fis, (int) file.length());
//MySqlJavaCon.InsertGuardar("");
				
      //File file = new File(fileImage);      
      InputStream fis = null;
      fis = new FileInputStream(fileImage);
      MySqlJavaCon.ps = MySqlJavaCon.mySqlConn.prepareStatement("UPDATE `magiei_db`.`t_paciente` SET  `Foto`=? WHERE idPaciente=" + lblIdPaciente.getText() + ";");
      MySqlJavaCon.ps.setBinaryStream(1,  fis, (int) fileImage.length());
      //MySqlJavaCon.ps.setBlob(1,  bis, (int) bis.toString().length());
MySqlJavaCon.InsertGuardar("");
				
				limpiarCamposPaciente();
			
			}else{
				MessageBox.show("Error","","Error al guardar Informacion ",Alert.AlertType.INFORMATION);
			}
			
		}
		else{
			MessageBox.show(null,"Flantan datos Obligatorios");
		}
		}
		catch (Exception ex) {
			MessageBox.show("Error","","Error al guargar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
		}
	}

	@FXML
	private void CancelarPaciente(ActionEvent event) {
		try{
			limpiarCamposPaciente();
			deshabilitarCamposPaciente(true);
			this.btnGuardarNvoPaciente.setDisable(true);
			this.btnCancelarAltaPaciente.setDisable(true);
		}
		catch (Exception ex) {
			MessageBox.show("Error","","Error al cancelar informacion \n ERROR : " + ex.getMessage(),Alert.AlertType.ERROR);
		}
	}
	
	
	@FXML
	private void AgregarImagenPacinete(ActionEvent event) {
		
		try {
			FileChooser AbreArchivo=new FileChooser();
			File archivo ;
			ExtensionFilter filtro= new ExtensionFilter("Imagenes","png");
			AbreArchivo.setSelectedExtensionFilter(filtro);
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
		
		
//		FileFilter Ft = new FileNameExtensionFilter("Imagenes","png");
//		jFileChooser1.addChoosableFileFilter(Ft);
//		PanelOpenFile xx =new PanelOpenFile();
//		xx.setVisible(true);
		//xx.
//		Integer returnVal=jFileChooser1.showOpenDialog(xx);
//		if( returnVal==JFileChooser.APPROVE_OPTION)
//		{
//			File fileName=jFileChooser1.getSelectedFile();
//			String strFile=fileName.toString();
//		}

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
							"where t_paciente.idPaciente=1; "));
		for (Integer a=0;a<dt.getRowCount() ;a++){
		StrStations += dt.getValueAt(a, 0).toString() + ", ";
			
			 for(Integer cc=0;cc<cboEstacionesPaciente.getItems().toArray().length ;cc++){
				 String sta=((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).getText();
				 if(sta.equals(dt.getValueAt(a, 0).toString())){
				((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(cc)).getContent())).setSelected(true);
				 }
			 }
			 
			   // ((CheckBox)(((CustomMenuItem)cboEstacionesPaciente.getItems().get(1)).content.get())).text.get()
		
		}
		StrStations=StrStations.substring(0, StrStations.length()-2);
		}
		catch(Exception ex) {
			MessageBox.show("Error","", ex.getMessage(),Alert.AlertType.ERROR);
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
		
		for (Integer a=1;a<=12 ;a++){
		MesFecha.add(a);
		}
		
		for (Integer a=1;a<=31 ;a++){
		DiaFecha.add(a);
		}
		
//		items.add(new CheckBox("df"));
//		items.add(new CheckBox("dhf"));
//		items.add(new CheckBox("dfgf"));
//		items.add(new CheckBox("dddf"));
//		
//		cboEstacionPaciente2.setItems(items);




//		cboEstacionPaciente2.setItems(data);
//            cboEstacionPaciente2.setCellFactory(listView -> new CheckItemListCell());
		
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
			MessageBox.show("Error","", ex.getMessage(),Alert.AlertType.ERROR);
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
	
//	public boolean isNumeric(String s) {
//		int len = s.length();
//		for (int i = 0; i < len; ++i) {
//		    if (!s.charAt(i).isNumeric()) {
//			return false;
//		    }
//		}
//		return true;
//	}

//	public boolean isNumeric(String s) {
//		return java.util.regex.Pattern.matches("\\d+", s);
//	}

	
	
	
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
	/*****************************************************************************************/
	
//   int answer = jfx.messagebox.MessageBox.show(primaryStage, 
// 						"Ejemplo de un DialogBox.\n\nLas opciones son las siguientes.\n[MessageBox.ICON_INFORMATION | MessageBox.OK | MessageBox.CANCEL]", 
//						"Information dialog",  
// 						jfx.messagebox.MessageBox.ICON_INFORMATION| jfx.messagebox.MessageBox.OK | jfx.messagebox.MessageBox.CANCEL); 
// 
// 
// 		if (answer == jfx.messagebox.MessageBox.OK) { 
// 			System.out.println("Ok"); 
// 		} else if (answer == jfx.messagebox.MessageBox.CANCEL) { 
// 			System.out.println("Cancel"); 
//		} 
// 
// 
// 		answer = jfx.messagebox.MessageBox.show(primaryStage, 
// 						"Ejemplo de un mensaje de error.\n\nLas opciones son las siguientes.\n[MessageBox.ICON_ERROR]", 
//						"Error dialog",  
//						jfx.messagebox.MessageBox.ICON_ERROR); 
//		System.out.println(answer); 
    
}

