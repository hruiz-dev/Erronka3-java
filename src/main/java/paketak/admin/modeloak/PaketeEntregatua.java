package paketak.admin.modeloak;

import java.util.Date;

/**
 * {@code PaketeEntregatua} klaseak entregatutako paketeen informazioa gordetzeko erabiltzen da.
 */
public class PaketeEntregatua {
    private int id;
    private Date entregaEginBeharData;
    private String hartzailea;
    private String dimentsioak;
    private Date entregatzeData;
    private String helburua;
    private String jatorria;

    /**
     * Eraikitzailea. Pakete entregatuaren atributuak ezartzen ditu.
     *
     * @param id                     paketearen identifikadorea
     * @param entregaEginBeharData  paketea entregatu behar den data
     * @param hartzailea            paketearen hartzailearen izena
     * @param dimentsioak           paketearen dimentsioak
     * @param entregatzeData        paketea entregatze data
     * @param helburua              paketearen helburua
     * @param jatorria              paketearen jatorriaren izena
     */
    public PaketeEntregatua(int id, Date entregaEginBeharData, String hartzailea, String dimentsioak, Date entregatzeData, String helburua, String jatorria) {
        this.id = id;
        this.entregaEginBeharData = entregaEginBeharData;
        this.hartzailea = hartzailea;
        this.dimentsioak = dimentsioak;
        this.entregatzeData = entregatzeData;
        this.helburua = helburua;
        this.jatorria = jatorria;
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
     * Metodo honek paketearen entregatze data itzultzen du.
     *
     * @return paketearen entregatze data
     */
    public Date getEntregatzeData() {
        return entregatzeData;
    }

    /**
     * Metodo honek paketearen entregatze data ezartzen du.
     *
     * @param entregatzeData paketearen entregatze data
     */
    public void setEntregatzeData(Date entregatzeData) {
        this.entregatzeData = entregatzeData;
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
}
