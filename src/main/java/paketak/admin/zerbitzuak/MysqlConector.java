package paketak.admin.zerbitzuak;

import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Singleton patroia erabiliz sortutako klasea
 * horrela bermatzen da gure mysql konexio bakarra egongo dela momentu
 * bakoitzean
 */
public class MysqlConector {

    private static final String URL = "jdbc:mysql://localhost:3306/pakAG?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static MysqlConector mysql;

    private Connection connection;

    private MysqlConector() {

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funtzio honek MysqlConector klasearen instantzia itzultzen du
     * @return MysqlConector
     */
    public static MysqlConector getInstance() {
        if (mysql == null) {
            mysql = new MysqlConector();
        }
        return mysql;
    }

    /**
     * Funtzio honek datu basean select motako queryak egiten ditu
     *
     * @param select select query-a
     * @param datuak komekin separatutako kontsultan behar diren datuak
     * @return selecta ejekutatzean lortutako datuak
     */
    public ResultSet createQuery(String select, String datuak) {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = sortuEstamentua(select, datuak);
            rs = stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Metodo honek datu basean select motako queryak egiten ditu
     *
     * @param select select query-a
     * @return selecta ejekutatzean lortutako datuak
     */
    public ResultSet createQuery(String select) {
        ResultSet rs = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(select);
            rs = stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Metodo honek datu basean insertatu nahi den datuak jasotzen ditu eta insert
     * egiten du
     *
     * @param update Select motakoa ez den edozein query
     * @param datuak komekin separatutako kontsultan behar diren datuak
     */
    public void createUpdate(String update, String datuak) {
        try {
            PreparedStatement stmt = sortuEstamentua(update, datuak);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo honek datu basean insertatu nahi den datuak jasotzen ditu eta insert
     * egiten du
     *
     * @param update Select motakoa ez den edozein query
     */
    public void createUpdate(String update) {
        try {
            PreparedStatement stmt = connection.prepareStatement(update);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prozedura honek datu basearekin konexioa ixten du
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo honek estamentuak sortzen ditu
     *
     * @param query  estamentuaren biurtu nahi den querya
     * @param datuak komekin separatutako kontsultan behar diren datuak
     * @return sortutako estamentua
     */
    public PreparedStatement sortuEstamentua(String query, String datuak) throws SQLException {

        PreparedStatement ps = connection.prepareStatement(query);
        int i = 0;

        do {
            String datu;
            if (datuak.contains(",")) {
                datu = datuak.substring(0, datuak.indexOf(","));
                datuak = datuak.substring(datuak.indexOf(",") + 1);
            } else {
                datu = datuak;
                datuak = "";
            }
            ps.setString(++i, datu);
        } while (!datuak.isEmpty());

        return ps;
    }

}
