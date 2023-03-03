package med.vol.api.domain.consulta.validacoes;

import med.vol.api.domain.ValidacaoException;
import med.vol.api.domain.consulta.dto.DadosAgendamentoConsulta;
import med.vol.api.domain.medico.repository.MedicoRepository;


public class ValidadorMedicoAtivo {

    private MedicoRepository repository;

    public void validar (DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("A consulta não pode ser agendada com médico excluído");
        }
    }
}
