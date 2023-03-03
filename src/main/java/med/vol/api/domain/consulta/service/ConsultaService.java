package med.vol.api.domain.consulta.service;

import med.vol.api.domain.consulta.Consulta;
import med.vol.api.domain.consulta.dto.DadosAgendamentoConsulta;
import med.vol.api.domain.consulta.repository.ConsultaRepository;
import med.vol.api.domain.medico.repository.MedicoRepository;
import med.vol.api.domain.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados) {




        var medico = medicoRepository.findById(dados.idMedico()).get();
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
    }
}
