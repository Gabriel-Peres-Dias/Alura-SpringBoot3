package med.vol.api.medico.dto;

import med.vol.api.medico.enuns.Especialidade;
import med.vol.api.medico.model.Medico;

public record DadosListagemMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(),medico.getEmail(),medico.getCrm(),medico.getEspecialidade());
    }
}
