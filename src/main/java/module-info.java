module com.example.school_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.school_project to javafx.fxml;
    exports com.example.school_project;
}