package org.senai.lab365.magazinesenaispringdata.exeptions.exeptionsId;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoExceptionHandlerId {
    @ExceptionHandler(ProdutoNaoEncontradoExceptionId.class)
    public ResponseEntity<ErrorResponseId> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoExceptionId encontradoException) {
        ErrorResponseId errorResponse = new ErrorResponseId();
        errorResponse.setMessage(encontradoException.getMessage());
        errorResponse.setImageUrl("https://v1.pinimg.com/videos/mc/720p/19/23/d4/1923d4eeeec7f2c7028956dc4a2c11ad.mp4");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
