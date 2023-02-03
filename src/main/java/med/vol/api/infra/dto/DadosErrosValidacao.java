package med.vol.api.infra.dto;

import org.springframework.validation.FieldError;

public record DadosErrosValidacao(
        String campo,
        String mensagem
) {

    public DadosErrosValidacao(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
