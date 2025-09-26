package com.administracao.models;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String cargo;
    private String perfil; // administrador, gerente, colaborador

    public User(String nome, String cpf, String email, String cargo, String perfil) {
        this.id = UUID.randomUUID().toString();
        this.nome = nome; this.cpf = cpf; this.email = email; this.cargo = cargo; this.perfil = perfil;
    }

    public String getId() { return id; }
    public String toString() { return String.format("User[id=%s, nome=%s, perfil=%s]", id, nome, perfil); }
}
