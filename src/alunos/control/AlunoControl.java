package alunos.control;

import alunos.entity.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoControl {
    private final List<Aluno> alunos = new ArrayList<>();

    public void adicionar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> pesquisarPorNome(String nome) {
        return alunos.stream().filter(aluno -> aluno.getNome().contains(nome)).collect(Collectors.toList());
    }
}
