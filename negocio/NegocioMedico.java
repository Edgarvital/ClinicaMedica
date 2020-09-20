/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.negocio;

import br.ufrpe.dados.RepositorioMedico;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Edgar Vinicius
 */
public class NegocioMedico implements Serializable{
    private RepositorioMedico repositorio;

    public NegocioMedico() {
        repositorio =  RepositorioMedico.getRepositorio();
    }     
    
    public void inserir(Medico t) throws Exception {
        if (t != null) {
            for (int i = 0; i < repositorio.recuperarTodos().size(); i++) {
                if(t.getCpf().equals(repositorio.recuperarTodos().get(i).getCpf()) != true 
                        && t.getLogin().equals(repositorio.recuperarTodos().get(i).getLogin()) != true){                    
                } else{
                    throw new Exception("Médico Já Cadastrado!");
                }
            }
            repositorio.inserir(t);   
        }
    }
    
    public void alterar(Medico m, String cpf) throws Exception{
        if(m != null && cpf != null && repositorio.recuperar(cpf) != null){            
            repositorio.alterar(m, cpf);
        }
        else {
            throw new Exception("Cpf ou Medico invalido!");
        }
    }
    
    public Medico recuperar(String cpf) throws Exception{
        Medico m = repositorio.recuperar(cpf);
        if(m.equals(null)){
            throw new Exception("Médico Inexistente!");
        }
        return m;
    }
    
    public void excluir(Medico medico) {
    }
    
    public void autenticarMedico(String login, String senha) throws Exception {        
        Medico m = null;
        for(int i = 0; i < recuperarTodos().size(); i++){
            m = recuperarTodos().get(i);
            if(recuperarTodos().get(i).getLogin().equals(login) && recuperarTodos().get(i).getSenha().equals(senha)){
                setMedicoLogado(m);
            }
        }
        if(m.equals(null)){
            throw new Exception("Médico Inexistente!");
        }
    }
    
    public Medico getMedicoLogado(){
        return repositorio.getMedicoLogado();
    }
    
    public void setMedicoLogado(Medico medico){
        repositorio.setMedicoLogado(medico);
    }
    
    public List<Medico> recuperarTodos(){
        return repositorio.recuperarTodos();
    }
    
    public void salvarDados() throws IOException{
        repositorio.salvarDados();
    }
    public void carregarDados() throws IOException, ClassNotFoundException {
        repositorio.carregarDados();
    }
    
}
