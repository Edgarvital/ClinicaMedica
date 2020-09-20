package br.ufrpe.negocio;

import java.io.Serializable;
import java.util.Date;

public class Cliente extends Pessoa implements Serializable {
    private String sexo;
    private NegocioConsulta negocioConsulta;
    public Cliente(String nome, String cpf, Date dataAniversario, String telefone, String sexo) {
        super(nome, cpf, dataAniversario, telefone);
        this.sexo = sexo;
        negocioConsulta = new NegocioConsulta();
    }   

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }   

    public NegocioConsulta getNegocioConsulta() {
        return negocioConsulta;
    }     
      
}
