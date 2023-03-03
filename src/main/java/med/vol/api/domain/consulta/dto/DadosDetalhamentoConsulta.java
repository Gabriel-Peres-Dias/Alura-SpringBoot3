package med.vol.api.domain.consulta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DadosDetalhamentoConsulta(
        Long id,
        Long idMedico,
        Long idPaciente,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDate data) {
}
