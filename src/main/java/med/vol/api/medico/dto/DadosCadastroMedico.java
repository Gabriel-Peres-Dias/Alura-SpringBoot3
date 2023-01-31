package med.vol.api.medico.dto;

import med.vol.api.endereco.DadosEndereco;
import med.vol.api.medico.enuns.Especialidade;

//Utilizando as records do java, passando somente os atributos na classe
public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
