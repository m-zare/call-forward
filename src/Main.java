import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.da.ActionDA;
import model.entity.ConnectionEntity;
import model.service.AsteriskManager;

import java.io.IOException;

/**
 * Created by Mostafa on 10/28/2016.
 */
public class Main extends Application{
   /*public static void main(String[] args) {
       AsteriskManager asteriskManager = new AsteriskManager();
       asteriskManager.test();
   }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        AsteriskManager asteriskManager = new AsteriskManager();
        asteriskManager.test();

        /*primaryStage.setResizable(false);
        primaryStage.setScene(
                new Scene(
                FXMLLoader.load(getClass().getResource("/view/mainFrame.fxml")),219,423));

        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });*/
    }

    public void dbPut(){
        try {
            ActionDA actionDA = new ActionDA(new ConnectionEntity());
            actionDA.dbPut("string","value");
            System.out.println("Success...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dbGet(){
        try {
            ActionDA actionDA = new ActionDA(new ConnectionEntity());
            System.out.println(actionDA.dbGet("string"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dbDel(){
        try {
            ActionDA actionDA = new ActionDA(new ConnectionEntity());
            actionDA.dbDel("string");
            System.out.println("Success...");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
