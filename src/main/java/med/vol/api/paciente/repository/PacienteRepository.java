package med.vol.api.paciente.repository;

import med.vol.api.paciente.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
