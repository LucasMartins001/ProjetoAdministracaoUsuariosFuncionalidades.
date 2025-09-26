package com.administracao.models;

import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Project implements Serializable {
    private String id;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String status; // planejado, em andamento, concluido, cancelado
    private List<String> teamIds = new ArrayList<>();

    public Project(String nome, String descricao, String dataInicio, String dataFim, String status) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome; this.descricao = descricao; this.dataInicio = dataInicio; this.dataFim = dataFim; this.status = status;
    }

    public String getId() { return id; }
    public void addTeamId(String teamId) { if (!teamIds.contains(teamId)) teamIds.add(teamId); }
    public void removeTeamId(String teamId) { teamIds.remove(teamId); }
    public List<String> getTeamIds() { return teamIds; }
    public String toString() { return String.format("Project[id=%s, nome=%s, status=%s, equipes=%d]", id, nome, status, teamIds.size()); }
}
