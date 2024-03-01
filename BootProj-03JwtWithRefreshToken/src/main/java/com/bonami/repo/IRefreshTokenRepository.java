package com.bonami.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bonami.model.RefreshToken;

public interface IRefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {

	@Query("from RefreshToken where token=:token")
	public Optional<RefreshToken> findByToken(String token);
}
