package org.cadastro_funcionarios.repository;


import org.cadastro_funcionarios.model.Endereco;
import org.cadastro_funcionarios.model.Funcionario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public class FuncionarioRepository {
    private final Path filePath;
    private final Map<String, Funcionario> storage = new LinkedHashMap<>();

    public FuncionarioRepository(Path filePath) {
        this.filePath = filePath;
    }

    public void load() throws IOException {
        storage.clear();
        if (Files.notExists(filePath)) {
            // cria arquivo e diretório pai se necessário
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            return;
        }

        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] cols = line.split(";", -1);
                if (cols.length < 14) continue;
                Funcionario f = new Funcionario();
                f.setMatricula(cols[0]);
                f.setNome(cols[1]);
                f.setCpf(cols[2]);
                f.setDataNascimento(cols[3].isEmpty() ? null : LocalDate.parse(cols[3]));
                f.setCargo(cols[4]);
                f.setSalario(cols[5].isEmpty() ? null : new BigDecimal(cols[5]));
                f.setDataContratacao(cols[6].isEmpty() ? null : LocalDate.parse(cols[6]));

                Endereco e = new Endereco();
                e.setLogradouro(cols[7]);
                e.setNumero(cols[8]);
                e.setComplemento(cols[9]);
                e.setBairro(cols[10]);
                e.setCidade(cols[11]);
                e.setEstado(cols[12]);
                e.setCep(cols[13]);

                f.setEndereco(e);

                storage.put(f.getMatricula(), f);
            }
        }
    }



}
