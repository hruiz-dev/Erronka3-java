package paketak.admin.kontrolatzaileak;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.modeloak.Paketea;
import paketak.admin.modeloak.PaketeaHistoriala;
import paketak.admin.zerbitzuak.BanatzaileZerbitzua;
import paketak.admin.zerbitzuak.Filter;
import paketak.admin.zerbitzuak.PaketeHistorialaZerbitzua;
import paketak.admin.zerbitzuak.TableViewCreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoriaKontrolatzailea {

    public static PaketeaHistoriala paketeAukeratua; // Aukeratutako paketearen informazioa gordetzeko aldagai estatikoa

    @FXML
    public TableView<PaketeaHistoriala> paketeakTabla; // Taula non paketeen historiala erakusten den
    @FXML
    public TextField bilatzaileaPaketea; // Paketeak bilatzeko testu eremua
    @FXML
    public ComboBox<String> filterComboxPaketea; // Paketeak iragazteko combobox-a

    @FXML
    public TableColumn<PaketeaHistoriala, String> entregaDataPaketea; // Paketearen entrega data erakusten duen zutabea
    @FXML
    public TableColumn<PaketeaHistoriala, String> entregatzeData; // Paketea entregatu zen data erakusten duen zutabea

    @FXML
    public TableView<Banatzailea> banatzaileakTabla; // Taula non banatzaileak erakusten diren
    @FXML
    public TextField bilatzaileaBanatzailea; // Banatzaileak bilatzeko testu eremua
    @FXML
    public ComboBox<String> filterComboxBanatzailea; // Banatzaileak iragazteko combobox-a

    @FXML
    public TableColumn<Banatzailea, String> pasahitzaBanatzailea; // Banatzailearen pasahitza erakusten duen zutabea

    public void initialize() {
        // paketeakTabla dagokien propietateak esleitu
        TableViewCreator.createTableView(PaketeaHistoriala.class, paketeakTabla);

        // banatzaileakTabla dagokien propietateak esleitu
        TableViewCreator.createTableView(Banatzailea.class, banatzaileakTabla);

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
            PaketeaHistoriala paketea = cellData.getValue();
            if (paketea != null) {
                return new SimpleStringProperty(paketea.getFormatedData());
            } else {
                return new SimpleStringProperty(null);
            }
        });

        entregatzeData.setCellValueFactory(cellData -> {
            PaketeaHistoriala paketea = cellData.getValue();
            if (paketea != null) {
                return new SimpleStringProperty(paketea.getFormatedEntregatzeData());
            } else {
                return new SimpleStringProperty(null);
            }
        });

        banatzaileTablaSortu();
        paketeakTablaSortu();
    }

    /**
     * Metodo honek paketeak bilatzeko erabiltzen diren datuak jasotzen ditu eta kondizio oiek erabiliz paketeak bilatzen ditu
     * eta tablan erakusten ditu
     */
    public void bilatuPaketea() {
        String filter = filterComboxPaketea.getValue();
        String bilatzailea = bilatzaileaPaketea.getText();

        ArrayList<PaketeaHistoriala> paketeak = PaketeaHistoriala.getPaketeak();
        ArrayList<PaketeaHistoriala> emaitza;

        if (filter == null) {
            filter = "Id";
        } else if(filter.equals("Entrega egin beharreko data")) {
            filter = "EntregaEginBeharData";

        } else if(filter.equals("Entregatze data")) {
            filter = "EntregatzeData";
        }

        emaitza = Filter.filtratu( paketeak, filter, bilatzailea);

        ObservableList<PaketeaHistoriala> data = FXCollections.observableArrayList(emaitza);
        paketeakTabla.setItems(data);
    }

    /**
     * Metodo honek tablan ikusten diren banatzaileak aldatzen ditu comboxa eta bilatzaileko textuaren arabera
     */
    public void bilatuBanatzaileak() {
        String bilatzailea = bilatzaileaBanatzailea.getText();
        String filter = filterComboxBanatzailea.getValue();
        ArrayList<Banatzailea> emaitza;

        if (filter == null){
            filter = "Id";
        }

        emaitza = Filter.filtratu(Banatzailea.getBanatzaileak(), filter, bilatzailea);

        ObservableList<Banatzailea> data = FXCollections.observableArrayList(emaitza);
        banatzaileakTabla.setItems(data);
    }

    /**
     * Datu basetik Paketen datuak aktualizatzen ditu
     */
    public void updatePaketea() {
        paketeakTablaSortu();
    }

    /**
     * Datu basetik Banatzaileen datuak aktualizatzen ditu
     */
    public void updateBanatzailea() {
        banatzaileTablaSortu();
    }

    /**
     * Metodo honek banatzaileak tablan ikusten diren datuak aktualizatzen ditu
     */
    public void tablanAukeratu() {
        Banatzailea banatzailea = banatzaileakTabla.getSelectionModel().getSelectedItem();

        ArrayList<PaketeaHistoriala> paketeak = PaketeaHistoriala.getPaketeak();
        ArrayList<PaketeaHistoriala> emaitza;

        emaitza = Filter.filtratu( paketeak, "BanatzaileaId",  String.valueOf(banatzailea.getId()));

        ObservableList<PaketeaHistoriala> data = FXCollections.observableArrayList(emaitza);
        paketeakTabla.setItems(data);
    }

    /**
     * Metodo honek panel berri bat sortzen du Tablan aukeratutako paketearen informazioa erakusteko
     */
    public void zehaztasunakIkusi(){
        PaketeaHistoriala paketeaHistoriala = paketeakTabla.getSelectionModel().getSelectedItem();
        paketeAukeratua = paketeaHistoriala;
        if (paketeaHistoriala != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/paketak/admin/paketea.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Informazioa");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Metodo honek Banatzaileak tablako datuak aktualizatzen ditu
     */
    public void banatzaileTablaSortu () {
        BanatzaileZerbitzua.updateBanatzaileakDB();
        List<Banatzailea> banatzaileak = Banatzailea.getBanatzaileak();

        ObservableList<Banatzailea> data = FXCollections.observableArrayList(banatzaileak);
        banatzaileakTabla.setItems(data);
    }

    /**
     * Metodo honek Paketeak tablako datuak aktualizatzen ditu
     */
    public void paketeakTablaSortu() {
        PaketeHistorialaZerbitzua.uppdatePaketeHistorialaDB();
        List<PaketeaHistoriala> paketeak = PaketeaHistoriala.getPaketeak();

        ObservableList<PaketeaHistoriala> data = FXCollections.observableArrayList(paketeak);
        paketeakTabla.setItems(data);
    }
}