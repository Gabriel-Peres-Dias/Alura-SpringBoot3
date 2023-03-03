package med.vol.api.domain.paciente.repository;

import med.vol.api.domain.paciente.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {

    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query("SELECT p.ativo FROM Paciente p WHERE p.id = :idPaciente")
    Boolean findByIdAtivo(Long idPaciente);
}
