package med.vol.api.domain.consulta.validacoes.agendamento;

import med.vol.api.infra.exception.ValidacaoException;
import med.vol.api.domain.consulta.dto.DadosAgendamentoConsulta;
import med.vol.api.domain.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteAtivo = repository.findByIdAtivo(dados.idPaciente());
        if (!pacienteAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
