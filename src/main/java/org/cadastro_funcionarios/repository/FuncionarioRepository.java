package org.cadastro_funcionarios.repository;

import org.cadastro_funcionarios.model.Endereco;
import org.cadastro_funcionarios.model.Funcionario;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class FuncionarioRepository {
    private static final String FILE_PATH = "funcionarios.csv";
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public FuncionarioRepository() {
        carregarDados();
    }

    // Operação básica: Adicionar (usada pelo Service)
    public void adicionar(Funcionario f) {
        funcionarios.add(f);
        salvarDados();
    }

    // Operação básica: Listar (usada pelo Service/Controller)
    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    // Operação básica: Buscar por Matrícula
    public Optional<Funcionario> buscarPorMatricula(String matricula) {
        return funcionarios.stream()
                .filter(f -> f.getMatricula().equals(matricula))
                .findFirst();
    }

    // Operação básica: Excluir por Matrícula
    public boolean excluir(String matricula) {
        boolean removido = funcionarios.removeIf(f -> f.getMatricula().equals(matricula));
        if (removido) {
            salvarDados();
        }
        return removido;
    }

    // --- PERSISTÊNCIA ---

    private void carregarDados() {
        if (!Files.exists(Paths.get(FILE_PATH))) return;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
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
        }
    }

    private void salvarDados() {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            for (Funcionario f : funcionarios) {
                bw.write(f.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no CSV: " + e.getMessage());
        }
    }
}