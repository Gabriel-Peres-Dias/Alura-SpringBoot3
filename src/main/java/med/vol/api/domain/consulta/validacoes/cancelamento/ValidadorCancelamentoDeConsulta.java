package med.vol.api.domain.consulta.validacoes.cancelamento;

import med.vol.api.domain.consulta.dto.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar (DadosCancelamentoConsulta dados);
}
