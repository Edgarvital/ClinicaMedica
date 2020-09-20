package br.ufrpe.negocio;
public enum Especialidade {
    CARDIO("Cardio"), LARINGO("Laringo"), OTORRINO("Otorrino"), OFTAL("Oftal");
    String especialidade;

    Especialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
