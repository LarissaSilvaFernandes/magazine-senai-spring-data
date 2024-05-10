package org.senai.lab365.magazinesenaispringdata.exeptions.exeptionsId;

public class ProdutoNaoEncontradoExceptionId extends RuntimeException {
    public ProdutoNaoEncontradoExceptionId(Long id) {
        super("Não foi possível encontrar id:" + id + " fornecido!");
    }
}
