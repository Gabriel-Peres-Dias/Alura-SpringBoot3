package med.vol.api.medico;

import med.vol.api.endereco.DadosEndereco;

//Utilizando as records do java, passando somente os atributos na classe
public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
