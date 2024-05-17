package paketak.admin.kontrolatzaileak;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import paketak.admin.modeloak.Banatzailea;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.MysqlConector;

import java.sql.*;
import java.util.ArrayList;

public class DashboardKontrolatzailea {

    private static MysqlConector mysql = MysqlConector.getInstance();
    @FXML
    public Label banatzekopaketeak;
    @FXML
    public Label laisterBanatzekoak;

    public void initialize(){
        aktualizatuPaketeDatuak();
    }

    public void showPanel(){
        System.out.println("Show panel sakatuta");
    }

    public static ArrayList<Paketea> getPaketeak(){
        ArrayList<Paketea> zerrenda = new ArrayList<Paketea>();

        ResultSet emaitza = mysql.createQuery("SELECT * FROM `Paketea` \n" +
                "WHERE `entrega_egin_beharreko_data` BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 10 DAY) \n" +
                "ORDER BY `entrega_egin_beharreko_data` ASC;");

        try {

            while (emaitza.next()) {

                int id = emaitza.getInt("id");
                Date entregaEginBeharData = emaitza.getDate("entrega_egin_beharreko_data");
                String hartzailea = emaitza.getString("hartzailea");
                String dimentsioak = emaitza.getString("dimensioak");
                boolean entregatuta = emaitza.getBoolean("entregatuta");
                String helburua = emaitza.getString("helburua");
                String jatorria = emaitza.getString("jatorria");

                Paketea paketea = new Paketea(id, entregaEginBeharData, hartzailea, dimentsioak, entregatuta, helburua, jatorria);
                zerrenda.add(paketea);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return zerrenda;
    }

    /**
     * Funtzio honek datu basean dauden pakete kopurua eta laister banatu behar diren pakete kopurua aktulizatzen du interfazean
     */
    public void aktualizatuPaketeDatuak(){
        ResultSet paketeak = mysql.createQuery("SELECT COUNT(*) FROM `Paketea` WHERE `Banatzailea_id` IS NULL");

        try {
            if (paketeak.next()) {
                int count = paketeak.getInt(1);
                banatzekopaketeak.setText(Integer.toString(count));
            }
            ResultSet banatzekoak = mysql.createQuery("SELECT COUNT(*) FROM `Paketea` \n" +
                    "WHERE `entrega_egin_beharreko_data` BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 10 DAY) \n" +
                    "ORDER BY `entrega_egin_beharreko_data` ASC;");

            if (banatzekoak.next()) {
                int count = banatzekoak.getInt(1);
                laisterBanatzekoak.setText(Integer.toString(count));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
