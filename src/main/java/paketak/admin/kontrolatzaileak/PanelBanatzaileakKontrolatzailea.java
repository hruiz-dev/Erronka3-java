package paketak.admin.kontrolatzaileak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.zerbitzuak.BanatzaileZerbitzua;
import paketak.admin.zerbitzuak.MysqlConector;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PanelBanatzaileakKontrolatzailea {
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
        BanatzaileZerbitzua.updateBanatzaileakDatubasetik();
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
        Alert alert = sortuAlerta("Ezabatu Banatzailea",
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
        String sql = "UPDATE `Banatzailea` SET `id` = ?," +
                " `izena` = ?," +
                " `abizena` = ?," +
                " `pasahitza` = ?," +
                " `erabiltzailea` = ?," +
                " `entregak` = ?," +
                " `berandu_entregatuta` = ?" +
                " WHERE `Banatzailea`.`id` = " + idTextArea.getText();

        Map<Integer, String> datuak = Map.of(
                1, idTextArea.getText(),
                2, izenaTextArea.getText(),
                3, abizenaTextArea.getText(),
                4, pasahitzaTextArea.getText(),
                5, erabiltzaileaTextArea.getText(),
                6, entregakTextArea.getText(),
                7, beranduEntregakTextArea.getText()
        );

        mysql.createUpdate(sql, datuak);

        tablaSortu();

        erakutsiAlertPanelSucessfull( idTextArea.getText() + " zenbakidun banatzailea datuak eguneratu dira.");
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
        Alert alert = sortuAlerta("Sortu Banatzailea", "Ziur zaude banatzailea sortu nahi duzula?",
                "Sortzean, banatzailearen datu guztiak datu basean sartuko dira.");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.CANCEL) {
            erakutsiAlertPanelErrorea("Banatzailea sortzea bertan behera geratu da.");
            return;
        }

        //Mysql kontsulta sortu
        String sql = "INSERT INTO `Banatzailea` " +
                "(`id`, `izena`, `abizena`, `pasahitza`, `erabiltzailea`, `entregak`, `berandu_entregatuta`) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?)";

        Map<Integer, String> datuak = Map.of(
                1, izenaTextArea.getText(),
                2, abizenaTextArea.getText(),
                3, pasahitzaTextArea.getText(),
                4, erabiltzaileaTextArea.getText(),
                5, entregakTextArea.getText(),
                6, beranduEntregakTextArea.getText()
        );

        mysql.createUpdate(sql, datuak);

        // Mysql hemandoko id lortu
        ResultSet lastId = mysql.createQuery("SELECT LAST_INSERT_ID()");
        String id = "";

        try {
            if (lastId.next()) { // Move cursor to the first row
                id = lastId.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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
        alertPanel.setStyle("-fx-background-color: #ff0000");
        alertPanel.setVisible(true);
    }

    /**
     * Metodo honek alerta panela fondo berde batekin erakusten du
     * @param mezua Erakusten den mezua
     */
    public void erakutsiAlertPanelSucessfull(String mezua) {
        alertLabel.setText(mezua);
        alertPanel.setStyle("-fx-background-color: #00ff00");
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
     * Funtzio honek arlet motako panel bat sortzen du
     * @param title alertaren titulua
     * @param head  alertaren goiburua
     * @param content alertaren edukia
     * @return sortutako alerta
     */
    public Alert sortuAlerta(String title,String head, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(content);
        return alert;

    }

    /**
     * Metodo honek tablan ikusten diren banatzaileak aldatzen ditu comboxa eta bilatzaileko textuaren arabera
     */
    public void bilatuBanatzaileak() {
        String bilatzailea = bilatzaileaTextField.getText();
        String filter = filterCombox.getValue();
        List<Banatzailea> banatzaileak = Banatzailea.getBanatzaileak();
        List<Banatzailea> emaitza = new ArrayList<>();

        switch (filter) {
            case "ID":
                for (Banatzailea banatzailea : banatzaileak) {
                    if (Integer.toString(banatzailea.getId()).contains(bilatzailea)) {
                        emaitza.add(banatzailea);
                    }
                }
                break;
            case "Izena":
                for (Banatzailea banatzailea : banatzaileak) {
                    if (banatzailea.getIzena().contains(bilatzailea)) {
                        emaitza.add(banatzailea);
                    }
                }
                break;
            case "Abizena":
                for (Banatzailea banatzailea : banatzaileak) {
                    if (banatzailea.getAbizena().contains(bilatzailea)) {
                        emaitza.add(banatzailea);
                    }
                }
                break;
            case "Erabiltzailea":
                for (Banatzailea banatzailea : banatzaileak) {
                    if (banatzailea.getErabiltzailea().contains(bilatzailea)) {
                        emaitza.add(banatzailea);
                    }
                }
                break;
        }
        ObservableList<Banatzailea> data = FXCollections.observableArrayList(emaitza);
        banatzaileakTaula.setItems(data);
    }

    /**
     * Metodo honek banatzailen datuak refreskatzen ditu datu basetik
     */
    public void updateBanatzailea() {
        BanatzaileZerbitzua.updateBanatzaileakDatubasetik();
        tablaSortu();
    }


}
