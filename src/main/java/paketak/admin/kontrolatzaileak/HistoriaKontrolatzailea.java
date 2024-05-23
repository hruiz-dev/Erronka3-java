package paketak.admin.kontrolatzaileak;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.BanatzaileZerbitzua;

import java.util.List;

public class HistoriaKontrolatzailea {

    @FXML
    public TableView<Paketea> paketeakTabla;
    @FXML
    public TextField bilatzaileaPaketea;
    @FXML
    public ComboBox filterComboxPaketea;
    @FXML
    public TableColumn idPaketea;
    @FXML
    public TableColumn<Paketea, String> entregaDataPaketea;
    @FXML
    public TableColumn hartzaileaPaketea;
    @FXML
    public TableColumn dimesioakPaketea;
    @FXML
    public TableColumn hauskorraPaketea;
    @FXML
    public TableColumn jatorriaPaketea;
    @FXML
    public TableColumn helburuaPaketea;
    @FXML
    public TableColumn entregatzenPaketea;
    @FXML
    public TableColumn banatzaileaIdPaketea;
    @FXML
    public TableView<Banatzailea> bantzaileakTabla;
    @FXML
    public TableColumn idBanatzailea;
    @FXML
    public TableColumn izenaBanatzailea;
    @FXML
    public TableColumn abizenaBanatzailea;
    @FXML
    public TableColumn erabiltzaileaBanatzailea;
    @FXML
    public TableColumn beranduEntregakBanatzailea;
    @FXML
    public TableColumn entregakBanatzailea;
    @FXML
    public TableColumn<Banatzailea, String> pasahitzaBanatzailea;

    public void initialize() {
        // paketeakTabla dagokien propietateak esleitu
        idPaketea.setCellValueFactory(new PropertyValueFactory<>("id"));
        hartzaileaPaketea.setCellValueFactory(new PropertyValueFactory<>("hartzailea"));
        dimesioakPaketea.setCellValueFactory(new PropertyValueFactory<>("dimesioak"));
        hauskorraPaketea.setCellValueFactory(new PropertyValueFactory<>("hauskorra"));
        jatorriaPaketea.setCellValueFactory(new PropertyValueFactory<>("jatorria"));
        helburuaPaketea.setCellValueFactory(new PropertyValueFactory<>("helburua"));
        entregatzenPaketea.setCellValueFactory(new PropertyValueFactory<>("entregatzen"));
        banatzaileaIdPaketea.setCellValueFactory(new PropertyValueFactory<>("banatzaileaId"));

        // banatzaileakTabla dagokien propietateak esleitu
        idBanatzailea.setCellValueFactory(new PropertyValueFactory<>("id"));
        izenaBanatzailea.setCellValueFactory(new PropertyValueFactory<>("izena"));
        abizenaBanatzailea.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        erabiltzaileaBanatzailea.setCellValueFactory(new PropertyValueFactory<>("erabiltzailea"));
        beranduEntregakBanatzailea.setCellValueFactory(new PropertyValueFactory<>("beranduEntregak"));
        entregakBanatzailea.setCellValueFactory(new PropertyValueFactory<>("entregak"));

        // pasahitzaBanatzailea osagaia eguneratzeko
        pasahitzaBanatzailea.setCellFactory(column -> {
            return new TableCell<Banatzailea, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.replaceAll(".", "*"));
                    }
                }
            };
        });

        // paketeakTabla-ko entregaDataPaketea osagaia eguneratzeko
        entregaDataPaketea.setCellValueFactory(cellData -> {
            Paketea paketea = cellData.getValue();
            if (paketea != null) {
                return new SimpleStringProperty(paketea.getFormatedData());
            } else {
                return new SimpleStringProperty(null);
            }
        });
    }

    public void bilatuPaketea() {
    }

    public void updatePaketea() {
    }

    public void tablanAukeratu() {
    }

    /**
     * Metodo honek Banatzaileak tablako datuak aktualizatzen ditu
     */
    public void banatzaileTablaSortu () {
        BanatzaileZerbitzua.updateBanatzaileakDB();
        List<Banatzailea> banatzaileak = Banatzailea.getBanatzaileak();

        ObservableList<Banatzailea> data = FXCollections.observableArrayList(banatzaileak);
        bantzaileakTabla.setItems(data);
    }
}
