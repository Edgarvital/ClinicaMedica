package br.ufrpe.negocio;

import java.io.Serializable;

public class Consulta implements Serializable{
    private String descricao;
    private String observacao;
    private Medico medico;
    private String data;
    private String hora;
    private String nomeCliente;

    public Consulta(String descricao, Medico medico, String data, String hora, String nomeCliente) {
        this.descricao = descricao;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.nomeCliente = nomeCliente;
        observacao = "";
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }   

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }   
    
}
