/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.domain.repository;

import br.gm.nicolas.OSApiApplication.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author nckz06_
 */
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
}
