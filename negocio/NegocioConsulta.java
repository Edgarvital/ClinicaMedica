/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.negocio;

import br.ufrpe.dados.RepositorioConsulta;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Edgar Vinicius
 */
public class NegocioConsulta implements Serializable {

    private RepositorioConsulta repositorio;

    public NegocioConsulta() {
        repositorio = new RepositorioConsulta();
    }

    public void inserir(String descricao, Medico medico, String data, String hora, String nomeCliente) throws Exception {
        for (int i = 0; i < medico.getNegocioConsulta().recuperarTodos().size(); i++) {
            if (medico.getNegocioConsulta().recuperarTodos().get(i).getData().equals(data)
                    && medico.getNegocioConsulta().recuperarTodos().get(i).getHora().equals(hora)) {
                throw new Exception("Horario Ocupado!");
            }
        }
        Consulta consulta = new Consulta(descricao, medico, data, hora, nomeCliente);
        repositorio.inserir(consulta);
    }

    public void alterar(String descricao, Medico medico, String data, String hora, String nomeCliente) throws Exception {
        if (data != null && repositorio.recuperar(data) != null) {
            for (int i = 0; i < medico.getNegocioConsulta().recuperarTodos().size(); i++) {
                if (medico.getNegocioConsulta().recuperarTodos().get(i).getData().equals(data)
                        && medico.getNegocioConsulta().recuperarTodos().get(i).getHora().equals(hora)) {
                    throw new Exception("Horario Ocupado!");
                }
            }
            Consulta c = new Consulta(descricao, medico, data, hora, nomeCliente);
            repositorio.alterar(c, data);
        } else {
            throw new Exception("Data ou Consulta invalido!");
        }
    }

    public Consulta recuperar(String data) throws Exception {
        Consulta m = repositorio.recuperar(data);
        if (m.equals(null)) {
            throw new Exception("Consulta Inexistente!");
        }
        return m;
    }

    public void excluir(Consulta consulta) throws Exception {
    }

    public List<Consulta> recuperarTodos() {
        return repositorio.recuperarTodos();
    }
    
    public void salvarDados() throws IOException{
        repositorio.salvarDados();
    }
    public void carregarDados() throws IOException, ClassNotFoundException {
        repositorio.carregarDados();
    }

}
