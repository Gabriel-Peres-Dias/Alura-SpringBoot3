package med.vol.api.domain.consulta.validacoes;

import med.vol.api.domain.consulta.dto.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
