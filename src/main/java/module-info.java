module no.ntnu.idatg2001 {
    requires javafx.controls;
    requires javafx.fxml;

    opens no.ntnu.idatg2001 to javafx.fxml;
    exports no.ntnu.idatg2001;
}