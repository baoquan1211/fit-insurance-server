package com.fit.health_insurance.security.repository;

import com.fit.health_insurance.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = "SELECT TOKEN FROM REFRESH_TOKENS AS T INNER JOIN USERS AS U" +
            " ON T.USER_ID = U.ID WHERE U.ID = :id AND T.IS_REVOKED = false", nativeQuery = true)
    List<String> findAllValidTokenByUser(@Param("id") Integer id);

    @Query(value = "SELECT * FROM REFRESH_TOKENS WHERE TOKEN = :token", nativeQuery = true)
    Optional<Token> findByToken(String token);
}
