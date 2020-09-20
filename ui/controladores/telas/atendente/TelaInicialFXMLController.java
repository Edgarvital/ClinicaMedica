/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.telas.atendente;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.fachada.FachadaMedico;
import br.ufrpe.ui.telas.atendente.TelaAtendente;
import br.ufrpe.ui.telas.cadastros.atendente.TelaCadastroAtendente;
import br.ufrpe.ui.telas.medico.TelaMedico;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Edgar Vinicius
 */
public class TelaInicialFXMLController implements Initializable {

    private FachadaAtendente fachadaAtendente;
    private FachadaMedico fachadaMedico;

    @FXML
    private TextField loginMedico;
    @FXML
    private PasswordField senhaMedico;
    @FXML
    private TextField loginAtendente;
    @FXML
    private PasswordField senhaAtendente;
    @FXML
    private Button cadastroB;
    @FXML
    private Button loginMedicoB;
    @FXML
    private Button loginAtendenteB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachadaMedico = new FachadaMedico();
        fachadaAtendente = new FachadaAtendente();
        try {
            fachadaAtendente.carregarDados();
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaInicialFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loginMedico(ActionEvent event) {
        try{
            fachadaMedico.autenticarMedico(loginMedico.getText(), senhaMedico.getText());
            TelaMedico tela = new TelaMedico();
            tela.start(new Stage());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Login ou Senha Invalido(s)");
        }
            loginMedicoB.getScene().getWindow().hide();
    }

    @FXML
    private void loginAtendente(ActionEvent event) {
        try {
            fachadaAtendente.autenticarAtendente(loginAtendente.getText(), senhaAtendente.getText());
            TelaAtendente tela = new TelaAtendente();
            tela.start(new Stage());
            loginAtendenteB.getScene().getWindow().hide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login ou Senha Invalido(s)");
        }
    }

    @FXML
    private void cadastroAtendente(ActionEvent event) throws Exception {
        TelaCadastroAtendente telaCadastro = new TelaCadastroAtendente();
        telaCadastro.start(new Stage());
        cadastroB.getScene().getWindow().hide();
    }

}
