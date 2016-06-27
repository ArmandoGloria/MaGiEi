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


public class MessageBox
{
	public static void show(String message, String title)
	{
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);

		Label lbl = new Label();
		lbl.setText(message);

		Button btnOK = new Button();
		btnOK.setText("OK");
		btnOK.setOnAction(e -> stage.close());

		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, btnOK);
		pane.setAlignment(Pos.CENTER);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	 public static void show(String Titulo,String Encabezado,String Mensaje,Alert.AlertType Tipo)
    {
	 Alert alert = new Alert(Tipo);
	alert.setTitle(Titulo);
	alert.setHeaderText(Encabezado);
	alert.setContentText(Mensaje);
	
	ImageView c = null;
	byte[] readAllBytes = null;
			Path get = Paths.get("c://x.png");
		try {
			readAllBytes = Files.readAllBytes(get);
		} catch (IOException ex) {
			Logger.getLogger(MessageBox.class.getName()).log(Level.SEVERE, null, ex);
		}
				
			    
			ByteArrayInputStream bis = new ByteArrayInputStream(readAllBytes);
			BufferedImage read = null;
		try {
			read = ImageIO.read(bis);
		} catch (IOException ex) {
			Logger.getLogger(MessageBox.class.getName()).log(Level.SEVERE, null, ex);
		}

			Image imagen = SwingFXUtils.toFXImage(read, null);
			
			//c.setImage(imagen);
//alert.setGraphic(new ImageView(ImageCache.getInstance().get("/images/gallio/gallio-sad.png")));

alert.setGraphic(new ImageView(imagen));
//alert.setDialogPane(value);

DialogPane dialogPane = alert.getDialogPane();
//dialogPane.getStylesheets().add(getClass().getResource("myDialogs.css").toExternalForm());
dialogPane.getStyleClass().add("myDialog");


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
