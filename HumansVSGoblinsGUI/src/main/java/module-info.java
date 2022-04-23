module org.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;

    opens org.project to javafx.fxml;
    exports org.project;
}