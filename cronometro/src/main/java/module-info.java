module com.psp.cronometro {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.psp.cronometro to javafx.fxml;
    exports com.psp.cronometro;
}
