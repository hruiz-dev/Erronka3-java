package paketak.admin.zerbitzuak;

import paketak.admin.modeloak.Banatzailea;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Filter {

    /**
     * Funtzio honek objetu lista bat pasatuta filter Aldagaieko eremuan komparazioak egiten ditu, hemen komprobatzen du,
     * filter eremuko aldagaiak kontenitzen duen bilatzailea aldagaieko baloa, hau orrela bada emaitza zerendara gehitzen da eta
     * bueltatzen du. Filtratzeko erabiliko den izena BantzaileaId bada contains beharrean berdina den konprobatuko du, zehatzagoa izan dedin.
     * <strong>Importatea: hemandoko objetuak geter metodo publiko eduki behar du bestela errorea emango du</strong>
     * @param objetuak Lista bat operazioa egiteko erabiliko diren objetuekin
     * @param filter Filtrotzeko erabiliko den aldagaiaren izena
     * @param bilatzailea Bilatuko den balioa
     * @return bilatzailea aldagaiaren balioa duen objetuak
     */
    public static <T> ArrayList<T> filtratu(ArrayList<T> objetuak, String filter, String bilatzailea){
    Class<?> clasea = objetuak.getFirst().getClass();
    ArrayList<T> emaitza = new ArrayList<>();

    Method method = null;

    try {
        method = clasea.getMethod("get" + filter);
    } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
    }

        try {
            for (T objetua : objetuak) {
                Object fieldValueObj = method.invoke(objetua);
                String fieldValue = null;
                if (fieldValueObj instanceof Integer) {
                    fieldValue = ((Integer) fieldValueObj).toString();
                } else if (fieldValueObj instanceof String) {
                    fieldValue = (String) fieldValueObj;
                } else if (fieldValueObj instanceof Date) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    fieldValue = formatter.format((Date) fieldValueObj);
                }
                if(filter.equals("BanatzaileaId")){
                    if (fieldValue != null && fieldValue.equals(bilatzailea)) {
                        emaitza.add(objetua);
                    }
                }
                else {
                    if (fieldValue != null && fieldValue.contains(bilatzailea)) {
                        emaitza.add(objetua);
                    }
                }

            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    return emaitza;
    }
}
