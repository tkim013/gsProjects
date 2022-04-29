package org.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Project.fxml"));
        Parent root = loader.load();
        MyController controller = loader.getController();
        scene = new Scene(root, 770, 770);
        stage.setScene(scene);
        stage.setTitle("Goblins don't exist.");
        stage.setResizable(false);
        stage.show();

        //required for clean exit. music player causes errors.
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });

        //key events for WASD and arrow key movement
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                case W:
                    controller.getButtonNorth().fire();
                    break;
                case DOWN:
                case S:
                    controller.getButtonSouth().fire();
                    break;
                case LEFT:
                case A:
                    controller.getButtonWest().fire();
                    break;
                case RIGHT:
                case D:
                    controller.getButtonEast().fire();
                    break;
                default:
                    break;
            }
        });
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}