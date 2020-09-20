/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.ui.controladores.telas.medico;

import br.ufrpe.fachada.FachadaAtendente;
import br.ufrpe.fachada.FachadaMedico;
import br.ufrpe.negocio.Cliente;
import br.ufrpe.negocio.Consulta;
import br.ufrpe.negocio.Medico;
import br.ufrpe.ui.TelaInicial;
import br.ufrpe.ui.telas.medico.TelaConsultaMedico;
import java.net.URL;
import java.util.ResourceBundle;
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
public class TelaMedicoFXMLController implements Initializable {

    private FachadaAtendente fachadaAtendente;
    private FachadaMedico fachadaMedico;
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
    private Button bSair;
    @FXML
    private Button visualizarConsultaB;
    @FXML
    private Button pesquisaB;
    @FXML
    private TextField pesquisaTxt;
    @FXML
    private TableView<Consulta> tabelaConsultas;
    @FXML
    private TableColumn<Consulta, String> dataCol;
    @FXML
    private TableColumn<Consulta, String> horaCol;
    @FXML
    private TableColumn<Consulta, String> nomeClienteCol;
    @FXML
    private TableColumn<Consulta, String> descricaoCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fachadaMedico = new FachadaMedico();
        fachadaAtendente = new FachadaAtendente();

        //Clientes
        nomeCCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpfCCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cpfCCol.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        telefoneCCol.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        sexoCCol.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        tabelaClientes.setItems(FXCollections.observableArrayList(fachadaMedico.recuperarClientes()));        

        //Consultas
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        horaCol.setCellValueFactory(new PropertyValueFactory<>("hora"));
        nomeClienteCol.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        descricaoCol.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        

        tabelaConsultas.setItems(FXCollections.observableArrayList(fachadaMedico.getMedicoLogado()
                .getNegocioConsulta().recuperarTodos()));
        pesquisaB.setOnMouseClicked((MouseEvent e) -> {
            tabelaConsultas.setItems(pesquisar());
        });
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
            TelaConsultaMedico tela = new TelaConsultaMedico();
            tela.start(new Stage());
            visualizarConsultaB.getScene().getWindow().hide();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um Cliente!!");
        }
    }

    @FXML
    private ObservableList<Consulta> pesquisar() {
        ObservableList<Consulta> consultaPesquisa = FXCollections.observableArrayList();
        for (int i = 0; i < fachadaMedico.recuperarConsultas().size(); i++) {
            if (fachadaMedico.recuperarConsultas().get(i).getData()
                    .toLowerCase().contains(pesquisaTxt.getText().toLowerCase())) {
                consultaPesquisa.add(fachadaMedico.recuperarConsultas().get(i));
            }

        }
        return consultaPesquisa;
    }

}
