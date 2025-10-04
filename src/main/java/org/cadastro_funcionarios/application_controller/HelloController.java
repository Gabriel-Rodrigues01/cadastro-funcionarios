package org.cadastro_funcionarios.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.cadastro_funcionarios.model.Endereco;
import org.cadastro_funcionarios.model.Funcionario;
import org.cadastro_funcionarios.repository.FuncionarioRepository;
import org.cadastro_funcionarios.service.FuncionarioService;
import org.cadastro_funcionarios.util.ReportUtils;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class HelloController {

    // injetados pelo FXML
    @FXML private TextField tfMatricula;
    @FXML private TextField tfNome;
    @FXML private TextField tfCpf;
    @FXML private TextField tfDataNasc;
    @FXML private TextField tfCargo;
    @FXML private TextField tfSalario;
    @FXML private TextField tfCep;
    @FXML private TextArea taOutput;



    private final FuncionarioService service;

    public HelloController() {
        FuncionarioRepository repo = new FuncionarioRepository(Paths.get("data", "funcionarios.csv"));
        this.service = new FuncionarioService(repo);
        try { service.load();
        } catch (Exception e) {
            System.err.println("Erro ao carregar CSV: " + e.getMessage());
        }
    }


}
