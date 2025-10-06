package org.cadastro_funcionarios.application_controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.cadastro_funcionarios.model.Endereco;
import org.cadastro_funcionarios.model.Funcionario;
import org.cadastro_funcionarios.repository.FuncionarioRepository;
import org.cadastro_funcionarios.service.FuncionarioService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioController {

    // --- Injeção de Componentes FXML (Nomes devem ser idênticos aos fx:id do FXML) ---

    // Feedback (CORREÇÃO do NullPointer: usar statusLabel)
    @FXML
    private Label statusLabel;

    // DADOS PESSOAIS
    @FXML private TextField txtMatricula;
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private DatePicker dpDataNascimento;
    @FXML private TextField txtCargo;
    @FXML private TextField txtSalario;
    @FXML private DatePicker dpDataContratacao;

    // ENDEREÇO
    @FXML private TextField txtLogradouro;
    @FXML private TextField txtNumero;
    @FXML private TextField txtComplemento;
    @FXML private TextField txtBairro;
    @FXML private TextField txtCidade;
    @FXML private TextField txtEstado;
    @FXML private TextField txtCep;

    // --- Camada Service ---
    private FuncionarioService funcionarioService;

    /**
     * Inicializa o Service e Repositório.
     * Este método é chamado automaticamente pelo FXMLLoader após a injeção dos componentes @FXML.
     */
    public void initialize() {
        // Injeção manual de dependência (sem framework)
        FuncionarioRepository repository = new FuncionarioRepository();
        this.funcionarioService = new FuncionarioService(repository);
        // Exemplo de como carregar dados ao iniciar:
        // System.out.println("Funcionários carregados: " + repository.listarTodos().size());
    }

    /**
     * Método de ação ligado ao botão "Salvar Novo Funcionário" (onAction="#cadastrarFuncionario").
     */
    @FXML
    protected void cadastrarFuncionario() {
        try {
            // 1. Coleta e Converte os Dados do Endereço
            Endereco endereco = new Endereco(
                    txtLogradouro.getText(), txtNumero.getText(), txtComplemento.getText(),
                    txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtCep.getText()
            );

            // 2. Coleta e Converte os Dados do Funcionário
            // O uso de 'new BigDecimal' e 'dpDataNascimento.getValue()' exige tratamento de exceção.
            Funcionario novoFuncionario = new Funcionario(
                    txtMatricula.getText(),
                    txtNome.getText(),
                    txtCpf.getText(),
                    dpDataNascimento.getValue(),
                    txtCargo.getText(),
                    new BigDecimal(txtSalario.getText()),
                    dpDataContratacao.getValue(),
                    endereco
            );

            // 3. Chama o Service para Validação e Persistência em CSV
            funcionarioService.cadastrar(novoFuncionario);

            // 4. Feedback de Sucesso e Limpeza da Tela
            statusLabel.setText("Sucesso: Funcionário " + novoFuncionario.getNome() + " cadastrado e salvo no CSV!");
            statusLabel.setStyle("-fx-text-fill: green;");
            limparCampos();

        } catch (NumberFormatException e) {
            // Captura erro se o Salário não for um número válido
            statusLabel.setText("Erro: Salário deve ser um valor numérico válido.");
            statusLabel.setStyle("-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {
            // Captura erros de validação (Matrícula, Idade, Salário positivo, etc.)
            statusLabel.setText("Erro de Validação: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            // Captura outros erros inesperados (como data nula)
            statusLabel.setText("Erro inesperado ao salvar: Campos de Data ou Salário não preenchidos.");
            statusLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        // Limpa todos os campos do formulário
        txtMatricula.clear();
        txtNome.clear();
        txtCpf.clear();
        dpDataNascimento.setValue(null);
        txtCargo.clear();
        txtSalario.clear();
        dpDataContratacao.setValue(null);
        txtLogradouro.clear();
        txtNumero.clear();
        txtComplemento.clear();
        txtBairro.clear();
        txtCidade.clear();
        txtEstado.clear();
        txtCep.clear();
    }
}