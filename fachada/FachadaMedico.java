/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.fachada;

import br.ufrpe.negocio.Cliente;
import br.ufrpe.negocio.Consulta;
import br.ufrpe.negocio.Medico;
import br.ufrpe.negocio.NegocioCliente;
import br.ufrpe.negocio.NegocioMedico;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class FachadaMedico {

    private NegocioMedico negocioMedico;
    private NegocioCliente negocioCliente;

    public FachadaMedico() {
        negocioCliente = new NegocioCliente();
        negocioMedico = new NegocioMedico();
    }
    

    public void atualizarConsulta(String data, String hora, String observacao) throws Exception {
        buscarConsulta(negocioCliente.getSelectedCliente(), data, hora).setObservacao(observacao);
    }

    public List<Consulta> apresentarConsultas(String CPF) throws Exception {
        return getMedicoLogado().getNegocioConsulta().recuperarTodos();
    }

    public NegocioMedico getNegocioMedico() {
        return negocioMedico;
    }

    public List<Cliente> recuperarClientes() {
        return getMedicoLogado().getNegocioClientes().recuperarTodos();
    }
    
    public List<Consulta> recuperarConsultas(){
        return getMedicoLogado().getNegocioConsulta().recuperarTodos();
    }
    
    public Consulta buscarConsulta(Cliente cliente, String data, String hora) throws Exception {
        String dataHora = data + " " + hora;
        Consulta c = cliente.getNegocioConsulta().recuperar(dataHora);
        return c;
    }

    public void autenticarMedico(String login, String senha) throws Exception {
        negocioMedico.autenticarMedico(login, senha);
    }

    public void setMedicoLogado(Medico medico) {
        negocioMedico.setMedicoLogado(medico);
    }

    public Medico getMedicoLogado() {
        return negocioMedico.getMedicoLogado();
    }

}
