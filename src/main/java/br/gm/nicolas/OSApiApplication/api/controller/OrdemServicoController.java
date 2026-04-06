/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.api.controller;

import br.gm.nicolas.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.gm.nicolas.OSApiApplication.domain.model.Cliente;
import br.gm.nicolas.OSApiApplication.domain.model.OrdemServico;
import br.gm.nicolas.OSApiApplication.domain.model.StatusOrdemServico;
import br.gm.nicolas.OSApiApplication.domain.repository.ClienteRepository;
import br.gm.nicolas.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.gm.nicolas.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nckz06_
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }
    
    @GetMapping("/cliente")
    public ResponseEntity<List<OrdemServico>> listarPorCliente(@RequestParam(name = "id") Long clienteID) {
        
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        List<OrdemServico> ordemServico = ordemServicoRepository.findByCliente(cliente.get());
        
        if (ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ordemServico);
        }
        
    }
    
    @GetMapping("/status/{statusOrdem}")
    public ResponseEntity<List<OrdemServico>> listarPorStatus(@PathVariable String statusOrdem) {
        
        List<OrdemServico> ordemServico = ordemServicoRepository.findByStatusIgnoreCase(statusOrdem);
        
        if (ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ordemServico);
        }
        
    }
    
    @GetMapping("/{statusOrdem}/cliente")
    public ResponseEntity<List<OrdemServico>> listarPorStatusCliente(
            @PathVariable String statusOrdem,
            @RequestParam(name="id") Long clienteID) {
        
        System.out.println(statusOrdem);
        List<OrdemServico> ordemServico = ordemServicoRepository.findByStatusClienteIgnoreCase(clienteID, statusOrdem);
        
        if (ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ordemServico);
        }
        
        
    }
    
    @GetMapping("/{ordemID}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemID) {
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(ordemID);
        
        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @PutMapping("/{ordemID}")
    public ResponseEntity<OrdemServico> atualizar(@PathVariable Long ordemID,
            @RequestBody OrdemServico ordemServico) {
        
        if(!ordemServicoRepository.existsById(ordemID)) {
            return ResponseEntity.notFound().build();
        }
        
        ordemServico.setId(ordemID);
        ordemServico = ordemServicoService.criar(ordemServico);
        return ResponseEntity.ok(ordemServico);
        
    }
    
    @PutMapping("/atualiza-status/{ordemServicoID}")
    public ResponseEntity<OrdemServico> atualizaStatus(
            @PathVariable Long ordemServicoID,
            @Valid @RequestBody AtualizaStatusDTO statusDTO
    ) {
        
        Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(ordemServicoID, statusDTO.status());
        
        if (optOS.isPresent()) {
            return ResponseEntity.ok(optOS.get());
        } else {
            return ResponseEntity.notFound().build();        
        }
    }
    
    @DeleteMapping("/{ordemID}")
    public ResponseEntity<Void> excluir(@PathVariable Long ordemID) {
        
        if (!ordemServicoRepository.existsById(ordemID)) {
            return ResponseEntity.notFound().build();
        }
        
        ordemServicoService.excluir(ordemID);
        return ResponseEntity.noContent().build();
        
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
}
