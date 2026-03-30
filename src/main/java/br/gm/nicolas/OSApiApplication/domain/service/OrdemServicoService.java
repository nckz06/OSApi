/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.domain.service;

import br.gm.nicolas.OSApiApplication.domain.model.OrdemServico;
import br.gm.nicolas.OSApiApplication.domain.model.StatusOrdemServico;
import br.gm.nicolas.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nckz06_
 */
@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar(OrdemServico ordemServico) {
        
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
        
    }
    
    public void excluir(Long ordemID) {
        ordemServicoRepository.deleteById(ordemID);
    }
    
}
