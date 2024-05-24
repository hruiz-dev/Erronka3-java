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
import paketak.admin.modeloak.PaketeaHistoriala;
import paketak.admin.zerbitzuak.BanatzaileZerbitzua;
import paketak.admin.zerbitzuak.Filter;
import paketak.admin.zerbitzuak.PaketeHistorialaZerbitzua;
import paketak.admin.zerbitzuak.TableViewCreator;

import java.util.ArrayList;
import java.util.List;

public class HistoriaKontrolatzailea {

    @FXML
    public TableView<PaketeaHistoriala> paketeakTabla;
    @FXML
    public TextField bilatzaileaPaketea;
    @FXML
    public ComboBox<String> filterComboxPaketea;

    @FXML
    public TableColumn<PaketeaHistoriala, String> entregaDataPaketea;
    @FXML
    public TableColumn<PaketeaHistoriala, String> entregatzeData;


    @FXML
    public TableView<Banatzailea> banatzaileakTabla;


    @FXML
    public TableColumn<Banatzailea, String> pasahitzaBanatzailea;

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
        String filter = filterCombox.getValue();
        ArrayList<Banatzailea> emaitza;

        if (filter == null){
            filter = "Id";
        }

        emaitza = Filter.filtratu(Banatzailea.getBanatzaileak(), filter, bilatzailea);

        ObservableList<Banatzailea> data = FXCollections.observableArrayList(emaitza);
        banatzaileakTaula.setItems(data);
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
        banatzaileakTabla.setItems(data);
    }

    public void paketeakTablaSortu() {
        PaketeHistorialaZerbitzua.uppdatePaketeHistorialaDB();
        List<PaketeaHistoriala> paketeak = PaketeaHistoriala.getPaketeak();

        ObservableList<PaketeaHistoriala> data = FXCollections.observableArrayList(paketeak);
        paketeakTabla.setItems(data);
    }
}
