package com.nt.repository;

import com.nt.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepo extends JpaRepository<UserInfo,Integer> {

    Optional<UserInfo> findByUsername(String username);
}
