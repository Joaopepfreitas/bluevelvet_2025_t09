package com.musicstore.bluevelvet.domain.repository;

import com.musicstore.bluevelvet.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações CRUD (Create, Read, Update, Delete) na entidade User.
 */
@Repository // Marca a interface como um repositório gerenciado pelo Spring
public interface UserRepository extends JpaRepository<User, Long> {

    // Adiciona um método customizado para a lógica de registro: verificar se o email já existe.
    // O Spring Data JPA cria a implementação SQL automaticamente a partir do nome do método.
    boolean existsByEmail(String email);

    // Você também precisará deste método para o Login (Spring Security):
    User findByEmail(String email);
}