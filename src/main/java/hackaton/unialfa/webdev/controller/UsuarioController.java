package hackaton.unialfa.webdev.controller;

import hackaton.unialfa.webdev.model.Perfil;
import hackaton.unialfa.webdev.model.UsuarioModel;
import hackaton.unialfa.webdev.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }


    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(repository.save(usuario));
    }

    @GetMapping("/{login}")
    public ResponseEntity<Perfil> buscarPerfilPorLogin(@PathVariable String login) {
        Optional<UsuarioModel> usuario = repository.findByLogin(login);
        UsuarioModel perfil = usuario.get();
        return ResponseEntity.ok(perfil.getPerfil());
    }

    @GetMapping("/listarPorPerfil")
    public ResponseEntity<List<UsuarioModel>> listarPorPerfil(@RequestParam Perfil perfil) {
        return ResponseEntity.ok(repository.findByPerfil(perfil));
    }


    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String password) {

        Optional<UsuarioModel> optUsuario = repository.findByLogin(login);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UsuarioModel usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }


}
