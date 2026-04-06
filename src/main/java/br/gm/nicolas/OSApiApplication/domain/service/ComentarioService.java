/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.domain.service;

import br.gm.nicolas.OSApiApplication.domain.model.Comentario;
import br.gm.nicolas.OSApiApplication.domain.model.OrdemServico;
import br.gm.nicolas.OSApiApplication.domain.repository.ComentarioRepository;
import br.gm.nicolas.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nckz06_
 */

@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository comentarioRepository;
    
    public Comentario criar(Comentario comentario) {
        
        comentario.setDataEnvio(LocalDateTime.now());
        
        return comentarioRepository.save(comentario);
        
    }
    
    public void excluir(Long comentarioID) {
        comentarioRepository.deleteById(comentarioID);
    }
    
}
