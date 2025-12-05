package com.portfolio.gestaoapi.service;

import com.portfolio.gestaoapi.dto.UsuarioDTO;
import com.portfolio.gestaoapi.model.Usuario;
import com.portfolio.gestaoapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Injetamos o seu EmailService aqui para poder usar ele
    @Autowired
    private EmailService emailService;

    public Usuario salvar(UsuarioDTO dto) {
        // Criando o usuário com os dados recebidos
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setCargo(dto.getCargo());
        // (Adicione senha ou outros campos aqui se tiver)

        // 2. PRIMEIRO: Salva no banco de dados
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // 3. SEGUNDO: Envia o e-mail de boas-vindas
        // O try-catch garante que, se o e-mail falhar, o usuário continua salvo
        try {
            emailService.enviarEmailBoasVindas(usuarioSalvo.getEmail(), usuarioSalvo.getNome());
        } catch (Exception e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        }

        return usuarioSalvo;
    }
}