/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model;

/**
 *
 * @author 31338283
 */
public class User_info {

    private int id_user_info; //chave primaria
    private int id_user; //chave estrangeira de usuario
    private String nome;
    private String email;
    private int idade;

    public User_info() {
    }

    public User_info(int id_user_info, int id_user, String nome, String email, int idade) {
        this.id_user_info = id_user_info;
        this.id_user = id_user;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }

    public int getId_user_info() {
        return id_user_info;
    }

    public void setId_user_info(int id_user_info) {
        this.id_user_info = id_user_info;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "User_info{" + "id_user_info=" + id_user_info + ", id_user=" + id_user + ", nome=" + nome + ", email=" + email + ", idade=" + idade + '}';
    }

}
