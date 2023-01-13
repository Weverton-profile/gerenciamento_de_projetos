package br.com.gerenciamento.model;

public class Log {
    
    private String descricao;
    private String data;
    
    public Log(String descricao, String data) {
        this.descricao = descricao;
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }

}
