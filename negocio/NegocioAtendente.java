/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.negocio;

import br.ufrpe.dados.RepositorioAtendente;
import java.io.IOException;

/**
 *
 * @author Edgar Vinicius
 */
public class NegocioAtendente {

    private RepositorioAtendente repositorio;

    public NegocioAtendente() {
        repositorio = RepositorioAtendente.getRepositorio();
    }
    
    public Atendente autenticar(String login, String senha) throws Exception{
        if(login.equals("Admin") && senha.equals("Admin")){
            return repositorio.recuperarTodos().get(0);
        } else{
            throw new Exception("Login ou Senha Invalido!");
        }
    }

    public void inserir(Atendente atendente) throws Exception {
        if (repositorio.recuperarTodos().size() < 1) {
            repositorio.inserir(atendente);
        } else {
            throw new Exception("Atendente Já Existe!");
        }
    }

    public RepositorioAtendente getRepositorio() {
        return repositorio;
    }   
    
    public void salvarDados() throws IOException{
        repositorio.salvarDados();
    }
    public void carregarDados() throws IOException, ClassNotFoundException {
        repositorio.carregarDados();
    }
    
}
