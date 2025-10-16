package com.patrickalves.cadastro.business;

import com.patrickalves.cadastro.infrastructure.entitys.Usuario;
import com.patrickalves.cadastro.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final  UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {
            repository.saveAndFlush(usuario);

    }

    public Usuario buscarUsuarioPorEmail(String email) {

        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email nao encontrado")
        );
    }

    public void deletarUsuario(String email) {
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        Usuario usuarioAtualizado = usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .id(usuario.getId())
                .build();
        repository.save(usuarioAtualizado);
    }
}
