package hackaton.unialfa.webdev.controller;


import hackaton.unialfa.webdev.dto.AlunoDto;
import hackaton.unialfa.webdev.model.AlunoModel;
import hackaton.unialfa.webdev.services.AlunoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/alunos")
public class AlunoController {


    @Autowired
    private AlunoService alunoService;


    @GetMapping("/search")
    public @ResponseBody List<AlunoDto> searchStudents(@RequestParam("q") String nomeAluno) {
        return alunoService.findByName(nomeAluno);

    }

    @PostMapping("/novo")
    @ResponseBody
    public ResponseEntity<Object> salvarAluno(@RequestBody AlunoDto alunoDto) {
        var aluno = new AlunoModel();
        BeanUtils.copyProperties(alunoDto, aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoService.salvarAluno(aluno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAluno(@PathVariable(value = "id") long id,
                                              @RequestBody AlunoDto alunoDto) {

        Optional<AlunoModel> alunoId = alunoService.findById(id);
        if (!alunoId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }

        var alunoEntity = new AlunoModel();
        BeanUtils.copyProperties(alunoDto, alunoEntity);
        alunoEntity.setId(alunoId.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(alunoService.salvarAluno(alunoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAluno(@PathVariable(value = "id") long id) {
        Optional<AlunoModel> alunoId = alunoService.findById(id);
        if (!alunoId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
        alunoService.deletarAluno(alunoId.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted sucessfully.");
    }


}
