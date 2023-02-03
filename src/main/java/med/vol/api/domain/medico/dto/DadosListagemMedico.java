package med.vol.api.domain.medico.dto;

import med.vol.api.domain.medico.enuns.Especialidade;
import med.vol.api.domain.medico.model.Medico;

public record DadosListagemMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
    }
}
