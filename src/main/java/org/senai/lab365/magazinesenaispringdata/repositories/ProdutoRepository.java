package org.senai.lab365.magazinesenaispringdata.repositories;

import org.senai.lab365.magazinesenaispringdata.models.ProdutoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoModel, Long> {


    @Query(value = "SELECT produto FROM ProdutoModel produto WHERE produto.descricao = :descricao")
    public ProdutoModel findByDescricao(String descricao);

    public List<ProdutoModel> findByNome(String nome);


}
