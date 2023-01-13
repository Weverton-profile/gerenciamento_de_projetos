package br.com.gerenciamento.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    
    private Integer id;
    private Integer id_projeto;
    private Integer membro_id;
    private Integer tarefaTempo;
    private String data_inicio;
    private String data_fim;
    private String andamento;
    private String nome;

    public Tarefa(Integer id_projeto, Integer membro_id, String andamento, String nome, Integer id,  Integer tarefaTempo, String data_inicio, String data_fim) {
        this.id_projeto = id_projeto;
        this.membro_id = membro_id;
        this.andamento = andamento;
        this.nome = nome;
        this.id = id;
        this.tarefaTempo = tarefaTempo;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }
    
    public Integer getId() {
        return id;
    }

    public Integer getId_projeto() {
        return id_projeto;
    }

    public Integer getMembro_id() {
        return membro_id;
    }

    public String getAndamento() {
        return andamento;
    }

    public String getNome() {
        return nome;
    }
    
    public Integer getTempo() {
        return tarefaTempo;
    }

    public String getData_inicio() {
        return data_inicio;
    }

    public String getData_fim() {
        return data_fim;
    }
    
    public long tempoReal() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Duration.between(LocalDateTime.parse(getData_inicio(), f), LocalDateTime.parse(getData_fim(), f)).toHours();
    }
}
