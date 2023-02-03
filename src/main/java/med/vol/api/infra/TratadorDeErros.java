package med.vol.api.infra;

import jakarta.persistence.EntityNotFoundException;
import med.vol.api.infra.dto.DadosErrosValidacao;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.sql.SQLIntegrityConstraintViolationException;

//anotação para os tipos de classe que lidam com erros
@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity tratarSqlIntegrityException(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntity.badRequest().build();
    }

}
