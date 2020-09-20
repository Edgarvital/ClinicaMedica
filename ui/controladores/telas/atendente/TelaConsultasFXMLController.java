/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.telas.atendente;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.negocio.Consulta;
import br.ufrpe.negocio.Medico;
import br.ufrpe.ui.telas.atendente.TelaAtendente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Edgar Vinicius
 */
public class TelaConsultasFXMLController implements Initializable {

    FachadaAtendente fachadaAtendente;
    @FXML
    private TableColumn<Consulta, String> dataCol;
    @FXML
    private TableColumn<Consulta, String> horaCol;
    @FXML
    private TableColumn<Consulta, Medico> medicoCol;
    @FXML
    private TableColumn<Consulta, String> descricaoCol;
    @FXML
    private Button observacoesB;
    @FXML
    private TableView<Consulta> tabelaConsultas;
    @FXML
    private Button voltarB;
    @FXML
    private TableColumn<Consulta, String> observacaoCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachadaAtendente = new FachadaAtendente();
        //Consulta
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        horaCol.setCellValueFactory(new PropertyValueFactory<>("hora"));
        medicoCol.setCellValueFactory(new PropertyValueFactory<>("medico"));
        descricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        observacaoCol.setCellValueFactory(new PropertyValueFactory<>("observacao"));

        tabelaConsultas.setItems(FXCollections.observableArrayList(fachadaAtendente.getSelectedCliente()
                .getNegocioConsulta().recuperarTodos()));
    }    

    @FXML
    private void exibirObservacoes(ActionEvent event) {
        String observacao = tabelaConsultas.getSelectionModel().getSelectedItem().getObservacao();
        JOptionPane.showMessageDialog(null, observacao);
    }

    @FXML
    private void voltar(ActionEvent event) throws Exception {
        TelaAtendente tela = new TelaAtendente();
        tela.start(new Stage());
        voltarB.getScene().getWindow().hide();
    }
    
}
