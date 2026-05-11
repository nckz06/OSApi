/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.api.controller;

import br.gm.nicolas.OSApiApplication.domain.model.Comentario;
import br.gm.nicolas.OSApiApplication.domain.model.OrdemServico;
import br.gm.nicolas.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.gm.nicolas.OSApiApplication.domain.service.ComentarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nckz06_
 */

@RestController
@RequestMapping("/ordem-servico")
public class ComentarioController {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ComentarioService comentarioService;
    
    
    @PutMapping("/comentario/{ordemID}")
    public ResponseEntity<OrdemServico> atualizaComentario(
            @PathVariable Long ordemID,
            @RequestBody Comentario comentario) {
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemID);
        
        if (ordemServico.isPresent()) {
            comentario.setId(ordemServico.get().getComentario().getId());
            Comentario comentarioOS = comentarioService.criar(comentario);

            ordemServico.get().setComentario(comentarioOS);
            return ResponseEntity.ok(ordemServicoRepository.save(ordemServico.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @DeleteMapping("/comentario/{ordemID}")
    public ResponseEntity<Void> excluirComentario(@PathVariable Long ordemID) {
        
        if (!ordemServicoRepository.existsById(ordemID)) {
            return ResponseEntity.notFound().build();
        }
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemID);
        
        Long comentarioID = ordemServico.get().getComentario().getId();
        
        comentarioService.excluir(comentarioID);
        ordemServico.get().setComentario(null);
        ordemServicoRepository.save(ordemServico.get());
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PostMapping("/comentario/{ordemID}")
    public ResponseEntity<OrdemServico> adicionaComentario(
            @PathVariable Long ordemID,
            @RequestBody Comentario comentario
        ) {
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemID);
        
        if (ordemServico.isPresent()) {
            Comentario comentarioOS = comentarioService.criar(comentario);

            ordemServico.get().setComentario(comentarioOS);
            OrdemServico novaOS = ordemServicoRepository.save(ordemServico.get());
            return ResponseEntity.ok(novaOS);
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
}
