/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {
    
    public List<String[]> buscarPorNome(String nomeBusca) {
        List<String[]> lista = new ArrayList<>();
        // O ILIKE ignora letras maiúsculas e minúsculas na busca
        String sql = "SELECT * FROM video WHERE titulo ILIKE ?";
        
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            // O % antes e depois permite achar o vídeo digitando só um pedaço do nome
            stmt.setString(1, "%" + nomeBusca + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo_video");
                String curtidas = String.valueOf(rs.getInt("curtidas"));
                String descurtidas = String.valueOf(rs.getInt("descurtidas")); // <- PEGANDO AS DESCURTIDAS
                
                // Agora enviamos as descurtidas também!
                lista.add(new String[]{id, titulo, tipo, curtidas, descurtidas}); 
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar vídeos: " + e.getMessage());
        }
        return lista;
    }
    
    public void curtirVideo(int idVideo) {
        String sql = "UPDATE video SET curtidas = curtidas + 1 WHERE id = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idVideo);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro ao curtir: " + e.getMessage());
        }
    }

    public void descurtirVideo(int idVideo) {
        String sql = "UPDATE video SET descurtidas = descurtidas + 1 WHERE id = ?";
        try (Connection con = ConexaoDB.conectar();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idVideo);
            stmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("Erro ao descurtir: " + e.getMessage());
        }
    }
}