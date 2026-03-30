/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.domain.service;

import br.gm.nicolas.OSApiApplication.domain.exception.DomainException;
import br.gm.nicolas.OSApiApplication.domain.model.Cliente;
import br.gm.nicolas.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nckz06_
 */
@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente) {
        
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
        
        // o método SAVE pode cadastrar ou atualizar um novo cliente
        // ID novo -> novo registro
        // ID existente -> atualização do cliente
        
        //verifica se o cliente existe
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            // lança exception
            throw new DomainException("Já existe um cliente cadastrado com esse email!");
        }
        
        return clienteRepository.save(cliente);
    
    }
    
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
    
}
