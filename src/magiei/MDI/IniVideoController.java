/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magiei.MDI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


/**
 * FXML Controller class
 *
 * @author Shadowsboy
 */

public class IniVideoController implements Initializable {

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

        MediaPlayer player2 = new MediaPlayer( new Media(getClass().getResource("2.mp4").toExternalForm()));
        MediaView mediaView2 = new MediaView(player2);
	mediaView2.setFitWidth(400);
	mediaView2.setFitHeight(200);
        videoPanel.getChildren().add(mediaView2);

//        Scene scene = new Scene(root, 1024, 768);

//        primaryStage.setScene(scene);
//        primaryStage.show();


        player2.play();
	
	}	

//Timer formClose = new Timer();
//private void myForm_Load(object sender, EventArgs e)
//    {
//    formClose.Interval = 10000;
//    formClose.Tick += new EventHandler(formClose_Tick);
//    formClose.Start();
//    }
// 
//void formClose_Tick(object sender, EventArgs e)
//    {
//    formClose.Stop();
//    formClose.Tick -= new EventHandler(formClose_Tick);
//    this.Close();
//    }
//
//
//Timer formCloser = new Timer();
//private void mySecondForm_Load(object sender, EventArgs e)
//{
//    formCloser.Interval = 10000;
//    formCloser.Enabled = true;
//    frmCloser.Tick += new EventHandler(formClose_Tick)
//}
// 
//private void formClose_Tick(object sender, EventArgs e)
//{
//    this.DialogResult = DialogResult.OK;
//}


}
