module org.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.project to javafx.fxml;
    exports org.project;
}