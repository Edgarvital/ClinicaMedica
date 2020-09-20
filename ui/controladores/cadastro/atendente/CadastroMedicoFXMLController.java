/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.cadastro.atendente;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.negocio.Especialidade;
import br.ufrpe.ui.telas.atendente.TelaAtendente;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Edgar Vinicius
 */
public class CadastroMedicoFXMLController implements Initializable {

    private FachadaAtendente fachada = new FachadaAtendente();
    @FXML
    private TextField login;
    @FXML
    private TextField nome;
    @FXML
    private TextField cpf;
    @FXML
    private TextField telefone;
    @FXML
    private PasswordField senha;
    @FXML
    private DatePicker dataNascimento;
    @FXML
    private ComboBox<Especialidade> especialidadeCB;
    @FXML
    private Button cadastrarB;
    @FXML
    private Button cancelarB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        especialidadeCB.setItems(FXCollections.observableArrayList(Especialidade.values()));
        especialidadeCB.setValue(Especialidade.CARDIO);
        // TODO
    }

    @FXML
    private void cadastrar(ActionEvent event) {
        try {
            Date date = java.sql.Date.valueOf(dataNascimento.getValue());
            fachada.cadastrarMedico(login.getText(), senha.getText(), especialidadeCB.getValue(), nome.getText(), cpf.getText(), date, telefone.getText());
            JOptionPane.showMessageDialog(null, "Medico Cadastrado Com Sucesso!");
            cadastrarB.getScene().getWindow().hide();
            TelaAtendente telaAtendente = new TelaAtendente();
            telaAtendente.start(new Stage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Medico Já Cadastrado no Sistema ou Informação Invalida!!");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        cancelarB.getScene().getWindow().hide();
    }

}
