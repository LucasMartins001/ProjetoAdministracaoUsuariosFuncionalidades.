package com.administracao.models;

import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
    private String id;
    private String nome;
    private String descricao;
    private List<String> memberIds = new ArrayList<>();

    public Team(String nome, String descricao) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome; this.descricao = descricao;
    }

    public String getId() { return id; }
    public void addMember(String userId) { if (!memberIds.contains(userId)) memberIds.add(userId); }
    public String toString() { return String.format("Team[id=%s, nome=%s, membros=%d]", id, nome, memberIds.size()); }
}
