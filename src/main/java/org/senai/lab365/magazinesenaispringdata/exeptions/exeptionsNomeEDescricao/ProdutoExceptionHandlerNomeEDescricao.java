package org.senai.lab365.magazinesenaispringdata.exeptions.exeptionsNomeEDescricao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoExceptionHandlerNomeEDescricao {
    @ExceptionHandler (ProdutoNaoEncontradoExceptionNomeEDescricao.class)
    public ResponseEntity<ErrorResponsePraNomeEDescricao> exceptionNomeEDescricao(ProdutoNaoEncontradoExceptionNomeEDescricao stringException){
        ErrorResponsePraNomeEDescricao errorResponsePraNomeEDescricao = new ErrorResponsePraNomeEDescricao();
        errorResponsePraNomeEDescricao.setDescricao(stringException.getMessage());
        errorResponsePraNomeEDescricao.setNome("https://i.pinimg.com/564x/37/ff/41/37ff410b8963686067000c0b34e02ce6.jpg");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponsePraNomeEDescricao);
    }
}
