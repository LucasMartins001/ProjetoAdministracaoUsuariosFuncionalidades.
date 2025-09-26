package com.administracao.services;

import com.administracao.models.Project;
import com.administracao.models.Task;
import com.administracao.models.Team;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RelatorioService {
    public static void gerarRelatorio(List<Project> projects, List<Task> tasks, List<Team> teams) {
        System.out.println("\n=== Relatório Rápido de Projetos ===");
        for (Project p: projects) {
            long total = tasks.stream().filter(t -> p.getId().equals(t.projectId)).count();
            long done = tasks.stream().filter(t -> p.getId().equals(t.projectId) && "concluida".equalsIgnoreCase(t.status)).count();
            System.out.println(p);
            System.out.println("  Tarefas: " + total + " (concluídas: " + done + ")");
            System.out.println("  Equipes alocadas: " + p.getTeamIds().size());
        }
        System.out.println("\nEquipes cadastradas: " + teams.size());
    }
}
