/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home_menus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author ikhlas
 */
public class home_menus extends Application {

    TextArea ar;

    @Override
    public void start(Stage primaryStage) {

        Evnt event = new Evnt();
        MenuBar bar = new MenuBar();

        Menu f = new Menu("File");
        MenuItem open = new MenuItem("open");
        MenuItem close = new MenuItem("close");
        MenuItem Exit = new MenuItem("Exite");
        f.getItems().setAll(open, close, Exit);
        Menu ed = new Menu("Edit");
        MenuItem Font = new MenuItem("Font");
        MenuItem color = new MenuItem("Color");
        ed.getItems().addAll(Font, color);
        bar.getMenus().addAll(f);
        bar.getMenus().add(ed);
        open.setOnAction(event);
        close.setOnAction(event);
        Exit.setOnAction(event);
        Font.setOnAction(event);
        color.setOnAction(event);
        ar = new TextArea();
        VBox box = new VBox(bar, ar);

        Scene scene = new Scene(box, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private class Evnt implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String menu = ((MenuItem) event.getSource()).getText();
            switch (menu) {
                case "open":
                    FileChooser fc = new FileChooser();
                    fc.setTitle("open File");
                    fc.setInitialDirectory(new File("."));
                    File select = fc.showOpenDialog(null);
                     {
                        Scanner s;
                        ar.setText("");
                        ar.setWrapText(true);
                        try {
                            s = new Scanner(select);
                            while (s.hasNextLine()) {
                                ar.appendText(s.nextLine());

                            }
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(home_menus.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;

                case "close":
                    ar.clear();
                    break;
                case "Exite":
                    System.exit(1);
                    break;
                case "Font":

                    TextInputDialog td = new TextInputDialog();
                    td.setHeaderText("Enter the appropriate font size");
                    td.showAndWait();
                    ar.setStyle(ar.getStyle() + "-fx-font-size:" + td.getEditor().getText() + "px ; ");

                    break;
                case "Color":
                    Alert e = new Alert(AlertType.CONFIRMATION);
                    e.setTitle("fff");
                    e.setTitle("Select Color");
                    e.setHeaderText("Please enter your favorite color ");
                    ColorPicker p = new ColorPicker(Color.BROWN);
                    e.setGraphic(p);
                    e.showAndWait();

                    String r = p.getValue().toString();
                    String b = r.substring(2);
                    System.out.println(b);

                    ar.setStyle(ar.getStyle() + "-fx-text-fill: #" + b + ";");
                    break;

            }
        }

    }

}
