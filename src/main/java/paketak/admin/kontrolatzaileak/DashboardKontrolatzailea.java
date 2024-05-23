package paketak.admin.kontrolatzaileak;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.MysqlConector;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DashboardKontrolatzailea {


    @FXML
    private VBox datuPanela;

    @FXML
    private Button btHasiera;

    @FXML
    private Button btBanatzaileak;

    @FXML
    private Button btPaketeak;

    public void initialize(){

    }

    /**
     * Metodo honek gure panel prizipalean ze panel kargatzen den kontrolatzen du
     * @param fxmlFile kargatu nahi den panelaren fitxategiaren izena
     */
    public void loadPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node node = loader.load();
            datuPanela.getChildren().setAll(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo honek Hasiera panela jartzen du panel aktibo gixa
     */
    public void showPanelHasiera(){
        loadPanel("/paketak/admin/panelHasiera.fxml");
        setActiveNull();
        btHasiera.getStyleClass().add("active");
    }

    /**
     * Metodo honek Banatzaileak panela jartzen du panel aktibo gixa
     */
    public void showPanelBanatzaileak(){
        loadPanel("/paketak/admin/panelBanatzaileak.fxml");
        setActiveNull();
        btBanatzaileak.getStyleClass().add("active");
    }

    /**
     * Metodo honek Paketeak panela jartzen du panel aktibo gixa
     */
    public void showPanelPaketeak(){
        loadPanel("/paketak/admin/panelPaketeak.fxml");
        setActiveNull();
        btPaketeak.getStyleClass().add("active");

    }

    /**
     * Metodo honek botoi guztiei active klasea kentzen die
     */
    public void setActiveNull(){
        btHasiera.getStyleClass().remove("active");
        btBanatzaileak.getStyleClass().remove("active");
        btPaketeak.getStyleClass().remove("active");
    }


}
