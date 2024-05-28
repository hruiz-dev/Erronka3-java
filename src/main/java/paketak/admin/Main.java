package paketak.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    private static String firstFxml = "login.fxml";
    @Override
    public void start(Stage newstage) throws IOException {
        stage = newstage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(firstFxml));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("pakAG Kudeatzailea - Administrazioa");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("irudiak/icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metodo honek gure Aplikazioaren intefazeko eszena aldatzen du
     * @param fxml FXML fitxategiaren izena
     */
    public void changeScene(String fxml){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stage.getScene().setRoot(root);
            stage.sizeToScene();
            stage.setMaximized(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        launch();
    }
}