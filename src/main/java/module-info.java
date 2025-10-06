module org.cadastro_funcionarios {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;


    exports org.cadastro_funcionarios.application_controller;


    opens org.cadastro_funcionarios.application_controller to javafx.fxml;



    opens org.cadastro_funcionarios.model to javafx.fxml;
    opens org.cadastro_funcionarios.service to javafx.fxml;
}