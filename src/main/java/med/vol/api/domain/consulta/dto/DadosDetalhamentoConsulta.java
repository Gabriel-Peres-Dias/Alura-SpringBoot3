package med.vol.api.domain.consulta.dto;

import java.time.LocalDate;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDate data) {
}
