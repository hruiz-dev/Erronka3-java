package paketak.admin.zerbitzuak;

import javafx.scene.control.Alert;

public class Komponenteak {

    /**
     * Funtzio honek arlet motako panel bat sortzen du
     * @param title alertaren titulua
     * @param head  alertaren goiburua
     * @param content alertaren edukia
     * @return sortutako alerta
     */
    public static Alert sortuAlerta(String title, String head, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(head);
        alert.setContentText(content);
        return alert;

    }
}
