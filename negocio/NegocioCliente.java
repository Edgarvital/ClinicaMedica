/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.negocio;

import br.ufrpe.dados.RepositorioCliente;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Edgar Vinicius
 */
public class NegocioCliente {
    private RepositorioCliente repositorio;   

    public NegocioCliente() {
        this.repositorio = RepositorioCliente.getRepositorio();
    }

    public void inserir(Cliente cliente) throws Exception {
        for (Cliente c : repositorio.recuperarTodos()) {
            if (c.getCpf().equals(cliente.getCpf()) && cliente != null) {
                throw new Exception("Cliente Já Existe no Sistema!");
            }
        }
        repositorio.inserir(cliente);
    }
    
    public Cliente recuperar(String cpf) throws Exception{
        Cliente c = repositorio.recuperar(cpf);
        if(c.equals(null)){
            throw new Exception("Cliente Inexistente!");
        }
        return c;
    }
    
    public void alterar(Cliente c, String cpf) throws Exception{
        if(c != null && cpf != null && repositorio.recuperar(cpf) != null){            
            repositorio.alterar(c, cpf);
        }
        else {
            throw new Exception("Cpf ou Cliente invalido!");
        }
    }

    public void excluir(Cliente cliente) {
    }
    
    public Cliente getSelectedCliente(){
        return repositorio.getSelectedCliente();
    }
    
    public void setSelectedCliente(Cliente cliente){
        repositorio.setSelectedCliente(cliente);
    }

    public List recuperarTodos() {
        List<Cliente> lista = repositorio.recuperarTodos();
        return lista;
    }
    
    public void salvarDados() throws IOException{
        repositorio.salvarDados();
    }
    public void carregarDados() throws IOException, ClassNotFoundException {
        repositorio.carregarDados();
    }

}
