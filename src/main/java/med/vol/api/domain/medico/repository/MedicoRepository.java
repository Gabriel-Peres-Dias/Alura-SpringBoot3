package med.vol.api.domain.medico.repository;

import med.vol.api.domain.medico.enuns.Especialidade;
import med.vol.api.domain.medico.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    // utilizamos o AND m.id NOT IN (subSelect) para checarmos se a data já estava agendada. o order by rand() é para randomizarmos
    // o valor da query, assim como o limit 1 para limitarmos a apenas um valor
    @Query("""
            SELECT m FROM Medico m 
            WHERE m.ativo = true
            AND
            m.especialidade = :especialidade
            AND m.id not in(
            SELECT c.medico.id FROM Consulta c WHERE c.data = :data
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
