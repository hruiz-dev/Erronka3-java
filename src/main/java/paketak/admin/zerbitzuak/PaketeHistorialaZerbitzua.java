package paketak.admin.zerbitzuak;

import paketak.admin.modeloak.PaketeaHistoriala;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PaketeHistorialaZerbitzua {

    private static MysqlConector mysql = MysqlConector.getInstance();

    public static void uppdatePaketeHistorialaDB() {

        ArrayList<PaketeaHistoriala> zerrenda = new ArrayList<PaketeaHistoriala>();
        ResultSet emaitza = mysql.createQuery("SELECT * FROM `Pakete_Historiala`");

        try {

            while (emaitza.next()) {

                int id = emaitza.getInt("id");
                Date entregaEginBeharData = emaitza.getDate("entrega_egin_beharreko_data");
                Date entregatzeData = emaitza.getDate("entregatze_data");
                String hartzailea = emaitza.getString("hartzailea");
                String dimentsioak = emaitza.getString("dimensioak");
                boolean entregatzen = emaitza.getBoolean("hauskorra");
                String helburua = emaitza.getString("helburua");
                String jatorria = emaitza.getString("jatorria");
                boolean hauskorra = emaitza.getBoolean("hauskorra");
                int banatzaileaId = emaitza.getInt("banatzailea_Id");

                PaketeaHistoriala paketea = new PaketeaHistoriala(id, entregaEginBeharData, hartzailea, dimentsioak, hauskorra, helburua, jatorria, entregatzeData, banatzaileaId);
                zerrenda.add(paketea);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PaketeaHistoriala.setPaketeak(zerrenda);
    }
}
