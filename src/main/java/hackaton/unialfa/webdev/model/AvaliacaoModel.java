package hackaton.unialfa.webdev.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_avaliacao")
public class AvaliacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoModel aluno;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorModel professor;

    private Integer idadeAluno;
    private Double alturaAluno;

    private Double pesoAluno;
    private Double imc;

    private Double evolucaoImc;

    public AvaliacaoModel() {
    }

    public AvaliacaoModel(Long id, LocalDate data, AlunoModel aluno, ProfessorModel professor, Integer idade, Double altura, Double peso, Double imc, Double evolucaoImc) {
        this.id = id;
        this.data = data;
        this.aluno = aluno;
        this.professor = professor;
        this.idadeAluno = idade;
        this.alturaAluno = altura;
        this.pesoAluno = peso;
        this.imc = imc;
        this.evolucaoImc = evolucaoImc;
    }

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

    public AlunoModel getAluno() {
        return aluno;
    }

    public void setAluno(AlunoModel aluno) {
        this.aluno = aluno;
    }

    public ProfessorModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorModel professor) {
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

