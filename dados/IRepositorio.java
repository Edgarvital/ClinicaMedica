/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.dados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Edgar
 */
public interface IRepositorio <T> {    
    public void inserir(T t);
    public void alterar(T t, String info);
    public T recuperar(String info);
    public void excluir(T t);
    public List<T> recuperarTodos(); 
    public void salvarDados() throws FileNotFoundException, IOException;
    public void carregarDados() throws IOException, ClassNotFoundException ;
}
