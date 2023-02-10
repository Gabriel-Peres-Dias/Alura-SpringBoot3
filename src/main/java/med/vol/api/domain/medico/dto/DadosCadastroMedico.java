package med.vol.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.endereco.DadosEndereco;
import med.vol.api.domain.medico.enuns.Especialidade;

public record DadosCadastroMedico(

        @NotBlank(message = "O campo Nome é obrigatório!")
        String nome,
        @NotBlank(message = "O campo E-mail é obrigatório!")
        @Email(message = "Formato inválido!")
        String email,
        @NotBlank(message = "O campo Telefone é obrigatório!")
        String telefone,
        @NotBlank(message = "O campo CRM é obrigatório!")
        @Pattern(regexp = "\\d{4,6}", message = "Formato inválido!")
        String crm,
        @NotNull(message = "O campo Especialidade é obrigatório!")
        Especialidade especialidade,
        @NotNull(message = "O campo Endereço é obrigatório!")
        @Valid
        DadosEndereco endereco) {
}
