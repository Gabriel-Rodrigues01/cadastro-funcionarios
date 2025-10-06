module org.cadastro_funcionarios {
    // Requisitos de bibliotecas do JavaFX
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base; // Incluído por boa prática

    // Exporte apenas o pacote application_controller
    exports org.cadastro_funcionarios.application_controller;

    // ABRE o pacote application_controller para o javafx.fxml para que ele possa injetar os FXMLs
    opens org.cadastro_funcionarios.application_controller to javafx.fxml;

    // CORREÇÃO: Remova ou comente a linha que tenta abrir o pacote raiz
    // opens org.cadastro_funcionarios to javafx.fxml;

    // O FXML precisa acessar as classes da camada Model e Service, se necessário
    opens org.cadastro_funcionarios.model to javafx.fxml;
    opens org.cadastro_funcionarios.service to javafx.fxml;
}