package org.cadastro_funcionarios.application_controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // CORREÇÃO: O caminho absoluto garante que o Java encontre o FXML
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org/cadastro_funcionarios/view/CadastroFuncionario.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistema de Cadastro");
        stage.setScene(scene);
        stage.show();
    }
    // ... main method
}