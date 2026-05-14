/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ListaReproducaoDAO {
    
    //  Criar uma nova lista no banco
    public boolean criarLista(String nome, int idUsuario) {
        String sql = "INSERT INTO lista_reproducao (nome, id_usuario) VALUES (?, ?)";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idUsuario);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao criar lista: " + e.getMessage());
            return false;
        }
    }

    //  Pegar o ID da lista pelo nome
    public int buscarIdListaPorNome(String nome, int idUsuario) {
        String sql = "SELECT id FROM lista_reproducao WHERE nome = ? AND id_usuario = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setInt(2, idUsuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return rs.getInt("id"); // Achou a lista!
            }
        } catch (Exception e) {}
        return -1; // Não achou
    }

    // Ligar o vídeo à lista 
    public void adicionarVideoNaLista(int idLista, int idVideo) {
        String sql = "INSERT INTO lista_video (id_lista, id_video) VALUES (?, ?)";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idLista);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao favoritar: Esse vídeo já pode estar nessa lista!");
        }
    }
    
    // Puxa os nomes de todas as listas do usuário
    public java.util.List<String> buscarMinhasListas(int idUsuario) {
        java.util.List<String> listas = new java.util.ArrayList<>();
        String sql = "SELECT nome FROM lista_reproducao WHERE id_usuario = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                listas.add(rs.getString("nome"));
            }
        } catch (Exception e) {}
        return listas;
    }

    // Puxa os vídeos que estão dentro de uma lista específica
    public java.util.List<String[]> buscarVideosDaLista(int idLista) {
        java.util.List<String[]> videos = new java.util.ArrayList<>();
        // O JOIN junta a tabela de vídeos com a tabela de relacionamento
        String sql = "SELECT v.id, v.titulo, v.tipo_video FROM video v " +
                     "JOIN lista_video lv ON v.id = lv.id_video WHERE lv.id_lista = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idLista);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                videos.add(new String[]{
                    String.valueOf(rs.getInt("id")),
                    rs.getString("titulo"),
                    rs.getString("tipo_video")
                });
            }
        } catch (Exception e) {}
        return videos;
    }

    // Remove um vídeo da lista
    public void removerVideo(int idLista, int idVideo) {
        String sql = "DELETE FROM lista_video WHERE id_lista = ? AND id_video = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idLista);
            stmt.setInt(2, idVideo);
            stmt.executeUpdate();
        } catch (Exception e) {}
    }
}
