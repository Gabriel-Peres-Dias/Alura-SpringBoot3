package med.vol.api.domain.consulta.validacoes.agendamento;

import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.domain.consulta.dto.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

//componente genérico
@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEntreMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEntreMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
