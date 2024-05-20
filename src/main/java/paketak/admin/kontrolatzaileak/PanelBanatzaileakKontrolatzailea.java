package paketak.admin.kontrolatzaileak;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.zerbitzuak.MysqlConector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void initialize() {
        // Tablako zutabe bakoitza Paketea objetuko atributu bati esleitu.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        izena.setCellValueFactory(new PropertyValueFactory<>("izena"));
        abizena.setCellValueFactory(new PropertyValueFactory<>("abizena"));
        erabiltzailea.setCellValueFactory(new PropertyValueFactory<>("erabiltzailea"));
        pasahitza.setCellValueFactory(new PropertyValueFactory<>("pasahitza"));
        entregak.setCellValueFactory(new PropertyValueFactory<>("entregak"));
        beranduEntregak.setCellValueFactory(new PropertyValueFactory<>("beranduEntregak"));

        tablaSortu();
    }

    public ArrayList<Banatzailea> getBanatzaileak(){

        ArrayList<Banatzailea> zerrenda = new ArrayList<Banatzailea>();
        // Sql kontsulta egin
        ResultSet emaitza = mysql.createQuery("SELECT * FROM `Banatzailea`");

        try {

            // Kkontsultatik datuak objetuera ppasa eta hauek zeernda batean gorde
            while (emaitza.next()) {

                int id = emaitza.getInt("id");
                String izena = emaitza.getString("izena");
                String abizena = emaitza.getString("abizena");
                String erabiltzailea = emaitza.getString("erabiltzailea");
                String pasahitza = emaitza.getString("pasahitza");
                int entregak = emaitza.getInt("entregak");
                int beranduEntregak = emaitza.getInt("berandu_entregatuta");

                Banatzailea banatzailea = new Banatzailea(id, izena, abizena, erabiltzailea, pasahitza, entregak, beranduEntregak);
                zerrenda.add(banatzailea);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return zerrenda;
    }

    /**
     * Datubaseti ateratako Banatzaileak Tablabistaratu
     */
    public void tablaSortu () {
        Banatzailea.setBanatzaileak(getBanatzaileak());
        List<Banatzailea> banatzaileak = Banatzailea.getBanatzaileak();
        ObservableList<Banatzailea> data = FXCollections.observableArrayList(banatzaileak);
        banatzaileakTaula.setItems(data);
        System.out.println("Bai");
    }


}
