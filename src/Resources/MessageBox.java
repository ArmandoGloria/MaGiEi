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

import java.io.File;
import java.net.URL;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import javafx.scene.paint.Color;

public class MessageBox
	
{
//	 URL estiloPadre=null;
	
	public static void show( String title,String Encabezado,String message,Alert.AlertType Tipo,  URL otro)
	{
		//Stage stage = new Stage(); 
		final Stage stage = new Stage(StageStyle.TRANSPARENT); 

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		
		//stage.initStyle(StageStyle.TRANSPARENT);

		Label lbl = new Label();
		lbl.setText(message);

		Button btnOK = new Button();
		btnOK.setText("OK");
		btnOK.setOnAction(e -> stage.close());

		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, btnOK);
		pane.setAlignment(Pos.CENTER);
		
		
//File f = new File("src/magiei/Principal/Magie1_Estilo.css");
//pane.getStylesheets().clear();
//pane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

pane.getStylesheets().add(otro.toExternalForm());
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
		Scene scene = new Scene(pane,Color.TRANSPARENT);
//		
//		
//scene.getStylesheets().clear();
//scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		
		stage.setScene(scene);
		
		
		stage.showAndWait();
	}
	
	 public static void show(String Titulo,String Encabezado,String Mensaje,Alert.AlertType Tipo, URL otro,boolean anterior)
    {
	    final Stage stage = new Stage(StageStyle.TRANSPARENT); 

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("ffg");
		stage.setMinWidth(250);
		 Alert alert = new Alert(Tipo);
	    alert.setTitle(Titulo);
	alert.setHeaderText(Encabezado);
	alert.setContentText(Mensaje);
		alert.initStyle(StageStyle.TRANSPARENT);
DialogPane dialogPane = alert.getDialogPane();


//		VBox pane = new VBox(20);
//		pane.getChildren().addAll(dialogPane);
//		pane.setAlignment(Pos.CENTER);

	dialogPane.getStylesheets().add(otro.toExternalForm()); 
dialogPane.setId("myDialog");
//	    Scene scene = new Scene(pane,Color.TRANSPARENT);
//		stage.setScene(scene);
	    alert.showAndWait();
	    
	    
	    
	    /*
	    
	    
	    final Stage stage = new Stage(StageStyle.TRANSPARENT); 
final Window parent = null ;
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("ffg");
		stage.setMinWidth(250);
		 Alert alert = new Alert(Tipo);
	alert.setTitle(Titulo);
	alert.setHeaderText(Encabezado);
	alert.setContentText(Mensaje);
		alert.initStyle(StageStyle.TRANSPARENT);
DialogPane dialogPane = alert.getDialogPane();
	dialogPane.getStylesheets().add(otro.toExternalForm());    
	alert.initOwner(parent);
//	dialogPane.getParent().getScene()..getParent().setStyle("-fx-background-color: transparent;");
//	dialogPane.getParent().setStyle("-fx-background-color: transparent;");
Scene scene = new Scene(dialogPane,Color.TRANSPARENT);


StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().add(dialogPane);
                
                Scene secondScene = new Scene(secondaryLayout, 200, 100);

//                Stage secondStage = new Stage();
//                secondStage.setTitle("Second Stage");
                stage.setScene(secondScene);
                
                //Set position of second window, related to primary window.
//                secondStage.setX(primaryStage.getX() + 250);
//                secondStage.setY(primaryStage.getY() + 100);
// 
//                secondStage.show();


	alert.initOwner(stage);


stage.showAndWait();

//	alert.showAndWait();
	
//		VBox pane = new VBox(20);
//		pane.getChildren().addAll(alert.getDialogPane());
//		pane.setAlignment(Pos.CENTER);
//pane.getStylesheets().add(otro.toExternalForm());
//pane.setId("myDialog");


//alert.initOwner(stage);
//
//		Scene scene = new Scene(alert.getDialogPane(),Color.TRANSPARENT);
//		stage.setScene(scene);
//		stage.showAndWait();
		
		
	    /*
	    
	 Alert alert = new Alert(Tipo);
	alert.setTitle(Titulo);
	alert.setHeaderText(Encabezado);
	alert.setContentText(Mensaje);
	alert.getDialogPane().setStyle("-fx-background-color: transparent;");
//alert.setGraphic(new ImageView(ImageCache.getInstance().get("/images/gallio/gallio-sad.png")));
final Stage stage = new Stage(StageStyle.TRANSPARENT); 

		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setMinWidth(250);

		Scene scene = new Scene(alert.getDialogPane().getParent(),Color.TRANSPARENT);
alert.getDialogPane().getParent().setStyle("-fx-background-color: transparent;");

// Get the Stage.
//Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

// Add a custom icon.
stage.setScene(scene);
//alert.setDialogPane(value);


//Scene scene = new Scene(pane,Color.TRANSPARENT);
//		
//		
//scene.getStylesheets().clear();
//scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
		
VBox pane = new VBox(20);
		pane.getChildren().addAll(alert.getDialogPane());
		pane.setAlignment(Pos.CENTER);
		
		
//File f = new File("src/magiei/Principal/Magie1_Estilo.css");
//pane.getStylesheets().clear();
//pane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

pane.getStylesheets().add(otro.toExternalForm());
pane.setId("myDialog");
		Scene scene2 = new Scene(pane,Color.TRANSPARENT);
stage.setScene(scene2);
		
		
		stage.showAndWait();

/*
DialogPane dialogPane = alert.getDialogPane();

		alert.initStyle(StageStyle.TRANSPARENT);
//String css = DynamicCSS.class.getResource("/jarcss.css").toExternalForm();
//dialogPane.getStylesheets().clear();
//dialogPane.getStylesheets().add(css);

//File f = new File("src/magiei/Principal/Magie1_Estilo.css");
//dialogPane.getStylesheets().clear();
//dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

dialogPane.getStylesheets().add(otro.toExternalForm());

//dialogPane.getStylesheets().add(otro.toExternalForm());
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









		stage.setScene(scene);
		
		
		stage.showAndWait();*/

//alert.setGraphic(c);
	//alert.showAndWait();
    }
	
	 public static void show2(String Titulo,String Encabezado,String Mensaje,Alert.AlertType Tipo)
    {
	 Alert alert = new Alert(Tipo);
	alert.setTitle(Titulo);
	alert.setHeaderText(Encabezado);
	alert.setContentText(Mensaje);
	
//alert.setGraphic(new ImageView(ImageCache.getInstance().get("/images/gallio/gallio-sad.png")));



//alert.setGraphic(new ImageView(imagen));




//alert.setDialogPane(value);

DialogPane dialogPane = alert.getDialogPane();

		alert.initStyle(StageStyle.TRANSPARENT);
//String css = DynamicCSS.class.getResource("/jarcss.css").toExternalForm();
//dialogPane.getStylesheets().clear();
//dialogPane.getStylesheets().add(css);

//File f = new File("src/magiei/Principal/Magie1_Estilo.css");
//dialogPane.getStylesheets().clear();
//dialogPane.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

//dialogPane.getStylesheets().add(otro.toExternalForm());

//dialogPane.getStylesheets().add(otro.toExternalForm());
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
	 
	 
	 
	 
//	 public MessageBox(URL otro)
//	 {
//		estiloPadre= otro;	 
//	 }
	 
	
	 
	 
}
