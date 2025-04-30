module com.example.ahmed {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ahmed to javafx.fxml;
    exports com.example.ahmed;
}