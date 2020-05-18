package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     * Permet de placer par défault la page de Menu comme page de démmarage
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Menu"));
        stage.setScene(scene);
        stage.show();

        stage.getIcons().add(new Image(App.class.getResourceAsStream("/org/img/ratoIcon.png")));
        stage.setTitle("LA RATISSERIE");
    }

    /**
     * Fait le lien vers toutes nos classes fxml créees
     * @param fxml
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Permet de charger les classes parentes aux fichiers fxml
     * @param fxml
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Permet de démarrer l'application quand elle est appelée
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}