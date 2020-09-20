/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.dados;

import br.ufrpe.negocio.Medico;
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
public class RepositorioMedico implements IRepositorio<Medico>, Serializable {

    private List<Medico> medicos;
    private static final RepositorioMedico repositorio = new RepositorioMedico();
    private Medico medicoLogado = null;
    private static final long serialVersionUID = 1L;

    private RepositorioMedico() {
        medicos = new ArrayList();
    }

    @Override
    public void inserir(Medico medico) {
        medicos.add(medico);
    }

    @Override
    public void alterar(Medico medico, String cpf) {
        Medico m = recuperar(cpf);
        medicos.remove(m);
        medicos.add(medico);
    }

    @Override
    public Medico recuperar(String cpf) {
        for (Medico c : medicos) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void excluir(Medico medico) {
        medicos.remove(medico);
    }

    public static RepositorioMedico getRepositorio() {
        return repositorio;
    }

    @Override
    public void salvarDados() throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream("./src//br/ufrpe/dados/persistencia/medicos.dat");
        ObjectOutputStream os = new ObjectOutputStream(file);
        os.writeObject(recuperarTodos());
        os.close();
    }

    @Override
    public void carregarDados() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("./src//br/ufrpe/dados/persistencia/medicos.dat");
        ObjectInputStream is = new ObjectInputStream(file);
        if (file != null) {
            List<Medico> medicos = (List<Medico>) is.readObject();
            setMedicos(medicos);
        }
        is.close();
    }

    @Override
    public List<Medico> recuperarTodos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public Medico getMedicoLogado() {
        return medicoLogado;
    }

    public void setMedicoLogado(Medico medicoLogado) {
        this.medicoLogado = medicoLogado;
    }
    
    

}
