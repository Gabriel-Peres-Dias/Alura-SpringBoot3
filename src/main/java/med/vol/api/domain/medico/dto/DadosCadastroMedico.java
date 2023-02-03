package med.vol.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.domain.endereco.DadosEndereco;
import med.vol.api.domain.medico.enuns.Especialidade;

//Utilizando as records do java, passando somente os atributos na classe
public record DadosCadastroMedico(

        @NotBlank(message = "O campo Nome é obrigatório!")
        String nome,
        @NotBlank(message = "O campo E-mail é obrigatório!")
        @Email(message = "Formato inválido!")
        String email,
        @NotBlank(message = "O campo Telefone é obrigatório!")
        String telefone,
        //regex = \\d = digitos, {4,6} = de 4 a 6 digitos
        @NotBlank(message = "O campo CRM é obrigatório!")
        @Pattern(regexp = "\\d{4,6}", message = "Formato inválido!")
        String crm,
        //notBlanck para campos que forem string, e notblank chega se é null também
        @NotNull(message = "O campo Especialidade é obrigatório!")
        Especialidade especialidade,
        //valid avisa que dentro desse dto também terá outras validações, fazendo que o bean também cheque o mesmo
        @NotNull(message = "O campo Endereço é obrigatório!")
        @Valid
        DadosEndereco endereco) {
}
