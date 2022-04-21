package org.project;

import javafx.application.Application;
import javafx.event.Event;
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
        Scene scene = new Scene(root, 770, 770);
        stage.setScene(scene);
        stage.setTitle("Goblins don't exist.");
        stage.show();

        //key events for WASD and arrow key movement
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                case W:
                    controller.northButtonAction(null);
                    break;
                case DOWN:
                case S:
                    controller.southButtonAction(null);
                    break;
                case LEFT:
                case A:
                    controller.westButtonAction(null);
                    break;
                case RIGHT:
                case D:
                    controller.eastButtonAction(null);
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