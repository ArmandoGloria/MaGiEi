/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.Principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Screen;


import MySQLconn.ConexionMySQL;
import Resources.MessageBox;
//import br.com.supremeforever.suprememdiwindow.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author SergioHome
 */
public class MaGiEi1 extends Application {
    
	
	int i=1;
	
    @Override
    public void start(Stage stage) throws Exception {
	
	    
	    
	// load main form in to VBox (Root)
        VBox mainPane = (VBox) FXMLLoader.load( getClass().getResource("/magiei/MDI/MDI.fxml" ) );
        // add main form into the scene
        Scene scene = new Scene(mainPane);
        
        stage.setTitle("MaGiEi 1.0 powered by InteliGene");
        stage.setScene(scene);
        stage.setMaximized(false);    // make the main form fit to the screen
        stage.setResizable(false);
        stage.show(); 
	
	
////	Button b=new Button("+");
////	MDICanvas canvas=new MDICanvas();
////	canvas.setPrefSize(1024, 600);
////	b.setOnAction((ActionEvent event)->{
////		    
////        AnchorPane root = null;
////		try {
////			root = FXMLLoader.load(getClass().getResource("/magiei/MDI/MDI.fxml"));
////		} catch (IOException ex) {
////			Logger.getLogger(MaGiEi1.class.getName()).log(Level.SEVERE, null, ex);
////		}
////		try {
////		MDIWindow mDIWindow = new MDIWindow("idPrincipal"+i,new ImageView("https://api0-a.akamaihd.net/api/maa2/banner-en_US.png"),"test"+i,root);
////		canvas.addMDIWindow(mDIWindow);
////		i++;
////		} 
////		catch (Exception ex) {
////			Logger.getLogger(MaGiEi1.class.getName()).log(Level.SEVERE, null, ex);
////		}
////	});
////	
////	
////	
////	VBox box = new VBox(b,canvas);
//////	AnchorPane.setBottomAnchor(box, 0d);
////	AnchorPane.setLeftAnchor(box, 0d);
//////	AnchorPane.setRightAnchor(box, 0d);
//////	AnchorPane.setTopAnchor(box, 0d);
////	
////        //Font.loadFont(MaGiEi1.class.getResource("centurygothic.TTF").toExternalForm(), 10);
////        /******AnchorPane root = FXMLLoader.load(getClass().getResource("/magiei/MDI/MDI.fxml"));*/
////	//Parent root = FXMLLoader.load(getClass().getResource("MDI.fxml"));
////        AnchorPane pane=new AnchorPane(box);
////        
////        Scene scene = new Scene(pane);
////        
////        
////        
////        stage.setScene(scene);
////        stage.setTitle("MaGiEi 1.0 powered by InteliGene");
////        //stage.getIcons().add(new Image("/Resources/Buttons/addButton.png"));  Agrega icono a ventana principal
////        stage.setResizable(false);
////        
////        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//	    MessageBox msj;
        launch(args);
    }
    
    
    
//    public static void main(String[] args) {
//        launch(args);
//    }
    
}
