/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import dao.UsuarioDAO;

public class UsuarioController {
    
    public int autenticar(String login, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.fazerLogin(login, senha);
    }
   
    public boolean cadastrar(String login, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.cadastrarUsuario(login, senha);
    }
}