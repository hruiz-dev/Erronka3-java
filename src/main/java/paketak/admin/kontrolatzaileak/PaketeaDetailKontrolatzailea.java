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

    /**
     * Metodo hau exekutatzen denean, pantailan agertuko diren datuak kargatzen dira.
     */
    public void initialize() {
        PaketeaHistoriala paketea = HistoriaKontrolatzailea.paketeAukeratua;
        labelTitulua.setText(paketea.getId() + " Paketearen informazioa");
        labelHartzailea.setText(paketea.getHartzailea());
        labelJatorria.setText(paketea.getJatorria());
        labelHelburua.setText(paketea.getHelburua());
        labelHauskorra.setText(paketea.isHauskorra() ? "Hauskorra" : "Ez da hauskorra");
        labelEntregatzeData.setText(paketea.getEntregatzeData().toString());
        labelEntregatuBeharrekoData.setText(paketea.getEntregaEginBeharData().toString());
        labelBanatzailea.setText(Integer.toString(paketea.getBanatzaileaId()));

    }

}
