package hackaton.unialfa.webdev.services;


import hackaton.unialfa.webdev.dto.ProfessorDto;
import hackaton.unialfa.webdev.model.ProfessorModel;
import hackaton.unialfa.webdev.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public Optional<ProfessorModel> findById(Long id) {
        return professorRepository.findById(id);
    }

    @Transactional
    public ProfessorModel salvarProfessor(ProfessorModel professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public void deletarAluno(ProfessorModel professor) {
        professorRepository.delete(professor);
    }

    public List<ProfessorDto> findByName(String nomeProfessor) {
        return this.professorRepository.findByNomeContainingIgnoreCase(nomeProfessor).stream().map(ProfessorDto::fromEntity).collect(Collectors.toList());
    }
}

