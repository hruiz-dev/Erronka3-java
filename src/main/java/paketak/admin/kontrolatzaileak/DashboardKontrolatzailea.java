package paketak.admin.kontrolatzaileak;

import paketak.admin.modeloak.Paketea;

import java.sql.*;
import java.util.ArrayList;

public class DashboardKontrolatzailea {
    public void showPanel(){
        System.out.println("Show panel sakatuta");
    }

    public static ArrayList<Paketea> getPaketeak(){
        ArrayList<Paketea> zerrenda = new ArrayList<Paketea>();

        String url = "jdbc:mysql://10.23.25.174:3306/pakAG";
        String usuario = "root";
        String contraseña = "root";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña)) {

            Statement sententzia = conexion.createStatement();

            String kontsulta = "SELECT * FROM `Paketea`";

            ResultSet emaitza = sententzia.executeQuery(kontsulta);

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

            emaitza.close();
            sententzia.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return zerrenda;
    }
}
