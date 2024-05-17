package paketak.admin.kontrolatzaileak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.TablaModelCreator;

import java.util.List;

public class PanelHasieraKontrolatzailea {

    @FXML
    private TableView<Paketea> paketeTabla;

    @FXML
    private TableColumn<Paketea, Integer> idColumn;
    @FXML
    private TableColumn<Paketea, String> entregaEginBeharDataColumn;
    @FXML
    private TableColumn<Paketea, String> hartzaileaColumn;
    @FXML
    private TableColumn<Paketea, String> dimentsioakColumn;
    @FXML
    private TableColumn<Paketea, Boolean> entregatutaColumn;
    @FXML
    private TableColumn<Paketea, String> helburuaColumn;
    @FXML
    private TableColumn<Paketea, String> jatorriaColumn;

    public void initialize() {
        // Tablako zutabe bakoitza Paketea objetuko atributu bati esleitu.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        entregaEginBeharDataColumn.setCellValueFactory(new PropertyValueFactory<>("entregaEginBeharData"));
        hartzaileaColumn.setCellValueFactory(new PropertyValueFactory<>("hartzailea"));
        dimentsioakColumn.setCellValueFactory(new PropertyValueFactory<>("dimentsioak"));
        entregatutaColumn.setCellValueFactory(new PropertyValueFactory<>("entregatuta"));
        helburuaColumn.setCellValueFactory(new PropertyValueFactory<>("helburua"));
        jatorriaColumn.setCellValueFactory(new PropertyValueFactory<>("jatorria"));

        tablaSortu();
    }

    /**
     * Datubaseti ateratako Paketak Tablabistaratu
     */
    public void tablaSortu () {
        Paketea.setPaketeak(DashboardKontrolatzailea.getPaketeak());
        List<Paketea> paketeak = Paketea.getPaketeak();
        ObservableList<Paketea> data = FXCollections.observableArrayList(paketeak);
        paketeTabla.setItems(data);
    }
}