package hackaton.unialfa.webdev.dto;


import hackaton.unialfa.webdev.model.ProfessorModel;

import java.io.Serializable;
import java.util.Objects;


public class ProfessorDto implements Serializable {
    private Long id;
    private String nome;
    private Integer matricula;
    private String email;

    public ProfessorDto() {
    }

    public ProfessorDto(Long id, String nome, Integer matricula, String email) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorDto entity = (ProfessorDto) o;
        return Objects.equals(this.id, entity.id) && Objects.equals(this.nome, entity.nome) && Objects.equals(this.matricula, entity.matricula) && Objects.equals(this.email, entity.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, matricula, email);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "id = " + id + ", " + "nome = " + nome + ", " + "matricula = " + matricula + ", " + "email = " + email + ")";
    }

    public static ProfessorDto fromEntity(ProfessorModel entity) {
        ProfessorDto dto = new ProfessorDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setMatricula(entity.getMatricula());

        return dto;
    }
}