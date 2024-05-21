package paketak.admin.zerbitzuak;

import paketak.admin.modeloak.Banatzailea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BanatzaileZerbitzua {

    private static MysqlConector mysql = MysqlConector.getInstance();

    /**
     * Metodo honek datu basetik banatzaile guztiak ateratzen ditu eta hauek banatzailea klaseko zerranda statikoan gordetzen du
     */
    public static void updateBanatzaileakDatubasetik(){

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

        Banatzailea.setBanatzaileak(zerrenda);
    }
}
