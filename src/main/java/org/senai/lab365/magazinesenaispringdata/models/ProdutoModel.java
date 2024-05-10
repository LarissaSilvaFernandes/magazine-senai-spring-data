package org.senai.lab365.magazinesenaispringdata.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
}
