package paketak.admin.kontrolatzaileak;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.modeloak.PaketeaHistoriala;

import java.io.IOException;


public class PaketeaDetailKontrolatzailea {
    @FXML
    private Label labelTitulua;
    @FXML
    Label labelHartzailea;
    @FXML
    Label labelJatorria;
    @FXML
    Label labelHelburua;
    @FXML
    Label labelHauskorra;
    @FXML
    Label labelEntregatzeData;
    @FXML
    Label labelEntregatuBeharrekoData;
    @FXML
    Label labelBanatzailea;

    public PaketeaDetailKontrolatzailea(PaketeaHistoriala paketeaHistoriala) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/paketak/admin/paketea.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOpacity(1);
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 450, 450));
        stage.showAndWait();
        labelTitulua.setText(paketeaHistoriala.getId() + " - " + paketeaHistoriala.getHelburua());
        labelHartzailea.setText(paketeaHistoriala.getHartzailea());

    }

}
