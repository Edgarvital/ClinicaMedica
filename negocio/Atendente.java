package br.ufrpe.negocio;
import java.util.Date;

public class Atendente extends Pessoa{
    private String login;
    private String senha;
    private boolean gerenteLogado; 

    public Atendente(String nome, String cpf, Date dataNascimento, String telefone) {
        super(nome, cpf, dataNascimento, telefone);
        login = "Admin";
        senha = "Admin";
    }     

    public boolean isGerenteLogado() {
        return gerenteLogado;
    }

    public void setGerenteLogado(boolean gerenteLogado) {
        this.gerenteLogado = gerenteLogado;
    }     
       
}
