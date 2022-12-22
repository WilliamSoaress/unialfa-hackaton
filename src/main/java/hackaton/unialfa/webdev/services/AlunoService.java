package hackaton.unialfa.webdev.services;


import hackaton.unialfa.webdev.dto.AlunoDto;
import hackaton.unialfa.webdev.model.AlunoModel;
import hackaton.unialfa.webdev.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Optional<AlunoModel> findById(Long id) {
        return alunoRepository.findById(id);
    }

    @Transactional
    public AlunoModel salvarAluno(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional
    public void deletarAluno(AlunoModel aluno) {
        alunoRepository.delete(aluno);
    }

    public List<AlunoDto> findByName(String nomeAluno) {
        return this.alunoRepository.findByNomeContainingIgnoreCase(nomeAluno)
                .stream()
                .map(AlunoDto::fromEntity)
                .collect(Collectors.toList());
    }

}

