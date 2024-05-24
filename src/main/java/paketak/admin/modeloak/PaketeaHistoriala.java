package paketak.admin.modeloak;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * {@code Paketea} klaseak pakete baten informazioa gordetzeko erabiltzen da.
 */
public class PaketeaHistoriala {
    private int id;
    private Date entregaEginBeharData;
    private Date entregatzeData;
    private String hartzailea;
    private String dimentsioak;
    private String helburua;
    private String jatorria;
    private boolean hauskorra;
    private int banatzaileaId;


    private static ArrayList<PaketeaHistoriala> paketeak = new ArrayList<PaketeaHistoriala>();

    /**
     * Eraikitzailea. Paketearen atributuak ezartzen ditu.
     *
     * @param id                     paketearen identifikadorea
     * @param entregaEginBeharData  paketea entregatu behar den data
     * @param hartzailea            paketearen hartzailearen izena
     * @param dimentsioak           paketearen dimentsioak
     * @param entregatzeData        paketea entregatuta dagoen data
     * @param helburua              paketearen helburua
     * @param jatorria              paketearen jatorriaren izena
     * @param hauskorra              paketearen egoera
     * @param banatzaileaId         paketearen banatzailearen identifikadorea
     */
    public PaketeaHistoriala(int id, Date entregaEginBeharData, String hartzailea, String dimentsioak, Boolean hauskorra, String helburua, String jatorria, Date entregatzeData, int banatzaileaId) {
        this.id = id;
        this.entregaEginBeharData = entregaEginBeharData;
        this.hartzailea = hartzailea;
        this.dimentsioak = dimentsioak;
        this.hauskorra = hauskorra;
        this.helburua = helburua;
        this.jatorria = jatorria;
        this.entregatzeData = entregatzeData;
        this.banatzaileaId = banatzaileaId;
    }

    /**
     * Metodo honek paketearen identifikadorea itzultzen du.
     *
     * @return paketearen identifikadorea
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo honek paketearen identifikadorea ezartzen du.
     *
     * @param id paketearen identifikadorea
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo honek paketearen entregatu behar den data itzultzen du.
     *
     * @return entregatu behar den data
     */
    public Date getEntregaEginBeharData() {
        return entregaEginBeharData;
    }

    /**
     * Metodo honek paketearen entregatu behar den data ezartzen du.
     *
     * @param entregaEginBeharData entregatu behar den data
     */
    public void setEntregaEginBeharData(Date entregaEginBeharData) {
        this.entregaEginBeharData = entregaEginBeharData;
    }

    public String getFormatedData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(entregaEginBeharData);
    }

    public String getFormatedEntregatzeData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(entregatzeData);
    }

    public Date getEntregatzeData() {
        return entregatzeData;
    }

    public void setEntregatzeData(Date entregatzeData) {
        this.entregatzeData = entregatzeData;
    }

    public boolean isHauskorra() {
        return hauskorra;
    }

    /**
     * Metodo honek paketearen hartzailearen izena itzultzen du.
     *
     * @return paketearen hartzailearen izena
     */
    public String getHartzailea() {
        return hartzailea;
    }

    /**
     * Metodo honek paketearen hartzailearen izena ezartzen du.
     *
     * @param hartzailea paketearen hartzailearen izena
     */
    public void setHartzailea(String hartzailea) {
        this.hartzailea = hartzailea;
    }

    /**
     * Metodo honek paketearen dimentsioak itzultzen ditu.
     *
     * @return paketearen dimentsioak
     */
    public String getDimentsioak() {
        return dimentsioak;
    }

    /**
     * Metodo honek paketearen dimentsioak ezartzen ditu.
     *
     * @param dimentsioak paketearen dimentsioak
     */
    public void setDimentsioak(String dimentsioak) {
        this.dimentsioak = dimentsioak;
    }

    /**
     * Metodo honek paketearen helburua itzultzen du.
     *
     * @return paketearen helburua
     */
    public String getHelburua() {
        return helburua;
    }

    /**
     * Metodo honek paketearen helburua ezartzen du.
     *
     * @param helburua paketearen helburua
     */
    public void setHelburua(String helburua) {
        this.helburua = helburua;
    }

    /**
     * Metodo honek paketearen jatorriaren izena itzultzen du.
     *
     * @return paketearen jatorriaren izena
     */
    public String getJatorria() {
        return jatorria;
    }

    /**
     * Metodo honek paketearen jatorriaren izena ezartzen du.
     *
     * @param jatorria paketearen jatorriaren izena
     */
    public void setJatorria(String jatorria) {
        this.jatorria = jatorria;
    }


    public boolean getHauskorra() {
        return hauskorra;
    }

    public void setHauskorra(boolean hauskorra) {
        this.hauskorra = hauskorra;
    }

    public int getBanatzaileaId() {
        return banatzaileaId;
    }

    public void setBanatzaileaId(int banatzaileaId) {
        this.banatzaileaId = banatzaileaId;
    }

    /**
     * Metodo honek paketeen zerrenda itzultzen du.
     *
     * @return paketeen zerrenda
     */
    public static ArrayList<PaketeaHistoriala> getPaketeak() {
        return paketeak;
    }

    /**
     * Metodo honek paketeen zerrenda ezartzen du.
     *
     * @param paketeak paketeen zerrenda
     */
    public static void setPaketeak(ArrayList<PaketeaHistoriala> paketeak) {
        PaketeaHistoriala.paketeak = paketeak;
    }
}
