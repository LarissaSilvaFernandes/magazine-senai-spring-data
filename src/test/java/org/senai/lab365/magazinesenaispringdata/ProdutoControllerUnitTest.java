package org.senai.lab365.magazinesenaispringdata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.senai.lab365.magazinesenaispringdata.controllers.ProdutoController;
import org.senai.lab365.magazinesenaispringdata.dtos.request.ProdutoRequest;
import org.senai.lab365.magazinesenaispringdata.dtos.response.ProdutoResponse;
import org.senai.lab365.magazinesenaispringdata.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProdutoControllerUnitTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    private ProdutoRequest produtoRequest;
    private ProdutoResponse produtoResponse;

    @BeforeEach
    public void setUp() {
        produtoRequest = new ProdutoRequest();
        produtoRequest.setNome("Gabinete Gamer");
        produtoRequest.setDescricao("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB");

        produtoResponse = new ProdutoResponse();
        produtoResponse.setId(123L);
        produtoResponse.setNome("Gabinete Gamer");
        produtoResponse.setDescricao("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB");
    }

    @Test
    public void validarCadastrarProduto() {
        when(produtoService.criar(any(ProdutoRequest.class))).thenReturn(new ResponseEntity<>(produtoResponse, HttpStatus.CREATED));

        ResponseEntity<ProdutoResponse> criarProdutoTest = produtoController.cadastrarProduto(produtoRequest);

        assertNotNull(criarProdutoTest);
        assertEquals(HttpStatus.CREATED, criarProdutoTest.getStatusCode());
        assertNotNull(criarProdutoTest.getBody());
        assertEquals(produtoRequest.getNome(), criarProdutoTest.getBody().getNome());
    }

    @Test
    public void validarListarProduto(){
        when(produtoService.listar()).thenReturn(new ResponseEntity<>(List.of(produtoResponse), HttpStatus.OK));

        var response = produtoController.listarProduto();

        assertNotNull(response);
        assertEquals("Gabinete Gamer", response.getBody().get(0).getNome());
    }
}
