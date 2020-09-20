package br.ufrpe.negocio;

import java.util.Date;

public class Medico extends Pessoa {

    private String login;
    private String senha;
    private Especialidade especialidade;
    private boolean medicoLogado;
    private NegocioClienteMedico negocioClientes;
    private NegocioConsulta negocioConsulta;

    public Medico(String login, String senha, Especialidade especialidade, String nome, String cpf, Date dataNascimento, String telefone) {
        super(nome, cpf, dataNascimento, telefone);
        this.login = login;
        this.senha = senha;
        this.especialidade = especialidade;
        medicoLogado = false;
        negocioClientes = new NegocioClienteMedico();
        negocioConsulta = new NegocioConsulta();
    }

    //GET'S E SET'S
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isMedicoLogado() {
        return medicoLogado;
    }

    public void setMedicoLogado(boolean medicoLogado) {
        this.medicoLogado = medicoLogado;
    }

    public NegocioClienteMedico getNegocioClientes() {
        return negocioClientes;
    }

    public NegocioConsulta getNegocioConsulta() {
        return negocioConsulta;
    }

    @Override
    public String toString() {
        return getNome();
    }
    
    
    
    
   
}
