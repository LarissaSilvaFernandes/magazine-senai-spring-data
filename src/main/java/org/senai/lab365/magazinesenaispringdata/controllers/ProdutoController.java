package org.senai.lab365.magazinesenaispringdata.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.senai.lab365.magazinesenaispringdata.dtos.request.ProdutoRequest;
import org.senai.lab365.magazinesenaispringdata.dtos.response.ProdutoResponse;
import org.senai.lab365.magazinesenaispringdata.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;


    @PostMapping
    @Operation(summary = "Cadastrar produto", description = "Registra um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição incorreta"),
            @ApiResponse(responseCode = "500", description = "Serviço indsiponivel")
    })
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest) {
        return produtoService.criar(produtoRequest);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProduto() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Resgatar produto pelo identificador", description = "Retorna o produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição incorreta"),
            @ApiResponse(responseCode = "404", description = "Not found - Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Serviço indsiponivel")
    })
    public ResponseEntity<List<ProdutoResponse>> listarPorId(@PathVariable Long id) {
        return produtoService.listarPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@RequestBody ProdutoRequest produtoRequest, @PathVariable Long id) {
        return produtoService.atualizar(produtoRequest, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        return produtoService.deletar(id);
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<ProdutoResponse> buscarProdutoPorDescricao(@PathVariable String descricao) {
        return produtoService.buscarProdutoPorDescricao(descricao);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoResponse>> buscarPorNome(@PathVariable String nome) {
       return produtoService.buscarProdutosPorNome(nome);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<ProdutoResponse>> buscarPorNomeParam(@RequestParam("nome") String nome) {
        return produtoService.buscarProdutosPorNome(nome);
    }

}
