package org.senai.lab365.magazinesenaispringdata.exeptions.exeptionsNomeEDescricao;

public class ProdutoNaoEncontradoExceptionNomeEDescricao extends RuntimeException{
    public ProdutoNaoEncontradoExceptionNomeEDescricao (String stringException){
        super("Não encontrando: "+ stringException);
    }
}
