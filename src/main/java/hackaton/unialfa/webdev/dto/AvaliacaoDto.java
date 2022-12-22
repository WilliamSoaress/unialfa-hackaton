package hackaton.unialfa.webdev.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class AvaliacaoDto implements Serializable {
    private Long id;
    private LocalDate data;
    private AlunoDto aluno;
    private ProfessorDto professor;
    private Integer idadeAluno;
    private Double alturaAluno;

    private Double pesoAluno;
    private Double imc;

    private Double evolucaoImc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public AlunoDto getAluno() {
        return aluno;
    }

    public void setAluno(AlunoDto aluno) {
        this.aluno = aluno;
    }

    public ProfessorDto getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorDto professor) {
        this.professor = professor;
    }

    public Integer getIdadeAluno() {
        return idadeAluno;
    }

    public void setIdadeAluno(Integer idadeAluno) {
        this.idadeAluno = idadeAluno;
    }

    public Double getAlturaAluno() {
        return alturaAluno;
    }

    public void setAlturaAluno(Double alturaAluno) {
        this.alturaAluno = alturaAluno;
    }

    public Double getPesoAluno() {
        return pesoAluno;
    }

    public void setPesoAluno(Double pesoAluno) {
        this.pesoAluno = pesoAluno;
    }

    public Double getImc() {
        return imc;
    }

    public void setImc(Double imc) {
        this.imc = imc;
    }

    public Double getEvolucaoImc() {
        return evolucaoImc;
    }

    public void setEvolucaoImc(Double evolucaoImc) {
        this.evolucaoImc = evolucaoImc;
    }
}