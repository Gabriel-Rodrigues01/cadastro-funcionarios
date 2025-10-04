module org.cadastro_funcionarios {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.cadastro_funcionarios.application_controller to javafx.fxml;

    exports org.cadastro_funcionarios.application_controller;
    exports org.cadastro_funcionarios.model;
    exports org.cadastro_funcionarios.repository;
    exports org.cadastro_funcionarios.service;
    exports org.cadastro_funcionarios.util;
}
