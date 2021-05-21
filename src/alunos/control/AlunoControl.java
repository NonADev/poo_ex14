package alunos.control;

import alunos.entity.Aluno;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlunoControl {

    private final ObservableList<Aluno> alunos = FXCollections.observableArrayList();
    public final TableView table = new TableView();

    public final StringProperty id = new SimpleStringProperty();
    public final StringProperty nome = new SimpleStringProperty();
    public final StringProperty ra = new SimpleStringProperty();
    public final StringProperty nasc = new SimpleStringProperty();

    public void adicionar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> pesquisarPorNome(String nome) {
        return alunos.stream().filter(aluno -> aluno.getNome().contains(nome)).collect(Collectors.toList());
    }

    public List<Aluno> getTable() {
        return alunos;
    }

    public void generateTable() {
        TableColumn<Aluno, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("id"));
        TableColumn<Aluno, String> colNome = new TableColumn<>("NOME");
        colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        TableColumn<Aluno, String> colNasc = new TableColumn<>("NASCIMENTO");
        colNasc.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nascimento"));

        table.getColumns().addAll(colId, colNome, colNasc);

        table.setItems(alunos);

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    setEntity(novo);
                }
        );
    }

    public void setEntity(Object alunoObj) {
        Aluno aluno = (Aluno) alunoObj;
        this.id.setValue(""+aluno.getId());
        this.nasc.setValue(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(aluno.getNascimento()));
        this.nome.setValue(aluno.getNome());
        this.ra.setValue(aluno.getRa());
    }
}
