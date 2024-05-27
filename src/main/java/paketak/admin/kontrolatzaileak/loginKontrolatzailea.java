package paketak.admin.kontrolatzaileak;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import paketak.admin.Main;
import paketak.admin.modeloak.Paketea;

import java.util.ArrayList;

public class loginKontrolatzailea {

    @FXML
    private TextField erabiltzaileaText;
    @FXML
    private PasswordField pasahitzaText;

    /**
     * Metodo honek textfield-etan sartutako erabiltzailea eta pasahitza konprobatzen du.
     * eta hauek zuzenak badira panel prinzipala kargatu
     */
    public void hasiSaioa() {
        if (erabiltzaileaText.getText().equals("admin") && pasahitzaText.getText().equals("admin")) {
            System.out.println("Saioa hasi da");

            Main a = new Main();
            a.changeScene("dashboard.fxml");

        } else {
            System.out.println("Erabiltzaile edo pasahitza okerra");
        }
    }

    public void enterSakatu(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            hasiSaioa();
        }
    }
}
