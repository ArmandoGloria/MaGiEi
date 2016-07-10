/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

/**
 *
 * @author Shadowsboy
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.Files.readAllBytes;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

import javafx.scene.layout.BackgroundImage;

public class MessageBox
{
	public static void show(String message, String title)
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		
		stage.initStyle(StageStyle.TRANSPARENT);

		Label lbl = new Label();
		lbl.setText(message);

		Button btnOK = new Button();
		btnOK.setText("OK");
		btnOK.setOnAction(e -> stage.close());

		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, btnOK);
		pane.setAlignment(Pos.CENTER);
		
		
File f = new File("src/magiei/Principal/Magie1_Estilo.css");
pane.getStylesheets().clear();
pane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
pane.setId("myDialog");
//pane.getStyleClass().add("myDialog");
//pane.setStyle("-fx-background-image: url('../../Resources/Imagenes/arrow.png')"); 
//
//
//
//
//File f = new File("src/magiei/Principal/Magie1_Estilo.css");
//
//
//
		Scene scene = new Scene(pane);
//		
//		
//scene.getStylesheets().clear();
//scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		
		stage.setScene(scene);
		
		
		stage.showAndWait();
	}
	
	 public static void show(String Titulo,String Encabezado,String Mensaje,Alert.AlertType Tipo)
    {
	 Alert alert = new Alert(Tipo);
	alert.setTitle(Titulo);
	alert.setHeaderText(Encabezado);
	alert.setContentText(Mensaje);
	
//	ImageView c = null;
//	byte[] readAllBytes = null;
//			Path get = Paths.get("C:\\Mensaje.png");
//		try {
//			readAllBytes = Files.readAllBytes(get);
//		} catch (IOException ex) {
//			Logger.getLogger(MessageBox.class.getName()).log(Level.SEVERE, null, ex);
//		}
//				
//			    
//			ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
//			BufferedImage read = null;
//		try {
//			read = ImageIO.read(bis);
//		} catch (IOException ex) {
//			Logger.getLogger(MessageBox.class.getName()).log(Level.SEVERE, null, ex);
//		}
//
//			Image imagen = SwingFXUtils.toFXImage(read, null);
			
			
			
			
			
			
			
			//c.setImage(imagen);
//alert.setGraphic(new ImageView(ImageCache.getInstance().get("/images/gallio/gallio-sad.png")));



//alert.setGraphic(new ImageView(imagen));




//alert.setDialogPane(value);

DialogPane dialogPane = alert.getDialogPane();

		alert.initStyle(StageStyle.TRANSPARENT);
//String css = DynamicCSS.class.getResource("/jarcss.css").toExternalForm();
//dialogPane.getStylesheets().clear();
//dialogPane.getStylesheets().add(css);

File f = new File("src/magiei/Principal/Magie1_Estilo.css");
dialogPane.getStylesheets().clear();
dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));


//dialogPane.getStylesheets().add(getClass().getResource("Magie1_Estilo.css").toExternalForm());


//BackgroundImage bgi=null;
////bgi.getImage().
//Background bg = null;
//bg.getImages().add(bgi);
//dialogPane.backgroundProperty().set(bg);
////dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());

//alert.getContentText().getClass().;





//dialogPane.getStyleClass().add("myDialog");
//dialogPane.setStyle("-fx-background-image: url('../../Resources/Imagenes/arrow.png')"); 
//alert.setDialogPane(dialogPane);






//DialogPane dialogPane = alert.getDialogPane();
// root


    //dialogPane.getStyleClass().remove("alert");
//    dialogPane.setId("DialogPane");
//dialogPane.getStyleClass().add("DialogPane");
//dialogPane.setStyle("DialogPane");
//   dialogPane.setStyle("-fx-background-image: url(\"http://docs.oracle.com/javafx/2/get_started/img/two_logins.png\");");
//
//// 1. Grid
//    // remove style to customize header
//
////    GridPane grid = (GridPane)dialogPane.lookup(".header-panel"); 
////    grid.setStyle("-fx-background-color: cadetblue; "
////            + "-fx-font-style: italic;");
//
//    // custom icon
//    StackPane stackPane = new StackPane(new ImageView(imagen));
//    stackPane.setPrefSize(24, 24);
//    stackPane.setAlignment(Pos.CENTER);
//    dialogPane.setGraphic(stackPane); 
//
//// 2. ContentText with just a Label
//    dialogPane.lookup(".content.label").setStyle("-fx-font-size: 16px; "
//            + "-fx-font-weight: bold; -fx-fill: blue;");
//    
//    dialogPane.lookup(".content.label").setStyle("-fx-font-size: 16px; "
//            + "-fx-font-weight: bold; -fx-fill: blue;");
//
//// 3- ButtonBar
//    ButtonBar buttonBar = (ButtonBar)alert.getDialogPane().lookup(".button-bar");
//    buttonBar.setStyle("-fx-font-size: 24px;"
//            + "-fx-background-color: indianred;");
//    buttonBar.getButtons().forEach(b->b.setStyle("-fx-font-family: \"Andalus\";"));










//alert.setGraphic(c);
	alert.showAndWait();
    }
	 
	 
//	 
//	@Override
//public Alert createAlert(Alert.AlertType alertType, String text, ButtonType... buttons) {
//  String theme = preferencesService.getPreferences().getTheme();
//  String themeCss = String.format("/themes/%s/style.css", theme);
//
//  Alert alert = new Alert(alertType);
//  alert.initStyle(StageStyle.TRANSPARENT);
//  alert.setDialogPane(fxmlLoader.loadAndGetRoot("dialog.fxml"));
//  alert.getDialogPane().getScene().getStylesheets().add(themeCss);
//  alert.getButtonTypes().setAll(buttons);
//  alert.setContentText(text);
//
//  return alert;
//} 
	 
	 
	 
}
