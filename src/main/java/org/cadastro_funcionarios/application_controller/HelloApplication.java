package org.cadastro_funcionarios.application_controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL; // Adicionando import para URL

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Tentativa 1: O caminho que teoricamente deveria funcionar (com base no pacote)
        URL fxmlUrl = getClass().getResource("/org/cadastro_funcionarios/view/CadastroFuncionario.fxml");

        // Se a Tentativa 1 falhar, tentamos o caminho relativo ao ClassLoader (mais direto)
        if (fxmlUrl == null) {
            // Este caminho funciona se a pasta 'view' for a primeira subpasta da raiz de recursos
            fxmlUrl = getClass().getResource("/view/CadastroFuncionario.fxml");
        }

        // Se mesmo assim for null, lançamos um erro claro:
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