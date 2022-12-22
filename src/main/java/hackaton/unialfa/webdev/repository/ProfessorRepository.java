package hackaton.unialfa.webdev.repository;

import hackaton.unialfa.webdev.model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long> {
    List<ProfessorModel> findByNomeContainingIgnoreCase(String nomeAluno);
}
