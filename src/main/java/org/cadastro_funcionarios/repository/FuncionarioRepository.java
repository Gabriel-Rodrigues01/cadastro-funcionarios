package org.cadastro_funcionarios.repository;

import org.cadastro_funcionarios.model.Funcionario;
import org.cadastro_funcionarios.model.Endereco;
import java.io.*;
import java.nio.file.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class FuncionarioRepository {
    // Caminho do arquivo CSV (será criado na raiz do projeto)
    private static final String FILE_PATH = "funcionarios.csv";
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioRepository() {
        carregarDados();
    }

    // Método que o FuncionarioService está tentando chamar (RESOLVE O ÚLTIMO ERRO)
    public void adicionar(Funcionario f) {
        // 1. Adiciona o novo funcionário à lista em memória
        funcionarios.add(f);
        // 2. Salva todos os dados atualizados no arquivo CSV
        salvarDados();
    }

    // Método necessário para o Service (Relatórios e consultas)
    public List<Funcionario> listarTodos() {
        // Retorna uma cópia para proteger a lista interna de modificações externas
        return new ArrayList<>(funcionarios);
    }

    // Método CRUD básico para buscar por Matrícula (Útil para checar duplicidade)
    public Optional<Funcionario> buscarPorMatricula(String matricula) {
        return funcionarios.stream()
                .filter(f -> f.getMatricula().equals(matricula))
                .findFirst();
    }

    // --- MÉTODOS DE PERSISTÊNCIA (LÓGICA INTERNA) ---

    // Carrega todos os funcionários do arquivo CSV para a memória
    private void carregarDados() {
        if (!Files.exists(Paths.get(FILE_PATH))) return;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                // Deve ter 7 campos de Funcionario + 7 campos de Endereco = 14 campos
                if (parts.length < 14) continue;

                // Mapeamento de Endereco (parts[7] a parts[13])
                Endereco endereco = new Endereco(
                        parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13]
                );

                // Mapeamento de Funcionario (parts[0] a parts[6])
                Funcionario f = new Funcionario(
                        parts[0], parts[1], parts[2],
                        LocalDate.parse(parts[3]),
                        parts[4],
                        new BigDecimal(parts[5]),
                        LocalDate.parse(parts[6]),
                        endereco
                );
                funcionarios.add(f);
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Erro ao carregar dados do CSV: " + e.getMessage());
            // Se houver erro, a lista 'funcionarios' ficará vazia ou parcial
        }
    }

    // Salva a lista atual de funcionários para o arquivo CSV
    private void salvarDados() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Funcionario f : funcionarios) {
                // Usa o método toCSV() que implementamos na Model
                bw.write(f.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no CSV: " + e.getMessage());
        }
    }
}