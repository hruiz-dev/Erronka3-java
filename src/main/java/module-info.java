module paketak.admin.kudetzaileakapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    exports paketak.admin;
    opens paketak.admin to javafx.fxml;

    exports paketak.admin.kontrolatzaileak;
    opens paketak.admin.kontrolatzaileak to javafx.fxml;

    opens paketak.admin.modeloak to javafx.base;
}