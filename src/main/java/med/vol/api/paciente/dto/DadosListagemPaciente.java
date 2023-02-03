package med.vol.api.paciente.dto;

import med.vol.api.paciente.model.Paciente;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String cpf
) {

    public DadosListagemPaciente (Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
