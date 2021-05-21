package alunos.boundary;

import alunos.control.AlunoControl;
import alunos.entity.Aluno;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AlunoBoundary extends Application {
    private final AlunoControl alunoControl = new AlunoControl();

    private final Label lblId = new Label("Id:");
    private final Label lblRa = new Label("Ra:");
    private final Label lblNome = new Label("Nome:");
    private final Label lblNasc = new Label("Nascimento:");

    private final TextField txtId = new TextField();
    private final TextField txtRa = new TextField();
    private final TextField txtNome = new TextField();
    private final DatePicker txtNasc = new DatePicker(LocalDate.now());

    private final Button btnAdicionar = new Button("Adicionar");
    private final Button btnPesquisar = new Button("Pesquisar");

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(300, 700);
        Scene scene = new Scene(pane);

        lblId.relocate(0, 5);
        lblRa.relocate(0, 30);
        lblNome.relocate(0, 55);
        lblNasc.relocate(0, 80);

        txtId.relocate(100, 0);
        txtRa.relocate(100, 25);
        txtNome.relocate(100, 50);
        txtNasc.relocate(100, 75);

        btnAdicionar.relocate(0, 120);
        btnPesquisar.relocate(70, 120);

        btnAdicionar.setOnAction(this::handle);
        btnPesquisar.setOnAction(this::handle);

        Bindings.bindBidirectional(txtId.textProperty(), alunoControl.id);
        Bindings.bindBidirectional(txtRa.textProperty(), alunoControl.ra);
        Bindings.bindBidirectional(txtNome.textProperty(), alunoControl.nome);
        Bindings.bindBidirectional(alunoControl.nasc, txtNasc.valueProperty(), new LocalDateStringConverter());

        alunoControl.generateTable();
        alunoControl.table.relocate(0,150);

        pane.getChildren().addAll(lblId, lblNasc, lblNome, lblRa, txtId, txtNasc, txtNome, txtRa, btnAdicionar, btnPesquisar, alunoControl.table);

        stage.setScene(scene);
        stage.setTitle("Gestão de Alunos");
        stage.show();
    }

    public void handle(ActionEvent event) {
        String evento = ((Button) event.getSource()).getText();

        switch (evento) {
            case "Adicionar":
                Aluno aluno = new Aluno();
                aluno.setNome(txtNome.getText());
                aluno.setId(Long.parseLong(txtId.getText()));
                aluno.setRa(txtRa.getText());
                aluno.setNascimento(txtNasc.getValue());

                alunoControl.adicionar(aluno);
                break;

            case "Pesquisar":
                List<Aluno> alunos = alunoControl.pesquisarPorNome(txtNome.getText());
                for (Aluno value : alunos) {
                }
                break;
        }
    }

    public void entityToBoundary(Aluno aluno) {

    }

    public Aluno boundaryToEntity() {
        return new Aluno();
    }
}
