package com.musicstore.bluevelvet.domain.service.impl;
// O pacote pode ser apenas 'com.musicstore.bluevelvet.domain.service' se você não tiver uma pasta 'impl'

import com.musicstore.bluevelvet.api.request.RegisterRequest;
import com.musicstore.bluevelvet.domain.entity.User;
import com.musicstore.bluevelvet.domain.repository.UserRepository;

import com.musicstore.bluevelvet.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // Cria um construtor com argumentos para todas as dependências 'final'
public class UserServiceImpl implements UserService {

    // Dependências injetadas:
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional // Garante que a operação seja tratada como uma única transação no banco de dados
    public void registerNewUser(RegisterRequest request) {

        // 1. Verificar se o e-mail já existe (Boa Prática)
        if (userRepository.existsByEmail(request.getEmail())) {
            // Em uma aplicação de produção, você lançaria uma exceção mais específica
            throw new IllegalArgumentException("O e-mail " + request.getEmail() + " já está em uso.");
        }

        // 2. Mapear o DTO (Request) para a Entidade (User)
        User newUser = new User();
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());

        // 3. Criptografar a senha! ESSENCIAL para segurança.
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        newUser.setPassword(encodedPassword);

        // 4. Definir a Role
        newUser.setRole(request.getRole());

        // 5. Salvar a nova entidade User no banco de dados
        userRepository.save(newUser);
    }
}