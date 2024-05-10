package org.senai.lab365.magazinesenaispringdata.services;

import org.modelmapper.ModelMapper;
import org.senai.lab365.magazinesenaispringdata.dtos.request.ProdutoRequest;
import org.senai.lab365.magazinesenaispringdata.dtos.response.ProdutoResponse;
import org.senai.lab365.magazinesenaispringdata.exeptions.exeptionsId.ProdutoNaoEncontradoExceptionId;
import org.senai.lab365.magazinesenaispringdata.exeptions.exeptionsNomeEDescricao.ProdutoNaoEncontradoExceptionNomeEDescricao;
import org.senai.lab365.magazinesenaispringdata.models.ProdutoModel;
import org.senai.lab365.magazinesenaispringdata.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ModelMapper modelMapper;


    public ResponseEntity<ProdutoResponse> criar(ProdutoRequest produtoRequest) {
        ProdutoModel produtoModel = modelMapper.map(produtoRequest, ProdutoModel.class);
        ProdutoModel produtoModelSalvo = produtoRepository.save(produtoModel);
        ProdutoResponse produtoResponse = modelMapper.map(produtoModelSalvo, ProdutoResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);

    }

    public ResponseEntity<List<ProdutoResponse>> listar() {
        List<ProdutoModel> produtoModelList = (List<ProdutoModel>) produtoRepository.findAll();
        List<ProdutoResponse> produtoResponses = produtoModelList.stream().map(produtoModel -> modelMapper.map(produtoModel, ProdutoResponse.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(produtoResponses);
    }

    public ResponseEntity<List<ProdutoResponse>> listarPorId(Long id) {
        Optional<ProdutoModel> produtoModels = produtoRepository.findById(id);
        if (produtoModels.isEmpty()) {
            throw new ProdutoNaoEncontradoExceptionId(id);
        }
        List<ProdutoResponse> produtoModelList = produtoModels.stream().map(produtoModel -> modelMapper.map(produtoModel, ProdutoResponse.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(produtoModelList);
    }

    public ResponseEntity<ProdutoResponse> atualizar(ProdutoRequest produtoRequest, Long id) {
        Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);

        if (produtoOptional.isEmpty()) {
            throw new ProdutoNaoEncontradoExceptionId(id);
        }
        ProdutoModel produtoModel = produtoOptional.get();
        if (produtoRequest.getNome() != null) {
            produtoModel.setNome(produtoRequest.getNome());
        }
        if (produtoModel.getDescricao() != null) {
            produtoModel.setDescricao(produtoRequest.getDescricao());
        }

        ProdutoModel salvoRepository = produtoRepository.save(produtoModel);
        ProdutoResponse produtoResponse = modelMapper.map(salvoRepository, ProdutoResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(produtoResponse);

    }

    public ResponseEntity<Void> deletar(Long id) {
        Optional<ProdutoModel> optionalProduto = produtoRepository.findById(id);
        optionalProduto.ifPresent(produtoModel -> produtoRepository.delete(produtoModel));
        throw new ProdutoNaoEncontradoExceptionId(id);
    }

    public ResponseEntity<ProdutoResponse> buscarProdutoPorDescricao(String descricao) {
        ProdutoModel produtoModel = produtoRepository.findByDescricao(descricao);
        if (Objects.isNull(produtoModel)) {
            throw new ProdutoNaoEncontradoExceptionNomeEDescricao(descricao);
        }
        ProdutoResponse produtoResponse = modelMapper.map(produtoModel, ProdutoResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(produtoResponse);
    }

    public ResponseEntity<List<ProdutoResponse>> buscarProdutosPorNome(String nome) {
        List<ProdutoModel> produtoModel = produtoRepository.findByNome(nome);
        if (ObjectUtils.isEmpty(produtoModel)) {
            throw new ProdutoNaoEncontradoExceptionNomeEDescricao(nome);
        }
        List<ProdutoResponse> produtosList = produtoModel.stream().map(produto ->
                modelMapper.map(produto, ProdutoResponse.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(produtosList);
    }
}













