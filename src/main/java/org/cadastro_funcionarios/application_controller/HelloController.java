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
    // ação do botão "Cadastrar"
    @FXML
    protected void onCadastrar() {
        try {
            // criar objeto Funcionario com dados lidos dos campos de texto
            Endereco e = new Endereco();
            e.setCep(tfCep.getText().trim());
            // aqui preenchemos apenas CEP no formulário simples; extensão: preencher logradouro, cidade, etc.

            Funcionario f = new Funcionario();
            f.setMatricula(tfMatricula.getText().trim());
            f.setNome(tfNome.getText().trim());
            f.setCpf(tfCpf.getText().trim());
            f.setDataNascimento(LocalDate.parse(tfDataNasc.getText().trim()));
            f.setCargo(tfCargo.getText().trim());
            f.setSalario(new BigDecimal(tfSalario.getText().trim()));
            f.setDataContratacao(LocalDate.now());
            f.setEndereco(e);

            // delega ao service (fará validações e persistência)
            service.cadastrar(f);

            taOutput.setText("Cadastrado com sucesso: " + f);
        } catch (Exception ex) {
            // mostrar erro amigável ao usuário
            taOutput.setText("Erro ao cadastrar: " + ex.getMessage());
        }
    }

    @FXML
    protected void onExcluir() {
        try {
            String matricula = tfMatricula.getText().trim();
            service.excluir(matricula);
            taOutput.setText("Excluído (se existia): " + matricula);
        } catch (Exception ex) {
            taOutput.setText("Erro ao excluir: " + ex.getMessage());
        }
    }



    @FXML
    protected void onConsultar() {
        try {
            String matricula = tfMatricula.getText().trim();
            Optional<Funcionario> opt = service.consultarPorMatricula(matricula);
            if (opt.isPresent()) {
                taOutput.setText(opt.get().toString());
            } else {
                taOutput.setText("Funcionário não encontrado para matrícula: " + matricula);
            }
        } catch (Exception ex) {
            taOutput.setText("Erro ao consultar: " + ex.getMessage());
        }
    }

    @FXML
    protected void onListarTodos() {
        List<Funcionario> all = service.listarTodos();
        StringBuilder sb = new StringBuilder();
        sb.append("Total: ").append(all.size()).append("\n\n");
        all.forEach(f -> sb.append(f).append("\n"));

        // exemplo: média salarial por cargo (usa lambdas/stream)
        sb.append("\nMédia salarial por cargo:\n");
        ReportUtils.mediaSalarialPorCargo(all).forEach((cargo, media) -> {
            sb.append(cargo).append(" -> R$ ").append(media).append("\n");
        });

        taOutput.setText(sb.toString());
    }

}
