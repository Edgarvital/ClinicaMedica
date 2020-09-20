/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.cadastro.atendente;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.ui.TelaInicial;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class CadastroAtendenteFXMLController implements Initializable {

    private FachadaAtendente fachada;
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

    public CadastroAtendenteFXMLController() {
        fachada = new FachadaAtendente();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void CadastrarGerente(ActionEvent event) {    
        try {
            Date date = java.sql.Date.valueOf(dataDate.getValue());
            fachada.cadastrarAtendente(nomeTxt.getText(), cpfTxt.getText(), date, telefoneTxt.getText());
            JOptionPane.showMessageDialog(null, "Gerente Cadastrado Com Sucesso!");
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.start(new Stage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gerente Já Cadastrado no Sistema!");
        }
        cadastrarB.getScene().getWindow().hide();
    }

    @FXML
    private void CancelarCadastro(ActionEvent event) {
    }

}
