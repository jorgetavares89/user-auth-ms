package com.jorge.user.auth.repository;

import com.jorge.user.auth.model.entity.UserAuthentication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAuthenticationRepository extends CrudRepository<UserAuthentication, Long> {
    Optional<UserAuthentication> findByUserIdAndToken(Long userId, String token);
}
