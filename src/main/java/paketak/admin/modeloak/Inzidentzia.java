package paketak.admin.modeloak;

/**
 * {@code Inzidentzia} klaseak inzidentzia bat deskribatzen du, inzidentziaren
 * kodea eta informazioa gordetzeko erabiltzen da.
 */
public class Inzidentzia {
    private int inzidentziaKodea;
    private String informazioa;

    /**
     * Eraikitzailea. Inzidentziaren atributuak ezartzen ditu.
     *
     * @param inzidentziaKodea inzidentziaren kodea
     * @param informazioa      inzidentziaren informazioa
     */
    public Inzidentzia(int inzidentziaKodea, String informazioa) {
        this.inzidentziaKodea = inzidentziaKodea;
        this.informazioa = informazioa;
    }

    /**
     * Metodo honek inzidentziaren kodea itzultzen du.
     *
     * @return inzidentziaren kodea
     */
    public int getInzidentziaKodea() {
        return inzidentziaKodea;
    }

    /**
     * Metodo honek inzidentziaren kodea ezartzen du.
     *
     * @param inzidentziaKodea inzidentziaren kodea
     */
    public void setInzidentziaKodea(int inzidentziaKodea) {
        this.inzidentziaKodea = inzidentziaKodea;
    }

    /**
     * Metodo honek inzidentziaren informazioa itzultzen du.
     *
     * @return inzidentziaren informazioa
     */
    public String getInformazioa() {
        return informazioa;
    }

    /**
     * Metodo honek inzidentziaren informazioa ezartzen du.
     *
     * @param informazioa inzidentziaren informazioa
     */
    public void setInformazioa(String informazioa) {
        this.informazioa = informazioa;
    }
}
