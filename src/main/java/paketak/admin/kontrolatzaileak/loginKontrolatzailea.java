package paketak.admin.kontrolatzaileak;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import paketak.admin.Main;
import paketak.admin.modeloak.Paketea;

import java.util.ArrayList;

public class loginKontrolatzailea {

    @FXML
    private TextField erabiltzaileaText;
    @FXML
    private PasswordField pasahitzaText;

    public void hasiSaioa() {
        if (erabiltzaileaText.getText().equals("admin") && pasahitzaText.getText().equals("admin")) {
            System.out.println("Saioa hasi da");
            ArrayList<Paketea> zerrenda = DashboardKontrolatzailea.getPaketeak();
            Paketea.setPaketeak(zerrenda);

            Main a = new Main();
            a.changeScene("dashboard.fxml");


            for(int i = 0; i<zerrenda.size(); i++){
                System.out.println(zerrenda.get(i).getHartzailea());
            }

        } else {
            System.out.println("Erabiltzaile edo pasahitza okerra");
        }
    }
}
