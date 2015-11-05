package com.br.lp2.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 1147106
 */
public class User implements Serializable {

    private int id_user, user_type;
    private String username, password;
    private User_info usuario_info;

    public User() {
        this.id_user = -1;
        this.user_type = 1;
        this.username = "anonimo";
        this.password = "123";
    }

    public User(int id_user, int user_type, String username, String password) {
        this.id_user = id_user;
        this.user_type = user_type;
        this.username = username;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", user_type=" + user_type + ", username=" + username + ", password=" + password + ", usuario_info=" + usuario_info + '}';
    }

}
