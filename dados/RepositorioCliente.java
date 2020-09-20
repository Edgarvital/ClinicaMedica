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
public class RepositorioCliente implements IRepositorio<Cliente>, Serializable {

    private List<Cliente> clientes;
    private static final long serialVersionUID = 1L;
    private static final RepositorioCliente repositorio = new RepositorioCliente();
    private Cliente selectedCliente;

    private RepositorioCliente() {
        clientes = new ArrayList();
        selectedCliente = null;
    }

    public void inserir(Cliente cliente) {
        clientes.add(cliente);
    }

    public void alterar(Cliente cliente, String cpf) {
        Cliente c = recuperar(cpf);
        clientes.remove(c);
        clientes.add(cliente);
    }

    public Cliente recuperar(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public void excluir(Cliente t) {
        clientes.remove(t);
    }

    public List<Cliente> recuperarTodos() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public static RepositorioCliente getRepositorio() {
        return repositorio;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public void salvarDados() throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream("./src//br/ufrpe/dados/persistencia/clientes.dat");
        ObjectOutputStream os = new ObjectOutputStream(file);
        os.writeObject(recuperarTodos());
        os.close();
    }

    public void carregarDados() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("./src//br/ufrpe/dados/persistencia/clientes.dat");
        ObjectInputStream is = new ObjectInputStream(file);
        if (file != null) {
            List<Cliente> clientes = (List<Cliente>) is.readObject();
            setClientes(clientes);
        }
        is.close();
    }

}
