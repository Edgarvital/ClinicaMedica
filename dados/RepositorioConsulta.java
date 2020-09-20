/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrpe.dados;

import br.ufrpe.negocio.Consulta;
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
public class RepositorioConsulta implements IRepositorio<Consulta>, Serializable{
    private List<Consulta> consultas;
    private static final long serialVersionUID = 1L;
    public RepositorioConsulta(){
        consultas = new ArrayList();
    }

    public void inserir(Consulta consulta) {
        consultas.add(consulta);
    }

    public void alterar(Consulta consulta, String data) {
        Consulta c = recuperar(data);
        consultas.remove(c);
        consultas.add(consulta);
    }

    public Consulta recuperar(String data) {
        String dataConsulta;
        for(Consulta c : consultas){
            dataConsulta = c.getData() + " " + c.getHora();
            if(dataConsulta.equals(data)) return c;
        }
        return null;
    }

    public void excluir(Consulta consulta) {
        consultas.remove(consulta);
    }

    public List<Consulta> recuperarTodos() {
        return consultas;
    }    

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    public void salvarDados() throws FileNotFoundException, IOException {
        FileOutputStream file = new FileOutputStream("br.ufrpe.dados.persistencia.consultas.dat");
        ObjectOutputStream os = new ObjectOutputStream(file);
        os.writeObject(recuperarTodos());
        os.close();
    }    

    public void carregarDados() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("br.ufrpe.dados.persistencia.consultas.dat");
        ObjectInputStream is = new ObjectInputStream(file);
        List<Consulta> consultas = (List<Consulta>) is.readObject();
        setConsultas(consultas); 
        is.close();
    }
    
    
}
