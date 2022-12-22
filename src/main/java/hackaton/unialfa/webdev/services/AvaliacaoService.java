package hackaton.unialfa.webdev.services;


import hackaton.unialfa.webdev.model.AlunoModel;
import hackaton.unialfa.webdev.model.AvaliacaoModel;
import hackaton.unialfa.webdev.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<AvaliacaoModel> findUltimasAvaliacoesByAluno(AlunoModel aluno, int numAvaliacoes) {
        // Busca todas as avaliações do aluno
        List<AvaliacaoModel> avaliacoes = avaliacaoRepository.findByAluno(aluno);

        // Ordena as avaliações pelo tempo em que foram realizadas
        avaliacoes.sort((a1, a2) -> a2.getData().compareTo(a1.getData()));

        // Retorna as últimas N avaliações
        return avaliacoes.subList(0, numAvaliacoes);
    }


    public double calculateImc(double weight, double height) {
        return weight / (height * height);
    }

    public boolean verificarEvolucaoIMC(AlunoModel aluno, AvaliacaoModel avaliacaoAtual) {
        // Busca a última avaliação do aluno
        List<AvaliacaoModel> avaliacoes = findUltimasAvaliacoesByAluno(aluno, 1);
        if (avaliacoes.isEmpty()) {
            return false; // Não há avaliações anteriores para comparar
        }

        // Compara o IMC atual com o último IMC registrado
        Double imcAnterior = avaliacoes.get(0).getImc();
        Double imcAtual = avaliacaoAtual.getImc();

        return imcAtual > imcAnterior;
    }

    @Transactional
    public AvaliacaoModel salvarAvaliacao(AvaliacaoModel avaliacao) {
        avaliacao.setData(LocalDate.now());
        this.calcularEvolucaoImc(avaliacao);
        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public void deletarAvaliacao(AvaliacaoModel avaliacao) {
        avaliacaoRepository.delete(avaliacao);
    }

    public Optional<AvaliacaoModel> findById(Long id) {
        return avaliacaoRepository.findById(id);
    }


    public Page<AvaliacaoModel> getAvaliacoes(int page, int size, String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (StringUtils.isEmpty(search) || Objects.equals(search, "null")) {
            Pageable pageableSortData = PageRequest.of(0, 20, Sort.by("data").descending());
            return avaliacaoRepository.findAll(pageableSortData);
        }
        return avaliacaoRepository.findByStudentName(search, pageable);
    }

    public List<Double> getEvolucaoImc(Long idAluno) {

        List<AvaliacaoModel> avaliacoes = avaliacaoRepository.findByAlunoIdOrderByDataAsc(idAluno);
        List<Double> imcs = new ArrayList<>();

        for (AvaliacaoModel avaliacao : avaliacoes) {
            imcs.add(avaliacao.getImc());
        }

        return imcs;
    }

    public Double getDiferencaImc(Long idAluno) {

        List<AvaliacaoModel> avaliacoes = avaliacaoRepository.findByAlunoIdOrderByDataAsc(idAluno);

        if (avaliacoes.size() < 2) {
            return null;
        }

        AvaliacaoModel avaliacaoAtual = avaliacoes.get(avaliacoes.size() - 1);
        AvaliacaoModel avaliacaoAnterior = avaliacoes.get(avaliacoes.size() - 2);

        Double diferenca = (avaliacaoAtual.getImc() - avaliacaoAnterior.getImc()) / avaliacaoAnterior.getImc() * 100;

        return diferenca;
    }

    public void calcularEvolucaoImc(AvaliacaoModel avaliacaoAtual) {

        AvaliacaoModel avaliacaoAnterior = avaliacaoRepository.findTopByAlunoIdAndDataBeforeOrderByDataDesc(avaliacaoAtual.getAluno().getId(), avaliacaoAtual.getData());

        if (avaliacaoAnterior == null) {
            avaliacaoAtual.setEvolucaoImc(0.0);
        } else {
            Double diferencaIMC = avaliacaoAtual.getImc() - avaliacaoAnterior.getImc();
            Double evolucaoImc = (diferencaIMC / avaliacaoAnterior.getImc()) * 100;

            avaliacaoAtual.setEvolucaoImc(evolucaoImc);
        }
    }


}

