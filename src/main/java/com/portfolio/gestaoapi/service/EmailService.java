package com.portfolio.gestaoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmailBoasVindas(String destinatario, String nome) {
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setTo(destinatario);
        mensagem.setSubject("Bem-vindo ao Sistema!");
        mensagem.setText("Olá " + nome + ",\n\n" +
                "Seu cadastro foi realizado com sucesso no nosso sistema.\n" +
                "Qualquer dúvida, entre em contato com o suporte.\n\n" +
                "Atenciosamente,\n" +
                "Equipe Dev Portfolio");

        mailSender.send(mensagem);
        System.out.println("📧 E-mail enviado com sucesso para: " + destinatario);
    }
}