package com.portfolio.gestaoapi.controller;

import com.portfolio.gestaoapi.dto.UsuarioDTO;
import com.portfolio.gestaoapi.model.Usuario;
import com.portfolio.gestaoapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Método para criar um novo usuário
    // URL: POST http://localhost:8080/usuarios
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        // Chama o serviço que salva no banco E envia o e-mail
        Usuario novoUsuario = usuarioService.salvar(usuarioDTO);

        // Retorna o código 200 (OK) e os dados do usuário criado
        return ResponseEntity.ok(novoUsuario);
    }
    // --- ADICIONE ESSE BLOCO PARA TESTAR NO NAVEGADOR ---
    @GetMapping("/testar-agora")
    public String testarPeloNavegador() {
        // Criando um usuário falso só para testar
        UsuarioDTO teste = new UsuarioDTO();
        teste.setNome("Teste Rápido");
        teste.setEmail("erick.souza.araujo.48@gmail.com"); // <--- MUDAR AQUI PARA O SEU E-MAIL
        teste.setCargo("Visitante");

        // Chama o serviço (que salva e manda e-mail)
        usuarioService.salvar(teste);

        return "Teste finalizado! Corra na sua caixa de entrada e veja se o e-mail chegou.";
    }
}
