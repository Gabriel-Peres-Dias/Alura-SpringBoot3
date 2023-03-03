package med.vol.api.domain.consulta.dto;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.consulta.enuns.MotivoCancelamento;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivoCancelamento) {
}
