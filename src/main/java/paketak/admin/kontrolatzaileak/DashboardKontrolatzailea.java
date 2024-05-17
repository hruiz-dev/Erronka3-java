package paketak.admin.kontrolatzaileak;

import paketak.admin.modeloak.Banatzailea;
import paketak.admin.modeloak.Paketea;
import paketak.admin.zerbitzuak.MysqlConector;

import java.sql.*;
import java.util.ArrayList;

public class DashboardKontrolatzailea {

    private static MysqlConector mysql = MysqlConector.getInstance();

    public void initialize(){
    }

    public void showPanel(){
        System.out.println("Show panel sakatuta");
    }

    public static ArrayList<Paketea> getPaketeak(){
        ArrayList<Paketea> zerrenda = new ArrayList<Paketea>();

        ResultSet emaitza = mysql.createQuery("SELECT * FROM `Paketea`");

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
}
