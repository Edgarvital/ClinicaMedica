/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.cadastro.atendente;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.negocio.Cliente;
import br.ufrpe.ui.telas.atendente.TelaAtendente;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Edgar Vinicius
 */
public class CadastroClienteFXMLController implements Initializable {

    private FachadaAtendente fachada = new FachadaAtendente();
    @FXML
    private TextField nomeTxt;
    @FXML
    private TextField cpfTxt;
    @FXML
    private TextField telefoneTxt;
    @FXML
    private DatePicker dataDate;
    @FXML
    private Button cadastrarB;
    @FXML
    private ChoiceBox<String> sexoCB;
    String[] sexo = {"Masculino", "Feminino"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexoCB.setValue("Masculino");
        sexoCB.setItems(FXCollections.observableArrayList(sexo));
        // TODO
    }

    @FXML
    private void CancelarCadastro(ActionEvent event) {
    }

    @FXML
    private void Cadastrar(ActionEvent event) {
        try {
            Date date = java.sql.Date.valueOf(dataDate.getValue());
            fachada.cadastrarCliente(nomeTxt.getText(), cpfTxt.getText(), 
                    date, telefoneTxt.getText(), sexoCB.getValue());            
            JOptionPane.showMessageDialog(null, "Cliente Cadastrado Com Sucesso!");
            TelaAtendente tela = new TelaAtendente();
            cadastrarB.getScene().getWindow().hide();
            tela.start(new Stage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente Já Existe no Sistema ou Informações Invalidas!");
        }
    }

}
