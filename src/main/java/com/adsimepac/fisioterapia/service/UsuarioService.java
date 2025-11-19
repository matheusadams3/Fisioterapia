package com.adsimepac.fisioterapia.service;

import com.adsimepac.fisioterapia.model.Usuario;
import com.adsimepac.fisioterapia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CADASTRAR USUÁRIO
    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Este email já está cadastrado!");
        }
        return usuarioRepository.save(usuario);
    }

    // LISTAR TODOS
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // BUSCAR POR ID
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    // EDITAR
    public Usuario atualizarUsuario(Long id, Usuario novoUsuario) {
        Usuario usuario = buscarPorId(id);

        usuario.setNome(novoUsuario.getNome());
        usuario.setEmail(novoUsuario.getEmail());
        usuario.setSenha(novoUsuario.getSenha());
        usuario.setCargo(novoUsuario.getCargo());

        return usuarioRepository.save(usuario);
    }

    // DELETAR
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // LOGIN
    public Usuario login(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new RuntimeException("Email não encontrado");
        }

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Senha incorreta");
        }

        return usuario; // login bem-sucedido
    }
}