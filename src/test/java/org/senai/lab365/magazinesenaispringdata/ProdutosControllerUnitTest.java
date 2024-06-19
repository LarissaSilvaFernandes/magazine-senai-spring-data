package org.senai.lab365.magazinesenaispringdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.senai.lab365.magazinesenaispringdata.controllers.ProdutoController;
import org.senai.lab365.magazinesenaispringdata.dtos.request.ProdutoRequest;
import org.senai.lab365.magazinesenaispringdata.dtos.response.ProdutoResponse;
import org.senai.lab365.magazinesenaispringdata.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProdutoController.class)
@AutoConfigureMockMvc
public class ProdutosControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCadastrarProduto() throws Exception {
        // Configuração do mock do serviço
        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setNome("Gabinete Gamer");
        produtoRequest.setDescricao("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB");

        ProdutoResponse produtoResponse = new ProdutoResponse();
        produtoResponse.setId(123L);
        produtoResponse.setNome("Gabinete Gamer");
        produtoResponse.setDescricao("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB");

        when(produtoService.criar(any(ProdutoRequest.class)))
                .thenReturn(new ResponseEntity<>(produtoResponse, HttpStatus.CREATED));

        // Chamada ao endpoint via MockMvc
        String produtoRequestJson = objectMapper.writeValueAsString(produtoRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Gabinete Gamer"))
                .andExpect(jsonPath("$.descricao").value("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB"))
                .andReturn();
    }

    @Test
    public void testCadastrarProdutoSemNomeDeveDarRequisicaoIncorreta() throws Exception {
        ProdutoRequest produtoRequest = new ProdutoRequest();
        produtoRequest.setDescricao("Gabinete Gamer Bluecase BG-009 - USB 3.0 - Lateral em Acrílico - Painel Frontal com LED RGB");

        String produtoRequestJson = objectMapper.writeValueAsString(produtoRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoRequestJson))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}