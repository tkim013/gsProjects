module org.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens org.project to javafx.fxml;
    exports org.project;
}