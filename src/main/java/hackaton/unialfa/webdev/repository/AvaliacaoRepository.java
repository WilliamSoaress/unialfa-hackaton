package hackaton.unialfa.webdev.repository;

import hackaton.unialfa.webdev.model.AlunoModel;
import hackaton.unialfa.webdev.model.AvaliacaoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoModel, Long> {
    List<AvaliacaoModel> findByAluno(AlunoModel aluno);

    @Query(value = "SELECT a FROM AvaliacaoModel a INNER JOIN a.aluno al WHERE LOWER(al.nome) LIKE LOWER(CONCAT('%', :nome, '%')) ORDER BY a.data DESC")
    Page<AvaliacaoModel> findByStudentName(@Param("nome") String name, Pageable pageable);

    List<AvaliacaoModel> findByAlunoIdOrderByDataAsc(Long idAluno);

    AvaliacaoModel findTopByAlunoIdAndDataLessThanOrderByDataDesc(Long id, LocalDate data);

    AvaliacaoModel findTopByAlunoIdAndDataBeforeOrderByDataDesc(Long id, LocalDate data);

    List<AvaliacaoModel> findByAlunoId(Long alunoId);
}
