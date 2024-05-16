package paketak.admin.modeloak;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Banatzailearen informazioa gordetzeko klasea.
 *
 * @author Hegoi eta Unax
 */
public class Banatzailea {
    private int id;
    private String izena;
    private String abizena;
    private String erabiltzailea;
    private String pasahitza;
    private int entregak;
    private int beranduEntregak;

    public static ArrayList<Banatzailea> banatzaileak = new ArrayList<Banatzailea>();

    /**
     * Banatzailearen informazioa gordetzeko konstruktorea.
     *
     * @param id              Banatzailearen ID-a.
     * @param izena           Banatzailearen izena.
     * @param abizena         Banatzailearen abizena.
     * @param erabiltzailea   Banatzailearen erabiltzailearen izena.
     * @param pasahitza       Banatzailearen pasahitza.
     * @param entregak        Banatzailearen entregak.
     * @param beranduEntregak Banatzailearen berandu entregak.
     */
    public Banatzailea(int id, String izena, String abizena, String erabiltzailea, String pasahitza, int entregak, int beranduEntregak) {
        this.id = id;
        this.izena = izena;
        this.abizena = abizena;
        this.erabiltzailea = erabiltzailea;
        this.pasahitza = pasahitza;
        this.entregak = entregak;
        this.beranduEntregak = beranduEntregak;
    }

    /**
     * Banatzailearen ID-a itzultzen du.
     *
     * @return Banatzailearen ID-a.
     */
    public int getId() {
        return id;
    }

    /**
     * Banatzailearen ID-a ezartzen du.
     *
     * @param id Banatzailearen ID-a.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Banatzailearen izena itzultzen du.
     *
     * @return Banatzailearen izena.
     */
    public String getIzena() {
        return izena;
    }

    /**
     * Banatzailearen izena ezartzen du.
     *
     * @param izena Banatzailearen izena.
     */
    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * Banatzailearen abizena itzultzen du.
     *
     * @return Banatzailearen abizena.
     */
    public String getAbizena() {
        return abizena;
    }

    /**
     * Banatzailearen abizena ezartzen du.
     *
     * @param abizena Banatzailearen abizena.
     */
    public void setAbizena(String abizena) {
        this.abizena = abizena;
    }

    /**
     * Banatzailearen erabiltzailearen izena itzultzen du.
     *
     * @return Banatzailearen erabiltzailearen izena.
     */
    public String getErabiltzailea() {
        return erabiltzailea;
    }

    /**
     * Banatzailearen erabiltzailearen izena ezartzen du.
     *
     * @param erabiltzailea Banatzailearen erabiltzailearen izena.
     */
    public void setErabiltzailea(String erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    /**
     * Banatzailearen pasahitza itzultzen du.
     *
     * @return Banatzailearen pasahitza.
     */
    public String getPasahitza() {
        return pasahitza;
    }

    /**
     * Metodo honek pertsonaren pasahitza ezartzen du.
     *
     * @param pasahitza pertsonaren pasahitza
     */
    public void setPasahitza(String pasahitza) {
        this.pasahitza = pasahitza;
    }

    /**
     * Metodo honek entregak itzultzen ditu.
     *
     * @return entregak
     */
    public int getEntregak() {
        return entregak;
    }

    /**
     * Metodo honek entregak ezartzen ditu.
     *
     * @param entregak pertsonaren entregak
     */
    public void setEntregak(int entregak) {
        this.entregak = entregak;
    }

    /**
     * Metodo honek berandu entregak itzultzen ditu.
     *
     * @return berandu entregak
     */
    public int getBeranduEntregak() {
        return beranduEntregak;
    }

    /**
     * Metodo honek berandu entregak ezartzen ditu.
     *
     * @param beranduEntregak pertsonaren berandu entregak
     */
    public void setBeranduEntregak(int beranduEntregak) {
        this.beranduEntregak = beranduEntregak;
    }

    /**
     * Funtzio honek banatzaileak itzultzen ditu.
     *
     * @return banatzaileak
     */
    public static ArrayList<Banatzailea> getBanatzaileak() {
        return banatzaileak;
    }

    /**
     * Funtzio honek banatzaileak ezartzen ditu.
     * @param banatzaileak banatzaileak
     */
    public static void setBanatzaileak(ArrayList<Banatzailea> banatzaileak) {
        Banatzailea.banatzaileak = banatzaileak;
    }
}
