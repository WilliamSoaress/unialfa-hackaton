package hackaton.unialfa.webdev.controller;


import hackaton.unialfa.webdev.dto.AvaliacaoDto;
import hackaton.unialfa.webdev.model.AlunoModel;
import hackaton.unialfa.webdev.model.AvaliacaoModel;
import hackaton.unialfa.webdev.repository.AvaliacaoRepository;
import hackaton.unialfa.webdev.repository.UsuarioRepository;
import hackaton.unialfa.webdev.services.AlunoService;
import hackaton.unialfa.webdev.services.AvaliacaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    private AlunoService alunoService;

    @Autowired
    private AvaliacaoService avaliacaoService;
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/{id}")
    public ResponseEntity<AlunoModel> getAlunoById(@PathVariable Long id) {
        return alunoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    @ResponseBody
    public ResponseEntity<Object> saveAvaliacao(@RequestBody AvaliacaoModel avaliacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.salvarAvaliacao(avaliacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAvaliacao(@PathVariable(value = "id") long id,
                                                  @RequestBody AvaliacaoDto avaliacaoDto) {

        Optional<AvaliacaoModel> avaliacaoId = avaliacaoService.findById(id);
        if (!avaliacaoId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }

        var avaliacaoEntity = new AvaliacaoModel();
        BeanUtils.copyProperties(avaliacaoDto, avaliacaoEntity);
        avaliacaoEntity.setId(avaliacaoId.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoService.salvarAvaliacao(avaliacaoEntity));
    }


    @GetMapping("/aluno/{alunoId}")
    public List<AvaliacaoModel> getAvaliacoesByAlunoId(@PathVariable Long alunoId) {
        return avaliacaoRepository.findByAlunoId(alunoId);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAvaliacao(@PathVariable(value = "id") long id) {
        Optional<AvaliacaoModel> avaliacaoId = avaliacaoService.findById(id);
        if (!avaliacaoId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
        avaliacaoService.deletarAvaliacao(avaliacaoId.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted sucessfully.");
    }

    @GetMapping("/diferenca-imc/{idAluno}")
    public ResponseEntity<Double> getDiferencaImc(@PathVariable Long idAluno) {

        Double diferenca = avaliacaoService.getDiferencaImc(idAluno);

        if (diferenca == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(diferenca);
        }
    }

    @GetMapping
    public Page<AvaliacaoModel> findByStudentName(
            @RequestParam("nome") String name,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return avaliacaoService.getAvaliacoes(page, size, name);
    }


}
