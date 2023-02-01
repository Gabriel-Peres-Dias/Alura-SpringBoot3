package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.medico.dto.DadosCadastroMedico;
import med.vol.api.medico.dto.DadosListagemMedico;
import med.vol.api.medico.model.Medico;
import med.vol.api.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    //PageableDefault = Ao invés de usarmos a url, definimos o default da paginação, requisições com parametro na url sobrescrevem
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        //parser de entidade para dadosListagemMedico. Sendo necessário criar um construtor no dto que receba a entidade
        //método alterado para Page, e sem necessidade do .stream e .toList(), pois a Page já faz isso
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
}
