package med.vol.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.vol.api.domain.medico.dto.DadosAtualizacaoMedico;
import med.vol.api.domain.medico.dto.DadosCadastroMedico;
import med.vol.api.domain.medico.dto.DadosDetalhamentoMedico;
import med.vol.api.domain.medico.dto.DadosListagemMedico;
import med.vol.api.domain.medico.model.Medico;
import med.vol.api.domain.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        // O status 201 - Created tem suas regrinhas, e essa é uma das coias que torna a apiRestfull
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    //PageableDefault = Ao invés de usarmos a url, definimos o default da paginação, requisições com parametro na url sobrescrevem
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        //parser de entidade para dadosListagemMedico. Sendo necessário criar um construtor no dto que receba a entidade
        //método alterado para Page, e sem necessidade do .stream e .toList(), pois a Page já faz isso
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    //como temos o @transactional, tods o trecho do código vai rodar dentro de uuma transação, o jpa percebe que teve alteração e já atualiza
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
