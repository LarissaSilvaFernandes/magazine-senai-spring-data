package org.senai.lab365.magazinesenaispringdata.dtos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProdutoRequest {
    @NotBlank(message="O campo Nome é obrigatorio")
    @Schema(example = "Tv Samsung")
    private String nome;
    @NotBlank
    @Schema(example = "Tv Samsung 43 polegadas 4K")
    private String descricao;
}
