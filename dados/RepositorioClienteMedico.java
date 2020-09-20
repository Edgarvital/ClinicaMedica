/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.dados;

import br.ufrpe.negocio.Cliente;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edgar Vinicius
 */
public class RepositorioClienteMedico implements IRepositorio<Cliente>, Serializable{
    private List<Cliente> clientes;
    private static final long serialVersionUID = 1L;
    public RepositorioClienteMedico(){
        clientes = new ArrayList();
    }

    public void inserir(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente recuperar(String cpf) {
        for(Cliente c : clientes){
            if(c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    public List<Cliente> recuperarTodos() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public void salvarDados() throws FileNotFoundException, IOException {
    }    

    @Override
    public void carregarDados() throws IOException, ClassNotFoundException {
    }

    @Override
    public void alterar(Cliente t, String info) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Cliente t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
