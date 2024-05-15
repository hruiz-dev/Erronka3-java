module paketak.admin.kudetzaileakapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    exports paketak.admin;
    opens paketak.admin to javafx.fxml;
    exports paketak.admin.kontrolatzaileak;
    opens paketak.admin.kontrolatzaileak to javafx.fxml;
}