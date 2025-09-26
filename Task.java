package com.administracao.models;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable {
    private String id;
    private String titulo;
    private String descricao;
    private String projectId;
    private String responsibleUserId;
    private String dataInicio;
    private String dataFim;
    private String status; // pendente, em andamento, concluida

    public Task(String titulo, String descricao, String projectId, String responsibleUserId, String dataInicio, String dataFim, String status) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo; this.descricao = descricao; this.projectId = projectId; this.responsibleUserId = responsibleUserId; this.dataInicio = dataInicio; this.dataFim = dataFim; this.status = status;
    }

    public String getId() { return id; }
    public void setStatus(String s) { this.status = s; }
    public String toString() { return String.format("Task[id=%s, titulo=%s, status=%s]", id, titulo, status); }
}
