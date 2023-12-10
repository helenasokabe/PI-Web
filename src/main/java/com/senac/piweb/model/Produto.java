package com.senac.piweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="prod")
public class Produto {
        @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    private String produto; 
    private String descricao; 
    private String tamanho;
    private Integer estoque;
    private double venda;
}
