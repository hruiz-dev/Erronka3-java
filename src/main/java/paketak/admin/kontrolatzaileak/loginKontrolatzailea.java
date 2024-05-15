package paketak.admin.kontrolatzaileak;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import paketak.admin.Main;

public class loginKontrolatzailea {

    @FXML
    private TextField erabiltzaileaText;
    @FXML
    private PasswordField pasahitzaText;

    public void hasiSaioa() {
        if (erabiltzaileaText.getText().equals("admin") && pasahitzaText.getText().equals("admin")) {
            System.out.println("Saioa hasi da");
            Main a = new Main();
            a.changeScene("dashboard.fxml");
        } else {
            System.out.println("Erabiltzaile edo pasahitza okerra");
        }
    }
}
