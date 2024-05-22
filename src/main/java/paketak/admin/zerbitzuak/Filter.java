package paketak.admin.zerbitzuak;

import paketak.admin.modeloak.Banatzailea;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Filter {

    /**
     * Funtzio honek objetu lista bat pasatuta filter Aldagaieko eremuan komparazioak egiten ditu, hemen komprobatzen du,
     * filter eremuko aldagaiak kontenitzen duen bilatzailea aldagaieko baloa, hau orrela bada emaitza zerendara gehitzen da eta
     * bueltatzen du.
     * <strong>Importatea: hemandoko objetuak geter metodo publiko eduki behar du bestela errorea emango du</strong>
     * @param objetuak Lista bat operazioa egiteko erabiliko diren objetuekin
     * @param filter Filtrotzeko erabiliko den aldagaiaren izena
     * @param bilatzailea Bilatuko den balioa
     * @return bilatzailea aldagaiaren balioa duen objetuak
     */
    public static <T> ArrayList<T> filtratu(ArrayList<T> objetuak, String filter, String bilatzailea){
        Class<?> clasea = objetuak.get(0).getClass();
        ArrayList<T> emaitza = new ArrayList<>();

        Method method = null;

        try {
            method = clasea.getMethod("get" + filter);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            for (T objetua : objetuak) {
                String fieldValue = (String) method.invoke(objetua);
                if (fieldValue.contains(bilatzailea)) {
                    emaitza.add(objetua);
                }

            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return emaitza;
    }
}
