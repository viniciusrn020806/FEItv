/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    
    // Conexao com o banco
    private static final String URL = "jdbc:postgresql://localhost:5432/feitv_db";
    private static final String USER = "postgres";
    
 
    private static final String PASS = "FEI"; 
    
    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USER, PASS);
            return conexao;
        } catch (SQLException e) {
            // Esta linha vai imprimir o motivo exato do erro!
            System.out.println("🔴 O MOTIVO REAL DO ERRO É: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar!", e);
        }
    }
}