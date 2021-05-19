package alunos.entity;

import java.time.LocalDate;

public class Aluno {
    private long Id;
    private String Ra;
    private String Nome;
    private LocalDate Nascimento;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getRa() {
        return Ra;
    }

    public void setRa(String ra) {
        Ra = ra;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public LocalDate getNascimento() {
        return Nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        Nascimento = nascimento;
    }
}
