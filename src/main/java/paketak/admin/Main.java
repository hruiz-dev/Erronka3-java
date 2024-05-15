package paketak.admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage newstage) throws IOException {
        stage = newstage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("pakAG Kudeatzailea - Administrazioa");
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}