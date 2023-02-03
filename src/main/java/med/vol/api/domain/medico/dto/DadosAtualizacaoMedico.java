package med.vol.api.domain.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull(message = "O ID não pode ser vazio!")
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
