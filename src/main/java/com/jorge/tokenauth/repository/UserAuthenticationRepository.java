package com.jorge.tokenauth.repository;

import com.jorge.tokenauth.model.entity.UserAuthentication;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthenticationRepository extends CrudRepository<UserAuthentication, Long> {
}
