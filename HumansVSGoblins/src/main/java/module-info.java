module com.project.humansvsgoblins {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.humansvsgoblins to javafx.fxml;
    exports com.project.humansvsgoblins;
}