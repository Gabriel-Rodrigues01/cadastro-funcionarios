package org.cadastro_funcionarios.application_controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL fxmlUrl = getClass().getResource("/org/cadastro_funcionarios/view/CadastroFuncionario.fxml");

        // CAMINHO DIRETO SE A DESGRAÇA DE CIMA NÃO ENTRAR
        if (fxmlUrl == null) {
            fxmlUrl = getClass().getResource("/view/CadastroFuncionario.fxml");
        }

        // SE tmb NÃO ENTRAR VAI DE UM JEITO OU DE OUTRO "FAMOSO TRATAMENTO DE CODIGO FUDIDO"
        if (fxmlUrl == null) {
            System.err.println("ERRO FATAL: Arquivo FXML não encontrado!");
            System.err.println("Verifique se 'CadastroFuncionario.fxml' está em 'src/main/resources/org.cadastro_funcionarios/view/'");
            throw new IllegalStateException("O arquivo 'CadastroFuncionario.fxml' não foi encontrado no Classpath. O caminho está incorreto.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Cadastro de Funcionários");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}