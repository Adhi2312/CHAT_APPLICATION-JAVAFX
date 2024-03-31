module com.example.chat_application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.chat_application to javafx.fxml;
    exports com.example.chat_application;
}