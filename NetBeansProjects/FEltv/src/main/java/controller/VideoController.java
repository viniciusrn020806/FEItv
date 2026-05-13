/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.VideoDAO;
import java.util.List;

public class VideoController {
    
    public List<String[]> buscarVideos(String nomeBusca) {
        VideoDAO dao = new VideoDAO();
        return dao.buscarPorNome(nomeBusca);
    }
    
    public void curtir(int idVideo) {
        VideoDAO dao = new VideoDAO();
        dao.curtirVideo(idVideo);
    }

    public void descurtir(int idVideo) {
        VideoDAO dao = new VideoDAO();
        dao.descurtirVideo(idVideo);
    }
}
