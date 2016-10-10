/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.MDI;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


/**
 * FXML Controller class
 *
 * @author Shadowsboy
 */
public class IniVideo2Controller implements Initializable {

	@FXML
	private Label lblIdPaciente;
	@FXML
	private Pane panelGrafiica;
	@FXML
	private Label lblVistasPuestoEstaArea;
	@FXML
	private TextField txtVisitasNoPaciente;
	@FXML
	private AnchorPane videoPanel;

	/**
	 * Initializes the controller class.
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		
		
//			Media media = null;
//        try{
//            File video = new File("1.mp4");
//            String url2 = video.toURI().toURL().toString();
////            System.out.println("URL: "+url);
//            media = new Media(url2);
//        }catch(Exception e){
////        System.err.println(e.toString());
//    }
//        MediaPlayer player = new MediaPlayer(media);
//        player.play();
//        MediaView mediaView = new MediaView(player);
//
//        panelGrafiica = new StackPane();
////        root.setAlignment(Pos.CENTER);
//        panelGrafiica.setStyle("-fx-background-color : white;");
//        panelGrafiica.getChildren().add(mediaView);

	
	
	
	
	
//		    StackPane root = new StackPane();

        MediaPlayer player2 = new MediaPlayer( new Media(getClass().getResource("1.mp4").toExternalForm()));
        MediaView mediaView2 = new MediaView(player2);
	mediaView2.setFitWidth(400);
	mediaView2.setFitHeight(200);
        videoPanel.getChildren().add(mediaView2);

//        Scene scene = new Scene(root, 1024, 768);

//        primaryStage.setScene(scene);
//        primaryStage.show();


        player2.play();
	
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
