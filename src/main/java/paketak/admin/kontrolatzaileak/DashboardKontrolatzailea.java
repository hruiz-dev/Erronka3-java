package paketak.admin.kontrolatzaileak;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.MysqlConector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DashboardKontrolatzailea {


    @FXML
    private VBox datuPanela;

    public void initialize(){

    }

    public void loadPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node node = loader.load();
            datuPanela.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPanelHasiera(){
        loadPanel("/paketak/admin/panelHasiera.fxml");
    }

    public void showPanelBanatzaileak(){
        loadPanel("/paketak/admin/panelBanatzaileak.fxml");
    }

    public void aldatuBtAukeratua(){

    }


}
