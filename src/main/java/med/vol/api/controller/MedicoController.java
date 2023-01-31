package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.medico.dto.DadosCadastroMedico;
import med.vol.api.medico.dto.DadosListagemMedico;
import med.vol.api.medico.model.Medico;
import med.vol.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar() {
        //parser de entidade para dadosListagemMedico. Sendo necessário criar um construtor no dto que receba a entidade
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
