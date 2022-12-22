package hackaton.unialfa.webdev.repository;

import hackaton.unialfa.webdev.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

    Optional<AlunoModel> findById(Long id);

    List<AlunoModel> findByNomeContainingIgnoreCase(String nomeAluno);
}
