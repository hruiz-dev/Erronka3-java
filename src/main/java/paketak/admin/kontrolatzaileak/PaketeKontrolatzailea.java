package paketak.admin.kontrolatzaileak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import paketak.admin.modeloak.Paketea;

public class PaketeKontrolatzailea {

    @FXML
    private TableView banatzaileakTaula;

    @FXML
    private TableColumn<Paketea, Integer> idColumn;
    @FXML
    private TableColumn<Paketea, String> entregaData;
    @FXML
    private TableColumn<Paketea, String> hartzailea;
    @FXML
    private TableColumn<Paketea, String> dimentsioak;
    @FXML
    private TableColumn<Paketea, String> hauskorra;
    @FXML
    private TableColumn<Paketea, String> helburua;
    @FXML
    private TableColumn<Paketea, String> jatorria;
    @FXML
    private TableColumn<Paketea, Boolean> entregatzen;
    @FXML
    private TableColumn<Paketea, Integer> banatzaileaId;

    @FXML
    private TextField idTextArea;
    @FXML
    private TextField entregaDataTextField;
    @FXML
    private TextField hartzaileaTextField;
    @FXML
    private TextField dimentsioakTextField;
    @FXML
    private ComboBox<String> hauskorraComboBox;
    @FXML
    private TextField helburuaTextField;
    @FXML
    private TextField jatorriaTextField;
    @FXML
    private ComboBox<String> entregatzenComboBox;
    @FXML
    private TextField banatzaileaIdTextField;

    @FXML
    private TextField bilatzaileaTextField;
    @FXML
    private ComboBox<String> filterCombox;

    @FXML
    private Pane alertPanel;
    @FXML
    private Label alertLabel;

    public void initialize() {
        // Tablako zutabe bakoitza Paketea objetuko atributu bati esleitu.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        entregaData.setCellValueFactory(new PropertyValueFactory<>("entregaData"));
        hartzailea.setCellValueFactory(new PropertyValueFactory<>("hartzailea"));
        dimentsioak.setCellValueFactory(new PropertyValueFactory<>("dimentsioak"));
        hauskorra.setCellValueFactory(new PropertyValueFactory<>("hauskorra"));
        helburua.setCellValueFactory(new PropertyValueFactory<>("helburua"));
        jatorria.setCellValueFactory(new PropertyValueFactory<>("jatorria"));
        entregatzen.setCellValueFactory(new PropertyValueFactory<>("entregatzen"));
        banatzaileaId.setCellValueFactory(new PropertyValueFactory<>("banatzaileaId"));

        tablaSortu();

//        aktualizatuPaketeDatuak();
    }

    public void tablaSortu() {

        ObservableList<Paketea> data = FXCollections.observableArrayList(Paketea.getPaketeak());
        banatzaileakTaula.setItems(data);

    }

    public void ezabatuPaketea() {

    }

    public void gordePaketea() {

    }

    public void garbituTextAreak() {

    }

    public void sortuPaketea() {

    }

    public void bilatuPaketea() {

    }

    public void updatePaketea() {

    }

    public void tablanAukeratu() {

    }

    public void itxiAlertPanel() {

    }


}
