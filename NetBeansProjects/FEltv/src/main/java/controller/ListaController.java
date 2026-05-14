/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ListaReproducaoDAO;

public class ListaController {
    
    public void salvarFavorito(String nomeLista, int idVideo, int idUsuario) {
        ListaReproducaoDAO dao = new ListaReproducaoDAO();
        
        // 1. Pergunta pro banco se essa lista já existe para este usuário
        int idLista = dao.buscarIdListaPorNome(nomeLista, idUsuario);
        
        // 2. Se não existir (-1), o código cria a lista na hora!
        if (idLista == -1) {
            dao.criarLista(nomeLista, idUsuario);
            idLista = dao.buscarIdListaPorNome(nomeLista, idUsuario); // Pega o ID novo
        }
        
        // 3. Adiciona o vídeo dentro da lista
        dao.adicionarVideoNaLista(idLista, idVideo);
    }
    
    public java.util.List<String> listarMinhasListas(int idUsuario) {
        ListaReproducaoDAO dao = new ListaReproducaoDAO();
        return dao.buscarMinhasListas(idUsuario);
    }

    public java.util.List<String[]> listarVideosDaLista(String nomeLista, int idUsuario) {
        ListaReproducaoDAO dao = new ListaReproducaoDAO();
        int idLista = dao.buscarIdListaPorNome(nomeLista, idUsuario);
        return dao.buscarVideosDaLista(idLista);
    }

    public void removerVideo(String nomeLista, int idVideo, int idUsuario) {
        ListaReproducaoDAO dao = new ListaReproducaoDAO();
        int idLista = dao.buscarIdListaPorNome(nomeLista, idUsuario);
        dao.removerVideo(idLista, idVideo);
    }
}
