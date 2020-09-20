/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.cadastro.atendente;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.negocio.Especialidade;
import br.ufrpe.negocio.Medico;
import br.ufrpe.ui.telas.atendente.TelaAtendente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Edgar Vinicius
 */
public class CadastroConsultaFXMLController implements Initializable {

    FachadaAtendente fachadaAtendente;
    @FXML
    private TableColumn<Medico, String> nomeCol;
    @FXML
    private TableColumn<Medico, String> cpfCol;
    @FXML
    private TableColumn<Medico, Especialidade> especialidadeCol;
    @FXML
    private Button cancelarB;
    @FXML
    private Button cadastrarB;
    @FXML
    private DatePicker data;
    @FXML
    private TextField descricao;
    @FXML
    private TableView<Medico> tabelaMedicos;
    @FXML
    private TextField hora;
    @FXML
    private Button atualizarB;
    @FXML
    private TextArea datasOcupadas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachadaAtendente = new FachadaAtendente();
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        especialidadeCol.setCellValueFactory(new PropertyValueFactory<>("especialidade"));

        tabelaMedicos.setItems(FXCollections.observableArrayList(fachadaAtendente.recuperarMedicos()));
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void cadastrar(ActionEvent event) {
        String cpf;
        cpf = tabelaMedicos.getSelectionModel().getSelectedItem().getCpf();
        try {
            Medico medico = fachadaAtendente.buscarMedico(cpf);
            fachadaAtendente.cadastrarConsulta(fachadaAtendente.getSelectedCliente(), descricao.getText(), medico, data.getValue().toString(), hora.getText());
            fachadaAtendente.setSelectedCliente(null);
            JOptionPane.showMessageDialog(null, "Consulta Cadastrada com Sucesso!");
            TelaAtendente tela = new TelaAtendente();
            cadastrarB.getScene().getWindow().hide();
            tela.start(new Stage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Selecionou o Médico ou a Data esta Ocupada!");
        }
    }

    @FXML
    private void atualizar(ActionEvent event) {
        String cpf = tabelaMedicos.getSelectionModel().getSelectedItem().getCpf();
        String mensagem = "";
        try {
            Medico medico = fachadaAtendente.buscarMedico(cpf);
            for (int i = 0; i < medico.getNegocioConsulta().recuperarTodos().size(); i++) {
                if (medico.getNegocioConsulta().recuperarTodos().get(i).getData().equals(data.getValue().toString())) {
                    mensagem += medico.getNegocioConsulta().recuperarTodos().get(i).getData() 
                            + " " + medico.getNegocioConsulta().recuperarTodos().get(i).getHora()
                            + "\n";
                }
            }
            datasOcupadas.setText(mensagem);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um Médico!!");
        }
    }

}
