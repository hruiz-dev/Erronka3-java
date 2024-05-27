package paketak.admin.kontrolatzaileak;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.MysqlConector;
import paketak.admin.zerbitzuak.PaketeZerbitzua;
import paketak.admin.zerbitzuak.TableViewCreator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HasieraKontrolatzailea {

    private static MysqlConector mysql = MysqlConector.getInstance();

    @FXML
    public Label banatzekopaketeak;
    @FXML
    public Label laisterBanatzekoak;

    @FXML
    public Label inzidentziakKont;
    @FXML
    private TableView<Paketea> paketeTabla;

    @FXML
    private TableColumn<Paketea, String> entregaEginBeharDataColumn;

    public void initialize() {
        // Tablako zutabe bakoitza Paketea objetuko atributu bati esleitu.
        TableViewCreator.createTableView(Paketea.class, paketeTabla);

        // Data zutabea formatu egokian erakusteko
        entregaEginBeharDataColumn.setCellValueFactory(cellData -> {
            Paketea paketea = cellData.getValue();
            if (paketea != null) {
                return new SimpleStringProperty(paketea.getFormatedData());
            } else {
                return new SimpleStringProperty(null);
            }
        });

        tablaSortu();

        aktualizatuPaketeDatuak();
    }

    /**
     * Datubasetik ateratako Paketak Tablan bistaratu
     */
    public void tablaSortu () {
        List<Paketea> paketeak = PaketeZerbitzua.hurrengoEgunakPaketeak();
        ObservableList<Paketea> data = FXCollections.observableArrayList(paketeak);
        paketeTabla.setItems(data);
    }

    /**
     * Funtzio honek datu basean dauden pakete kopurua eta laister banatu behar diren pakete kopurua aktulizatzen du interfazean
     */
    public void aktualizatuPaketeDatuak(){

        // Sql kontsultak egin
        ResultSet paketeak = mysql.createQuery("SELECT COUNT(*) FROM `Paketea` WHERE `Banatzailea_id` IS NULL");

        ResultSet inzidentziak = mysql.createQuery("SELECT COUNT(*) FROM `Inzidenzia`");

        ResultSet banatzekoak = mysql.createQuery("SELECT COUNT(*) FROM `Paketea` \n" +
                "WHERE `entrega_egin_beharreko_data` BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 10 DAY) \n" +
                "ORDER BY `entrega_egin_beharreko_data` ASC;");

        try {
            // Kontsultaren emaitzak lortu eta interfazearen labelak aktulizatu
            if (paketeak.next()) {
                int count = paketeak.getInt(1);
                banatzekopaketeak.setText(Integer.toString(count));
            }

            if (inzidentziak.next()) {
                int count = inzidentziak.getInt(1);
                inzidentziakKont.setText(Integer.toString(count));
            }

            if (banatzekoak.next()) {
                int count = banatzekoak.getInt(1);
                laisterBanatzekoak.setText(Integer.toString(count));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}