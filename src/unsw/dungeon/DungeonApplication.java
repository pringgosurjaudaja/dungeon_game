package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * Application class of the game
 *
 */
public class DungeonApplication extends Application {

	Stage main;
    @Override
    public void start(Stage primaryStage) throws IOException {
        main = primaryStage;
    	main.setTitle("Dungeon");

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testingdungeon.json");
        DungeonController controller = dungeonLoader.loadController();

        //FXMLLoader loader = new FXMLLoader(getClass().getResource("TextAreaExample.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        main.setScene(scene);
        controller.setMainStage(main);
        main.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
