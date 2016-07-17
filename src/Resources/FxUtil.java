/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

//import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

//import java.util.List;
//import javafx.beans.value.ObservableValue;
//import javafx.scene.Node;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextArea;
//import javafx.scene.control.TextField;
//import javafx.scene.control.Tooltip;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;

/**
 *
 * @author Shadowsboy
 */
//public class GlobalClass1 {
//	
// 
//	
//    public final void  mensajeB(String Titulo,String Mensaje,Integer Tipo)
//    {
//	   // String backupDir = "/Users/al/backups";
//     
//	// create a jframe
//	JFrame frame = new JFrame("JOptionPane showMessageDialog example");
//
//	// show a joptionpane dialog using showMessageDialog
//	JOptionPane.showMessageDialog(frame,
//        Titulo + ": '" + Mensaje + "'.",
//        Titulo,
//        Tipo);//JOptionPane.INFORMATION_MESSAGE);
//    
//	frame.dispose();
//    }
//	
////	
//// private static final String STILE_BORDER_VALIDATION = "-fx-border-color: #FF0000";
//// private static final Tooltip toolTip = new Tooltip("This field is requested.");
//// 
//// public static boolean checkEmptyFields(Node... itemToCheck) {
//// //used to determinate how many fields failed in validation
//// List<Node> failedFields = new ArrayList<>();
//// toolTip.setStyle("-fx-background-color: linear-gradient(#FF6B6B , #FFA6A6 );"
//// + " -fx-font-weight: bold;");
////// hackTooltipStartTiming(toolTip);***********************************************************************
//// for (Node n : arrayToList(itemToCheck)) {
//// // Validate TextFields
//// if (n instanceof TextField) {
//// TextField textField = (TextField) n;
//// textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//// removeToolTipAndBorderColor(textField, toolTip);
//// });
//// if (textField.getText() == null || textField.getText().trim().equals("")) {
//// failedFields.add(n);
//// addToolTipAndBorderColor(textField, toolTip);
//// } else {
//// removeToolTipAndBorderColor(textField, toolTip);
//// }
//// } // Validate Combo Box
//// else if (n instanceof ComboBox) {
//// ComboBox comboBox = (ComboBox) n;
//// comboBox.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
//// removeToolTipAndBorderColor(comboBox, toolTip);
//// });
//// if (comboBox.getValue() == null) {
//// failedFields.add(n);
//// addToolTipAndBorderColor(comboBox, toolTip);
//// } else {
//// removeToolTipAndBorderColor(comboBox, toolTip);
//// }
//// } // Validate TextArea
//// else if (n instanceof TextArea) {
//// TextArea textArea = (TextArea) n;
//// textArea.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//// removeToolTipAndBorderColor(textArea, toolTip);
//// });
//// if (textArea.getText() == null || textArea.getText().trim().equals("")) {
//// failedFields.add(n);
//// addToolTipAndBorderColor(textArea, toolTip);
//// } else {
//// removeToolTipAndBorderColor(textArea, toolTip);
//// }
//// }
//// //ADD YOUR VALIDATION HERE
//// //TODO
//// }
//// 
//// return failedFields.isEmpty();
//// }
//// 
//// /**
//// * *******ADD AND REMOVE STYLES********
//// */
//// private static void addToolTipAndBorderColor(Node n, Tooltip t) {
//// Tooltip.install(n, t);
//// n.setStyle(STILE_BORDER_VALIDATION);
//// }
//// 
//// private static void removeToolTipAndBorderColor(Node n, Tooltip t) {
//// Tooltip.uninstall(n, t);
//// n.setStyle(null);
//// }
//// 
//// /**
//// * ***********ARRAY TO LIST UTILITY************
//// */
//// private static List<Node> arrayToList(Node[] n) {
//// List<Node> listItems = new ArrayList<>();
//// for (Node n1 : n) {
//// listItems.add(n1);
//// }
//// return listItems;
//// }
// 
// /**
// * ***********FORCE TOOL TIP TO BE DISPLAYED FASTER************
// */
//// private static void hackTooltipStartTiming(Tooltip tooltip) {
//// try {
//// Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
//// fieldBehavior.setAccessible(true);
//// Object objBehavior = fieldBehavior.get(tooltip);
//// 
//// Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
//// fieldTimer.setAccessible(true);
//// Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);
//// 
//// objTimer.getKeyFrames().clear();
//// objTimer.getKeyFrames().add(new KeyFrame(new Duration(5)));
//// } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
//// System.out.println(e);
//// }
//// }
// 
//}
public class FxUtil {

    public enum AutoCompleteMode {
        STARTS_WITH,CONTAINING,;
    }

    public static<T> void autoCompleteComboBox(ComboBox<T> comboBox, AutoCompleteMode mode) {
        ObservableList<T> data = comboBox.getItems();

        comboBox.setEditable(true);
        comboBox.getEditor().focusedProperty().addListener(observable -> {
            if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
                comboBox.getEditor().setText(null);
            }
        });
        comboBox.addEventHandler(KeyEvent.KEY_PRESSED, t -> comboBox.hide());
        comboBox.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

            private boolean moveCaretToPos = false;
            private int caretPos;

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.DOWN) {
                    if (!comboBox.isShowing()) {
                        comboBox.show();
                    }
                    caretPos = -1;
                    moveCaret(comboBox.getEditor().getText().length());
                    return;
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                } else if (event.getCode() == KeyCode.DELETE) {
                    moveCaretToPos = true;
                    caretPos = comboBox.getEditor().getCaretPosition();
                }

                if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT || event.getCode().equals(KeyCode.SHIFT) || event.getCode().equals(KeyCode.CONTROL)
                        || event.isControlDown() || event.getCode() == KeyCode.HOME
                        || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                    return;
                }

                ObservableList<T> list = FXCollections.observableArrayList();
                for (T aData : data) {
                    if (mode.equals(AutoCompleteMode.STARTS_WITH) && aData.toString().toLowerCase().startsWith(comboBox.getEditor().getText().toLowerCase())) {
                        list.add(aData);
                    } else if (mode.equals(AutoCompleteMode.CONTAINING) && aData.toString().toLowerCase().contains(comboBox.getEditor().getText().toLowerCase())) {
                        list.add(aData);
                    }
                }
                String t = comboBox.getEditor().getText();

                comboBox.setItems(list);
                comboBox.getEditor().setText(t);
                if (!moveCaretToPos) {
                    caretPos = -1;
                }
                moveCaret(t.length());
                if (!list.isEmpty()) {
                    comboBox.show();
                }
            }

            private void moveCaret(int textLength) {
                if (caretPos == -1) {
                    comboBox.getEditor().positionCaret(textLength);
                } else {
                    comboBox.getEditor().positionCaret(caretPos);
                }
                moveCaretToPos = false;
            }
        });
    }

    public static<T> T getComboBoxValue(ComboBox<T> comboBox){
        if (comboBox.getSelectionModel().getSelectedIndex() < 0) {
            return null;
        } else {
            return comboBox.getItems().get(comboBox.getSelectionModel().getSelectedIndex());
        }
    }
    
    
    
}
