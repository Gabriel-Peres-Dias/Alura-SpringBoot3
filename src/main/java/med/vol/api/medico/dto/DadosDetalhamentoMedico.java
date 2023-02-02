package med.vol.api.medico.dto;

import med.vol.api.endereco.Endereco;
import med.vol.api.medico.enuns.Especialidade;
import med.vol.api.medico.model.Medico;

public record DadosDetalhamentoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DadosDetalhamentoMedico (Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(),medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
