package paketak.admin.zerbitzuak;

import paketak.admin.modeloak.Banatzailea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class BanatzaileZerbitzua {

    private static MysqlConector mysql = MysqlConector.getInstance();

    /**
     * Metodo honek datu basetik banatzaile guztiak ateratzen ditu eta hauek banatzailea klaseko zerranda statikoan gordetzen du
     */
    public static void updateBanatzaileakDB(){

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

    /**
     * Funtzio honek banatzaile berria datu basean gordetzeko erabiltzen da
     * @return sartu den banatzailearen id-a
     */
    public static String insertBanatzaileaDB(Banatzailea banatzailea){
        //Mysql kontsulta sortu
        String sql = "INSERT INTO `Banatzailea` " +
                "(`id`, `izena`, `abizena`, `pasahitza`, `erabiltzailea`, `entregak`, `berandu_entregatuta`) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?)";

        // Kontsulta exekutatu
        mysql.createUpdate(sql, sortubantzaileaMap(banatzailea, false));

        // Mysql hemandoko id lortu
        ResultSet lastId = mysql.createQuery("SELECT LAST_INSERT_ID()");
        String id = "";

        try {
            // Azken sartuttako Id-a lortu
            if (lastId.next()) {
                id = lastId.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    /**
     * Funtzio honek banatzailea datuak guataika artzen ditu id-a izan ezik eta map moduan bueltatzen
     * ditu clasean ordenatuta dauden poaiziao berdinetan
     * @param banatzailea map bihurtu nahi den banatzailea
     * @return banatzailea map bihurtuta
     */
    public static Map<Integer, String> sortubantzaileaMap(Banatzailea banatzailea, Boolean id){

        if (id) {
            return Map.of(
                    1, Integer.toString(banatzailea.getId()),
                    2, banatzailea.getIzena(),
                    3, banatzailea.getAbizena(),
                    4, banatzailea.getPasahitza(),
                    5, banatzailea.getErabiltzailea(),
                    6, Integer.toString(banatzailea.getEntregak()),
                    7, Integer.toString(banatzailea.getBeranduEntregak())
            );
        }
        return Map.of(
                1, banatzailea.getIzena(),
                2, banatzailea.getAbizena(),
                3, banatzailea.getPasahitza(),
                4, banatzailea.getErabiltzailea(),
                5, String.valueOf(banatzailea.getEntregak()),
                6, String.valueOf(banatzailea.getBeranduEntregak())
        );

    }

    /**
     * Datubasean update Kontsulta bat egiten du Banatzaile baten iformazioa aktuailizatzeko
     * @param banatzailea aktualizatu nahi den banatzailea
     * @return true
     */
    public static Boolean updateBanatzaileaDB(Banatzailea banatzailea){
        //sql kontsulta sortu
        String sql = "UPDATE `Banatzailea` SET `id` = ?," +
                " `izena` = ?," +
                " `abizena` = ?," +
                " `pasahitza` = ?," +
                " `erabiltzailea` = ?," +
                " `entregak` = ?," +
                " `berandu_entregatuta` = ?" +
                " WHERE `Banatzailea`.`id` = " + banatzailea.getId();

        mysql.createUpdate(sql, sortubantzaileaMap(banatzailea, true));

        return true;
    }
}
