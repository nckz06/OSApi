/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.domain.dto;

import br.gm.nicolas.OSApiApplication.domain.model.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author nckz06_
 */
public record AtualizaStatusDTO(
    @NotNull(message = "Status é obrigatório!")
    StatusOrdemServico status) {

    
    
}
