package med.vol.api.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.vol.api.endereco.DadosEndereco;
import med.vol.api.medico.enuns.Especialidade;

//Utilizando as records do java, passando somente os atributos na classe
public record DadosCadastroMedico(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        //regex = \\d = digitos, {4,6} = de 4 a 6 digitos
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        //notBlanck para campos que forem string, e notblank chega se é null também
        @NotNull
        Especialidade especialidade,
        //valid avisa que dentro desse dto também terá outras validações, fazendo que o bean também cheque o mesmo
        @NotNull
        @Valid
        DadosEndereco endereco) {
}
