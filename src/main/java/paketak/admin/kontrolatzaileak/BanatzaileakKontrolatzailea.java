package paketak.admin.kontrolatzaileak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.zerbitzuak.BanatzaileZerbitzua;
import paketak.admin.zerbitzuak.Filter;
import paketak.admin.zerbitzuak.Komponenteak;
import paketak.admin.zerbitzuak.MysqlConector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BanatzaileakKontrolatzailea {
    private static MysqlConector mysql = MysqlConector.getInstance();

    @FXML
    private TableView<Banatzailea> banatzaileakTaula;

    @FXML
    private TableColumn<Banatzailea, Integer> idColumn;
    @FXML
    private TableColumn<Banatzailea, String> izena;
    @FXML
    private TableColumn<Banatzailea, String> abizena;
    @FXML
    private TableColumn<Banatzailea, String> erabiltzailea;
    @FXML
    private TableColumn<Banatzailea, String> pasahitza;
    @FXML
    private TableColumn<Banatzailea, Integer> entregak;
    @FXML
    private TableColumn<Banatzailea, Integer> beranduEntregak;

    @FXML
    private TextField idTextArea;
    @FXML
    private TextField izenaTextArea;
    @FXML
    private TextField abizenaTextArea;
    @FXML
    private TextField erabiltzaileaTextArea;
    @FXML
    private PasswordField pasahitzaTextArea;
    @FXML
    private TextField entregakTextArea;
    @FXML
    private TextField beranduEntregakTextArea;

    @FXML
    private Pane alertPanel;
    @FXML
    private Label alertLabel;

    @FXML
    private TextField bilatzaileaTextField;
    @FXML
    private ComboBox<String> filterCombox;


    public void initialize() {
        // Tablako zutabe bakoitza Paketea objetuko atributu bati esleitu.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        abizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        erabiltzailea.setCellValueFactory(new PropertyValueFactory<>("erabiltzailea"));
        pasahitza.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        entregak.setCellValueFactory(new PropertyValueFactory<>("entregak"));
        beranduEntregak.setCellValueFactory(new PropertyValueFactory<>("beranduEntregak"));

        // Pasahitza tablan ez ikusteko kolugnan erakustendena modifikatu
        pasahitza.setCellFactory(column -> {
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

        tablaSortu();
    }

    /**
     *Metodo honek datubasetik ateratako Banatzaileak Tablan bistarentzen ditu
     */
    public void tablaSortu () {
        BanatzaileZerbitzua.updateBanatzaileakDB();
        List<Banatzailea> banatzaileak = Banatzailea.getBanatzaileak();

        ObservableList<Banatzailea> data = FXCollections.observableArrayList(banatzaileak);
        banatzaileakTaula.setItems(data);
    }

    /**
     * Metodo honek taulako lerro batean kilk egitean lerro arretako datuak textAreaetan bistaratzen ditu
     */
    public void tablanAukeratu() {
        Banatzailea banatzailea = banatzaileakTaula.getSelectionModel().getSelectedItem();

        idTextArea.setText(String.valueOf(banatzailea.getId()));
        izenaTextArea.setText(banatzailea.getIzena());
        abizenaTextArea.setText(banatzailea.getAbizena());
        erabiltzaileaTextArea.setText(banatzailea.getErabiltzailea());
        pasahitzaTextArea.setText(banatzailea.getPasahitza());
        entregakTextArea.setText(String.valueOf(banatzailea.getEntregak()));
        beranduEntregakTextArea.setText(String.valueOf(banatzailea.getBeranduEntregak()));

    }

    // TODO: Datubasean trigger bat sortu Banatzailea ezabatzean honek esleituta dituen pakete guztien banatzailea id null jartzea, pakete historian ere bai
    public void ezabatuBanatzailea() {
        Alert alert = Komponenteak.sortuAlerta("Ezabatu Banatzailea",
                "Ziur zaude banatzailea ezabatu nahi duzula?",
                "Ezabatzean, banatzailearen datu guztiak ezabatuko dira.");

        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Banatzailea banatzailea = banatzaileakTaula.getSelectionModel().getSelectedItem();
            mysql.createUpdate("DELETE FROM `Banatzailea` WHERE `id` = " + banatzailea.getId());

            tablaSortu();
            garbituTextAreak();

            erakutsiAlertPanelSucessfull(Integer.toString(banatzailea.getId()) + " zenbakidun banatzailea ezabatu da.");
            return;
        }
        erakutsiAlertPanelErrorea("Banatzailea ezabatzea bertan behera geratu da.");
    }

    /**
     * Metodo honek banatzailearen datu beriak artzen ditu textAretik eta hauek update baten bidez aktualizatu
     */
    public void gordeBanatzailea() {
        if (!komprobatuTextAreak()) {
            return;
        }
        Banatzailea banatzailea = new Banatzailea(
                Integer.parseInt(idTextArea.getText()),
                izenaTextArea.getText(),
                abizenaTextArea.getText(),
                erabiltzaileaTextArea.getText(),
                pasahitzaTextArea.getText(),
                Integer.parseInt(entregakTextArea.getText()),
                Integer.parseInt(beranduEntregakTextArea.getText())
        );

        Boolean a = BanatzaileZerbitzua.updateBanatzaileaDB(banatzailea);

        if (a) {
            erakutsiAlertPanelSucessfull( idTextArea.getText() + " zenbakidun banatzailea datuak eguneratu dira.");
        }

    }

    /**
     * metodo honek textArea guztiak garbitzen ditu
     */
    public void garbituTextAreak() {
        idTextArea.setText("");
        izenaTextArea.setText("");
        abizenaTextArea.setText("");
        erabiltzaileaTextArea.setText("");
        pasahitzaTextArea.setText("");
        entregakTextArea.setText("");
        beranduEntregakTextArea.setText("");
    }

    /**
     * Metodo honek datu basean banatzaile berri bat sortzene du, eta honen id zein den esaten dio kudeatzaileari
     */
    public void sortubanatzailea() {
        if (!komprobatuTextAreak()) {
            return;
        }
        Alert alert = Komponenteak.sortuAlerta("Sortu Banatzailea", "Zihur zaude banatzailea sortu nahi duzula?",
                "Sortzean, banatzailearen datu guztiak datu basean sartuko dira.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.CANCEL) {
            erakutsiAlertPanelErrorea("Banatzailea sortzea bertan behera geratu da.");
            return;
        }
        Banatzailea banatzailea = new Banatzailea(
                Integer.parseInt(idTextArea.getText()),
                izenaTextArea.getText(),
                abizenaTextArea.getText(),
                erabiltzaileaTextArea.getText(),
                pasahitzaTextArea.getText(),
                Integer.parseInt(entregakTextArea.getText()),
                Integer.parseInt(beranduEntregakTextArea.getText())
        );
        // Banatzailea datu basean gorde
        String id = BanatzaileZerbitzua.insertBanatzaileaDB(banatzailea);

        // Kudeatzaileari Id erakutsi
        erakutsiAlertPanelSucessfull(id + " zenbakidun banatzailea sortu da.");
    }

    public void itxiAlertPanel() {
        alertPanel.setVisible(false);
    }

    /**
     * Metodo honek alerta panela fondo gorri batekin erakusten du
     * @param mezua Erakusten den mezua
     */
    public void erakutsiAlertPanelErrorea(String mezua) {
        alertLabel.setText(mezua);
        alertPanel.setStyle("-fx-background-color: #d64b4b");
        alertPanel.setVisible(true);
    }

    /**
     * Metodo honek alerta panela fondo berde batekin erakusten du
     * @param mezua Erakusten den mezua
     */
    public void erakutsiAlertPanelSucessfull(String mezua) {
        alertLabel.setText(mezua);
        alertPanel.setStyle("-fx-background-color: #b5dba7");
        alertPanel.setVisible(true);
    }

    /**
     * Metodo honek textArea guztiak bete diren konprobatzen du
     * @return True bete badira, false bestela
     */
    public boolean komprobatuTextAreak() {
        if (idTextArea.getText().isEmpty() || izenaTextArea.getText().isEmpty() || abizenaTextArea.getText().isEmpty() || erabiltzaileaTextArea.getText().isEmpty() || pasahitzaTextArea.getText().isEmpty() || entregakTextArea.getText().isEmpty() || beranduEntregakTextArea.getText().isEmpty()) {
            erakutsiAlertPanelErrorea("Datu guztiak bete behar dira.");
            return false;
        }
        return true;
    }

    /**
     * Metodo honek tablan ikusten diren banatzaileak aldatzen ditu comboxa eta bilatzaileko textuaren arabera
     */
public void bilatuBanatzaileak() {
    String bilatzailea = bilatzaileaTextField.getText();
    String filter = filterCombox.getValue();
    ArrayList<Banatzailea> emaitza;

    if (filter.equals("")){
        filter = "Id";
    }

    emaitza = Filter.filtratu(Banatzailea.getBanatzaileak(), filter, bilatzailea);

    ObservableList<Banatzailea> data = FXCollections.observableArrayList(emaitza);
    banatzaileakTaula.setItems(data);
}

    /**
     * Metodo honek banatzailen datuak refreskatzen ditu datu basetik
     */
    public void updateBanatzailea() {
        BanatzaileZerbitzua.updateBanatzaileakDB();
        tablaSortu();
    }


}
