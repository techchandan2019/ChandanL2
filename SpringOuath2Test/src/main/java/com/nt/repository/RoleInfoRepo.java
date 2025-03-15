package com.nt.repository;

import com.nt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleInfoRepo extends JpaRepository<Role, Integer> {
}
