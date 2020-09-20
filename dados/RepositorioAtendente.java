/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.dados;

import br.ufrpe.negocio.Atendente;
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
public class RepositorioAtendente implements IRepositorio<Atendente>, Serializable {

    private List<Atendente> atendentes;
    private static final long serialVersionUID = 1L;
    private static final RepositorioAtendente repositorio = new RepositorioAtendente();

    private RepositorioAtendente() {
        atendentes = new ArrayList();
    }

    @Override
    public void inserir(Atendente gerente) {
        atendentes.add(gerente);
    }

    @Override
    public void alterar(Atendente gerente, String cpf) {
        Atendente c = recuperar(cpf);
        atendentes.remove(c);
        atendentes.add(gerente);
    }

    @Override
    public Atendente recuperar(String cpf) {
        for (Atendente c : atendentes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void excluir(Atendente t) {
        atendentes.remove(t);
    }

    @Override
    public List<Atendente> recuperarTodos() {
        return atendentes;
    }

    public static RepositorioAtendente getRepositorio() {
        return repositorio;
    }

    public void setAtendentes(List<Atendente> gerentes) {
        this.atendentes = gerentes;
    }

    public void salvarDados() throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream("./src//br/ufrpe/dados/persistencia/atendente.dat");
        ObjectOutputStream os = new ObjectOutputStream(file);
        os.writeObject(recuperarTodos());
        os.close();
    }

    public void carregarDados() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("./src//br/ufrpe/dados/persistencia/atendente.dat");
        ObjectInputStream is = new ObjectInputStream(file);
        if (file != null) {
            List<Atendente> gerentes = (List<Atendente>) is.readObject();
            setAtendentes(gerentes);
        }
        is.close();
    }

}
