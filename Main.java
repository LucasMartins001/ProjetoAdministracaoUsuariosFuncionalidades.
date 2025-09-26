package com.administracao;

import com.administracao.models.*;
import com.administracao.repos.SimpleRepo;
import com.administracao.services.RelatorioService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static SimpleRepo<User> userRepo = new SimpleRepo<>("data/users.ser");
    static SimpleRepo<Project> projectRepo = new SimpleRepo<>("data/projects.ser");
    static SimpleRepo<Team> teamRepo = new SimpleRepo<>("data/teams.ser");
    static SimpleRepo<Task> taskRepo = new SimpleRepo<>("data/tasks.ser");

    public static void main(String[] args) {
        ensureDataDir();
        System.out.println("=== Sistema de Administração de Usuários e Funcionalidades ===");
        int opt = -1;
        while (opt != 0) {
            System.out.println("\nMenu Principal:");
            System.out.println("1 - Usuários"); System.out.println("2 - Projetos"); System.out.println("3 - Equipes"); System.out.println("4 - Tarefas"); System.out.println("5 - Relatórios"); System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            try { opt = Integer.parseInt(sc.nextLine()); } catch (Exception e) { opt = -1; }
            switch(opt) {
                case 1: manageUsers(); break;
                case 2: manageProjects(); break;
                case 3: manageTeams(); break;
                case 4: manageTasks(); break;
                case 5: RelatorioService.gerarRelatorio(projectRepo.loadAll(), taskRepo.loadAll(), teamRepo.loadAll()); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida"); 
            }
        }
        sc.close();
    }

    static void ensureDataDir() { new java.io.File("data").mkdirs(); }

    // users minimal
    static void manageUsers() {
        List<User> users = userRepo.loadAll();
        System.out.println("\n-- Usuários --"); System.out.println("1 Listar  2 Criar  3 Remover  0 Voltar"); System.out.print("Escolha: ");
        String o = sc.nextLine();
        if (o.equals("1")) { for (User u: users) System.out.println(u); }
        else if (o.equals("2")) {
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("CPF: "); String cpf = sc.nextLine();
            System.out.print("Email: "); String email = sc.nextLine();
            System.out.print("Cargo: "); String cargo = sc.nextLine();
            System.out.print("Perfil (administrador/gerente/colaborador): "); String perfil = sc.nextLine();
            User u = new User(nome, cpf, email, cargo, perfil);
            users.add(u); userRepo.saveAll(users); System.out.println("Criado: " + u);
        } else if (o.equals("3")) { System.out.print("Nome/id para remover: "); String q = sc.nextLine(); users.removeIf(u -> u.getId().contains(q) || u.toString().contains(q)); userRepo.saveAll(users); System.out.println("Removido se encontrado."); }
        else { System.out.println("Voltando..."); }
    }

    // projects with team allocation
    static void manageProjects() {
        List<Project> list = projectRepo.loadAll();
        System.out.println("\n-- Projetos --"); System.out.println("1 Listar  2 Criar  3 Alocar equipe  4 Remover  0 Voltar"); System.out.print("Escolha: ");
        String o = sc.nextLine();
        if (o.equals("1")) { for (Project p: list) System.out.println(p); }
        else if (o.equals("2")) {
            System.out.print("Nome: "); String nome = sc.nextLine();
            System.out.print("Descrição: "); String desc = sc.nextLine();
            System.out.print("Data início (dd/MM/yyyy): "); String di = sc.nextLine();
            System.out.print("Data fim (dd/MM/yyyy): "); String df = sc.nextLine();
            System.out.print("Status: "); String st = sc.nextLine();
            Project p = new Project(nome, desc, di, df, st);
            list.add(p); projectRepo.saveAll(list); System.out.println("Criado: " + p);
        } else if (o.equals("3")) {
            System.out.print("ID do projeto: "); String pid = sc.nextLine();
            System.out.print("ID da equipe: "); String tid = sc.nextLine();
            for (Project p: list) { if (p.getId().equals(pid)) { p.addTeamId(tid); } }
            projectRepo.saveAll(list); System.out.println("Alocação tentada."); 
        } else if (o.equals("4")) { System.out.print("Nome/id para remover: "); String q = sc.nextLine(); list.removeIf(p -> p.getId().contains(q) || p.toString().contains(q)); projectRepo.saveAll(list); System.out.println("Removido se encontrado."); }
    }

    static void manageTeams() {
        List<Team> list = teamRepo.loadAll();
        System.out.println("\n-- Equipes --"); System.out.println("1 Listar  2 Criar  3 Adicionar membro  0 Voltar"); System.out.print("Escolha: ");
        String o = sc.nextLine();
        if (o.equals("1")) { for (Team t: list) System.out.println(t); }
        else if (o.equals("2")) {
            System.out.print("Nome equipe: "); String nome = sc.nextLine();
            System.out.print("Descrição: "); String desc = sc.nextLine();
            Team t = new Team(nome, desc); list.add(t); teamRepo.saveAll(list); System.out.println("Criado: " + t);
        } else if (o.equals("3")) {
            System.out.print("ID da equipe: "); String tid = sc.nextLine();
            System.out.print("ID do usuário: "); String uid = sc.nextLine();
            for (Team t: list) { if (t.getId().equals(tid)) t.addMember(uid); }
            teamRepo.saveAll(list); System.out.println("Tentou adicionar membro."); 
        }
    }

    static void manageTasks() {
        List<Task> list = taskRepo.loadAll();
        System.out.println("\n-- Tarefas --"); System.out.println("1 Listar  2 Criar  3 Mudar status  0 Voltar"); System.out.print("Escolha: ");
        String o = sc.nextLine();
        if (o.equals("1")) { for (Task t: list) System.out.println(t); }
        else if (o.equals("2")) {
            System.out.print("Título: "); String titulo = sc.nextLine();
            System.out.print("Descrição: "); String desc = sc.nextLine();
            System.out.print("ID do projeto: "); String pid = sc.nextLine();
            System.out.print("ID do responsável (usuário): "); String uid = sc.nextLine();
            System.out.print("Data início (dd/MM/yyyy): "); String di = sc.nextLine();
            System.out.print("Data fim (dd/MM/yyyy): "); String df = sc.nextLine();
            System.out.print("Status: "); String st = sc.nextLine();
            Task t = new Task(titulo, desc, pid, uid, di, df, st); list.add(t); taskRepo.saveAll(list); System.out.println("Criada: " + t);
        } else if (o.equals("3")) {
            System.out.print("ID da tarefa: "); String id = sc.nextLine(); System.out.print("Novo status: "); String ns = sc.nextLine();
            for (Task t: list) { if (t.getId().equals(id)) t.setStatus(ns); }
            taskRepo.saveAll(list); System.out.println("Status alterado se encontrado."); 
        }
    }
}
