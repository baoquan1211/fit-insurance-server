package com.fit.health_insurance.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = "SELECT token FROM auth_tokens AS t INNER JOIN app_users AS u" +
            "ON t.user_id = u.id" +
            "WHERE u.id = :id AND (t.isExpired = false AND t.isRevoked = false)", nativeQuery = true
    )
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}
