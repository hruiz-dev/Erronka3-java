package paketak.admin.kontrolatzaileak;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.Filter;
import paketak.admin.zerbitzuak.Komponenteak;
import paketak.admin.zerbitzuak.MysqlConector;
import paketak.admin.zerbitzuak.PaketeZerbitzua;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaketeKontrolatzailea {

    @FXML
    private TableView paketeakTabla;

    @FXML
    private TableColumn<Paketea, Integer> idColumn;
    @FXML
    private TableColumn<Paketea, String> entregaData;
    @FXML
    private TableColumn<Paketea, String> hartzailea;
    @FXML
    private TableColumn<Paketea, String> dimesioak;
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
    private TextField jatorriaTextArea;
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

    private static MysqlConector mysql = MysqlConector.getInstance();
    public void initialize() {
        // Tablako zutabe bakoitza Paketea objetuko atributu bati esleitu.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        hartzailea.setCellValueFactory(new PropertyValueFactory<>("hartzailea"));
        dimesioak.setCellValueFactory(new PropertyValueFactory<>("dimentsioak"));
        hauskorra.setCellValueFactory(new PropertyValueFactory<>("hauskorra"));
        helburua.setCellValueFactory(new PropertyValueFactory<>("helburua"));
        jatorria.setCellValueFactory(new PropertyValueFactory<>("jatorria"));
        entregatzen.setCellValueFactory(new PropertyValueFactory<>("entregatzen"));
        banatzaileaId.setCellValueFactory(new PropertyValueFactory<>("banatzaileaId"));

        // Data zutabea formatu egokian erakusteko
        entregaData.setCellValueFactory(cellData -> {
            Paketea paketea = cellData.getValue();
            if (paketea != null) {
                return new SimpleStringProperty(paketea.getFormatedData());
            } else {
                return new SimpleStringProperty(null);
            }
        });

        tablaSortu();
    }

    /**
     * Metodo honek tablan gure Paketen datuk Eguneratu eta Bistaratzen ditu
     */
    public void tablaSortu() {
        PaketeZerbitzua.updatepaketeakDB();
        ObservableList<Paketea> data = FXCollections.observableArrayList(Paketea.getPaketeak());
        paketeakTabla.setItems(data);

    }

    /**
     * Metodo honek lehenik alerta bat erekuratzen du, hau baizkoa ematen badiogu
     * Paketea datub asetik ezabatzen du.
     */
    public void ezabatuPaketea() {
        Alert alert = Komponenteak.sortuAlerta("Ezabatu paketea",
                "Ziur zaude paketea ezabatu nahi duzula?",
                "Ezabatzean, paketearen datu guztiak ezabatuko dira.");

        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            Paketea paketea = (Paketea) paketeakTabla.getSelectionModel().getSelectedItem();
            mysql.createUpdate("DELETE FROM `Paketea` WHERE `id` = " + paketea.getId());

            tablaSortu();
            garbituTextAreak();

            erakutsiAlertPanelSucessfull(Integer.toString(paketea.getId()) + " zenbakidun paketea ezabatu da.");
            return;
        }
        erakutsiAlertPanelErrorea("paketea ezabatzea bertan behera geratu da.");

    }

    /**
     * Metodo honek Datu Basean Update egiten du, eta pasatako paketearen datuak eguneratzen ditu.
     */
    public void gordePaketea() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = formatter.parse(entregaDataTextField.getText());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Paketea paketea = new Paketea(
                Integer.parseInt(idTextArea.getText()),
                date,
                hartzaileaTextField.getText(),
                dimentsioakTextField.getText(),
               Boolean.parseBoolean(hauskorraComboBox.getValue()),
                helburuaTextField.getText(),
                jatorriaTextArea.getText(),
                Boolean.parseBoolean(entregatzenComboBox.getValue()),
                Integer.parseInt(banatzaileaIdTextField.getText())
        );

        PaketeZerbitzua.updatepaketeaDB(paketea);

        tablaSortu();
        garbituTextAreak();

        erakutsiAlertPanelSucessfull(Integer.toString(paketea.getId()) + " zenbakidun paketea eguneratu da.");

    }

    /**
     * Metodo honek Text Area guztiak garbitzen ditu.
     */
    public void garbituTextAreak() {
        idTextArea.setText("");
        entregaDataTextField.setText("");
        hartzaileaTextField.setText("");
        dimentsioakTextField.setText("");
        hauskorraComboBox.setValue("false");
        helburuaTextField.setText("");
        jatorriaTextArea.setText("");
        entregatzenComboBox.setValue("false");
        banatzaileaIdTextField.setText("");

    }

    /**
     * Emtodo honek pakete berri bat sortzen du datu basean
     */
    public void sortuPaketea() {

        if (!komprobatuTextAreak()) {
            return;
        }
        Alert alert = Komponenteak.sortuAlerta("Sortu paketea",
                "Ziur zaude paketea sortu nahi duzula?",
                "Sortzean, paketearen datu guztiak datu basean sartuko dira.");
        if (alert.getResult() != ButtonType.OK) {
            erakutsiAlertPanelErrorea("paketea sortzea bertan behera geratu da.");
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
             date = formatter.parse(entregaDataTextField.getText());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Paketea paketea = new Paketea(
                Integer.parseInt(idTextArea.getText()),
                date,
                hartzaileaTextField.getText(),
                dimentsioakTextField.getText(),
                Boolean.parseBoolean(hauskorraComboBox.getValue()),
                helburuaTextField.getText(),
                jatorriaTextArea.getText(),
                Boolean.parseBoolean(entregatzenComboBox.getValue()),
                Integer.parseInt(banatzaileaIdTextField.getText())
        );

        String id = PaketeZerbitzua.insertpaketeaDB(paketea);

        erakutsiAlertPanelSucessfull(id + " zenbakidun paketea sortu da.");
    }

    /**
     * Metodo honek paketeak filtratzen ditu bilatzailearen arabera
     */
    public void bilatuPaketea() {
        String bilatzailea = bilatzaileaTextField.getText();
        String filter = filterCombox.getValue();
        ArrayList<Paketea> emaitza;

        if (filter == null){
            filter = "Id";
        } else if (filter.equals("Entrega egin beharreko data")) {
            filter = "EntregaEginBeharData";
        }

        emaitza = Filter.filtratu(Paketea.getPaketeak(), filter, bilatzailea);

        ObservableList<Paketea> data = FXCollections.observableArrayList(emaitza);
        paketeakTabla.setItems(data);
    }

    /**
     * Metodo honek tabla refreskatzen du
     */
    public void updatePaketea() {
        tablaSortu();
    }

    /**
     * Metodo honek tablan aukeratutako kolugna erabiliz horren datuak textArea guztietan sartzen ditu
     */
    public void tablanAukeratu() {
        Paketea paketea = (Paketea) paketeakTabla.getSelectionModel().getSelectedItem();
        idTextArea.setText(Integer.toString(paketea.getId()));
        entregaDataTextField.setText(paketea.getFormatedData());
        hartzaileaTextField.setText(paketea.getHartzailea());
        dimentsioakTextField.setText(paketea.getDimentsioak());
        hauskorraComboBox.setValue(Boolean.toString(paketea.getHauskorra()));
        helburuaTextField.setText(paketea.getHelburua());
        jatorriaTextArea.setText(paketea.getJatorria());
        entregatzenComboBox.setValue(Boolean.toString(paketea.isEntregatzen()));
        banatzaileaIdTextField.setText(Integer.toString(paketea.getBanatzaileaId()));


    }

    /**
     * Metodo honek alerta panela ixten du
     */
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
    public Boolean komprobatuTextAreak() {
        if (idTextArea.getText().isEmpty() || entregaDataTextField.getText().isEmpty() || hartzaileaTextField.getText().isEmpty() || dimentsioakTextField.getText().isEmpty() || helburuaTextField.getText().isEmpty() || jatorriaTextArea.getText().isEmpty() || banatzaileaIdTextField.getText().isEmpty()) {
            erakutsiAlertPanelErrorea("Eremu guztiak bete behar dira.");
            return false;
        }
        return true;
    }


}
