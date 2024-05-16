package paketak.admin.kontrolatzaileak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.TablaModelCreator;

import java.util.List;

public class PanelHasieraKontrolatzailea {

    @FXML
    private TableView paketeTabla;

    public void initialize() {
        tablaSortu();
    }

    public void tablaSortu () {
        paketeTabla = TablaModelCreator.createTableModel(Paketea.class, paketeTabla);
        List<Paketea> banatzaileak = Paketea.getPaketeak();
        ObservableList<Paketea> data = FXCollections.observableArrayList(banatzaileak);
        paketeTabla.setItems(data);
    }
}
