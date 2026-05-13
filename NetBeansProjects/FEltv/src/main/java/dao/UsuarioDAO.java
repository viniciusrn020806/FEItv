/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    
 public int fazerLogin(String login, String senha) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
        
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id"); // RETORNA O ID DO USUÁRIO!
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return -1; // Retorna -1 se der erro ou não encontrar
    }
    public boolean cadastrarUsuario(String login, String senha) {
        String sql = "INSERT INTO usuario (login, senha) VALUES (?, ?)";
        
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            stmt.executeUpdate(); // Executa a inserção no banco
            return true; // Deu certo!
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false; // Falhou (provavelmente o usuário já existe)
        }
    }
}
