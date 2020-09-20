/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.fachada;

import br.ufrpe.negocio.Atendente;
import br.ufrpe.negocio.Cliente;
import br.ufrpe.negocio.Consulta;
import br.ufrpe.negocio.Especialidade;
import br.ufrpe.negocio.Medico;
import br.ufrpe.negocio.NegocioAtendente;
import br.ufrpe.negocio.NegocioCliente;
import br.ufrpe.negocio.NegocioMedico;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class FachadaAtendente {

    private NegocioCliente negocioCliente = new NegocioCliente();
    private NegocioMedico negocioMedico = new NegocioMedico();
    private NegocioAtendente negocioAtendente = new NegocioAtendente();

    public void cadastrarAtendente(String nome, String cpf, Date dataNascimento, String telefone) throws Exception {
        Atendente atendente = new Atendente(nome, cpf, dataNascimento, telefone);
        negocioAtendente.inserir(atendente);
    }

    public Atendente autenticarAtendente(String login, String senha) throws Exception {
        Atendente a;
        a = negocioAtendente.autenticar(login, senha);
        return a;
    }

    public void cadastrarMedico(String login, String senha, Especialidade especialidade, String nome, String cpf, Date dataNascimento, String telefone) throws Exception {
        Medico medico = new Medico(login, senha, especialidade, nome, cpf, dataNascimento, telefone);
        negocioMedico.inserir(medico);
    }

    public void alterarMedico(String login, String senha, Especialidade especialidade, String nome, String cpf, Date dataNascimento, String telefone, String cpfAnt) throws Exception {
        Medico medico = new Medico(login, senha, especialidade, nome, cpf, dataNascimento, telefone);
        negocioMedico.alterar(medico, cpfAnt);
    }

    public Medico buscarMedico(String cpf) throws Exception {
        Medico m = negocioMedico.recuperar(cpf);
        return m;
    }

    public List<Medico> recuperarMedicos() {
        return negocioMedico.recuperarTodos();
    }

    public void cadastrarCliente(String nome, String cpf, Date dataAniversario, String telefone, String sexo) throws Exception {
        Cliente cliente = new Cliente(nome, cpf, dataAniversario, telefone, sexo);
        negocioCliente.inserir(cliente);
    }

    public void alterarCliente(String nome, String cpf, Date dataAniversario, String telefone, String cpfAnt, String sexo) throws Exception {
        Cliente cliente = new Cliente(nome, cpf, dataAniversario, telefone, sexo);
        negocioCliente.alterar(cliente, cpfAnt);
    }

    public Cliente buscarCliente(String cpf) throws Exception {
        Cliente c = negocioCliente.recuperar(cpf);
        return c;
    }

    public List<Cliente> recuperarClientes() {
        return negocioCliente.recuperarTodos();
    }

    public Cliente getSelectedCliente() {
        return negocioCliente.getSelectedCliente();
    }

    public void setSelectedCliente(Cliente cliente) {
        negocioCliente.setSelectedCliente(cliente);
    }

    public void cadastrarConsulta(Cliente cliente, String descricao, Medico medico, String data, String hora) throws Exception {
        cliente.getNegocioConsulta().inserir(descricao, medico, data, hora, cliente.getNome());
        medico.getNegocioConsulta().inserir(descricao, medico, data, hora, cliente.getNome());
        if (medico.getNegocioClientes().recuperarTodos().contains(cliente)) {}
        else {
            medico.getNegocioClientes().inserir(cliente);
        }
    }

    public void alterarConsulta(Cliente cliente, String descricao, Medico medico, String data, String dataAnt, String hora) throws Exception {
        cliente.getNegocioConsulta().alterar(descricao, medico, data, hora, cliente.getNome());
        medico.getNegocioConsulta().alterar(descricao, medico, data, hora, cliente.getNome());
    }

    public Consulta buscarConsulta(Cliente cliente, String data, String hora) throws Exception {
        String dataHora = data + " " + hora;
        Consulta c = cliente.getNegocioConsulta().recuperar(dataHora);
        return c;
    }

    public void salvarDados() {
        try {
            negocioCliente.salvarDados();
            negocioMedico.salvarDados();
            negocioAtendente.salvarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao Tenta Salva os Dados!");
        }
    }

    public void carregarDados() throws IOException, ClassNotFoundException {
        negocioMedico.carregarDados();
        negocioCliente.carregarDados();
        negocioAtendente.carregarDados();
    }

}
