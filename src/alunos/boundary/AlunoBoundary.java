package alunos.boundary;

import alunos.control.AlunoControl;
import alunos.entity.Aluno;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class AlunoBoundary extends Application {
    private final AlunoControl alunoControl = new AlunoControl();

    private final Label lblId = new Label("Id:");
    private final Label lblRa = new Label("Ra:");
    private final Label lblNome = new Label("Nome:");
    private final Label lblNasc = new Label("Nascimento:");

    private final TextField txtId = new TextField("qwe");
    private final TextField txtRa = new TextField("qwe");
    private final TextField txtNome = new TextField("qwe");
    private final TextField txtNasc = new TextField("08/11/1999");

    private final Button btnAdicionar = new Button("Adicionar");
    private final Button btnPesquisar = new Button("Pesquisar");

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(300, 150);
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
        btnAdicionar.setOnAction(this::handle);
        btnPesquisar.relocate(70, 120);
        btnPesquisar.setOnAction(this::handle);

        pane.getChildren().addAll(lblId, lblNasc, lblNome, lblRa, txtId, txtNasc, txtNome, txtRa, btnAdicionar, btnPesquisar);

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
                aluno.setId(txtId.getText());
                aluno.setRa(txtRa.getText());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                aluno.setNascimento(LocalDate.parse(txtNasc.getText(), formatter));

                alunoControl.adicionar(aluno);
                break;

            case "Pesquisar":
                List<Aluno> alunos = alunoControl.pesquisarPorNome(txtNome.getText());
                System.out.println("=====================================================================");
                for (Aluno value : alunos) System.out.println(value.getNome());
                System.out.println("=====================================================================");
                break;
        }
    }

    public void entityToBoundary(Aluno aluno) {

    }

    public void boundaryToEntity() {

    }
}
