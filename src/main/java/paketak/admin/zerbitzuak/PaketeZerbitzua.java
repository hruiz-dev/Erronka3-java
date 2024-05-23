package paketak.admin.zerbitzuak;

import paketak.admin.modeloak.Paketea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaketeZerbitzua {

    private static MysqlConector mysql = MysqlConector.getInstance();

    /**
     * Metodo honek datu baseko pakete guztiak aktualizatzen ditu
     */
    public static void updatepaketeakDB(){

        ArrayList<Paketea> zerrenda = new ArrayList<>();

        // Sql kontsulta egin
        ResultSet emaitza = mysql.createQuery("SELECT * FROM `Paketea`");

        try {
            while (emaitza.next()) {

                    int id = emaitza.getInt("id");
                    String entregaEginBeharData = emaitza.getString("entrega_egin_beharreko_data");
                    String hartzailea = emaitza.getString("hartzailea");
                    String dimentsioak = emaitza.getString("dimensioak");
                    String hauskorra = emaitza.getString("hauskorra");
                    String helburua = emaitza.getString("helburua");
                    String jatorria = emaitza.getString("jatorria");
                    boolean entregatzen = emaitza.getBoolean("entregatzen");
                    int banatzaileaId = emaitza.getInt("Banatzailea_id");

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date data = formatter.parse(entregaEginBeharData);

                    Paketea paketea = new Paketea(id, data, hartzailea, dimentsioak, Boolean.valueOf(hauskorra), helburua, jatorria, entregatzen, banatzaileaId);
                    zerrenda.add(paketea);
            }
        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        Paketea.setPaketeak(zerrenda);
    }

    /**
     * Metodo hnek hurrengo 10 egunetan entrgatu beharreko paketeak erakusten ditu.
     * @return hurrengo 10 egunetan entregatu beharreko paketeak
     */
    public static ArrayList<Paketea> hurrengoEgunakPaketeak(){
        ArrayList<Paketea> zerrenda = new ArrayList<Paketea>();

        // Sql kontsulta egin
        ResultSet emaitza = mysql.createQuery("SELECT * FROM `Paketea` \n" +
                "WHERE `entrega_egin_beharreko_data` BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 10 DAY) \n" +
                "ORDER BY `entrega_egin_beharreko_data` ASC;");

        try {
            while (emaitza.next()) {

                int id = emaitza.getInt("id");
                String entregaEginBeharData = emaitza.getString("entregaEginBeharData");
                String hartzailea = emaitza.getString("hartzailea");
                String dimentsioak = emaitza.getString("dimentsioak");
                String hauskorra = emaitza.getString("hauskorra");
                String helburua = emaitza.getString("helburua");
                String jatorria = emaitza.getString("jatorria");
                boolean entregatzen = emaitza.getBoolean("entregatzen");
                int banatzaileaId = emaitza.getInt("banatzaileaId");

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatter.parse(entregaEginBeharData);

                Paketea paketea = new Paketea(id, data, hartzailea, dimentsioak, Boolean.valueOf(hauskorra), helburua, jatorria, entregatzen, banatzaileaId);
                zerrenda.add(paketea);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return zerrenda;
    }

    /**
     * Metodo honek datu basera pakete berri bat insertatzen du
     * @param paketea sartu nahi den paketea
     * @return sartutako paketearen id-a
     */
    public static String insertpaketeaDB(Paketea paketea) {
        //Mysql kontsulta sortu
        String sql = "INSERT INTO `Paketea` " +
                "(`id`, `entrega_egin_beharreko_data`, `hartzailea`, `dimensioak`, `hauskorra`, `helburua`, `jatorria`, `entregatzen`, `Banatzailea_id`) " +
                "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Kontsulta exekutatu
        mysql.createUpdate(sql, sortuPaketeaMap(paketea, false));

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
     * Funtzi honek paketearen datuekin sortutako map bat itzultzen du
     * @param paketea map bihurtu nahi den paketea
     * @param id id-a sartu nahi den ala ez
     * @return paketearen datuekin sortutako map-a
     */
    public static Map<Integer, String> sortuPaketeaMap(Paketea paketea,Boolean id) {

        String a = paketea.getBanatzaileaId() == 0 ? null : String.valueOf(paketea.getBanatzaileaId());

        Map<Integer, String> map = new HashMap<>();

        if (id) {
            map.put(1, String.valueOf(paketea.getId()));
            map.put(2, paketea.getFormatedData());
            map.put(3, paketea.getHartzailea());
            map.put(4, paketea.getDimentsioak());
            map.put(5, paketea.getHauskorra() ? "1" : "0");
            map.put(6, paketea.getHelburua());
            map.put(7, paketea.getJatorria());
            map.put(8, paketea.getEntregatzen() ? "1" : "0");
            map.put(9, a);
            return map;
        }

        map.put(1, paketea.getFormatedData());
        map.put(2, paketea.getHartzailea());
        map.put(3, paketea.getDimentsioak());
        map.put(4, paketea.getHauskorra() ? "1" : "0");
        map.put(5, paketea.getHelburua());
        map.put(6, paketea.getJatorria());
        map.put(7, paketea.getEntregatzen() ? "1" : "0");
        map.put(8, a);

        return map;

    }

    /**
     * Metodo honek datu Paketearen datuak aktualizatzen ditu
     * @param paketea aktualizatu nahi den paketea
     */
    public static void updatepaketeaDB(Paketea paketea) {
        //sql kontsulta sortu
        String sql = "UPDATE `Paketea` SET `id` = ?," +
                " `entrega_egin_beharreko_data` = ?," +
                " `hartzailea` = ?," +
                " `dimensioak` = ?," +
                " `hauskorra` = ?," +
                " `helburua` = ?," +
                " `jatorria` = ?," +
                " `entregatzen` = ?," +
                " `Banatzailea_id` = ?" +
                " WHERE `id` = " + paketea.getId();

        // Kontsulta exekutatu
        mysql.createUpdate(sql, sortuPaketeaMap(paketea, true));
    }


}
