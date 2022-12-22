package hackaton.unialfa.webdev.repository;

import hackaton.unialfa.webdev.model.Perfil;
import hackaton.unialfa.webdev.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    public Optional<UsuarioModel> findByLogin(String login);


    List<UsuarioModel> findByPerfil(Perfil perfil);


}
