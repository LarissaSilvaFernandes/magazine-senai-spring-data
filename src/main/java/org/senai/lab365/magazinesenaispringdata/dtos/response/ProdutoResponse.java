package org.senai.lab365.magazinesenaispringdata.dtos.response;

import lombok.Data;

@Data
public class ProdutoResponse {
    private Long id;
    private String nome;
    private String descricao;
}
