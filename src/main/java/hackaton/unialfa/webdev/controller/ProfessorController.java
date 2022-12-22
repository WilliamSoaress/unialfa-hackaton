package hackaton.unialfa.webdev.controller;


import hackaton.unialfa.webdev.dto.ProfessorDto;
import hackaton.unialfa.webdev.model.ProfessorModel;
import hackaton.unialfa.webdev.services.ProfessorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/professor")
public class ProfessorController {


    @Autowired
    private ProfessorService professorService;


    @GetMapping("/search")
    public @ResponseBody List<ProfessorDto> searchProfessor(@RequestParam("q") String nomeProfessor) {
        return professorService.findByName(nomeProfessor);

    }

    @PostMapping("/novo")
    @ResponseBody
    public ResponseEntity<Object> salvarProfessor(@RequestBody ProfessorDto professorDto) {
        var professorEntity = new ProfessorModel();
        BeanUtils.copyProperties(professorDto, professorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.salvarProfessor(professorEntity));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfessor(@PathVariable(value = "id") long id,
                                                  @RequestBody ProfessorDto professorDto) {

        Optional<ProfessorModel> professorId = professorService.findById(id);
        if (!professorId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
        }

        var professorEntity = new ProfessorModel();
        BeanUtils.copyProperties(professorDto, professorEntity);
        professorEntity.setId(professorId.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(professorService.salvarProfessor(professorEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessor(@PathVariable(value = "id") long id) {
        Optional<ProfessorModel> professorId = professorService.findById(id);
        if (!professorId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        }
        professorService.deletarAluno(professorId.get());
        return ResponseEntity.status(HttpStatus.OK).body("Movie deleted sucessfully.");
    }

}
