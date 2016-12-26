package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Mostafa on 12/6/2016.
 */
public class mainFrame implements Initializable {
    @FXML
    private TextField txtCID;
    @FXML
    private ComboBox<String> comboExt;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView tblView;
    @FXML
    private Button btnDel;
    @FXML
    private TextArea txtHistory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("click");
            }
        });

        btnDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("del button");
            }
        });
    }
}
