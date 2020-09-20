/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.telas.atendente;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.negocio.Cliente;
import br.ufrpe.negocio.Especialidade;
import br.ufrpe.negocio.Medico;
import br.ufrpe.ui.TelaInicial;
import br.ufrpe.ui.telas.atendente.TelaConsultaAtendente;
import br.ufrpe.ui.telas.cadastros.atendente.TelaCadastroCliente;
import br.ufrpe.ui.telas.cadastros.atendente.TelaCadastroConsulta;
import br.ufrpe.ui.telas.cadastros.atendente.TelaCadastroMedico;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Edgar Vinicius
 */
public class TelaAtendenteFXMLController implements Initializable {

    private FachadaAtendente fachadaAtendente;
    @FXML
    private TableView<Medico> tabelaMedicos;
    @FXML
    private TableColumn<Medico, String> nomeMCol;
    @FXML
    private TableColumn<Medico, String> cpfMCol;
    @FXML
    private TableColumn<Medico, String> telefoneMCol;
    @FXML
    private TableColumn<Medico, Especialidade> especialidadeCol;
    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TableColumn<Cliente, String> nomeCCol;
    @FXML
    private TableColumn<Cliente, String> cpfCCol;
    @FXML
    private TableColumn<Cliente, String> telefoneCCol;
    @FXML
    private TableColumn<Cliente, String> sexoCCol;
    @FXML
    private Button cadastrarClienteB;
    @FXML
    private Button cadastrarMedicoB;
    @FXML
    private Button cadastrarConsultaB;
    @FXML
    private Button bSair;
    @FXML
    private Button visualizarConsultaB;
    @FXML
    private Button pesquisaB;
    @FXML
    private TextField pesquisaTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachadaAtendente = new FachadaAtendente();
        //Medico
        nomeMCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfMCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        telefoneMCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        especialidadeCol.setCellValueFactory(new PropertyValueFactory<>("especialidade"));

        tabelaMedicos.setItems(FXCollections.observableArrayList(fachadaAtendente.recuperarMedicos()));

        //Cliente
        nomeCCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfCCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cpfCCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        telefoneCCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        sexoCCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        tabelaClientes.setItems(FXCollections.observableArrayList(fachadaAtendente.recuperarClientes()));
        pesquisaB.setOnMouseClicked((MouseEvent e) -> {
            tabelaClientes.setItems(pesquisar());
        });

    }

    @FXML
    private void cadastrarCliente(ActionEvent event) throws Exception {
        TelaCadastroCliente cadastroCliente = new TelaCadastroCliente();
        cadastroCliente.start(new Stage());
        cadastrarClienteB.getScene().getWindow().hide();
    }

    @FXML
    private void cadastrarMedico(ActionEvent event) throws Exception {
        TelaCadastroMedico cadastroMedico = new TelaCadastroMedico();
        cadastroMedico.start(new Stage());
        cadastrarMedicoB.getScene().getWindow().hide();
    }

    @FXML
    private void cadastrarConsulta(ActionEvent event) {
        String cpf;
        cpf = tabelaClientes.getSelectionModel().getSelectedItem().getCpf();
        try {
            Cliente cliente = fachadaAtendente.buscarCliente(cpf);
            fachadaAtendente.setSelectedCliente(cliente);
            TelaCadastroConsulta tela = new TelaCadastroConsulta();
            tela.start(new Stage());
            cadastrarConsultaB.getScene().getWindow().hide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cliente Inexistente!");
        }
    }

    @FXML
    private void sair(ActionEvent event) throws Exception {
        fachadaAtendente.salvarDados();
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.start(new Stage());
        bSair.getScene().getWindow().hide();
    }

    @FXML
    private void visualizarConsulta(ActionEvent event) {
        try {
            String cpf = tabelaClientes.getSelectionModel().getSelectedItem().getCpf();
            Cliente cliente = fachadaAtendente.buscarCliente(cpf);
            fachadaAtendente.setSelectedCliente(cliente);
            TelaConsultaAtendente tela = new TelaConsultaAtendente();
            tela.start(new Stage());
            visualizarConsultaB.getScene().getWindow().hide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um Cliente!!");
        }

    }

    @FXML
    private ObservableList<Cliente> pesquisar() {
        ObservableList<Cliente> clientePesquisa = FXCollections.observableArrayList();
        for (int i = 0; i < fachadaAtendente.recuperarClientes().size(); i++) {
            if (fachadaAtendente.recuperarClientes().get(i).getNome()
                    .toLowerCase().contains(pesquisaTxt.getText().toLowerCase())) {
                clientePesquisa.add(fachadaAtendente.recuperarClientes().get(i));
            }

        }
        return clientePesquisa;
    }

}
