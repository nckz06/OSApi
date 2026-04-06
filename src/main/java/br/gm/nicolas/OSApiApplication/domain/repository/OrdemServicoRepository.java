/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.domain.repository;

import br.gm.nicolas.OSApiApplication.domain.model.Cliente;
import br.gm.nicolas.OSApiApplication.domain.model.OrdemServico;
import br.gm.nicolas.OSApiApplication.domain.model.StatusOrdemServico;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author nckz06_
 */
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    
    List<OrdemServico> findByCliente(Cliente cliente);
    
    @Query(nativeQuery = true, value = """
       select id, cliente_id, descricao, preco, status, data_abertura, data_finalizacao, comentario_id from ordem_servico
       where status = :status;                                                 
    """)
    List<OrdemServico> findByStatusIgnoreCase(String status);
    
    @Query(nativeQuery = true, value = """
       select id, cliente_id, descricao, preco, status, data_abertura, data_finalizacao, comentario_id from ordem_servico
       where cliente_id = :clienteID and status = :status;
    """)
    List<OrdemServico> findByStatusClienteIgnoreCase(Long clienteID, String status);
    
}
