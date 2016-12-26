import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.service.AsteriskManager;

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

        primaryStage.setResizable(false);
        primaryStage.setScene(
                new Scene(
                FXMLLoader.load(getClass().getResource("/view/mainFrame.fxml")),219,423));

        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }
}
