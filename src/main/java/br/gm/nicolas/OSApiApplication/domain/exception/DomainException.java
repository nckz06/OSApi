/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gm.nicolas.OSApiApplication.domain.exception;

/**
 *
 * @author nckz06_
 */
public class DomainException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public DomainException(String message) {
        super(message);
    }
    
}
