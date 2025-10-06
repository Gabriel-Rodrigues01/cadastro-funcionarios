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


    @FXML
    private Label statusLabel;

    // DADOS PESSOAIS DO FUNCIONARIO
    @FXML private TextField txtMatricula;
    @FXML private TextField txtNome;
    @FXML private TextField txtCpf;
    @FXML private DatePicker dpDataNascimento;
    @FXML private TextField txtCargo;
    @FXML private TextField txtSalario;
    @FXML private DatePicker dpDataContratacao;

    // ENDEREÇO  DO FUNCIONARIO
    @FXML private TextField txtLogradouro;
    @FXML private TextField txtNumero;
    @FXML private TextField txtComplemento;
    @FXML private TextField txtBairro;
    @FXML private TextField txtCidade;
    @FXML private TextField txtEstado;
    @FXML private TextField txtCep;


    private FuncionarioService funcionarioService;

    //INICIALIZA O SERVICEE REPOSITORIO


    public void initialize() {
        FuncionarioRepository repository = new FuncionarioRepository();
        this.funcionarioService = new FuncionarioService(repository);


    }
        //METODO DE AÇÃO LIGADO NO BUTÃO "SALVAR NOVO FUNVIONARIO"
    @FXML
    protected void cadastrarFuncionario() {
        try {

            Endereco endereco = new Endereco(
                    txtLogradouro.getText(), txtNumero.getText(), txtComplemento.getText(),
                    txtBairro.getText(), txtCidade.getText(), txtEstado.getText(), txtCep.getText()
            );

            // COLETA E CONVERTE OS DADOS PARA O USO DO  "new BigDecimal" E "dpDataNascimento"
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
            //VALIDAÇÃO DO CSV
            funcionarioService.cadastrar(novoFuncionario);

            //MENSAGEM DE SUCESSO "DEU CERTO ESSA PORRA"
            statusLabel.setText("Sucesso: Funcionário " + novoFuncionario.getNome() + " cadastrado e salvo no CSV!");
            statusLabel.setStyle("-fx-text-fill: green;");
            limparCampos();

            //MOSTRA TODOS OS ERROS DE ENTRADA
        } catch (NumberFormatException e) {
            statusLabel.setText("Erro: Salário deve ser um valor numérico válido.");
            statusLabel.setStyle("-fx-text-fill: red;");
        } catch (IllegalArgumentException e) {
            statusLabel.setText("Erro de Validação: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
        } catch (Exception e) {
            statusLabel.setText("Erro inesperado ao salvar: Campos de Data ou Salário não preenchidos.");
            statusLabel.setStyle("-fx-text-fill: red;");
            e.printStackTrace();
        }
    }

    //LIMPA OS CAMPOS AO FINAL
    private void limparCampos() {
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