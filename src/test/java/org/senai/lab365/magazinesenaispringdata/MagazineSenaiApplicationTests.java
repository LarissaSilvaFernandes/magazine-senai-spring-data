package org.senai.lab365.magazinesenaispringdata;

import org.junit.jupiter.api.Test;
import org.senai.lab365.magazinesenaispringdata.controllers.ProdutoController;
import org.senai.lab365.magazinesenaispringdata.dtos.request.ProdutoRequest;
import org.senai.lab365.magazinesenaispringdata.dtos.response.ProdutoResponse;
import org.senai.lab365.magazinesenaispringdata.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class MagazineSenaiApplicationTests {

    @Autowired
    private ProdutoController produtoController;

    @Autowired
    private ProdutoService produtoService;

    @Test
    void validarCadastrarProduto() {
        // Configurar o ProdutoRequest
        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setNome("Gabinete Gamer");
        produtoRequest.setDescricao("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB");

        // Mock da resposta esperada do serviço
        ProdutoResponse produtoResponse = new ProdutoResponse();
        produtoResponse.setId(123L);
        produtoResponse.setNome("Gabinete Gamer");
        produtoResponse.setDescricao("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB");


        // Assumindo que o produtoService está corretamente configurado para criar o produto
        ResponseEntity<ProdutoResponse> criarProdutoTest = produtoController.cadastrarProduto(produtoRequest);

        // Verificações
        assertNotNull(criarProdutoTest);
        assertEquals(HttpStatus.CREATED, criarProdutoTest.getStatusCode());
        assertNotNull(criarProdutoTest.getBody());
        assertEquals(produtoRequest.getNome(), criarProdutoTest.getBody().getNome());

    }

}
