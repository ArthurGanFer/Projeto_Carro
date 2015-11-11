/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.lp2.model.dao;

import com.br.lp2.model.User;
import com.br.lp2.model.User_info;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1147106
 */
public class UserDAO implements GenericDAO<User> {

    private Connection conn;

    public UserDAO() {
        //1. Realizar a conexão
        conn = ConnectionDB.getInstance();
    }

    @Override
    public boolean insert(User user) {
        boolean resp = false;
        String sql = "INSERT INTO records_user(username,password,user_type) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUser_type());
            ps.executeUpdate();
            ResultSet rs2 = ps.getGeneratedKeys();
            int chave = -1;
            while (rs2.next()) {
                chave = rs2.getInt(1);
            }
            boolean resposta = this.insertInfo(user.getUsuario_info(), chave);
            if (resposta == false) {
                System.out.println("Erro");
            } else {
                System.out.println("Sucesso");
                resp = true;
            }
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    private boolean insertInfo(User_info user_info, int chave) {
        boolean resp = false;
        String sql = "INSERT INTO user_info (id_user, nome, email, idade) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, chave);
            ps.setString(2, user_info.getNome());
            ps.setString(3, user_info.getEmail());
            ps.setInt(4, user_info.getIdade());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Erro");
            } else {
                System.out.println("Sucesso");
                resp = true;
            }

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public List<User> read() {
        List<User> users = new ArrayList<>();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM records_user u INNER JOIN user_info i ON u.id_usuario = i.id_usuario";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);

            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                User user = new User();
                user.setId_user(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_type(rs.getInt("user_type"));
                users.add(user);
                User_info user_info = new User_info();
                user_info.setId_user_info(rs.getInt("id_user_info"));
                user_info.setNome(rs.getString("nome"));
                user_info.setEmail(rs.getString("email"));
                user_info.setIdade(rs.getInt("idade"));
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public User readByUsername(String username) {
        User user = new User();
        //2. Criar o preparedStatement
        String sql = "SELECT * FROM records_user WHERE username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            //3. Executa a query
            ResultSet rs = ps.executeQuery();

            //4. Mostrar os resultados
            while (rs.next()) {
                user.setId_user(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setUser_type(rs.getInt("user_type"));
            }

            //5. Fechar tudo
            ps.close();
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean resp = false;
        String sql = "UPDATE records_user SET username=?,password=?,user_type=? WHERE id_user=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUser_type());
            ps.setInt(4, user.getId_user());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Error: user not updated");
            } else {
                System.out.println("User updated successfully");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean delete(User user) {
        boolean resp = false;
        String sql = "DELETE FROM records_user WHERE id_user=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId_user());
            int resposta = ps.executeUpdate();
            if (resposta == 0) {
                System.out.println("Error: user not removed");
            } else {
                System.out.println("User removed successfully");
                resp = true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

}
