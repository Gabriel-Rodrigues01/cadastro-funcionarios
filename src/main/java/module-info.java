module org.cadastro_funcionarios {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.cadastro_funcionarios to javafx.fxml;
    exports org.cadastro_funcionarios;
    exports org.cadastro_funcionarios.application_controller;
    opens org.cadastro_funcionarios.application_controller to javafx.fxml;
}