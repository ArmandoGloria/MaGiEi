/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.Principal;

import static java.lang.Thread.sleep;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.text.Font;
import javafx.stage.Stage;
//import javafx.stage.Screen;
//
//
//import MySQLconn.ConexionMySQL;
//import Resources.MessageBox;
////import br.com.supremeforever.suprememdiwindow.*;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//
//
//import javafx.event.ActionEvent;
//import javafx.scene.control.Button;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author SergioHome
 */
public class MaGiEi1 extends Application {
    
	public Object cerrojoImplicito = new Object();

	int i=1;
	
    @Override
    public void start(Stage stage) throws Exception {
	    Stage stage2 = new Stage() ;
	    
	    
	// load main form in to VBox (Root)
//	hideAcordion();
//        setDataPane(fadeAnimate("/magiei/MDI/IniVideo2.fxml"));
//        VBox mainPane = (VBox) FXMLLoader.load( getClass().getResource("/magiei/MDI/IniVideo2.fxml" ) );
//	Duration.millis(100500);
//	mainPane=null;
        VBox mainPane = (VBox) FXMLLoader.load( getClass().getResource("/magiei/MDI/IniVideo.fxml" ) );
        // add main form into the scene
        Scene scene = new Scene(mainPane);
        
        stage.setTitle("MaGiEi 1.1 powered by InteliGene");
        stage.setScene(scene);
        stage.setMaximized(false);    // make the main form fit to the screen
        stage.setResizable(false);
	
	            
	
//	
//	synchronized (cerrojoImplicito) {
//		
//		
//		
//        stage2.show(); 
//		
//                    cerrojoImplicito.wait(5000);
//	sleep(25000);
//		    stage2.close();
//                }
//
//	sleep(25000);
//	Duration.millis(15000);
//	
//	
//	
//	
//	VBox mainPane2 = (VBox) FXMLLoader.load( getClass().getResource("/magiei/MDI/MDI.fxml" ) );
//        // add main form into the scene
//        Scene scene2 = new Scene(mainPane2);
//        
//        stage.setTitle("MaGiEi 1.1 powered by InteliGene");
//        stage.setScene(scene2);
//        stage.setMaximized(false);    // make the main form fit to the screen
//        stage.setResizable(false);
        stage.show(); 
	
	PauseTransition pause = new PauseTransition(Duration.seconds(10));
	pause.setOnFinished(e -> stage.hide());
	pause.play();
	
        VBox mainPane2 = (VBox) FXMLLoader.load( getClass().getResource("/magiei/MDI/MDI.fxml" ) );
        // add main form into the scene
        Scene scene2 = new Scene(mainPane2);
        stage2.setScene(scene2);
	
	PauseTransition pause2 = new PauseTransition(Duration.seconds(4));
	pause2.setOnFinished(e -> stage2.show());
	pause2.play();
	
//        stage2.show(); 
	
	
	
	
//////	
////// VBox[] v=new VBox[2];
//////	v[0] = (VBox) FXMLLoader.load(getClass().getResource("/magiei/MDI/IniVideo2.fxml"));
//////	v[1] = (VBox) FXMLLoader.load(getClass().getResource("/magiei/MDI/MDI.fxml"));
//////        FadeTransition ft = new FadeTransition(Duration.millis(4000));
//////        ft.setNode(v[0]);
//////        ft.setFromValue(0.0);
//////        ft.setToValue(1);
//////        ft.setCycleCount(1);//Transition.INDEFINITE);
////////        ft.setAutoReverse(true);
//////	FadeTransition ft2 = new FadeTransition(Duration.millis(4000));
//////        ft2.setNode(v[1]);
//////        ft2.setFromValue(1);
//////        ft2.setToValue(0);
//////        ft2.setCycleCount(1);//Transition.INDEFINITE);
////////        ft2.setAutoReverse(true);
////////	ParallelTransition pt = new ParallelTransition(ft2, ft); 
////////        pt.play();
//////// PanelCentral.getChildren().setAll(v);
////// 
////// 
////// 
////// 
////// 
////// SequentialTransition slideshow = new SequentialTransition();
//////
//////    for (VBox vs : v) {
//////
//////        SequentialTransition sequentialTransition = new SequentialTransition();
//////
//////        FadeTransition fadeIn = ft;//Transition.getFadeTransition(ft, 0.0, 1.0, 2000);
//////        FadeTransition stayOn = ft2;//Transition.getFadeTransition(ft2, 1.0, 1.0, 2000);
////////        FadeTransition fadeOut = Transition.getFadeTransition(slide, 1.0, 0.0, 2000);
//////
//////        sequentialTransition.getChildren().addAll(fadeIn, stayOn);//, fadeOut); 
//////	sequentialTransition.setCycleCount(Animation.INDEFINITE); 
//////	sequentialTransition.setAutoReverse(true);
//////        slideshow.getChildren().add(sequentialTransition);    
//////	
//////        scene.getChildren().add(vs);
////////        slideshow.getChildren().add(sequentialTransition);
//////	
////////        PanelCentral.getChildren().setAll(vs);   
//////    }
//////    slideshow.play();                  
//////
//////	
//////	
//////	
////////	Scene scene = new Scene(mainPane);
//////        
//////        stage2.setTitle("MaGiEi 1.1 powered by InteliGene");
//////        stage2.setScene(scene);
//////        stage2.setMaximized(false);    // make the main form fit to the screen
//////        stage2.setResizable(false);
//////        stage2.show(); 
//////	
	
	
	
	
	
	
	
	
	
	
	
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
